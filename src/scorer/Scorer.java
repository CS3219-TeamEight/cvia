package scorer;

import java.util.ArrayList;

import job.JobDesc;
import main.ParseResultStorage;

public class Scorer {
	ArrayList<ParseResultStorage> parsedData;
	JobDesc jobDesc;
	
	public Scorer(ArrayList<ParseResultStorage> parsedData, JobDesc jobDesc){
		this.parsedData = parsedData;
		this.jobDesc = jobDesc;
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
