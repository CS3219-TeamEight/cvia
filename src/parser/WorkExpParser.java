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
    
    /**
     * To parse in each work experience found under the Work experience section
     * Parse in the following: Job Title
     *                         Duration of the job
     */
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
                    System.out.println("Offset = " + offset);
                }
                pointers.add(i - offset);
                
                ArrayList<String> beginningPart = new ArrayList<>();
                if (offset == 0) {
                    beginningPart.add(lines.get(i));
                    beginningPart.add(lines.get(i + 1));
                } else {
                    for (int j = i - offset; j < i; j++) {
                        System.out.println(lines.get(j));
                        beginningPart.add(lines.get(j));
                    }
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
