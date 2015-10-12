package qualification;

public class WorkExp extends Qualification {

    private String position;
    
    public WorkExp(String position, String companyName, double duration) {
        this.position = position;
        this.name = companyName;
        this.duration = duration;
    }
    
    public String getPosition() {
        return position;
    }
    
    public String getCompany() {
        return name;
    }
    
    public double getDuration() {
        return duration;
    }

}
