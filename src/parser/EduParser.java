package parser;

import qualification.Education;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EduParser implements SectionParser {

    ArrayList<String> lines;
    Education edu;
    /**
    ArrayList<String> scoreList;
    
    public EduParser(Section section) {
        lines = new ArrayList<>(section.getLines());
        for (int i = 0; i < lines.size(); i++) {
            getScore(i);
        }
    }
    
    private void getScore(int lineNum) {
        String line = lines.get(lineNum);
        String score = "";
        if (line.toLowerCase().contains("gpa") || line.toLowerCase().contains("cap")) {
            int capIndex = line.indexOf("cap");
            for (int i = capIndex-2; i>=0; i--) {
                char c = line.charAt(i);
                if (c >= '0' && c <= '9' || c =='.') {
                    score += c;
                }
            }
            scoreList.add(score);
            
        }
        

    }
    **/
    
    public EduParser(Section section) {
        lines = new ArrayList<>(section.getLines());
        getEduDetails();
    }
    
    private void getEduDetails() {
        String institute = lines.get(0).trim();
        ArrayList<String> parts = new ArrayList<String>(Arrays.asList(lines.get(1).split(", ")));
        String degree = parts.get(0);
        String dept = parts.get(1);
        
        ArrayList<String> years = new ArrayList<String>(Arrays.asList(parts.get(2).split(" - ")));
        int start = Integer.parseInt(years.get(0));
        int end = Integer.parseInt(years.get(1));
        int duration = end - start;
        
        edu = new Education(institute, degree, dept, duration);
        
    }
    
    public void printEduDetails() {
        System.out.println("Institute: " + edu.getInstitute()
                + "\nDepartment: " + edu.getDept()
                + "\nDegree: " + edu.getDegree()
                + "\nDuration: " + edu.getDuration() + " years\n");
    }
    
}
