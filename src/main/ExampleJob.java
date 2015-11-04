package main;

import job.JobDesc;

public class ExampleJob {
	JobDesc exampleDesc = new JobDesc();
	
	public ExampleJob(){
		exampleDesc.setEduWeightage("2");
		exampleDesc.setWorkWeightage("3");
		exampleDesc.setLanguageWeightage("1");
		exampleDesc.setSkillsetWeightage("4");
		
		exampleDesc.workExp("2 years");
		exampleDesc.setLanguages("english, chinese");
		exampleDesc.setSkillSets("java, python, ruby, ajax");
		exampleDesc.setJobTitle("Software Engineer");
		exampleDesc.setEducationLevel("2");
		exampleDesc.setEducationTitle("Computer Science");
	}
	
	public JobDesc getExample(){
		return exampleDesc;
	}
}
