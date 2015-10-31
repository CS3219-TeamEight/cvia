package parser;

import qualification.Education;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;

import com.joestelmach.natty.*;

public class EduParser implements SectionParser {

    DateParser dateParser;
    ArrayList<String> lines;
    private ArrayList<Integer> pointers;
    private ArrayList<Double> durations;
    private ArrayList<Education> education;
    private int offset;
    
    public EduParser(Section section, DateParser dateParser) {
        this.dateParser = dateParser;
        lines = new ArrayList<String>(section.getLines());
        pointers = new ArrayList<Integer>();
        durations = new ArrayList<Double>();
        education = new ArrayList<Education>();
        
        parseEducation();
        
    }
    
    private void parseEducation() {
        
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
        
        for (int i = 0; i < pointers.size()-1; i++) {
            education.add(storeEduExperience(i, pointers.get(i), pointers.get(i+1)));
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
    
    private Education storeEduExperience(int index, int start, int end) {
        double duration = durations.get(index);
        double cap = -1;
        for (int i = start; i < end; i++) {
            cap = getCAP(i);
            if (cap > 0) break;
        }
        Education edu = new Education(duration, cap);
        return edu;
    }
    
    public void printEduExperience() {
        for (Education edu : education) {
            System.out.println("CAP: " + edu.getCap() + "\nDuration: " + edu.getDuration());
        }
    }
    
}
