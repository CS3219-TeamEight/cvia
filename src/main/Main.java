package main;

import archive.ExampleJob;
import parser.*;
import scorer.Scorer;
import utilities.PDFConverter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static PDFConverter converter = new PDFConverter();


    public static void main(String[] args) {

        // global
        ResumeParser parser = new ResumeParser();
        ParserFactory factory = new ParserFactory();
        WorkExpParser workParser = factory.getWorkParser();
        EduParser eduParser = factory.getEduParser();
        LanguageParser languageParser = factory.getLanguageParser();
        SkillParser skillParser = factory.getSkillParser();

        // To parse in CV and convert it from PDF to plaintext format and saving it to a .txt file
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Please enter path for file:");
            String inputPath = sc.nextLine();
            sc.close();

            String outputPath = "plaintext.txt";
            converter.convertToText(inputPath, outputPath);
            File file = new File(outputPath);
            parser.initialize(file);
        } catch (IOException e) {

        }

        //1. To extract the possible sections found in the CV
        //2. Store the extracted information under each sections
        ArrayList<Section> sections = parser.getSections();
        ParseResultStorage storage = new ParseResultStorage();
        System.out.println("=============================================");
        try {
            for (Section section : sections) {
                if (section.getType().equals("WORK")) {
                    storage.storeWorkExp(workParser.parseWorkSection(section));
                    storage.printWorkExperience();
                } else if (section.getType().equals("EDU")) {
                    storage.storeEducation(eduParser.parseEducation(section));
                    storage.printEduExperience();
                } else if (section.getType().equals("LANGUAGES")) {
                    storage.storeLanguage(languageParser.parseLanguageSection(section));
                    storage.printLanguages();
                } else if (section.getType().equals("SKILLS")) {
                    storage.storeSkills(skillParser.parseSkillsSection(section));
                    storage.printSkills();
                }
            }
        } catch (Exception e) {
            System.out.println("Error: could not parse your resume."
                + "An admin has been notified to manually parse it.");
        }
        
        /**
        JSONConverter converter = new JSONConverter();
        System.out.println("=============================================");
        System.out.println("Testing parsed data -> JSON");
        String json = converter.getJSONString(storage);
        System.out.println(json);
        System.out.println("=============================================");
        **/

        //For testing
        ExampleJob ej = new ExampleJob(factory);
        Scorer scorer = new Scorer(ej.getExample(), storage);
        System.out.println("Total Score: " + scorer.computeScore());
        
        /**
        System.out.println("=============================================");
        System.out.println("Testing JSON -> parsed data");
        ParseResultStorage fromJson = converter.getParsedData(json);
        Scorer jsonscorer = new Scorer(ej.getExample(), fromJson);
        System.out.println("Total Score from json: " + jsonscorer.computeScore());
        System.out.println(converter.getJSONString(fromJson));
        System.out.println("=============================================");
        **/
        
        
        /**
        try {
            
        } catch (Exception e) {
            System.out.println("Error: could not parse your resume."
                    + "An admin has been notified to manually parse it.");
        }
        **/
	}
}
