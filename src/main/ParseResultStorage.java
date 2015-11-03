package main;

import java.util.ArrayList;

import qualification.Education;
import qualification.WorkExp;

public class ParseResultStorage {
    
    String id = "";
    ArrayList<Education> education;
    ArrayList<WorkExp> workExp;
    
    public ParseResultStorage() {
        education = new ArrayList<Education>();
        workExp = new ArrayList<WorkExp>();
    }
    
    public void storeEducation(ArrayList<Education> education) {
        this.education = new ArrayList<Education>(education);
    }
    
    public void storeWorkExp(ArrayList<WorkExp> workExp) {
        this.workExp = new ArrayList<WorkExp>(workExp);
    }
    
    public String getId() {
        return id;
    }
    
    public void printEduExperience() {
        for (Education edu : education) {
            System.out.println("CAP: " + edu.getCap() + "\nDuration: " + edu.getDuration() + "\nDegree: " + edu.getDegree());
            if (edu.isGraduate()) {
                System.out.println("graduate");
            } else {
                System.out.println("undergraduate");
            }
        }
    }
    
    public void printWorkExperience() {
        for (WorkExp exp : workExp) {
            System.out.println("Duration: " + exp.getDuration() + " years");
        }
    }

}
