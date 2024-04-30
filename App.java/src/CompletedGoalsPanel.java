import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class CompletedGoalsPanel {

    private final JFrame frame;
    private final JPanel mainPanel;
    private final List<goal> completedGoals;
    private final goalManager goalManager;

    public CompletedGoalsPanel(goalManager goalManager) {
        this.goalManager = goalManager;
        this.completedGoals = goalManager.getCompletedGoals();
        this.frame = new JFrame("Completed Goals");
        this.mainPanel = new JPanel();
        initializeUI();
    }

    private void initializeUI() {
        mainPanel.setLayout(new GridLayout(0, 1, 0, 10));

        if (completedGoals.isEmpty()) {
            // If no completed goals, display centered message
            JLabel messageLabel = new JLabel("Complete some Goals First");
            messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
            messageLabel.setHorizontalAlignment(JLabel.CENTER);

            JPanel messagePanel = new JPanel(new BorderLayout());
            messagePanel.add(messageLabel, BorderLayout.CENTER);

            mainPanel.add(messagePanel);
        } else {
            // Iterate through the list of completed goals
            for (goal completedGoal : completedGoals) {
                // Create a panel for each completed goal
                JPanel goalPanel = createGoalPanel(completedGoal);
                mainPanel.add(goalPanel);
            }
        }

        // Add a separate panel for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 30));
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.addActionListener(e -> {
            frame.dispose();
            // Assuming Dashboard is the class for the dashboard window
            Dashboard dashboard = new Dashboard(goalManager);
        });
        buttonPanel.add(backButton);

        // Clear button
        JButton clearButton = new JButton("Clear");
        clearButton.setPreferredSize(new Dimension(100, 30)); // Set button size
        clearButton.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font
        clearButton.addActionListener(e -> {
            // Clear all completed goals
            goalManager.clearCompletedGoals();
            // Refresh the UI
            mainPanel.removeAll(); // Remove all goal panels
            initializeUI(); // Reinitialize UI to reflect changes
        });
        buttonPanel.add(clearButton);

        // Add the main panel and button panel to the frame
        frame.setLayout(new BorderLayout());
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Set frame properties
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //function to create the completed Goals panel
    private JPanel createGoalPanel(goal completedGoal) {
        JPanel goalPanel = new JPanel(new BorderLayout());
        goalPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        goalPanel.setBackground(Color.WHITE);
        goalPanel.setPreferredSize(new Dimension(300, 100));

        // Title label
        JLabel titleLabel = new JLabel(completedGoal.getGoalName());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBorder(new EmptyBorder(10, 10, 5, 10));
        goalPanel.add(titleLabel, BorderLayout.NORTH);

        // Status label
        JLabel statusLabel = new JLabel("Status: Completed");
        statusLabel.setBorder(new EmptyBorder(0, 10, 10, 10));
        goalPanel.add(statusLabel, BorderLayout.CENTER);

        // Add hover effect
        goalPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                goalPanel.setBackground(new Color(220, 255, 220));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                goalPanel.setBackground(Color.WHITE);
            }
        });

        return goalPanel;
    }
}
