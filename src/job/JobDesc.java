package job;

import java.util.ArrayList;

public class JobDesc {
	private double workDuration;
	private double educationCap;
	private int educationLevel;
	private String educationTitle;
	private String jobTitle;
	private ArrayList<String> languages;
	private ArrayList<String> others;
	
	private int eduWeightage;
	private int workWeightage;
	private int languageWeightage;
	private int otherWeightage;
	
	public double getWorkDuration() {
		return workDuration;
	}
	public void setWorkDuration(double workDuration) {
		this.workDuration = workDuration;
	}
	public double getEducationCap() {
		return educationCap;
	}
	public void setEducationCap(double educationCap) {
		this.educationCap = educationCap;
	}
	public int getEducationLevel() {
		return educationLevel;
	}
	public void setEducationLevel(int educationLevel) {
		this.educationLevel = educationLevel;
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
	public void setLanguages(ArrayList<String> languages) {
		this.languages = languages;
	}
	public ArrayList<String> getOthers() {
		return others;
	}
	public void setOthers(ArrayList<String> others) {
		this.others = others;
	}
	public int getEduWeightage() {
		return eduWeightage;
	}
	public void setEduWeightage(int eduWeightage) {
		this.eduWeightage = eduWeightage;
	}
	public int getWorkWeightage() {
		return workWeightage;
	}
	public void setWorkWeightage(int workWeightage) {
		this.workWeightage = workWeightage;
	}
	public int getLanguageWeightage() {
		return languageWeightage;
	}
	public void setLanguageWeightage(int languageWeightage) {
		this.languageWeightage = languageWeightage;
	}
	public int getOtherWeightage() {
		return otherWeightage;
	}
	public void setOtherWeightage(int otherWeightage) {
		this.otherWeightage = otherWeightage;
	}
	
}
