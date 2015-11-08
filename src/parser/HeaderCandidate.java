package parser;

/**
 * To store the headers of respective sections in the resume
 */
public class HeaderCandidate {
    
    private String type;
    private String text;
    private int score;
    private int lineNum;

    public HeaderCandidate(String type, String text, int score, int lineNum) {
        this.type = type;
        this.text = text;
        this.score = score;
        this.lineNum = lineNum;
    }
    
    public HeaderCandidate(HeaderCandidate original) {
        this.type = original.getType();
        this.text = original.getText();
        this.score = original.getScore();
        this.lineNum = original.getLineNum();
    }
    
    public String getType() {
        return type;
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
