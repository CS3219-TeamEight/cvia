package main;

import parser.ResumeParser;
import parser.Section;
import parser.EduParser;
import parser.WorkExpParser;
import utilities.PDFConverter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	private static PDFConverter converter = new PDFConverter();
	
	
	public static void main(String[] args) {
	    // global
		ResumeParser parser = new ResumeParser();;
        ParserFactory factory = new ParserFactory();
        WorkExpParser workParser = factory.getWorkParser();
        EduParser eduParser = factory.getEduParser();
        // update parsers to be truly global
        
        // for each CV
        try {
            Scanner sc = new Scanner (System.in);
            System.out.println("Please enter path for file:");
            String inputPath = sc.nextLine();
            sc.close();
            
            String outputPath = "plaintext.txt";
            converter.convertToText(inputPath, outputPath);
            File file = new File(outputPath);
            parser.initialize(file);
        } catch (IOException e) {
            
        }

        try {
            ArrayList<Section> sections = parser.getSections();
            ParseResultStorage storage = new ParseResultStorage();
            for (Section section : sections) {
                if (section.getType().equals("WORK")) {
                    workParser.parseWorkSection(section);
                    storage.storeWorkExp(workParser.getAllExp());
                    storage.printWorkExperience();
                } else if (section.getType().equals("EDU")) {
                    eduParser.parseEducation(section);
                    storage.storeEducation(eduParser.getAllEdu());
                    storage.printEduExperience();
                }
            }
        } catch (Exception e) {
            System.out.println("Error: could not parse your resume."
                    + "An admin has been notified to manually parse it.");
        }
	}
}
