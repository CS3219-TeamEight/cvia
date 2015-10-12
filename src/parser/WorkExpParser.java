package parser;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;

public class WorkExpParser implements SectionParser {

    ArrayList<String> lines;
    HashMap<Integer, Float> dateLines; // line number, no. of years
    ArrayList<String> months;
    ArrayList<String> ongoing;
    
    public WorkExpParser(Section section) {
        lines = new ArrayList<>(section.getLines());
        dateLines = new HashMap<>();
        /** months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");
        
        ongoing.add("ongoing");
        ongoing.add("present");
        **/
    }
    
    /**
    private ArrayList<Integer> findYears(String line) {
        ArrayList<Integer> years = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c >= '0' && c <= '9') {
                int num = Character.getNumericValue(c);
                int j;
                for (j = i+1; j < line.length(); j++) {
                    char c2 = line.charAt(j);
                    if (c2 >= '0' && c2 <= '9') {
                        num = num * 10 + Character.getNumericValue(c2);
                    } else {
                        break;
                    }
                }
                if (num > 1900 && num < 2100) {
                    years.add(num);
                }
                i = j+1;
            }
        }
        return years;
    }
    
    // likely that each job experience will have only one line containing date information
    private void findDates(String line) {
        ArrayList<Integer> monthsFound = new ArrayList<>();
        for (int i = 0; i < months.size(); i++) {
            if (line.contains(months.get(i))) {
                // not compared with lowercase to minimize the likelihood of
                // the verb "may" being mistaken as a month
                monthsFound.add(i+1);
            }
        }
        ArrayList<Integer> yearsFound = findYears(line);
        boolean isOngoing = false;
        for (String cont : ongoing) {
            if (line.contains(cont)) {
                isOngoing = true;
                break;
            }
        }
        
        
    }
    **/
    
    private List<Date> parseDates(String line) {
        Parser parser = new Parser();
        List<DateGroup> groups = parser.parse(line);
        List<Date> datesFound = new ArrayList<>();
        for (DateGroup group : groups) {
            List<Date> dates = group.getDates();
            for (Date date : dates) {
                datesFound.add(date);
            }
        }
        return datesFound;
    }

}
