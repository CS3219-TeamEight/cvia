package main;

import job.JobDesc;

public class ExampleJob {
	JobDesc exampleDesc;
	
	public ExampleJob(ParserFactory factory){
		exampleDesc = new JobDesc(factory);
		exampleDesc.setEduWeightage("2");
		exampleDesc.setWorkWeightage("3");
		exampleDesc.setLanguageWeightage("2");
		exampleDesc.setSkillsetWeightage("5");
		
		exampleDesc.workExp("2 years");
		exampleDesc.setLanguages("enGlish, chinese");
		exampleDesc.setSkillSets("java, python, ruby, ajax");
		exampleDesc.setJobTitle("Software Engineer");
		exampleDesc.setEducationLevel("2");
		exampleDesc.setEducationTitle("Computer Science");
	}
	
	public JobDesc getExample(){
		return exampleDesc;
	}
}
