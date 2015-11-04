package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Dictionary {
    
    private HashMap<String, String> dictionary = new HashMap<String, String>();
    private ArrayList<String> types = new ArrayList<String>();

    public Dictionary(String filename) {
        populate(filename);
    }
    
    public String matches(String line) {
        return dictionary.get(line);
    }
    
    public String contains(String line) {
        ArrayList<String> keywords = new ArrayList<>(dictionary.keySet());
        String position = "";
        boolean found = false;
        for (String keyword : keywords) {
            if (line.contains(keyword)) {
                position = keyword;
                found = true;
                break;
            }
        }
        if (found) {
            return dictionary.get(position);
        } else {
            return "UNKNOWN";
        }
    }
    
    public String containsSingle(String line) {
        ArrayList<String> keywords = new ArrayList<>(dictionary.keySet());
        for (String keyword : keywords) {
            if (line.contains(keyword)) {
                return line;
            }
        }
        return "UNKNOWN";
    }
    
    private void populate(String filename){
        BufferedReader br = null;
        try {

            String sCurrentLine;
            String type = null;
            
            br = new BufferedReader(new FileReader(filename));

            while ((sCurrentLine = br.readLine()) != null) {
                if (sCurrentLine.equals(";")){
                    sCurrentLine = br.readLine();
                    type = sCurrentLine;
                    types.add(type);
                }
                else{
                    dictionary.put(sCurrentLine, type);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
