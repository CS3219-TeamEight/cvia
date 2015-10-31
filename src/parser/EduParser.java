package parser;

import qualification.Education;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;

import com.joestelmach.natty.*;

public class EduParser implements SectionParser {

    ArrayList<String> lines;
    private ArrayList<String> ongoing;
    private ArrayList<Integer> pointers;
    private ArrayList<Double> durations;
    private ArrayList<Education> education;
    private int offset;
    
    public EduParser(Section section) {
        lines = new ArrayList<String>(section.getLines());
        pointers = new ArrayList<Integer>();
        ongoing = new ArrayList<String>();
        durations = new ArrayList<Double>();
        education = new ArrayList<Education>();

        ongoing.add("ongoing");
        ongoing.add("current");
        ongoing.add("present");
        
        for (int i = 0; i < lines.size(); i++) {
            splitEduExperience(i);
        }
        System.out.println(pointers.size() + "Institutes");
        pointers.add(-1); // dummy pointer to signify end of section
        
        for (int i = 0; i < pointers.size()-1; i++) {
            storeEduExperience(i, pointers.get(i), pointers.get(i+1));
        }
        
    }
    
    private double getCAP(int lineNum) {
        String line = lines.get(lineNum);
        double cap = 0;
        // assumption: CAP will be written with a decimal point, even if it is followed by only 0s
        for (int i = 0; i < line.length(); i++) {
            // program has to go through each index in line anyway
            if (line.charAt(i) == '.') {
                String prefix = getString(line, i, -1);
                String suffix = getString(line, i, 1);
                if (suffix.equals("") || prefix.equals("")) break;
                String capString = prefix.concat(".").concat(suffix);
                try {
                    cap = Double.parseDouble(capString);
                    break;
                } catch (NumberFormatException e) {
                    // not CAP. Probably some string like Ph.D
                    continue;
                }
            }
        }
        return cap;
    }
    
    private String getString(String line, int pos, int dir) {
        String val = "";
        if (pos == 0 || pos == (line.length() - 1)) return "";
        for (int i = pos+dir; (i>0 && i<line.length()) ; i+=dir) {
            if (Character.isDigit(line.charAt(i))) {
                char c = line.charAt(i);
                if (dir == 1) {
                    val = new StringBuilder().append(val.toCharArray()).append(c).toString();
                } else if (dir == -1) {
                    val = new StringBuilder().append(c).append(val.toCharArray()).toString();
                }
            } else break;
        }
        return val;
    }
    
    // the parser could be extracted to its own component
    private void splitEduExperience(int lineNum) {
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
                    if (line.toLowerCase().contains(ongoingString)) {
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
            durations.add(duration);
            if (pointers.size() == 0) {
                offset = lineNum;
            }
            pointers.add(lineNum - offset);
        }
        
        // assumption: uniform format. The work experience section can be split
        // to individual jobs based on location of the date information
    }
    
    private void storeEduExperience(int index, int start, int end) {
        double duration = durations.get(index);
        double cap = -1;
        for (int i = start; i < end; i++) {
            cap = getCAP(i);
            if (cap > 0) break;
        }
        Education edu = new Education(duration, cap);
        education.add(edu);
    }
    
    public void printEduExperience() {
        for (Education edu : education) {
            System.out.println("CAP: " + edu.getCap() + "\nDuration: " + edu.getDuration());
        }
    }
    
}
