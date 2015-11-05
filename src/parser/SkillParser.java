package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import job.JobDesc;

public class SkillParser {

    private Dictionary skillsDictionary;
    
    public SkillParser(Dictionary skillsDictionary) {
        this.skillsDictionary = skillsDictionary;
    }
    
    public Set<String> parseSkillsSection(Section section) {
        ArrayList<String> lines = new ArrayList<>(section.getLines()); // contains lines relevant to work exp ONLY
        int lineCount = section.getLineCount();
        Set<String> skills = new HashSet<String>();
        
        for (int i = 0; i < lineCount; i++) {
            ArrayList<String> skill = getSkill(lines.get(i));
            skills.addAll(skill);
        }
        
        return skills;
    }
    
    private ArrayList<String> getSkill(String line) {        
        return skillsDictionary.containsMultiple(line.toLowerCase());
    }
}