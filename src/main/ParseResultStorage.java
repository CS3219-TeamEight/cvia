package main;

import java.util.ArrayList;

import qualification.Education;
import qualification.WorkExp;

public class ParseResultStorage {
    
    String id = "";
    ArrayList<Education> education;
    ArrayList<WorkExp> workExp;
    ArrayList<String> languages;
    
    public ParseResultStorage() {
        education = new ArrayList<>();
        workExp = new ArrayList<>();
        languages = new ArrayList<>();
    }
    
    public void storeEducation(ArrayList<Education> education) {
        this.education = new ArrayList<>(education);
    }
    
    public void storeWorkExp(ArrayList<WorkExp> workExp) {
        this.workExp = new ArrayList<>(workExp);
    }
    
    public void storeLanguage(ArrayList<String> languages) {
        this.languages = new ArrayList<>(languages);
    }
    
    public String getId() {
        return id;
    }
    
    public void printEduExperience() {
        for (Education edu : education) {
            System.out.println("Degree: " + edu.getDegree() + "\nField: " + edu.getField());
            if (edu.isGraduate()) {
                System.out.println("Graduate");
            } else {
                System.out.println("Undergraduate");
            }
            System.out.println();
        }
        System.out.println("=============================================");
    }
    
    public void printWorkExperience() {
        for (WorkExp exp : workExp) {
            System.out.println("Position: " + exp.getTitle() + "\nDuration: " + exp.getDuration() + " years");
            System.out.println();
        }
        System.out.println("=============================================");
    }
    
    public void printLanguages() {
    	for (String language : languages){
    		System.out.println("Languages: " + language);
    	}
    	System.out.println("=============================================");
    }

}
