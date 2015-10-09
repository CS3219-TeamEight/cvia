package parser;

public class HeaderCandidate {
    
    private String text;
    private int score;
    private int lineNum;

    public HeaderCandidate(String text, int lineNum) {
        this.text = text;
        this.lineNum = lineNum;
    }
    
    public String getText() {
        return text;
    }
    
    public int getScore() {
        return score;
    }
    
    public int getLineNum() {
        return lineNum;
    }

}
