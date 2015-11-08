package parser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SkillParser {

    private Dictionary skillsDictionary;

    public SkillParser(Dictionary skillsDictionary) {
        this.skillsDictionary = skillsDictionary;
    }

    //To parse the skillsets found under the skillset section
    public Set<String> parseSkillsSection(Section section) {
        ArrayList<String> lines =
            new ArrayList<>(section.getLines()); // contains lines relevant to work exp ONLY
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
