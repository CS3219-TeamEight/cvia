package parser;

public class Duration {

    private double duration;
    private int startYear;
    private int startMonth;
    private boolean ongoing;
    
    public Duration(double duration, int startYear, int startMonth, boolean ongoing) {
        this.duration = duration;
        this.startYear = startYear;
        this.startMonth = startMonth;
        this.ongoing = ongoing;
    }
    
    public double getDuration() {
        return duration;
    }
    
    public int getStartYear() {
        return startYear;
    }
    
    public int getStartMonth() {
        return startMonth;
    }
    
    public boolean isOngoing() {
        return ongoing;
    }

}
