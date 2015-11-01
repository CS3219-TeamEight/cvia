package parser;

import java.util.ArrayList;

public class Section {
    
    private String type;
    private ArrayList<String> lines;
    private int lineCount;

    public Section(String type, ArrayList<String> lines, int num) {
        this.type = type;
        this.lines = new ArrayList<String>(lines);
        this.lineCount = num; // for efficiency, to avoid having to run lines.size()
    }
    
    public String getType() {
        return type;
    }
    
    public ArrayList<String> getLines() {
        return lines;
    }
    
    public int getLineCount() {
        return lineCount;
    }

}
