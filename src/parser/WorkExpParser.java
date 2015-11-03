package parser;

import qualification.WorkExp;

import java.util.ArrayList;

public class WorkExpParser implements SectionParser {

    private DateParser dateParser;
    private ArrayList<String> lines; // contains lines relevant to work exp ONLY
    private int lineCount;
    private ArrayList<WorkExp> workExp;
    private ArrayList<Integer> pointers;
    private int offset;
    
    public WorkExpParser(DateParser dateParser) {
        this.dateParser = dateParser;
        workExp = new ArrayList<WorkExp>();
        pointers = new ArrayList<Integer>();
    }
    
    public void parseWorkSection(Section section) {
        lines = new ArrayList<String>(section.getLines());
        lineCount = section.getLineCount();
        
        for (int i = 0; i < lineCount; i++) {
            double duration = dateParser.identifyDates(lines.get(i)).getDuration();
            if (duration > 0) {
                WorkExp work = new WorkExp(duration);
                workExp.add(work);
                if (pointers.size() == 0) {
                    offset = i;
                }
                pointers.add(i - offset);
            }
        }
        pointers.add(lineCount - 1); // dummy pointer to signify end of section
    }
    
    public void printWorkExperience() {
        for (WorkExp exp : workExp) {
            System.out.println("Duration: " + exp.getDuration() + " years");
        }
    }
}
