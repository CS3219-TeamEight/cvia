package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.Map;

public class ResumeParser {
    
    HeaderDictionary dictionary;
    LinkedHashMap<String, ArrayList<HeaderCandidate>> candidates = new LinkedHashMap<>();
    ArrayList<String> lines = new ArrayList<>();
    ArrayList<HeaderCandidate> headers = new ArrayList<>();
    ArrayList<Section> sections = new ArrayList<>();
    File resume;

    public ResumeParser(File resume) {
        this.resume = resume;
        dictionary = new HeaderDictionary();
        findHeaderCandidates();
        determineHeaders();
        extractSections();
    }
    
    public void findHeaderCandidates() {
        try {
            FileReader fr = new FileReader(resume);
            BufferedReader br = new BufferedReader(fr);
            String line;
            int lineNum = 0;
            while ( (line = br.readLine()) != null) {
                lineNum++;
                lines.add(line);
                evaluateCandidate(line.trim(), lineNum);
            }
            fr.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void evaluateCandidate(String line, int lineNum) {
        if (line.endsWith(".")) return;
        String headerType = dictionary.contains(line.toLowerCase());
        if (headerType == null) {
            return;
        } else {
            int score = 0;
            ArrayList<String> words = new ArrayList<String>(Arrays.asList(line.split(" ")));
            
            char headChar = line.charAt(0);
            if (Character.isUpperCase(headChar)) {
                score += 5;
            }
            
            boolean isCapitalized = true;
            for (String word : words) {
                char first = word.charAt(0);
                if (Character.isLowerCase(first)) {
                    isCapitalized = false;
                    break;
                }
            }
            if (isCapitalized) {
                score += 5;
            }
            
            HeaderCandidate candidate = new HeaderCandidate(headerType, line, score, lineNum);
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
    
    public void determineHeaders() {
        Iterator itr = candidates.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry candidateSet = (Map.Entry)itr.next();
            HeaderCandidate header = new HeaderCandidate("", "", 0, 0); // dummy
            for (HeaderCandidate candidate : (ArrayList<HeaderCandidate>)candidateSet.getValue()) {
                if (candidate.getScore() > header.getScore()) {
                    header = new HeaderCandidate(candidate);
                }
            }
            
            headers.add(header);
        }
    }
    
    public void extractSections(){
        for (int i = 0; i < headers.size(); i++) {
            HeaderCandidate header = headers.get(i);
            int startLine = header.getLineNum();
            int endLine;
            if (i == headers.size()-1) {
                endLine = lines.size() - 1;
            } else {
                endLine = headers.get(i+1).getLineNum() - 1;
            }
            
            ArrayList<String> sectionLines = new ArrayList<>();
            for (int j = startLine; j < endLine; j++) {
                sectionLines.add(lines.get(j));
            }
            
            Section section = new Section(header.getType(), sectionLines);
            sections.add(section);
            
        }
    }
    
    public void printAllSections() {
        for (Section section : sections) {
            System.out.println("Type: " + section.getType());
            ArrayList<String> lines = section.getLines();
            for (String line : lines) {
                System.out.println(line);
            }
            System.out.println("");
        }
    }
    
    // temporary method for bugfix
    public void printAllCandidates() {
        Iterator itr = candidates.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry candidateSet = (Map.Entry)itr.next();
            System.out.println("Type: " + candidateSet.getKey());
            for (HeaderCandidate candidate : (ArrayList<HeaderCandidate>)candidateSet.getValue()) {
                System.out.println(candidate.getText() + " at line " + candidate.getLineNum());
            }
            System.out.println("");
        }
    }

}
