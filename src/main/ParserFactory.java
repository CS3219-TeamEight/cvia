package main;

import parser.DateParser;
import parser.EduParser;
import parser.WorkExpParser;

public class ParserFactory {
    
    DateParser dateParser;
    EduParser eduParser;
    WorkExpParser workExpParser;
    
    public ParserFactory() {
        produceParsers();
    }
    
    private void produceParsers() {
        dateParser = new DateParser();
        eduParser = new EduParser(dateParser);
        workExpParser = new WorkExpParser(dateParser);
        
    }
    
    public DateParser getDateParser() {
        return dateParser;
    }
    
    public EduParser getEduParser() {
        return eduParser;
    }
    
    public WorkExpParser getWorkParser() {
        return workExpParser;
    }

}
