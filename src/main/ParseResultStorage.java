package main;

import java.util.ArrayList;

import qualification.Education;
import qualification.WorkExp;

public class ParseResultStorage {
    
    String id = "";
    ArrayList<Education> education;
    ArrayList<WorkExp> workExp;
    
    public ParseResultStorage() {
        education = new ArrayList<>();
        workExp = new ArrayList<>();
    }
    
    public void storeEducation(ArrayList<Education> education) {
        this.education = new ArrayList<>(education);
    }
    
    public void storeWorkExp(ArrayList<WorkExp> workExp) {
        this.workExp = new ArrayList<>(workExp);
    }
    
    public String getId() {
        return id;
    }
    
    public void printEduExperience() {
        for (Education edu : education) {
            System.out.println("Degree: " + edu.getDegree() + "\nField: " + edu.getField());
            if (edu.isGraduate()) {
                System.out.println("graduate");
            } else {
                System.out.println("undergraduate");
            }
        }
    }
    
    public void printWorkExperience() {
        for (WorkExp exp : workExp) {
            System.out.println("Duration: " + exp.getDuration() + " years\nPosition: " + exp.getTitle());
        }
    }

}
