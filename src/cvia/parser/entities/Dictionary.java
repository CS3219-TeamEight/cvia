package cvia.parser.entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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
        ArrayList<String> words = new ArrayList<String>(Arrays.asList(line.split("[,\\s]+")));

        int size = words.size();

        for (int i = size; i > 0; i--) {
            for (int j = 0; (j + i) <= size; j++) {
                String sub = "";
                for (int k = j; k < j + i; k++) {
                    sub = sub.concat(words.get(k)).concat(" ");
                }
                sub = sub.substring(0, sub.length() - 1);

                if (dictionary.containsKey(sub)) {
                    //System.out.println("Found " + dictionary.get(sub));
                    return dictionary.get(sub);
                }
            }
        }
        //System.out.println("Not found");
        return "UNKNOWN";
    }

    public ArrayList<String> containsMultiple(String line) {
        line = line.toLowerCase();
        ArrayList<String> words = new ArrayList<String>(Arrays.asList(line.split("[,\\s]+")));
        ArrayList<String> result = new ArrayList<String>();
        int size = words.size();

        for (int i = size; i > 0; i--) {
            for (int j = 0; (j + i) <= size; j++) {
                String sub = "";
                for (int k = j; k < j + i; k++) {
                    sub = sub.concat(words.get(k)).concat(" ");
                }
                sub = sub.substring(0, sub.length() - 1);
                if (dictionary.containsKey(sub)) {
                    //System.out.println("Found " + dictionary.get(sub));
                    result.add(sub);
                }
            }
        }
        //System.out.println("Not found");
        return result;
    }

    public String containsSingle(String line) {

        if (dictionary.containsKey(line)) {
            return line;
        }
        return "UNKNOWN";
    }

    private void populate(String filename) {
        BufferedReader br = null;
        try {

            String sCurrentLine;
            String type = null;

            br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream('/' + filename)));

            while ((sCurrentLine = br.readLine()) != null) {
                if (sCurrentLine.equals(";")) {
                    sCurrentLine = br.readLine();
                    type = sCurrentLine;
                    types.add(type);
                } else {
                    dictionary.put(sCurrentLine, type);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
