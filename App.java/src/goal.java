import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date; 
import java.util.List;

public class goal implements Serializable {

    // Attributes
    private String name;
    private String description;
    private final List<subGoal> subGoals;
    private boolean status;
    private Date dueDate;

    // Constructors
    public goal() {
        this.subGoals = new ArrayList<>();
        this.status = false; // showing that it's incomplete
    }

    public goal(String name) {
        this.name = name;
        this.subGoals = new ArrayList<>();
        this.status = false; // showing that it's incomplete
    }

    // Setters
    public void setGoalName(String name) {
        this.name = name;
    }

    public void setSubGoals(subGoal setSubGoal) {
        this.subGoals.add(setSubGoal);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean setStatus(boolean value) {
        this.status = value;
        return value;
    }

    // Getters
    public String getGoalName() {
        return this.name;
    }

    public boolean getGoalStatus() {
        return status;
    }

    public String getDescription() {
        return this.description;
    }

    public List<subGoal> getSubGoals() {
        return this.subGoals;
    }

    public String getDueDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        return dateFormat.format(this.dueDate);
    }

    ////check individual checkbox
    public void checkIndividualSubgoal(int value)
    {
        subGoals.get(value).setStatus(true);
    }

    public Date getDueDateUnformatted() {
        return this.dueDate;
    }

    // Other methods
    public void isCompleted() {
        //set each subgoal setting it to true
        //then completing the whole goal
        for (subGoal subGoal : subGoals) {
            subGoal.setStatus(true); 
        }
        this.status = true; 
    }

    public void markAllSubGoalsAsCompleted() {
        //just setting all the subgoals to true
        for (subGoal subGoal : subGoals) {
            subGoal.setStatus(true); // Set each sub goal status to true
        }
    }


}
