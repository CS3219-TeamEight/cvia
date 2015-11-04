package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import qualification.WorkExp;
import job.JobDesc;

public class LanguageParser {

    private Dictionary languageDictionary;
    
    public LanguageParser(Dictionary languageDictionary) {
        this.languageDictionary = languageDictionary;
    }
    
    public ArrayList<String> parseLanguageSection(Section section) {
        ArrayList<String> lines = new ArrayList<>(section.getLines()); // contains lines relevant to work exp ONLY
        int lineCount = section.getLineCount();
        ArrayList<String> languages = new ArrayList<>();
        
        for (int i = 0; i < lineCount; i++) {
            String language = getLanguage(lines.get(i));
            if (!language.equals("UNKNOWN"))
            	languages.add(language);
        }
        
        return languages;
    }
    
    private String getLanguage(String line) {        
        return languageDictionary.containsSingle(line.toLowerCase());
    }
}
