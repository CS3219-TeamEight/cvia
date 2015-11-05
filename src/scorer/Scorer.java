package scorer;

import java.util.ArrayList;

import job.JobDesc;
import main.ParseResultStorage;
import qualification.WorkExp;

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
		int jobCount = 0;

		for (WorkExp work : result.getWorkExp()) {
			if (work.getTitle().equalsIgnoreCase(jobDesc.getJobTitle())) {
				duration += work.getDuration();
				jobCount ++;
			}
		}
		
		if (jobCount > 0)
			workExpScore = duration / jobDesc.getWorkDuration();
		else 
			workExpScore = 0;
		
		if(jobDesc.getWorkDuration() != 0) { 
			return workExpScore * jobDesc.getWorkWeightage() / 10;
		} else {
			return jobDesc.getWorkWeightage() / 10;
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

	private double computeWorkExpScore(JobDesc jobDesc, double workExp, double expectedExp){
		return workExp / expectedExp * jobDesc.getWorkWeightage() / 10;
	}

	private double computeSkillsScore() {
		double score= 0;
		int numOfSkills = 0;
		ArrayList<String> skills = result.getSkills();

		// Loop through to find the number of languages that matches the Job Description
		for (int i = 0; i < skills.size(); i++) {
			if (jobDesc.getskillSets().contains(skills.get(i).toLowerCase())){
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
		ArrayList<String> lang = result.getLanguages();

		// Loop through to find the number of languages that matches the Job Description
		for (int i = 0; i < lang.size(); i++) {
			if (jobDesc.getLanguages().contains(lang.get(i).toLowerCase())){
				numOfLanguage++;
			}
		}

		double base = jobDesc.getLanguages().size();
		score = numOfLanguage/base;

		return score * (double) jobDesc.getLanguageWeightage() / 10;
	}
}
