package parser;

import java.util.ArrayList;

public class Section {
    
    String type;
    ArrayList<String> lines;

    public Section(String type, ArrayList<String> lines) {
        this.type = type;
        this.lines = new ArrayList<String>(lines);
    }
    
    public String getType() {
        return type;
    }
    
    public ArrayList<String> getLines() {
        return lines;
    }

}
