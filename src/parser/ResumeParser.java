package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ResumeParser {
    
    HeaderDictionary dictionary;
    HashMap<String, ArrayList<HeaderCandidate>> candidates = new HashMap<>();
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
            int lineNum = 0;
            while ( (line = br.readLine()) != null) {
                lineNum++;
                evaluateCandidate(line.trim(), lineNum);
            }
            fr.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void evaluateCandidate(String line, int lineNum) {
        int score = 0;
        if (line.endsWith(".")) return;
        String headerType = dictionary.contains(line);
        if (headerType == null) {
            return;
        } else {
            ArrayList<String> words = new ArrayList<String>(Arrays.asList(line.split(" ")));
            boolean isCapitalized = true;
            for (String word : words) {
                char first = word.charAt(0);
                if (Character.isLowerCase(first)) {
                    isCapitalized = false;
                    break;
                }
            }
            
            if (isCapitalized) {
                HeaderCandidate candidate = new HeaderCandidate(line, lineNum);
                ArrayList<HeaderCandidate> candidateList;
                if (candidates.containsKey(headerType)) {
                    candidateList = candidates.get(headerType);
                } else {
                    candidateList = new ArrayList<>();
                }
                candidateList.add(candidate);
                candidates.put(headerType, candidateList);
            }
        }
    }
    
    // temporary method for bugfix
    public void printAllCandidates() {
        Iterator itr = candidates.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry candidateSet = (Map.Entry)itr.next();
            System.out.println("Type: " + candidateSet.getKey());
            for (HeaderCandidate candidate : (ArrayList<HeaderCandidate>)candidateSet.getValue()) {
                System.out.println(candidate);
            }
            System.out.println("");
        }
    }

}
