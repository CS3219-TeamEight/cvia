package qualification;

import java.util.ArrayList;

public class Education extends Qualification {
    
    private double duration;
    private double cap;
    
    public Education(double duration, double cap) {
        this.duration = duration;
        this.cap = cap;
    }
    
    public double getCap() {
        return cap;
    }
    
    public double getDuration() {
        return duration;
    }
    
    private int calculateScore() {
        int init = 0;
        return init;
    }

}