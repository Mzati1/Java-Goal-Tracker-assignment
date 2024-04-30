import java.io.Serializable;

public class subGoal implements Serializable {
    // attributes
    private String name;
    private boolean status;

    // constructor
    public subGoal() {

    }

    // param constructor
    public subGoal(String name) {
        this.name = name;
        this.status = false;
    }

    // getters and setters
    // getters

    public String getSubGoalName() {
        return name;
    }

    public boolean getSubGoalStatus() {
        return status;
    }

    public boolean isCompleted() {
        return status;
    }

    // setter for the status

    public void setStatus(boolean value) {
        this.status = value;
    }

    public void setSubGoalName(String text) {
        this.name = text;
    }

}
