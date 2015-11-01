package qualification;

public class Education extends Qualification {
    
    private double duration;
    private double cap;
    private boolean graduated;
    
    public Education(double duration, double cap, boolean graduated) {
        this.duration = duration;
        this.cap = cap;
        this.graduated = graduated;
    }
    
    public double getCap() {
        return cap;
    }
    
    public double getDuration() {
        return duration;
    }
    
    public boolean isGraduate() {
        return graduated;
    }
    
    private int calculateScore() {
        int init = 0;
        return init;
    }

}