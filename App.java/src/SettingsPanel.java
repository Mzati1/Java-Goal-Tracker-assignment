import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPanel {

    public goalManager goalManager;
    public SettingsPanel() {
        //get the instance(Blank instance)
        goalManager = goalManager.getInstance();


        JFrame frame = new JFrame("Settings");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(0, 1, 10, 10));
        frame.setPreferredSize(new Dimension(400, 300));

        // Reset app button
        JButton resetAppButton = new JButton("Reset App");
        resetAppButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int choice = JOptionPane.showConfirmDialog(frame, "Are you sure you want to Reset the App?",
                        "Confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    //this should clear everything by creating a new goal manager instance
                    goalManager goalManagerNew = goalManager.getInstance();
                    GoalManagerSerializationHelper.saveGoalManager(goalManagerNew, Main.GOAL_MANAGER_FILE);
                    frame.dispose();
                    Dashboard dashboard = new Dashboard(GoalManagerSerializationHelper.loadGoalManager(Main.GOAL_MANAGER_FILE));
                }
            }
        });
        frame.add(resetAppButton);

        // Back button
        JButton backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Go back to the dashboard
                Dashboard dashboard = new Dashboard(GoalManagerSerializationHelper.loadGoalManager(Main.GOAL_MANAGER_FILE));
                frame.dispose();
            }
        });
        frame.add(backButton);


        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Apply theme based on initial settings
    }

}
