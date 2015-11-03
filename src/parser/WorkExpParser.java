package parser;

import qualification.WorkExp;

import java.util.ArrayList;

public class WorkExpParser implements SectionParser {

    private DateParser dateParser;
    private Dictionary jobTitleDictionary;
    
    private ArrayList<String> lines; // contains lines relevant to work exp ONLY
    private int lineCount;
    private ArrayList<WorkExp> workExp;
    private ArrayList<Integer> pointers;
    private int offset;
    private double totalWorkExp = 0;
    
    public WorkExpParser(DateParser dateParser, Dictionary jobTitleDictionary) {
        this.dateParser = dateParser;
        this.jobTitleDictionary = jobTitleDictionary;
        workExp = new ArrayList<>();
        pointers = new ArrayList<>();
    }
    
    public void parseWorkSection(Section section) {
        lines = new ArrayList<>(section.getLines());
        lineCount = section.getLineCount();
        
        for (int i = 0; i < lineCount; i++) {
            double duration = dateParser.identifyDates(lines.get(i)).getDuration();
            if (duration > 0) {
                WorkExp work = new WorkExp(duration);
                totalWorkExp += work.getDuration();
                workExp.add(work);
                if (pointers.size() == 0) {
                    offset = i;
                }
                pointers.add(i - offset);
            }
        }
        pointers.add(lineCount - 1); // dummy pointer to signify end of section
    }

	public double getTotalWorkDuration() {
		return totalWorkExp;
	}
	
	public ArrayList<WorkExp> getAllExp() {
	    return workExp;
	}
}
