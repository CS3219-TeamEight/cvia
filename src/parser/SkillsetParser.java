package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import job.JobDesc;

public class SkillsetParser {

	JobDesc job;
	
	private ArrayList<String> skillsets = new ArrayList<String>();
	private File resume;
	
	public SkillsetParser() {
		
	}
	
	public void findSkillsets (File resume) {
		this.resume = resume;
		determineSkillsets();
	}

	private void determineSkillsets() {
		try {
			FileReader fr = new FileReader(resume);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null) {
            	for (String skill : job.getskillSets()) {
            		if (Pattern.compile(skill).matcher(line).find())
            			skillsets.add(skill);
            	}
            }
            fr.close();
            br.close();
		} catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public ArrayList<String> getSkillsets () {
		return skillsets;
	}
	
}
