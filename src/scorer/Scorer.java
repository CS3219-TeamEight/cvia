package scorer;

import java.util.ArrayList;
import java.util.Collections;

import job.JobDesc;
import parser.LanguageParser;
import parser.SkillsetParser;
import main.ParseResultStorage;
import qualification.Education;
import qualification.WorkExp;
import main.ParseResultStorage;

public class Scorer {
	
	SkillsetParser skillset;
	ParseResultStorage result;
	
	public Scorer(JobDesc jobDesc, int count){
	}
	
	public ArrayList<Candidate> getTopCandidates(ArrayList<ParseResultStorage> parsedData, JobDesc jobDesc, int wanted) {
	    
	    
	    ArrayList<Candidate> candidates = new ArrayList<>();
	    
	    for (ParseResultStorage data : parsedData) {
	        String id = data.getId();
	        double score = 0;
	        // calculate score here
	        Candidate candidate = new Candidate(id, score);
	        candidates.add(candidate);
	    }
	    
	    Collections.sort(candidates);
	    for (int i = wanted; i < candidates.size(); i++) {
	        candidates.remove(i);
	    }
	    
	    return candidates;
	}
	
	private double computeWorkScore(ArrayList<WorkExp> workExp, double wanted) {
	    double duration = 0;
	    for (WorkExp work : workExp) {
	        duration += work.getDuration();
	    }
	    
	    return (duration / wanted);
	}
	
	/**
	public double computeScore(){
		double score = 0;
		
		for (int i = 0; i < parsedData.size(); i++){
			if(parsedData.get(i) instanceof ParseResultStorage){
				score += computeWorkExpScore(((ParseResultStorage)parsedSections.get(i)).getTotalWorkExp(), jobDesc.getWorkDuration());
			}
				// score += computeCapScore((EduParser)parsedSections.get(i)).getTotalWorkExp());
				// score += computeEducationScore();
			
		}
		
		
		return score;
	}
	**/
	
	private double computeWorkExpScore(JobDesc jobDesc, double workExp, double expectedExp){
		return workExp / expectedExp * jobDesc.getWorkWeightage();
	}
	
	private double computeSkillsetScore(JobDesc jobDesc) {
		ArrayList<String> skills = new ArrayList<String>(skillset.getSkillsets());
		double score= 0;
		int base = jobDesc.getskillSets().size();
		int numOfSkills = skills.size();
		score = numOfSkills/base;
		return score * (double) jobDesc.getSkillsetWeightage() / 100;
	}
	
	private double computeLanguageScore(JobDesc jobDesc) {
		double score = 0;
		int numOfLanguage = 0;
		ArrayList<String> lang = new ArrayList<String>(result.getLanguages());
		
		//Loop through to find the number of languages that matches the Job Description
		for (String language : jobDesc.getLanguages()) {
			for (int i=0; i<lang.size(); i++) {
				if (language.equals(lang.get(i))) {
					numOfLanguage ++ ;
				}
			}
		}
		
		int base = jobDesc.getLanguages().size();
		score = numOfLanguage/base;
		
		return score * (double) jobDesc.getLanguageWeightage() / 100;
	}
}
