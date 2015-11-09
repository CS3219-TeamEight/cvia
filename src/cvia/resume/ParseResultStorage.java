package cvia.resume;

import cvia.resume.entities.Education;
import cvia.resume.entities.WorkExp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ParseResultStorage {

    String id = "";
    ArrayList<Education> education;
    ArrayList<WorkExp> workExp;
    Set<String> languages;
    Set<String> skills;

    public ParseResultStorage() {
        education = new ArrayList<>();
        workExp = new ArrayList<>();
        languages = new HashSet<>();
        skills = new HashSet<>();
    }

    public void storeEducation(ArrayList<Education> education) {
        this.education = new ArrayList<>(education);
    }

    public void storeWorkExp(ArrayList<WorkExp> workExp) {
        this.workExp = new ArrayList<>(workExp);
    }

    public void storeLanguage(Set<String> languages) {
        this.languages = languages;
    }

    public void storeSkills(Set<String> skills) {
        this.skills = skills;
    }

    public String getId() {
        return id;
    }

    public Set<String> getLanguages() {
        return languages;
    }

    public ArrayList<Education> getEducation() {
        return education;
    }

    public ArrayList<WorkExp> getWorkExp() {
        return workExp;
    }

    public Set<String> getSkills() {
        return skills;
    }


    /* ---------------------- */
    /* For Debugging Purposes */
    /* ---------------------- */
    //To print out parsed education results
    public void printEduExperience() {
        for (Education edu : education) {
            System.out.println("Degree: " + edu.getDegree() + "\nField: " + edu.getField());
            if (edu.isGraduated()) {
                System.out.println("Graduate");
            } else {
                System.out.println("Undergraduate");
            }
            System.out.println();
        }
        System.out.println("=============================================");
    }

    //To print out parsed Work experience results
    public void printWorkExperience() {
        for (WorkExp exp : workExp) {
            System.out.println(
                "Position: " + exp.getTitle() + "\nDuration: " + exp.getDuration() + " years");
            System.out.println();
        }
        System.out.println("=============================================");
    }

    //To print out parsed language results
    public void printLanguages() {
        for (String language : languages) {
            System.out.println("Languages: " + language);
        }
        System.out.println("=============================================");
    }

    //To print out parsed skillsets results
    public void printSkills() {
        for (String skill : skills) {
            System.out.println("Skills: " + skill);
        }
        System.out.println("=============================================");
    }

}
