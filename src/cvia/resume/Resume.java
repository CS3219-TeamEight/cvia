package cvia.resume;

public class Resume {
    private String resumePath;
    private String parsedContents;
    private double score;

    public Resume(String resumePath) {
        this.resumePath = resumePath;
    }

    public String getResumePath() {
        return resumePath;
    }

    public void setResumePath(String resumePath) {
        this.resumePath = resumePath;
    }

    public String getParsedContents() {
        return parsedContents;
    }

    public void setParsedContents(String parsedContents) {
        this.parsedContents = parsedContents;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
