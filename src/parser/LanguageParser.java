package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import job.JobDesc;

public class LanguageParser {

	JobDesc job;

	private ArrayList<String> language = new ArrayList<String>();
	private File resume;

	public LanguageParser() {	
	}

	public void findLanguage (File resume) {
		this.resume = resume;
		determineLanguage();
	}

	private void determineLanguage() {
		try {
			FileReader fr = new FileReader(resume);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null) {
				for (String lang : job.getskillSets()) {
					if (Pattern.compile(lang).matcher(line).find())
						language.add(lang);
				}
			}
			fr.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getLanguage() {
		return language;
	}
}
