package scorer;

import java.util.ArrayList;
import java.util.Collections;

import job.JobDesc;
import main.ParseResultStorage;
import qualification.Education;
import qualification.WorkExp;

public class Scorer {
	
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
}
