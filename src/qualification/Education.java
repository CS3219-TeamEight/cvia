package qualification;

import java.util.ArrayList;

public class Education extends Qualification {
    
    private String degree;
    private String dept;
    private int duration;
    private ArrayList<Module> modules = new ArrayList<>();
    
    public Education(String instituteName, String degree, String dept, int duration) {
        this.name = instituteName;
        this.degree = degree;
        this.dept = dept;
        this.duration = duration;
        //this.score = calculateScore();
    }
    
    public String getInstitute() {
        return name;
    }
    
    public String getDegree() {
        return degree;
    }
    
    public String getDept() {
        return dept;
    }
    
    public int getDuration() {
        return duration;
    }
    
    private int calculateScore() {
        int init = 0;
        for(Module mod : modules) {
            init += mod.getScore();
        }
        return init;
    }

}