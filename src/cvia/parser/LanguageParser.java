package cvia.parser;

import cvia.parser.entities.Dictionary;
import cvia.parser.entities.Section;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class LanguageParser implements SectionParser {

    private Dictionary languageDictionary;

    public LanguageParser(Dictionary languageDictionary) {
        this.languageDictionary = languageDictionary;
    }

    //To parse the different languages found under the language section
    public Set<String> parseLanguageSection(Section section) {
        ArrayList<String> lines =
            new ArrayList<>(section.getLines()); // contains lines relevant to work exp ONLY
        int lineCount = section.getLineCount();
        Set<String> languages = new HashSet<String>();

        for (int i = 0; i < lineCount; i++) {
            ArrayList<String> language = getLanguage(lines.get(i));
            languages.addAll(language);
        }

        return languages;
    }

    private ArrayList<String> getLanguage(String line) {
        return languageDictionary.containsMultiple(line.toLowerCase());
    }
}
