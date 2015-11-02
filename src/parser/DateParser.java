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
        ongoing.add("projected");
        ongoing.add("now");

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
    
    public Duration identifyDates(String line) {
        double duration = 0;
        int startYear = 0;
        int startMonth = 0;
        boolean current = false;
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
            
            if (years.size() > 0) {
                startYear = years.get(0).getYear();
                startMonth = getMonth(line, years.get(0).getIndex() - 2);
                boolean isOngoing = false;
                for (String s : ongoing) {
                    if (line.toLowerCase().contains(s)) {
                        isOngoing = true;
                        break; 
                    }
                }
                
                if (isOngoing) { // ongoing considered first, since line might include PROJECTED end
                    // System.out.println("Is ongoing");
                    current = true;
                    if (startMonth != -1) {
                        Calendar today = Calendar.getInstance();
                        int thisYear = today.get(Calendar.YEAR);
                        int thisMonth = today.get(Calendar.MONTH);
                        duration = (thisYear - startYear) + (thisMonth - startMonth) / 12.0;
                    }
                } else {
                    if (years.size() == 1) {
                     // started and ended in same year
                     // System.out.println("started and ended in same year");
                        int index = years.get(0).getIndex() - 2;
                        int spaceCount = 0;
                        while (index >= 0 && spaceCount < 2) {
                            if (line.charAt(index) == ' ' && line.charAt(index+1) != ' ') spaceCount++;
                            index--;
                        }
                        int month2 = getMonth(line, index);
                        if (month2 != -1) {
                            //System.out.println("From month " + month2 + " to startMonth " + month + " in year " + startYear);
                            duration = (startMonth - month2) / 12.0;
                        }
                    } else if (years.size() == 2) {
                     // System.out.println("Found 2 years");
                        // started and ended in different years, or just wrote same year twice
                        int year2 = years.get(1).getYear();
                        int month2 = getMonth(line, years.get(1).getIndex() - 2);
                        if (startMonth != -1 && month2 != -1) {
                            //System.out.println("From year " + startYear + " month " + startMonth + " to year " + year2 + " month " + month2);
                            duration = (year2 - startYear) + (month2 - startMonth) / 12.0;
                        } else if (startMonth == -1 && month2 == -1) {
                            // assume that years are valid but months were not provided
                            duration = (year2 - startYear + 1);
                        }
                    } // else something is wrong, disregard this line
                }
            } // no years found, completely disregard
        }
        
        return new Duration(duration, startYear, startMonth, current);
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
