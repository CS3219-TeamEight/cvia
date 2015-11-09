package cvia.parser;

import cvia.parser.entities.Dictionary;
import cvia.parser.entities.Duration;
import cvia.parser.entities.Section;
import cvia.resume.entities.Education;

import java.util.ArrayList;

public class EduParser implements SectionParser {

    DateParser dateParser;
    Dictionary fosDictionary;
    Dictionary lvlDictionary;

    public EduParser(DateParser dateParser, Dictionary fosDictionary, Dictionary lvlDictionary) {
        this.dateParser = dateParser;
        this.fosDictionary = fosDictionary;
        this.lvlDictionary = lvlDictionary;
    }

    /**
     * To parse in each education found under the education section
     * Parse in the following: Level of Education
     * Grad or non-grad for that level
     * Field of Studies
     * Duration of studies
     */
    public ArrayList<Education> parseEducation(Section section) {
        ArrayList<String> lines = new ArrayList<>(section.getLines());
        int lineCount = section.getLineCount();
        ArrayList<Integer> pointers = new ArrayList<>();
        ArrayList<Duration> durations = new ArrayList<>();
        ArrayList<Education> education = new ArrayList<>();
        int offset = 0;

        for (int i = 0; i < lineCount; i++) {
            // later remove duration from consideration
            Duration duration = dateParser.identifyDates(lines.get(i));
            double time = duration.getDuration();
            if (time > 0) {
                durations.add(duration);
                if (pointers.size() == 0) {
                    offset = i;
                }
                pointers.add(i - offset);
            }
        }
        pointers.add(lineCount - 1); // dummy pointer to signify end of section

        for (int i = 0; i < pointers.size() - 1; i++) {
            education.add(
                storeEduExperience(lines, durations, i, pointers.get(i), pointers.get(i + 1),
                    offset));
        }

        return education;
    }

    private String getDegree(String line) {
        return lvlDictionary.contains(line.toLowerCase());
    }

    private String getField(ArrayList<String> lines) {
        String info = "";
        for (String line : lines) {
            info = info + line + " ";
        }
        System.out.println(info);

        return fosDictionary.contains(info.toLowerCase());
    }

    /**
     * To store the education identified
     */
    private Education storeEduExperience(ArrayList<String> lines, ArrayList<Duration> durations,
        int index, int start, int end, int offset) {
        Duration duration = durations.get(index);
        String degree = "UNKNOWN";
        for (int i = start; i < end; i++) {
            String line = lines.get(i);
            String result = getDegree(line);
            if (!result.equals("UNKNOWN")) {
                // found degree
                degree = result;
                break;
            }
        }

        ArrayList<String> beginningPart = new ArrayList<>();
        if (offset == 0) {
            beginningPart.add(lines.get(start));
            beginningPart.add(lines.get(start + 1));
        } else {
            for (int i = start; i <= start + offset + 1; i++) {
                beginningPart.add(lines.get(i));
            }
        }

        Education edu = new Education(duration.getDuration(), !duration.isOngoing(), degree,
            getField(beginningPart));
        // although there are different types of institutes (university, high school, etc)
        // catered to university only, due to difficulty in differentiating them
        return edu;
    }
}
