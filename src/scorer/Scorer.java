package scorer;

import java.util.ArrayList;
import java.util.Collections;

import job.JobDesc;
import parser.LanguageParser;
import main.ParseResultStorage;
import qualification.Education;
import qualification.WorkExp;
import main.ParseResultStorage;

public class Scorer {
	
	ParseResultStorage result;
	JobDesc jobDesc;
	
	public Scorer(JobDesc jobDesc, ParseResultStorage result){
		this.jobDesc = jobDesc;
		this.result = result;
	}
	
	private double computeWorkScore() {
	    double duration = 0;
	    for (WorkExp work : result.getWorkExp()) {
	        duration += work.getDuration();
	    }
	    
	    return (duration / jobDesc.getWorkDuration()) * jobDesc.getWorkWeightage();
	}
	
	public double computeScore(){
		double score = 0;
		System.out.println("Resume Scores");
		System.out.println("Work Score: " + computeWorkScore());
		score += computeWorkScore();
		System.out.println("Language Score: " + computeLanguageScore());
		score += computeLanguageScore();
		
		return score;
	}
	
	private double computeWorkExpScore(JobDesc jobDesc, double workExp, double expectedExp){
		return workExp / expectedExp * jobDesc.getWorkWeightage();
	}
	
	private double computeSkillsetScore(JobDesc jobDesc) {
		double score= 0;
		return score * (double) jobDesc.getSkillsetWeightage() / 100;
	}
	
	private double computeLanguageScore() {
		double score = 0;
		int numOfLanguage = 0;
		ArrayList<String> lang = result.getLanguages();

		// Loop through to find the number of languages that matches the Job Description
		for (int i = 0; i < lang.size(); i++) {
			if (jobDesc.getLanguages().contains(lang.get(i))){
				numOfLanguage++;
			}
		}
		
		int base = jobDesc.getLanguages().size();
		score = numOfLanguage/base;
		
		return score * (double) jobDesc.getLanguageWeightage() / 100;
	}
}
