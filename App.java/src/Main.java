/**
 * This is a goal tracker application developed by Group 4.
 * It allows users to set, track, and manage their goals effectively.
 * The application provides features such as creating new goals, setting deadlines,
 * tracking progress, and receiving reminders to help users stay focused on their objectives.
 * Please check the README.md File for more information regarding this project!
 *
 * @author Jesse Mbekeani
 * @author Jacqueline Zimba
 * @author Chiposa Chiomba
 * @author Anita Gwede
 * @author Shameel Sheriff
 * @author George Kalua
 * @author Blessings Mte
 * @author Mzati Tembo
 */


import javax.swing.*;

public class Main {

    public static final String GOAL_MANAGER_FILE = "goalManager.ser";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            //start notifications
            Notification.main(null);

            try {
                // Load the goal manager from serialization
                goalManager goalManager = GoalManagerSerializationHelper.loadGoalManager(GOAL_MANAGER_FILE);
                // If the file doesn't exist or there's an error loading, create a new instance
                if (goalManager == null) {
                    goalManager = goalManager.getInstance();
                }
                // Create and display the dashboard
                Dashboard dashboard = new Dashboard(goalManager);
            } catch (Exception e) {
                // Handle any exceptions that occur during loading or initialization
                e.printStackTrace();
            }
        });
    }
}
