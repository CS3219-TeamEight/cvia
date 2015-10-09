package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;

public class ResumeParser {
    
    HeaderDictionary dictionary;
    ArrayList<String> candidates = new ArrayList<>();
    File resume;

    public ResumeParser(File resume) {
        this.resume = resume;
        dictionary = new HeaderDictionary();
    }
    
    public void findHeaderCandidates() {
        try {
            FileReader fr = new FileReader(resume);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ( (line = br.readLine()) != null) {
                evaluateCandidate(line.trim());
            }
            fr.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void evaluateCandidate(String line) {
        
    }

}
