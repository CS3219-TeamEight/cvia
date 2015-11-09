package cvia.main;

import cvia.job.JobDesc;
import cvia.parser.*;
import cvia.parser.entities.Section;
import cvia.resume.ParseResultStorage;
import cvia.resume.Resume;
import cvia.scorer.Scorer;
import cvia.utilities.JSONConverter;
import cvia.utilities.PDFConverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CVIA {

    private static CVIA instance;
    private WorkExpParser workParser;
    private EduParser eduParser;
    private LanguageParser languageParser;
    private SkillParser skillParser;
    private Scanner sc;

    public CVIA() {
        ParserFactory factory = new ParserFactory();
        workParser = factory.getWorkParser();
        eduParser = factory.getEduParser();
        languageParser = factory.getLanguageParser();
        skillParser = factory.getSkillParser();
        sc = new Scanner(System.in);
    }

    public JobDesc initJobDesc(String jobTitle, String jobIndustry, String jobExperience,
        String educationLevel, String educationField, String languageSkills, String workSkills,
        String description, double mutiplierJob, double mutiplierEdu, double mutiplierLang,
        double mutiplierSkill) {

        JobDesc job =
            new JobDesc(jobTitle, jobIndustry, jobExperience, educationLevel, educationField,
                languageSkills, workSkills, description, mutiplierJob, mutiplierEdu, mutiplierLang,
                mutiplierSkill);

        return job;
    }

    public Resume initResume(String inputPath) {
        Resume resume = new Resume(inputPath);
        return resume;
    }

    public Resume parseResume(JobDesc job, Resume resume) {
        PDFConverter converter = new PDFConverter();
        ResumeParser parser = new ResumeParser();

        try {
            String parsedText = converter.convertToText(resume.getResumePath());
            parser.initialize(parsedText);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Section> sections = parser.getSections();
        ParseResultStorage parsedStorage = new ParseResultStorage();
        try {
            for (Section section : sections) {
                switch (section.getType()) {
                    case "WORK":
                        parsedStorage.storeWorkExp(workParser.parseWorkSection(section));
                        parsedStorage.printWorkExperience();
                        break;
                    case "EDU":
                        parsedStorage.storeEducation(eduParser.parseEducation(section));
                        parsedStorage.printEduExperience();
                        break;
                    case "LANGUAGES":
                        parsedStorage.storeLanguage(languageParser.parseLanguageSection(section));
                        parsedStorage.printLanguages();
                        break;
                    case "SKILLS":
                        parsedStorage.storeSkills(skillParser.parseSkillsSection(section));
                        parsedStorage.printSkills();
                        break;
                }
            }

            Scorer score = new Scorer(job, parsedStorage);
            resume.setScore(score.computeScore());
            resume.setParsedContents((new JSONConverter()).getJSONString(parsedStorage));
            return resume;
        } catch (Exception e) {
            System.err.println("Error: could not parse your resume.\n"
                + "An admin has been notified to manually parse it.");
            e.printStackTrace();
            return null;
        }
    }

    public Resume scoreParsed(Resume resume, JobDesc job) {
        ParseResultStorage parsedStorage =
            (new JSONConverter().getParsedData(resume.getParsedContents()));
        Scorer score = new Scorer(job, parsedStorage);
        resume.setScore(score.computeScore());
        return resume;
    }

    public static CVIA getInstance() {
        if (instance == null) {
            instance = new CVIA();
        }
        return instance;
    }
}
