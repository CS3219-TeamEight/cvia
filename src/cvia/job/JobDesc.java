package cvia.job;

import cvia.parser.ParserFactory;

import java.util.HashSet;

public class JobDesc {
    private double workDuration;
    private String educationLevel;
    private String educationTitle;
    private String jobTitle;
    private String jobIndustry;

    private HashSet<String> languages = new HashSet<String>();
    private HashSet<String> skillSets = new HashSet<String>();

    private double eduWeightage;
    private double workWeightage;
    private double languageWeightage;
    private double skillsetWeightage;

    private ParserFactory parserFactory = new ParserFactory();

    public JobDesc(String jobTitle, String jobIndustry, String jobExperience, String educationLevel,
        String educationField, String languageSkills, String workSkills, String description,
        double mutiplierJob, double mutiplierEdu, double mutiplierLang, double mutiplierSkill) {

        this.setJobTitle(jobTitle);
        this.setWorkDuration(jobExperience);
        this.setEducationLevel(educationLevel);
        this.setEducationTitle(educationField);
        this.setLanguages(languageSkills);
        this.setSkillSets(workSkills);
        this.setWorkWeightage(mutiplierJob);
        this.setEduWeightage(mutiplierEdu);
        this.setLanguageWeightage(mutiplierLang);
        this.setSkillsetWeightage(mutiplierSkill);
        this.setJobIndustry(jobIndustry);
    }

    public double getWorkDuration() {
        return workDuration;
    }

    //To parse in the number of years of work experience required for the job
    public void setWorkDuration(String workDuration) {
        String[] duration = workDuration.split("[,\\s]+");
        String durationString;
        double totalDuration = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < duration.length; i++) {
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
            String durationStringArray[] = durationString.split("[,\\s]+");
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

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
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
        if (this.jobTitle.equals("UNKNOWN"))
        	this.jobTitle = parserFactory.getJobTitleDictionary().contains(jobIndustry.toLowerCase());
    }

    public HashSet<String> getLanguages() {
        return languages;
    }

    //To parse in the language required for the job
    public void setLanguages(String language) {
        language = language.toLowerCase();
        String[] languageList = language.split("[,\\s]+");
        for (String lang : languageList) {
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
        for (String skill : skills) {
            skillSets.add(skill.toLowerCase());
        }
    }

    public double getEduWeightage() {
        return eduWeightage;
    }

    public void setEduWeightage(double eduWeightage) {
        this.eduWeightage = eduWeightage;
    }

    public double getWorkWeightage() {
        return workWeightage;
    }

    public void setWorkWeightage(double workWeightage) {
        this.workWeightage = workWeightage;
    }

    public double getLanguageWeightage() {
        return languageWeightage;
    }

    public void setLanguageWeightage(double languageWeightage) {
        this.languageWeightage = languageWeightage;
    }

    public double getSkillsetWeightage() {
        return skillsetWeightage;
    }

    public void setSkillsetWeightage(double skillsetWeightage) {
        this.skillsetWeightage = skillsetWeightage;
    }

	public String getJobIndustry() {
		return jobIndustry;
	}

	public void setJobIndustry(String jobIndustry) {
		this.jobIndustry = jobIndustry;
	}
    
}
