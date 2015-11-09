package cvia.scorer;

import cvia.job.JobDesc;
import cvia.resume.ParseResultStorage;
import cvia.resume.entities.Education;
import cvia.resume.entities.WorkExp;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Scorer {

    Map<String, Double> degreeMultiplier = new HashMap<String, Double>();
    ParseResultStorage result;
    JobDesc jobDesc;

    public Scorer(JobDesc jobDesc, ParseResultStorage result) {
        this.jobDesc = jobDesc;
        this.result = result;
        setDegreeMutiplier();
    }

    //Setup hashmap for education scorer
    private void setDegreeMutiplier() {
        degreeMultiplier.put("phd", 2.5);
        degreeMultiplier.put("masters", 2.0);
        degreeMultiplier.put("degree", 1.5);
        degreeMultiplier.put("Bachelor", 1.0);
        degreeMultiplier.put("UNKNOWN", 0.75);
    }

    //To compute the total score for a given resume
    public double computeScore() {
        double score = 0;
        System.out.println("Resume Scores");
        System.out.println("Work Score: " + computeWorkScore());
        score += computeWorkScore();
        System.out.println("Language Score: " + computeLanguageScore());
        score += computeLanguageScore();
        System.out.println("Skills Score: " + computeSkillsScore());
        score += computeSkillsScore();
        System.out.println("Education Score: " + computeEduScore());
        score += computeEduScore();

        return score;
    }

    //To compute the work score for a given resume
    private double computeWorkScore() {
        double duration = 0;
        double workExpScore = 0;
        double jobCount = 0;

        if (jobDesc.getWorkDuration() != 0) {
            for (WorkExp work : result.getWorkExp()) {
                if (work.getTitle().equalsIgnoreCase(jobDesc.getJobTitle())) {
                    duration += work.getDuration();
                } else {
                    duration += work.getDuration() / 2;
                }
            }
            workExpScore = duration / jobDesc.getWorkDuration() * jobDesc.getWorkWeightage();

            if (workExpScore > (jobDesc.getWorkWeightage()) * 2)
                workExpScore = jobDesc.getWorkWeightage() * 2;
            return workExpScore;
        } else {
            for (WorkExp work : result.getWorkExp()) {

                if (work.getTitle().equalsIgnoreCase(jobDesc.getJobTitle())) {
                    jobCount++;
                }
            }

            if (jobCount > 0) {
                workExpScore = jobDesc.getWorkWeightage();
            }

            return workExpScore;
        }

    }

    //To compute the skillset score for a given resume
    private double computeSkillsScore() {
        double score = 0;
        int numOfSkills = 0;
        Set<String> skills = result.getSkills();

        // Loop through to find the number of languages that matches the Job Description
        for (String s : skills) {
            if (jobDesc.getskillSets().contains(s.toLowerCase())) {
                numOfSkills++;
            }
        }

        double base = jobDesc.getskillSets().size();
        score = numOfSkills / base;
        return score * (double) jobDesc.getSkillsetWeightage();
    }

    private double computeLanguageScore() {
        double score = 0;
        int numOfLanguage = 0;
        Set<String> lang = result.getLanguages();

        // Loop through to find the number of languages that matches the Job Description
        for (String s : lang) {
            if (jobDesc.getLanguages().contains(s.toLowerCase())) {
                numOfLanguage++;
            }
        }

        double base = jobDesc.getLanguages().size();
        score = numOfLanguage / base;
        return score * (double) jobDesc.getLanguageWeightage();
    }

    //To compute the Education Score for a given resume
    private double computeEduScore() {
        double eduScore = 0;

        for (Education edu : result.getEducation()) {
            double tempEduScore = 0.0;

            if (edu.getField().equalsIgnoreCase(jobDesc.getEducationTitle())) {
                if (edu.isGraduate()) {
                    tempEduScore = 1.0;
                } else {
                    tempEduScore = 0.75;
                }
            } else {
                if (edu.isGraduate()) {
                    tempEduScore = 0.5;
                } else {
                    tempEduScore = 0.25;
                }
            }

            tempEduScore *= degreeMultiplier.get(edu.getDegree());

            if (tempEduScore > eduScore) {
                eduScore = tempEduScore;
            }
        }

        eduScore = eduScore * jobDesc.getEduWeightage();
        return eduScore;
    }
}
