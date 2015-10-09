package parser;

import java.util.HashMap;

public class HeaderDictionary {
    
    private HashMap<String, type> dictionary = new HashMap<>();
    
    public enum type {
        WORK, EDUCATION, SKILL
    }

    public HeaderDictionary() {
        
    }
    
    public type contains(String headerCand) {
        return dictionary.get(headerCand);
    }
    
    

}
