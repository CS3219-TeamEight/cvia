package scorer;

import java.util.ArrayList;

import job.JobDesc;
import parser.EduParser;
import parser.SectionParser;
import parser.WorkExpParser;

public class Scorer {
	ArrayList<SectionParser> parsedSections;
	JobDesc jobDesc;
	
	public Scorer(ArrayList<SectionParser> parsedSections, JobDesc jobDesc){
		this.parsedSections = parsedSections;
		this.jobDesc = jobDesc;
	}
	
	public double computeScore(){
		double score = 0;
		
		for (int i = 0; i < parsedSections.size(); i++){
			if(parsedSections.get(i) instanceof WorkExpParser){
				score += computeWorkExpScore(((WorkExpParser)parsedSections.get(i)).getTotalWorkExp(), jobDesc.getWorkDuration());
			}
			else if(parsedSections.get(i) instanceof EduParser){
				score += computeCapScore((EduParser)parsedSections.get(i)).getTotalWorkExp());
				score += computeEducationScore();
			}
			
		}
		
		
		return score;
	}
	
	private double computeWorkExpScore(double workExp, double expectedExp){
		return workExp / expectedExp * jobDesc.getWorkWeightage();
	}
}
