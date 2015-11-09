package cvia.main;

import cvia.parser.*;
import cvia.resume.ParseResultStorage;
import cvia.parser.entities.Section;
import cvia.utilities.PDFConverter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainBackup {

    private static PDFConverter converter = new PDFConverter();

    public static void mainBackup(String[] args) {
    	
/*    	CVIA facade = new CVIA();
    	
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
*/
    }
}
