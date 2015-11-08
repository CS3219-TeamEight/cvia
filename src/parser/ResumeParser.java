package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ResumeParser {

    private Dictionary headerDictionary;

    private LinkedHashMap<String, ArrayList<HeaderCandidate>> candidates =
        new LinkedHashMap<String, ArrayList<HeaderCandidate>>();
    private ArrayList<String> lines = new ArrayList<String>();
    private ArrayList<HeaderCandidate> headers = new ArrayList<HeaderCandidate>();
    private ArrayList<Section> sections = new ArrayList<Section>();
    private File resume;

    private static final String FILENAME_DICTIONARY_HEADER = "./HeadingDictionary.txt";

    public ResumeParser() {

    }

    //To initialize the parsing of the resume
    public void initialize(File resume) {
        this.resume = resume;
        headerDictionary = new Dictionary(FILENAME_DICTIONARY_HEADER);
        findHeaderCandidates();
        determineHeaders();
        extractSections();
    }

    public ArrayList<HeaderCandidate> getHeaders() {
        return headers;
    }

    public ArrayList<Section> getSections() {
        return sections;
    }

    //To indentify the possible section headers
    public void findHeaderCandidates() {
        try {
            FileReader fr = new FileReader(resume);
            BufferedReader br = new BufferedReader(fr);
            String line;
            int lineNum = 0;
            while ((line = br.readLine()) != null) {
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

    //To evaluate each possible section header present
    private void evaluateCandidate(String line, int lineNum) {
        if (line.endsWith("."))
            return;
        String headerType = headerDictionary.matches(line.toLowerCase());
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
                candidateList = new ArrayList<HeaderCandidate>();
            }
            candidateList.add(candidate);
            candidates.put(headerType, candidateList);
        }
    }

    //To identify the correct headers present
    public void determineHeaders() {
        Iterator itr = candidates.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry candidateSet = (Map.Entry) itr.next();
            HeaderCandidate header = new HeaderCandidate("", "", 0, 0); // dummy
            for (HeaderCandidate candidate : (ArrayList<HeaderCandidate>) candidateSet.getValue()) {
                if (candidate.getScore() > header.getScore()) {
                    header = new HeaderCandidate(candidate);
                }
            }

            headers.add(header);
        }
    }

    //To extract the sections under each header
    public void extractSections() {
        for (int i = 0; i < headers.size(); i++) {
            HeaderCandidate header = headers.get(i);
            int startLine = header.getLineNum();
            int endLine;
            if (i == headers.size() - 1) {
                endLine = lines.size() - 1;
            } else {
                endLine = headers.get(i + 1).getLineNum() - 1;
            }

            ArrayList<String> sectionLines = new ArrayList<String>();
            for (int j = startLine; j <= endLine; j++) {
                sectionLines.add(lines.get(j));
            }

            Section section =
                new Section(header.getType(), sectionLines, (endLine - startLine + 1));
            sections.add(section);

        }
    }

    //To print out all information in the section
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
            Map.Entry candidateSet = (Map.Entry) itr.next();
            System.out.println("Type: " + candidateSet.getKey());
            for (HeaderCandidate candidate : (ArrayList<HeaderCandidate>) candidateSet.getValue()) {
                System.out.println(candidate.getText() + " at line " + candidate.getLineNum());
            }
            System.out.println("");
        }
    }

}
