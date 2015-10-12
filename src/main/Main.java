package main;

import parser.ResumeParser;
import parser.Section;
import parser.WorkExpParser;
import utilities.PDFConverter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	private static PDFConverter converter = new PDFConverter();
	
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner (System.in);
        System.out.println("Please enter path for file:");
        String inputPath = sc.nextLine();
        
        System.out.println("Please enter output path for file:");
        String outputPath = sc.nextLine();
        
        sc.close();
        
		//For testing my own pdf file
		//String _stanley_pdf = "/Users/Stanley/Desktop/Test.pdf";
		
		converter.convertToText(inputPath, outputPath);
		
		File file = new File(outputPath);
		ResumeParser parser = new ResumeParser(file);
		//parser.printAllSections();
		ArrayList<Section> sections = parser.getSections();
		WorkExpParser workParser;
		for (Section section : sections) {
		    if (section.getType().equals("WORK")) {
		        workParser = new WorkExpParser(section);
		        workParser.printAllWorkExp();
		    }
		}
		
	}
}
