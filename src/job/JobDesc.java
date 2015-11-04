package job;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Pattern;
import java.lang.Double;
import java.lang.Integer;

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
	
	public JobDesc(){
		languages = new HashSet<String>();
		skillSets = new HashSet<String>();
	}

	public void workExp(String workExp) {
		String[] work = workExp.split("[,\\s]+");
		StringBuilder sb = new StringBuilder();
		String yearString;
		boolean numFlag = false;
		double years = 0;
		boolean found = false;

		//To determine work experience
		for (int i = 0; i<work.length; i++) {
			if (Pattern.compile("year|years").matcher(work[i]).find()) { //Located year or years in word[i]
				for (char c : work[i].toCharArray()) {
					if (Character.isDigit(c)) {
						sb.append(c);
						found = true;
						numFlag = true;
					} else if (found) {
						sb.append(" ");
						found = false;
					}

					if (numFlag) {
						yearString = sb.toString().trim();

						//years of experience if only 1 number provided. Eg 2years
						if (yearString.split("[,\\s]+").length == 1) {
							years = Double.parseDouble(yearString); 
						} else if (yearString.split("[,\\s]+").length > 1) {       //Average years of experience if 2 numbers are provided. Eg 2or3years
							String [] yearStringArray =yearString.split("[,\\s]+");
							for (int j = 0; j<yearStringArray.length; j++) {
								years += Double.parseDouble(yearStringArray[j]);
							}
							years /= yearStringArray.length;
						}
					} else {
						for (char ch : work[i-1].toCharArray()) {
							if (Character.isDigit(ch)) {
								sb.append(ch);
								found = true;
								numFlag = true;
							} else if (found) {
								sb.append(" ");
								found = false;
							}
						}
						yearString = sb.toString().trim();
						//years of experience if only 1 number provided. Eg 2years
						if (yearString.split("[,\\s]+").length == 1) {
							years = Double.parseDouble(yearString); 
						} else if (yearString.split("[,\\s]+").length > 1) {       //Average years of experience if 2 numbers are provided. Eg 2or3years
							String [] yearStringArray =yearString.split("[,\\s]+");
							for (int j = 0; j<yearStringArray.length; j++) {
								years += Double.parseDouble(yearStringArray[j]);
							}
							years /= yearStringArray.length;
						}
					}
				}
			}
			
			setWorkDuration (years);
		}

	}

	public double getWorkDuration() {
		return workDuration;
	}
	public void setWorkDuration(Double workDuration) {
		this.workDuration = workDuration;
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
	public HashSet<String> getLanguages() {
		return languages;
	}
	public void setLanguages(String language) {
		// this.languages = languages;
		String[] languageList = language.split("[,\\s]+");
		for(String lang : languageList) {
			languages.add(lang);
		}
	}
	public HashSet<String> getskillSets() {
		return skillSets;
	}
	public void setSkillSets(String skillset) {
		String[] skills = skillset.split("[,\\s]+");
		for(String skill : skills) {
			skillSets.add(skill);
		}
	}
	public ArrayList<String> getOthers() {
		return others;
	}
	public void setOthers(String other) {
		//		this.others = others;
		String[] otherList = other.split("[,\\s]+");
		for(String random : otherList) {
			others.add(random);
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
