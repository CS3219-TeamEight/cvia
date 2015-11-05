package scorer;

import java.util.ArrayList;
import java.util.Set;

import job.JobDesc;
import main.ParseResultStorage;
import qualification.WorkExp;
import qualification.Education;

public class Scorer {

	ParseResultStorage result;
	JobDesc jobDesc;

	public Scorer(JobDesc jobDesc, ParseResultStorage result){
		this.jobDesc = jobDesc;
		this.result = result;
	}

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
	    	workExpScore = duration / jobDesc.getWorkDuration() * jobDesc.getWorkWeightage() / 10;
	    	
	    	if (workExpScore > (jobDesc.getWorkWeightage()/10) * 2) 
	    		workExpScore = jobDesc.getWorkWeightage() / 10 * 2;
	    	return workExpScore;
	    } else {
	    	for (WorkExp work : result.getWorkExp()) {
	    		if (work.getTitle().equalsIgnoreCase(jobDesc.getJobTitle())) {
	    			jobCount ++;
	    		} 
	    	}
	    	
	    	if(jobCount > 0) {
	    		workExpScore = jobDesc.getWorkWeightage() / 10;
	    	}
	    	
	    	return workExpScore;
	    }
	    

	}

	public double computeScore(){
		double score = 0;
		System.out.println("Resume Scores");
		System.out.println("Work Score: " + computeWorkScore());
		score += computeWorkScore();
		System.out.println("Language Score: " + computeLanguageScore());
		score += computeLanguageScore();
		System.out.println("Skills Score: " + computeSkillsScore());
		score += computeSkillsScore();


		return score;
	}

	private double computeSkillsScore() {
		double score= 0;
		int numOfSkills = 0;
		Set<String> skills = result.getSkills();

		// Loop through to find the number of languages that matches the Job Description
		for (String s : skills) {
			if (jobDesc.getskillSets().contains(s.toLowerCase())){
				numOfSkills ++;
			}
		}

		double base = jobDesc.getskillSets().size();
		score = numOfSkills/base;

		return score * (double) jobDesc.getSkillsetWeightage() / 10;
	}

	private double computeLanguageScore() {
		double score = 0;
		int numOfLanguage = 0;
		Set<String> lang = result.getLanguages();

		// Loop through to find the number of languages that matches the Job Description
		for (String s : lang) {
			if (jobDesc.getLanguages().contains(s.toLowerCase())){
				numOfLanguage++;
			}
		}

		double base = jobDesc.getLanguages().size();
		score = numOfLanguage/base;

		return score * (double) jobDesc.getLanguageWeightage() / 10;
	}
	
	private double computeEducationScore() {
		double eduScore = 0;
		double tempEduScore;
			
		for(Education edu : result.getEducation()) {
			tempEduScore = 0;
			
			if(edu.getField().equalsIgnoreCase(jobDesc.getEducationTitle())) {
				if (edu.isGraduate()) {
					tempEduScore = 1;
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
		
			if (tempEduScore > eduScore) {
				eduScore = tempEduScore;
			}
		}
		
		eduScore = eduScore * jobDesc.getEduWeightage() / 10;
		return eduScore;
	}
}
