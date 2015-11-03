package qualification;

public class Education {
    
    private double duration;
    private double cap;
    private boolean graduated;
    private String degree;
    
    public Education(double duration, double cap, boolean graduated, String degree) {
        this.duration = duration;
        this.cap = cap;
        this.graduated = graduated;
        this.degree = degree;
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
    
    public String getDegree() {
        return degree;
    }
}