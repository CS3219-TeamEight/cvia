package parser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class DateParser {

    private ArrayList<String> ongoing;
    private HashMap<String, Integer> months;
    
    public DateParser() {
        
        ongoing = new ArrayList<String>();
        ongoing.add("ongoing");
        ongoing.add("current");
        ongoing.add("present");

        months = new HashMap<String, Integer>();
        months.put("jan", 1);
        months.put("january", 1);
        months.put("feb", 2);
        months.put("february", 2);
        months.put("mar", 3);
        months.put("march", 3);
        months.put("early", 3);
        months.put("apr", 4);
        months.put("april", 4);
        months.put("may", 5);
        months.put("jun", 6);
        months.put("june", 6);
        months.put("mid", 6);
        months.put("jul", 7);
        months.put("july", 7);
        months.put("aug", 8);
        months.put("august", 8);
        months.put("sep", 9);
        months.put("september", 9);
        months.put("late", 9);
        months.put("oct", 10);
        months.put("october", 10);
        months.put("nov", 11);
        months.put("november", 11);
        months.put("dec", 12);
        months.put("december", 12);
    }
    
    public double identifyDates(String line) {
        double duration = 0;
        ArrayList<Year> years = new ArrayList<Year>();
        
        if (line.contains("20") || line.contains("19")) {
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
                    if (num > 1950 && num <= 2050) { // reasonable range
                     // System.out.println(num);
                        years.add(new Year(num, j-4)); // reasonable assumption: the year is 4-digits 
                    }
                    i = j+1;
                }
            }
            
            if (years.size() == 1) {
             // System.out.println("Found 1 year");
                // started and ended in same year, or ongoing
                int year = years.get(0).getYear();
                int month = getMonth(line, line.indexOf(Integer.toString(year)) - 2);
                if (month != -1) {
                    boolean isOngoing = false;
                    for (String s : ongoing) {
                        if (line.toLowerCase().contains(s)) {
                            isOngoing = true;
                         // System.out.println("Is ongoing");
                            break; 
                        }
                    }
                    if (isOngoing) {
                        if (month != -1) {
                            System.out.println("Ongoing from year " + year + " month " + month);
                            Calendar today = Calendar.getInstance();
                            int thisYear = today.get(Calendar.YEAR);
                            int thisMonth = today.get(Calendar.MONTH);
                            duration = (thisYear - year) + (thisMonth - month) / 12.0;
                        }
                    } else {
                        // started and ended in same year
                        int index = years.get(0).getIndex() - 2;
                        int spaceCount = 0;
                        while (spaceCount < 2) {
                            if (line.charAt(index) == ' ' && line.charAt(index+1) != ' ') spaceCount++;
                            index--;
                        }
                        int month2 = getMonth(line, index);
                        if (month2 != -1) {
                            System.out.println("From month " + month2 + " to month " + month + " in year " + year);
                            duration = (month2 - month) / 12.0;
                        }
                    }
                }
            } else if (years.size() == 2) {
             // System.out.println("Found 2 years");
                // started and ended in different years, or just wrote same year twice
                int year1 = years.get(0).getYear();
                int year2 = years.get(1).getYear();
                int month1 = getMonth(line, years.get(0).getIndex() - 2);
                int month2 = getMonth(line, years.get(1).getIndex() - 2);
                if (month1 != -1 && month2 != -1) {
                    System.out.println("From year " + year1 + " month " + month1 + " to year " + year2 + " month " + month2);
                    duration = (year2 - year1) + (month2 - month1) / 12.0;
                }
                
            }// otherwise something is wrong, ignore
            
        }
        return duration;
    }
    
    private int getMonth(String line, int index) {
     // System.out.println(index);
        String month = "";
        if (index < 2) return -1;
        for (int i = index; i>=0 && Character.isAlphabetic(line.charAt(i)); i--) {
            month = line.charAt(i) + month;
        }
        month = month.toLowerCase();
     // System.out.println(month);
        if (months.containsKey(month)) {
            return months.get(month);
        } else return -1;
    }
    
    class Year {
        
        private int year;
        private int index;
        
        public Year(int year, int index) {
            this.year = year;
            this.index = index;
        }
        
        public int getYear() {
            return year;
        }
        
        public int getIndex() {
            return index;
        }
    }

}
