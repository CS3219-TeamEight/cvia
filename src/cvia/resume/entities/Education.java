package cvia.resume.entities;

/**
 * Store the Education information of a resume
 */
public class Education {

    private double duration;
    private boolean graduated;
    private String degree;
    private String field;

    public Education(double duration, boolean graduated, String degree, String field) {
        this.duration = duration;
        this.graduated = graduated;
        this.degree = degree;
        this.field = field;
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

    public String getField() {
        return field;
    }
}
