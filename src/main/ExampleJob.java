package main;

import job.JobDesc;

public class ExampleJob {
	JobDesc exampleDesc;
	
	public ExampleJob(){
		exampleDesc.setEduWeightage("2");
		exampleDesc.setWorkWeightage("3");
		exampleDesc.setLanguageWeightage("2");
		exampleDesc.setSkillsetWeightage("5");
		
		exampleDesc.workExp("2 years");
		exampleDesc.setLanguages("english, chinese");
		exampleDesc.setSkillSets("java, python, ruby, ajax");
		exampleDesc.setJobTitle("Software Engineer");
		exampleDesc.setEducationLevel("2");
		exampleDesc.setEducationTitle("Computer Science");
	}
}
