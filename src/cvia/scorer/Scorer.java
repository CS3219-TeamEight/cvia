package cvia.scorer;

import cvia.job.JobDesc;
import cvia.resume.ParseResultStorage;
import cvia.resume.entities.Education;
import cvia.resume.entities.WorkExp;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Scorer {

    Map<String, Double> educationMultiplier = new HashMap<String, Double>();
    ParseResultStorage result;
    JobDesc jobDesc;

    public Scorer(JobDesc jobDesc, ParseResultStorage result) {
        this.jobDesc = jobDesc;
        this.result = result;
        setEducationMulitiplier();
    }

    //Setup hashmap for education scorer
    private void setEducationMulitiplier() {
        educationMultiplier.put("ph.d", 2.5);
        educationMultiplier.put("master", 2.0);
        educationMultiplier.put("honors", 1.5);
        educationMultiplier.put("bachelor", 1.0);
        educationMultiplier.put("diploma", 0.75);
        educationMultiplier.put("UNKNOWN", 0.50);
        educationMultiplier.put("none", 0.50);
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
            double tempEduScore;

            if (edu.getField().equalsIgnoreCase(jobDesc.getEducationTitle())) {
                tempEduScore = edu.isGraduated() ? 1.0 : 0.75;
            } else {
                tempEduScore = edu.isGraduated() ? 0.5 : 0.25;
            }

            double reqMulti = educationMultiplier.get(jobDesc.getEducationLevel());
            double parsedMulti = educationMultiplier.get(edu.getDegree());
            tempEduScore *= (parsedMulti < reqMulti) ? 0.5 * parsedMulti : parsedMulti;
            if (tempEduScore > eduScore) {
                eduScore = tempEduScore;
            }
        }

        eduScore = eduScore * jobDesc.getEduWeightage();
        return eduScore;
    }
}
