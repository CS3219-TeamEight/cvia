package main;

import parser.DateParser;
import parser.Dictionary;
import parser.EduParser;
import parser.LanguageParser;
import parser.SkillParser;
import parser.WorkExpParser;

public class ParserFactory {
    
    DateParser dateParser;
    EduParser eduParser;
    WorkExpParser workExpParser;
    LanguageParser languageParser;
    SkillParser skillParser;
    
    public Dictionary getFosDictionary() {
		return fosDictionary;
	}

	public void setFosDictionary(Dictionary fosDictionary) {
		this.fosDictionary = fosDictionary;
	}

	public Dictionary getJobTitleDictionary() {
		return jobTitleDictionary;
	}

	public void setJobTitleDictionary(Dictionary jobTitleDictionary) {
		this.jobTitleDictionary = jobTitleDictionary;
	}

	public Dictionary getLanguageDictionary() {
		return languageDictionary;
	}

	public void setLanguageDictionary(Dictionary languageDictionary) {
		this.languageDictionary = languageDictionary;
	}

	public Dictionary getSkillDictionary() {
		return skillDictionary;
	}

	public void setSkillDictionary(Dictionary skillDictionary) {
		this.skillDictionary = skillDictionary;
	}

	protected Dictionary fosDictionary;
	protected Dictionary lvlDictionary;
    protected Dictionary jobTitleDictionary;
    protected Dictionary languageDictionary;
    protected Dictionary skillDictionary;

    private static final String FILENAME_DICTIONARY_FIELD = "./FieldsDictionary.txt";
    private static final String FILENAME_DICTIONARY_JOB = "./JobsDictionary.txt";
    private static final String FILENAME_DICTIONARY_LANGUAGE = "./LanguageDictionary.txt";
    private static final String FILENAME_DICTIONARY_SKILL = "./SkillDictionary.txt";
    private static final String FILENAME_DICTIONARY_LEVEL = "./LvlDictionary.txt";
    
    public ParserFactory() {
        produceParsers();
    }
    
    //To initialize the respective Dictionaries needed for parsing
    private void produceParsers() {
        dateParser = new DateParser();
        
        fosDictionary = new Dictionary(FILENAME_DICTIONARY_FIELD);
        lvlDictionary = new Dictionary(FILENAME_DICTIONARY_LEVEL);
        eduParser = new EduParser(dateParser, fosDictionary, lvlDictionary);
        
        jobTitleDictionary = new Dictionary(FILENAME_DICTIONARY_JOB);
        workExpParser = new WorkExpParser(dateParser, jobTitleDictionary);
        
        languageDictionary = new Dictionary(FILENAME_DICTIONARY_LANGUAGE);
        languageParser = new LanguageParser(languageDictionary);
        
        skillDictionary = new Dictionary(FILENAME_DICTIONARY_SKILL);
        skillParser = new SkillParser(skillDictionary);
        
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
    
    public LanguageParser getLanguageParser() {
        return languageParser;
    }
    
    public SkillParser getSkillParser() {
        return skillParser;
    }

}
