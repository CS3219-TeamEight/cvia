package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WorkExpParser implements SectionParser {

    ArrayList<String> lines;
    HashMap<Integer, Double> dateLines; // line number, no. of years
    ArrayList<String> months;
    ArrayList<String> ongoing;
    
    public WorkExpParser(Section section) {
        lines = new ArrayList<>(section.getLines());
        dateLines = new HashMap<>();
        for (int i = 0; i < lines.size(); i++) {
            getDuration(i);
        }
        /**
        months.add("January");
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
    
    // assumes LinkedIn format for now
    private void getDuration(int lineNum) {
        String line = lines.get(lineNum);
        int years = 0;
        int months = 0;
        if (line.toLowerCase().contains("year")) {
            // later account for cases where there are more than 1 occurrences of year&month
            int yearIndex = line.indexOf("year");
            int counter = 1;
            for (int i = yearIndex-2; i>=0; i--) {
                char c = line.charAt(i);
                if (c >= '0' && c <= '9') {
                    int num = Character.getNumericValue(c);
                    years += num * counter;
                    counter *= 10;
                } else {
                    break;
                }
            }
            
        }
        
        if (line.toLowerCase().contains("month")) {
            int monthIndex = line.indexOf("month");
            int counter = 1;
            for (int i = monthIndex-2; i>=0; i--) {
                char c = line.charAt(i);
                if (c >= '0' && c <= '9') {
                    int num = Character.getNumericValue(c);
                    months += num * counter;
                    counter *= 10;
                } else {
                    break;
                }
            }
        }
        Double exp = years*1.0 + months/12.0;
        if (exp > 0.0) {
            System.out.println("Worked for " + exp + " years");
            dateLines.put(lineNum, exp);
        }
    }
}
