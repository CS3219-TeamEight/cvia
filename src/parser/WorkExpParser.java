package parser;

import qualification.WorkExp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.util.List;
import java.util.Calendar;
/**
import java.util.Iterator;
import java.util.Map;
**/

public class WorkExpParser implements SectionParser {

    private ArrayList<String> lines; // contains lines relevant to work exp ONLY
    private ArrayList<WorkExp> workExp;
    private ArrayList<Integer> pointers;
    private ArrayList<Double> durations;
    private int offset;
    private DateParser dateParser;
    
    public WorkExpParser(Section section, DateParser dateParser) {
        this.dateParser = dateParser;
        lines = new ArrayList<String>(section.getLines());
        workExp = new ArrayList<WorkExp>();
        pointers = new ArrayList<Integer>();
        durations = new ArrayList<Double>();
        
        parseWorkSection();
    }
    
    private void parseWorkSection() {
        for (int i = 0; i < lines.size(); i++) {
            double duration = dateParser.identifyDates(lines.get(i));
            if (duration > 0) {
                durations.add(duration);
                
                if (pointers.size() == 0) {
                    offset = i;
                }
                pointers.add(i - offset);
            }
        }
        pointers.add(-1); // dummy pointer to signify end of section
        
    }
    
    public void storeWorkExp() {
        for (int i = 0; i < pointers.size()-1; i++) {
            double duration = durations.get(i);
            System.out.println(duration + " years");
            ArrayList<String> expLines = new ArrayList<String>();
            for (int j = pointers.get(i); j < pointers.get(i+1); j++) {
                expLines.add(lines.get(j));
            }
        }
    }
    
}
