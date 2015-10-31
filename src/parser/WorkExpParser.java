package parser;

import qualification.WorkExp;

import java.util.ArrayList;
/**
import java.util.Iterator;
import java.util.Map;
**/

public class WorkExpParser implements SectionParser {

    private DateParser dateParser;
    private ArrayList<String> lines; // contains lines relevant to work exp ONLY
    private ArrayList<WorkExp> workExp;
    private ArrayList<Integer> pointers;
    private int offset;
    
    public WorkExpParser(Section section, DateParser dateParser) {
        this.dateParser = dateParser;
        lines = new ArrayList<String>(section.getLines());
        workExp = new ArrayList<WorkExp>();
        pointers = new ArrayList<Integer>();
        
        parseWorkSection();
    }
    
    private void parseWorkSection() {
        for (int i = 0; i < lines.size(); i++) {
            double duration = dateParser.identifyDates(lines.get(i));
            if (duration > 0) {
                WorkExp work = new WorkExp(duration);
                workExp.add(work);
                if (pointers.size() == 0) {
                    offset = i;
                }
                pointers.add(i - offset);
            }
        }
        pointers.add(-1); // dummy pointer to signify end of section
    }
    
    public void printWorkExperience() {
        for (WorkExp exp : workExp) {
            System.out.println("Duration: " + exp.getDuration() + " years");
        }
    }
}
