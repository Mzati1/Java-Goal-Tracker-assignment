import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Singleton implementation
public class goalManager implements Serializable {
    /*kept meeting an error regaurinding the serialVersionUID 
    changing so made it final and static to be shared acorss the program*/
    @Serial
    private static final long serialVersionUID = 8155735211665930334L;

    // Static member variable to hold the single instance
    private static goalManager instance;

    private final List<goal> goals;

    // Private constructor to prevent instantiation from outside the class
    private goalManager() {
        goals = new ArrayList<>();
    }

    // Static method to provide access to the single instance
    public static synchronized goalManager getInstance() {
        // Check if the instance is null, create a new instance
        if (instance == null) {
            instance = new goalManager();
        }
        return instance;
    }

    // Method to add a goal to the list of goals
    public void addGoal(goal goal) {
        goals.add(goal);
    }

    /* GETTERS */

    // Method to get list of goals
    public List<goal> getGoals() {
        return goals;
    }

    // get single goal
    public goal getSingleGoal(int i) {
        return goals.get(i);
    }

    // get the length of the goals list
    public int getLengthOfArray() {
        return goals.size();
    }

    // get subgoals list
    public List<subGoal> getSubgoals(int i) {
        // Access the sub-goals list of the goal at index i
        return getSingleGoal(i).getSubGoals();
    }

    // get sub Goal length
    public int getSubGoalsLength(int goalIndex) {
        goal mainGoal = getSingleGoal(goalIndex);
        List<subGoal> subGoals = mainGoal.getSubGoals();
        return subGoals.size();
    }

    // checking if it's empty
    public boolean checkIfEmpty() {
        return goals.isEmpty();
    }

    // Complete whole goal by marking all subgoals completed
    public void markAllSubGoalsAsCompleted() {
        for (goal g : goals) {
            List<subGoal> subGoals = g.getSubGoals();
            boolean allSubGoalsCompleted = true;
            for (subGoal sg : subGoals) {
                if (!sg.isCompleted()) {
                    allSubGoalsCompleted = false;
                    break;
                }
            }
            if (allSubGoalsCompleted) {
                g.setStatus(true);
            }
        }
    }

    // Delete single goal
    public void deleteGoal(int index) {
        if (index >= 0 && index < goals.size()) {
            goals.remove(index);
            // Save (serialize)
            GoalManagerSerializationHelper.saveGoalManager(this, Main.GOAL_MANAGER_FILE);
        } else {
        }
    }

    public List<goal> getCompletedGoals() {
        List<goal> completedGoals = new ArrayList<>();
        for (goal g : goals) {
            if (g.getGoalStatus()) {
                completedGoals.add(g);
            }
        }
        return completedGoals;
    }

    public void clearCompletedGoals() {
        // Remove all completed goals from the list

        //using Java iterator
        Iterator<goal> iterator = goals.iterator();
        while (iterator.hasNext()) {
            goal g = iterator.next();
            if (g.getGoalStatus()) {

                // Remove it from the list
                iterator.remove();

            }
        }
        // Save the updated list of goals
        GoalManagerSerializationHelper.saveGoalManager(this, Main.GOAL_MANAGER_FILE);
    }


    public double getCompletedGoals_Progress() {
        return getCompletedGoals().size();
    }

    public List<goal> getIncompletedGoals() {
        List<goal> incompletedGoals = new ArrayList<>();
        for (goal g : goals) {
            if (!g.getGoalStatus()) {
                incompletedGoals.add(g);
            }
        }
        return incompletedGoals;
    }

    public double getIncompletedGoals_Progress() {
        return getIncompletedGoals().size();
    }
}
