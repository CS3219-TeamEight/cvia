package parser;

import java.util.ArrayList;

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
