package parser;

import java.util.ArrayList;

public class HeaderDictionary {
    
    private ArrayList<ArrayList<String>> dictionary = new ArrayList<>();
    

    public HeaderDictionary() {
        
    }
    
    public boolean contains(String headerCand, int index) {
        return dictionary.get(index).contains(headerCand);
    }

}
