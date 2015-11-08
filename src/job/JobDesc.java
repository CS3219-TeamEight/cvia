package job;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Pattern;
import java.lang.Double;
import java.lang.Integer;

import main.ParserFactory;

public class JobDesc {
	private double workDuration;
	private double educationCap;
	private int educationLevel;
	private String educationTitle;
	private String jobTitle;
	
	private HashSet<String> languages;
	private HashSet<String> skillSets;
	private ArrayList<String> others;

	private int eduWeightage;
	private int workWeightage;
	private int languageWeightage;
	private int otherWeightage;
	private int skillsetWeightage;
	
	private ParserFactory parserFactory;
	
	public JobDesc(ParserFactory parserFactory){
		languages = new HashSet<String>();
		skillSets = new HashSet<String>();
		this.parserFactory = parserFactory;
	}

	public double getWorkDuration() {
		return workDuration;
	}
	
	//To parse in the number of years of work experience required for the job
	public void setWorkDuration(String workDuration) {
		String [] duration = workDuration.split("[,\\s]+");
		String durationString;
		double totalDuration = 0;
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i<duration.length; i++) {
			for (char c : duration[i].toCharArray()) {
				if (Character.isDigit(c)) {
					sb.append(c);
				} else {
					sb.append(" ");
				}
			}
		}
		durationString = sb.toString().trim();
		if (!durationString.equals("")) {
			String durationStringArray [] = durationString.split("[,\\s]+");
			for (String data : durationStringArray) {
				totalDuration += Double.parseDouble(data);
			}
		
			this.workDuration = totalDuration / durationStringArray.length;
			System.out.println("Work Duration: " + this.workDuration);
		} else {
			this.workDuration = 0;
			System.out.println("Work Duration: " + this.workDuration);
		}
		
	}
	
	public double getEducationCap() {
		return educationCap;
	}
	
	public void setEducationCap(String educationCap) {
		this.educationCap = Double.parseDouble(educationCap);
	}
	public int getEducationLevel() {
		return educationLevel;
	}
	public void setEducationLevel(String educationLevel) {
		this.educationLevel = Integer.parseInt(educationLevel);
	}
	public String getEducationTitle() {
		return educationTitle;
	}
	public void setEducationTitle(String educationTitle) {
		this.educationTitle = educationTitle.toLowerCase();
	}
	public String getJobTitle() {
		return jobTitle;
	}
	
	//To parse in the field of the job
	public void setJobTitle(String jobTitle) {
		this.jobTitle = parserFactory.getJobTitleDictionary().contains(jobTitle.toLowerCase());
	}
	public HashSet<String> getLanguages() {
		return languages;
	}
	
	//To parse in the language required for the job
	public void setLanguages(String language) {
		language = language.toLowerCase();
		String[] languageList = language.split("[,\\s]+");
		for(String lang : languageList) {
			languages.add(lang.toLowerCase());
		}
	}
	public HashSet<String> getskillSets() {
		return skillSets;
	}
	
	//To parse in the skillsets required for the job
	public void setSkillSets(String skillset) {
		skillset = skillset.toLowerCase();
		String[] skills = skillset.split("[,\\s]+");
		for(String skill : skills) {
			skillSets.add(skill.toLowerCase());
		}
	}
	public ArrayList<String> getOthers() {
		return others;
	}
	
	//To set any other requirements needed for the job -- Not in use now
	public void setOthers(String other) {
		//		this.others = others;
		String[] otherList = other.split("[,\\s]+");
		for(String random : otherList) {
			others.add(random.toLowerCase());
		}
	}
	public int getEduWeightage() {
		return eduWeightage;
	}
	public void setEduWeightage(String eduWeightage) {
		this.eduWeightage = Integer.parseInt(eduWeightage);
	}
	public int getWorkWeightage() {
		return workWeightage;
	}
	public void setWorkWeightage(String workWeightage) {
		this.workWeightage = Integer.parseInt(workWeightage);
	}
	public int getLanguageWeightage() {
		return languageWeightage;
	}
	public void setLanguageWeightage(String languageWeightage) {
		this.languageWeightage = Integer.parseInt(languageWeightage);
	}
	public int getOtherWeightage() {
		return otherWeightage;
	}
	public void setOtherWeightage(String otherWeightage) {
		this.otherWeightage = Integer.parseInt(otherWeightage);
	}
	public int getSkillsetWeightage() {
		return skillsetWeightage;
	}
	public void setSkillsetWeightage(String skillsetWeightage) {
		this.skillsetWeightage = Integer.parseInt(skillsetWeightage);
	}
}
