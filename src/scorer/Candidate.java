package scorer;

public class Candidate implements Comparable<Candidate> {
    
    private String id;
    private double score;
    
    public Candidate(String id, double score) {
        this.id = id;
        this.score = score;
    }
    
    public String getId() {
        return id;
    }
    
    public double getScore() {
        return score;
    }
    
    @Override
    public int compareTo(Candidate obj) {
        if (this.score == obj.getScore()) {
            return 0;
        } else if (this.score > obj.getScore()) {
            return 1;
        } else return -1;
    }

}
