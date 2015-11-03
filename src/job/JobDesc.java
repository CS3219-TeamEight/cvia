package job;

import java.util.ArrayList;
import java.lang.Double;
import java.lang.Integer;

public class JobDesc {
	private double workDuration;
	private double educationCap;
	private int educationLevel;
	private String educationTitle;
	private String jobTitle;
	private ArrayList<String> languages;
	private ArrayList<String> skillSets;
	private ArrayList<String> others;
	
	private int eduWeightage;
	private int workWeightage;
	private int languageWeightage;
	private int otherWeightage;
	private int skillsetWeightage;
	
	public double getWorkDuration() {
		return workDuration;
	}
	public void setWorkDuration(String workDuration) {
		this.workDuration = Double.parseDouble(workDuration);
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
		this.educationTitle = educationTitle;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public ArrayList<String> getLanguages() {
		return languages;
	}
	public void setLanguages(String language) {
		// this.languages = languages;
		String[] languageList = language.split(", ");
		for(String lang : languageList) {
			others.add(lang);
		}
	}
	public ArrayList<String> getskillSets() {
		return skillSets;
	}
	public void setSkillSets(String skillset) {
		String[] skills = skillset.split(", ");
		for(String skill : skills) {
			skillSets.add(skill);
		}
	}
	public ArrayList<String> getOthers() {
		return others;
	}
	public void setOthers(String others) {
//		this.others = others;
		String[] otherList = others.split(", ");
		for(String random : otherList) {
			languages.add(random);
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
