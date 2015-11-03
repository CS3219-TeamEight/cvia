package parser;

import qualification.WorkExp;

import java.util.ArrayList;

public class WorkExpParser implements SectionParser {

    private DateParser dateParser;
    private Dictionary jobTitleDictionary;
    
    public WorkExpParser(DateParser dateParser, Dictionary jobTitleDictionary) {
        this.dateParser = dateParser;
        this.jobTitleDictionary = jobTitleDictionary;
    }
    
    public ArrayList<WorkExp> parseWorkSection(Section section) {
        ArrayList<String> lines = new ArrayList<>(section.getLines()); // contains lines relevant to work exp ONLY
        int lineCount = section.getLineCount();
        ArrayList<WorkExp> workExp = new ArrayList<>();
        ArrayList<Integer> pointers = new ArrayList<>();
        int offset = 0;
        
        for (int i = 0; i < lineCount; i++) {
            double duration = dateParser.identifyDates(lines.get(i)).getDuration();
            if (duration > 0) {
                if (pointers.size() == 0) {
                    offset = i;
                }
                pointers.add(i - offset);
                
                ArrayList<String> beginningPart = new ArrayList<>();
                for (int j = i - offset; j < i + 2; j++) {
                    beginningPart.add(lines.get(j));
                }
                
                WorkExp work = new WorkExp(getJobTitle(beginningPart), duration);
                workExp.add(work);
            }
        }
        
        pointers.add(lineCount - 1); // dummy pointer to signify end of section
        return workExp;
    }
    
    private String getJobTitle(ArrayList<String> lines) {
        String info = "";
        for (String line : lines) {
            info = info + line + " ";
        }
        
        return jobTitleDictionary.contains(info.toLowerCase());
    }
}
