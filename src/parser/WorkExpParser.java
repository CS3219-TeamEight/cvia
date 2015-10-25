package parser;

import qualification.WorkExp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Date;
import java.util.List;
import java.util.Calendar;

import com.joestelmach.natty.*;
import org.apache.commons.lang.time.DateUtils;
/**
import java.util.Iterator;
import java.util.Map;
**/

public class WorkExpParser implements SectionParser {

    private ArrayList<String> lines; // contains lines relevant to work exp ONLY
    private HashMap<Integer, WorkExp> dateLines; // line number, no. of years
    private ArrayList<String> ongoing;
    private ArrayList<WorkExp> workExp;
    private ArrayList<Integer> pointers;
    private ArrayList<Double> durations;
    private int offset;
    
    public WorkExpParser(Section section) {
        lines = new ArrayList<String>(section.getLines());
        ongoing = new ArrayList<String>();
        dateLines = new HashMap<Integer, WorkExp>();
        workExp = new ArrayList<WorkExp>();
        pointers = new ArrayList<Integer>();
        durations = new ArrayList<Double>();
        
        ongoing.add("ongoing");
        ongoing.add("present");
        for (int i = 0; i < lines.size(); i++) {
            splitWorkExperience(i);
        }
        pointers.add(lines.size()); // dummy pointer to signify end of section
        
        storeWorkExp();
    }
    
    private void splitWorkExperience(int lineNum) {
        String line = lines.get(lineNum);
        Parser parser = new com.joestelmach.natty.Parser();
        List<DateGroup> groups = parser.parse(line);
        if (groups.size() > 0) {
            DateGroup dateGroup = groups.get(0);
            List<Date> dates = dateGroup.getDates();
            
            Date start = dates.get(0);
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(start);
            int startYear = cal1.get(Calendar.YEAR);
            int startMonth = cal1.get(Calendar.MONTH);
            
            Date end = new Date();
            if (dates.size() == 2) {
                end = dates.get(1);
            } else if (dates.size() == 1) {
                boolean isOngoing = false;
                for (String ongoingString : ongoing) {
                    if (line.contains(ongoingString)) {
                        isOngoing = true;
                        break;
                    }
                }
                if (isOngoing) {
                    end = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
                }
            }
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(end);
            int endYear = cal2.get(Calendar.YEAR);
            int endMonth = cal2.get(Calendar.MONTH);
            
            double duration = (endYear - startYear) + (endMonth - (startMonth-1))/12.0;
            // String desc = lines.get(lineNum-1); // currently optimized for LinkedIn
            // ArrayList<String> job = parseWorkDesc(desc);
            // WorkExp work = new WorkExp(job.get(0), job.get(1), duration);
            // dateLines.put(lineNum, work);
            // workExp.add(work);
            System.out.println(duration + " years");
            durations.add(duration);
            if (pointers.size() == 0) {
                offset = lineNum;
            }
            pointers.add(lineNum - offset);
        }
        
        // assumption: uniform format. The work experience section can be split
        // to individual jobs based on location of the date information
    }
    
    public void storeWorkExp() {
        for (int i = 0; i < pointers.size()-1; i++) {
            double duration = durations.get(i);
            ArrayList<String> expLines = new ArrayList<String>();
            for (int j = pointers.get(i); j < pointers.get(i+1); j++) {
                expLines.add(lines.get(j));
                // find keywords and store whole list in WorkExp object
            }
            
        }
    }
    
    // generalize this to all
    public ArrayList<String> parseWorkDesc(String line) {
        ArrayList<String> desc = new ArrayList<String>();
        try {
            // LinkedIn format
            ArrayList<String> parts = new ArrayList<String>(Arrays.asList(line.split("  ")));
            String position = parts.get(0).trim();
            String job = parts.get(2).trim();
            desc.add(position);
            desc.add(job);
        } catch (Exception e) {
            
        }

        return desc;
    }
    
    public void printAllWorkExp() {
        for (WorkExp exp : workExp) {
            System.out.println("Company: " + exp.getCompany()
                    + "\nPosition: " + exp.getPosition()
                    + "\nDuration: " + exp.getDuration() + " years\n");
        }
    }
    
}
