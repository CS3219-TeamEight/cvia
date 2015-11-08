package resume;

/**
 * Store the work experience in a resume
 */
public class WorkExp {

    private String jobTitle;
    private double duration;

    public WorkExp(String jobTitle, double duration) {
        this.jobTitle = jobTitle;
        this.duration = duration;
    }

    public String getTitle() {
        return jobTitle;
    }

    public double getDuration() {
        return duration;
    }

}
