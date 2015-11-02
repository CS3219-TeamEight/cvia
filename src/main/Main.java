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
		ResumeParser parser = new ResumeParser();;
		
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

        ArrayList<Section> sections = parser.getSections();
        ParserFactory factory = new ParserFactory();
        
        try {
            for (Section section : sections) {
                if (section.getType().equals("WORK")) {
                    WorkExpParser workParser = factory.getWorkParser();
                    workParser.parseWorkSection(section);
                    workParser.printWorkExperience();
                } else if (section.getType().equals("EDU")) {
                    EduParser eduParser = factory.getEduParser();
                    eduParser.parseEducation(section);
                    eduParser.printEduExperience();
                }
            }
        } catch (Exception e) {
            System.out.println("Error: could not parse your resume."
                    + "An admin has been notified to manually parse it.");
        }
	}
}
