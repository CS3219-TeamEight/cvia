package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class HeaderDictionary {
    
    private HashMap<String, String> dictionary = new HashMap<String, String>();
    private ArrayList<String> types = new ArrayList<String>();

    public HeaderDictionary() {
        populate();
    }
    
    public String contains(String headerCand) {
        return dictionary.get(headerCand);
    }
    
    private void populate(){
    	BufferedReader br = null;
    	try {

			String sCurrentLine;
			String type = null;
			
			br = new BufferedReader(new FileReader("./HeadingDictionary.txt"));

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
