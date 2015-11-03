package scorer;

import java.util.ArrayList;
import java.util.Collections;

import job.JobDesc;
import main.ParseResultStorage;

public class Scorer {
	ArrayList<ParseResultStorage> parsedData;
	ArrayList<Candidate> topCandidates;
	JobDesc jobDesc;
	int wanted;
	
	public Scorer(JobDesc jobDesc, int count){
		this.jobDesc = jobDesc;
		wanted = count;
		topCandidates = new ArrayList<>();
	}
	
	public void feedParsedDate(ArrayList<ParseResultStorage> parsedData) {
	    this.parsedData = parsedData;
	}
	
	public void prepareTopCandidates() {
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
	    topCandidates = candidates;
	}
	
	public ArrayList<Candidate> getTopCandidates() {
	    return topCandidates;
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
	
	private double computeWorkExpScore(double workExp, double expectedExp){
		return workExp / expectedExp * jobDesc.getWorkWeightage();
	}
}
