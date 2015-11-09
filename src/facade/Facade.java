package facade;

import java.io.IOException;
import java.util.Scanner;

import job.JobDesc;
import main.ParseResultStorage;
import main.ParserFactory;
import parser.EduParser;
import parser.LanguageParser;
import parser.ResumeParser;
import parser.Section;
import parser.SkillParser;
import parser.WorkExpParser;
import resume.Resume;
import scorer.Scorer;
import utilities.PDFConverter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Facade {

	ResumeParser parser = new ResumeParser();;
	ParserFactory factory = new ParserFactory();
	WorkExpParser workParser = factory.getWorkParser();
	EduParser eduParser = factory.getEduParser();
	LanguageParser languageParser = factory.getLanguageParser();
	SkillParser skillParser = factory.getSkillParser();
	Scanner sc = new Scanner(System.in);
	PDFConverter converter = new PDFConverter();
	File file;


	public Facade() {
		super();
	}

	private static String outputPath = "plaintext.txt";

	public ParseResultStorage inputResume (String inputPath) {
		try {
			converter.convertToText(inputPath, outputPath);
			file = new File(outputPath);
			parser.initialize(file);
		} catch (IOException e) {
			System.out.println("Failed to initialize file");
		}
		
		ArrayList<Section> sections = parser.getSections();
		ParseResultStorage storage = new ParseResultStorage();
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
		return storage;
	}
	
	public JobDesc inputJobDesc(String jobTitle, String jobIndustry, String jobExperience,
            String educationLevel, String educationField, String languageSkills, String workSkills,
            String description, double mutiplierJob, double mutiplierEdu, double mutiplierLang,
            double mutiplierSkill) {
		
		JobDesc job = new JobDesc(jobTitle, jobIndustry, jobExperience,
	            educationLevel, educationField, languageSkills, workSkills,
	            description, mutiplierJob, mutiplierEdu, mutiplierLang,
	            mutiplierSkill);
		
		return job;
	}

	public Scorer parseResume(JobDesc job, ParseResultStorage resume) {
		Scorer score = new Scorer(job, resume);
		return score;
	}

	public void rankParsed(String parsedJson, JobDesc job) {

	}
}
