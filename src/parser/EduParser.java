package parser;

import qualification.Education;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class EduParser implements SectionParser {

    DateParser dateParser;
    Dictionary fosDictionary;
    
    ArrayList<String> lines;
    int lineCount;
    private ArrayList<Integer> pointers;
    private ArrayList<Duration> durations;
    private ArrayList<Education> education;
    private ArrayList<String> degreeNames;
    private HashMap<String, String> degreeTypes;
    private int offset;
    
    private static final String DEGREE_PHD = "Ph.D";
    private static final String DEGREE_MASTER = "Master";
    private static final String DEGREE_BACHELOR = "Bachelor";
    private static final String DEGREE_DOCTOR = "Doctor";
    private static final String DEGREE_UNKNOWN = "unknown";
    
    public EduParser(DateParser dateParser, Dictionary fosDictionary) {
        this.dateParser = dateParser;
        this.fosDictionary = fosDictionary;
        
        pointers = new ArrayList<>();
        durations = new ArrayList<>();
        education = new ArrayList<>();
        degreeNames = new ArrayList<>();
        degreeTypes = new HashMap<>();
        
        degreeNames.add("phd");
        degreeNames.add("ph.d");
        degreeNames.add("doctor of philosophy");
        degreeNames.add("master of");
        degreeNames.add("master in");
        degreeNames.add("master's");
        degreeNames.add("bachelor of");
        degreeNames.add("bachelor in");
        degreeNames.add("bachelor's");
        degreeNames.add("doctor of");
        degreeNames.add("doctor in");
        
        degreeTypes.put("phd", DEGREE_PHD);
        degreeTypes.put("ph.d", DEGREE_PHD);
        degreeTypes.put("doctor of philosophy", DEGREE_PHD);
        degreeTypes.put("master of", DEGREE_MASTER);
        degreeTypes.put("master in", DEGREE_MASTER);
        degreeTypes.put("master's", DEGREE_MASTER);
        degreeTypes.put("bachelor of", DEGREE_BACHELOR);
        degreeTypes.put("bachelor in", DEGREE_BACHELOR);
        degreeTypes.put("bachelor's", DEGREE_BACHELOR);
        degreeTypes.put("doctor of", DEGREE_DOCTOR);
        degreeTypes.put("doctor in", DEGREE_DOCTOR);
        
    }
    
    public void parseEducation(Section section) {
        lines = new ArrayList<>(section.getLines());
        lineCount = section.getLineCount();
        
        for (int i = 0; i < lineCount; i++) {
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
            education.add(storeEduExperience(i, pointers.get(i), pointers.get(i+1)));
        }
    }
    
    private Optional<String> getDegree(int lineNum) {
        String line = lines.get(lineNum);
        String degree = null;
        boolean found = false;
        for (String deg : degreeNames) {
            if (line.toLowerCase().contains(deg)) {
                degree = deg;
                found = true;
                break;
            }
        }
        if (found) {
            return Optional.of(degreeTypes.get(degree));
        } else {
            return Optional.empty();
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
        Duration duration = durations.get(index);
        double cap = -1;
        String degree = DEGREE_UNKNOWN;
        boolean capFound = false;
        boolean degFound = false;
        for (int i = start; i < end && (!capFound || !degFound); i++) {
            if (!capFound) {
                double val = getCAP(i);
                if (val > 0) {
                    cap = val;
                    capFound = true;
                }
            }
            if (!degFound) {
                Optional<String> deg = getDegree(i);
                if (deg.isPresent()) {
                    // found degree
                    degree = deg.get();
                    degFound = true;
                }
            }
        }
        Education edu = new Education(duration.getDuration(), cap, !duration.isOngoing(), degree);
        // although there are different types of institutes (university, high school, etc)
        // catered to university only, due to difficulty in differentiating them
        return edu;
    }
    
    public ArrayList<Education> getAllEdu() {
        return education;
    }
    
}
