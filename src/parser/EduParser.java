package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EduParser implements SectionParser {

    ArrayList<String> lines;
    HashMap<Integer, Double> dateLines; // line number, no. of years
    ArrayList<String> scoreList;
    
    public EduParser(Section section) {
        lines = new ArrayList<>(section.getLines());
        dateLines = new HashMap<>();
        for (int i = 0; i < lines.size(); i++) {
            getScore(i);
        }
    }
    
    private void getScore(int lineNum) {
        String line = lines.get(lineNum);
        String score = "";
        if (line.toLowerCase().contains("gpa") || line.toLowerCase().contains("cap")) {
            int capIndex = line.indexOf("cap");
            for (int i = capIndex-2; i>=0; i--) {
                char c = line.charAt(i);
                if (c >= '0' && c <= '9' || c =='.') {
                    score += c;
                }
            }
            scoreList.add(score);
            
        }
        

    }
}
