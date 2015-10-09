package qualification;

import java.util.ArrayList;

public class Education extends Qualification {
    
    private ArrayList<Module> modules = new ArrayList<>();
    
    public Education(String instituteName, ArrayList<Module> modules) {
        this.name = instituteName;
        this.modules = new ArrayList<>(modules);
        this.score = calculateScore();
    }
    
    private int calculateScore() {
        int init = 0;
        for(Module mod : modules) {
            init += mod.getScore();
        }
        return init;
    }

}