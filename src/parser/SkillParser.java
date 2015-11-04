package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import job.JobDesc;

public class SkillParser {

    private Dictionary skillsDictionary;
    
    public SkillParser(Dictionary skillsDictionary) {
        this.skillsDictionary = skillsDictionary;
    }
    
    public ArrayList<String> parseSkillsSection(Section section) {
        ArrayList<String> lines = new ArrayList<>(section.getLines()); // contains lines relevant to work exp ONLY
        int lineCount = section.getLineCount();
        ArrayList<String> skills = new ArrayList<>();
        
        for (int i = 0; i < lineCount; i++) {
            String skill = getSkill(lines.get(i));
            if (!skill.equals("UNKNOWN"))
            	skills.add(skill);
        }
        
        return skills;
    }
    
    private String getSkill(String line) {        
        return skillsDictionary.containsSingle(line.toLowerCase());
    }
}