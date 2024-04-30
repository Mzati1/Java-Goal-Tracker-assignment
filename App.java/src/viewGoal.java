import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class viewGoal {
    //attributes
    private JFrame frame;
    private JTextArea mainGoalTitle;
    private JTextArea mainGoalDescription;
    private JPanel subGoalsPanel;
    private JButton submitBtn;
    private JButton deleteBtn;
    private JButton saveBtn;
    private JButton completeBtn;
    private JButton backBtn;
    private final goalManager goalManager;
    private final int GoalID;

    //constructor
    public viewGoal(goalManager goalManager, int panelID) {
        this.goalManager = goalManager;
        GoalID = panelID;
        //passing functions in constructor for
        //easy editing and making of components
        initialize();
        setupComponents();
        styleComponents();
        setupListeners(panelID);
        setVisible(true);
    }

    private void initialize() {
        frame = new JFrame("View Goal");
        mainGoalTitle = new JTextArea(goalManager.getSingleGoal(GoalID).getGoalName());
        mainGoalDescription = new JTextArea(goalManager.getSingleGoal(GoalID).getDescription());
        subGoalsPanel = new JPanel();
        submitBtn = new JButton("Back to Dashboard");
        deleteBtn = new JButton("Delete Goal");
        backBtn = new JButton("Back");
        saveBtn = new JButton("Save");
        completeBtn = new JButton("Complete Goal");
    }

    private void setupComponents() {
        //frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        //main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel subGoalPanel = new JPanel(new GridLayout(3, 2));

        //subpanel
        subGoalPanel.add(new JLabel("<html><b> Title:</b></html>"));
        mainGoalTitle.setFont(new Font("Arial", Font.BOLD, 18));
        subGoalPanel.add(mainGoalTitle);
        subGoalPanel.add( new JLabel("<html><b> Description:</b></html>"));
        mainGoalDescription.setFont(new Font("Arial", Font.BOLD, 16));
        JScrollPane mainGoalDescScrollPane = new JScrollPane(mainGoalDescription);
        subGoalPanel.add(mainGoalDescScrollPane);

        //label
        JLabel dueDateLabel = new JLabel("  Due Date: " + goalManager.getSingleGoal(GoalID).getDueDate());
        dueDateLabel.setFont(new Font("Arial", Font.BOLD, 16));
        subGoalPanel.add(dueDateLabel);

        //button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(backBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(saveBtn);
        buttonPanel.add(completeBtn);

        mainGoalTitle.setLineWrap(true);
        mainGoalTitle.setWrapStyleWord(true);
        mainGoalDescription.setLineWrap(true);
        mainGoalDescription.setWrapStyleWord(true);

        //components adding to main panel
        mainPanel.add(subGoalPanel, BorderLayout.NORTH);
        mainPanel.add(subGoalsPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(mainPanel);

        //getting the single goal
        java.util.List<subGoal> subGoals = goalManager.getSingleGoal(GoalID).getSubGoals();
        //function to add checkboxes
        addSubGoalsCheckbox(subGoals);
    }

    private void styleComponents() {
        mainGoalTitle.setLineWrap(true);
        mainGoalTitle.setWrapStyleWord(true);
        mainGoalDescription.setLineWrap(true);
        mainGoalDescription.setWrapStyleWord(true);

        // Style buttons
        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        Color buttonTextColor = new Color(0, 0, 0);
        Color buttonBackgroundColor = new Color(64, 106, 213);
        Dimension buttonSize = new Dimension(180, 40);
        Insets buttonMargin = new Insets(10, 20, 10, 20);

        submitBtn.setFont(buttonFont);
        submitBtn.setForeground(buttonTextColor);
        submitBtn.setBackground(buttonBackgroundColor);
        submitBtn.setPreferredSize(buttonSize);
        submitBtn.setMargin(buttonMargin);

        deleteBtn.setFont(buttonFont);
        deleteBtn.setForeground(buttonTextColor);
        deleteBtn.setBackground(buttonBackgroundColor);
        deleteBtn.setPreferredSize(buttonSize);
        deleteBtn.setMargin(buttonMargin);

        saveBtn.setFont(buttonFont);
        saveBtn.setForeground(buttonTextColor);
        saveBtn.setBackground(buttonBackgroundColor);
        saveBtn.setPreferredSize(buttonSize);
        saveBtn.setMargin(buttonMargin);

        completeBtn.setFont(buttonFont);
        completeBtn.setForeground(buttonTextColor);
        completeBtn.setBackground(buttonBackgroundColor);
        completeBtn.setPreferredSize(buttonSize);
        completeBtn.setMargin(buttonMargin);

        backBtn.setFont(buttonFont);
        backBtn.setForeground(buttonTextColor);
        backBtn.setBackground(buttonBackgroundColor);
        backBtn.setPreferredSize(buttonSize);
        backBtn.setMargin(buttonMargin);
    }

    private void setupListeners(int panelID) {
        //listerners

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dashboard dashboard = new Dashboard(goalManager.getInstance());
                frame.dispose();
            }
        });

        deleteBtn.addActionListener(new ActionListener() {
            //deletes goal and saves the manager 
            //making changed perminant
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this goal?",
                        "Confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    goalManager.deleteGoal(GoalID);
                    frame.dispose();
                    Dashboard dashboard = new Dashboard(GoalManagerSerializationHelper.loadGoalManager(Main.GOAL_MANAGER_FILE));
                }
            }
        });

        saveBtn.addActionListener(new ActionListener() {
            //updates if any changes were made
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(frame, "Are you sure you want to Save this goal?",
                        "Confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    saveGoal(panelID);
                    Dashboard dashboard = new Dashboard(GoalManagerSerializationHelper.loadGoalManager(Main.GOAL_MANAGER_FILE));
                    frame.dispose();
                }
            }
        });

        backBtn.addActionListener(new ActionListener() {
            //updates if any changes were made
            @Override
            public void actionPerformed(ActionEvent e) {
                //go to dashboard
                goalManager goalManager = GoalManagerSerializationHelper.loadGoalManager(Main.GOAL_MANAGER_FILE);
                Dashboard dashboard = new Dashboard(goalManager);
                frame.dispose();
            }
        });

        completeBtn.addActionListener(new ActionListener() {
            //completes the goal( and sub goals)
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAndCompleteGoals(panelID);
            }
        });
    }

    private void setVisible(boolean value) {
        //make visible
        frame.setVisible(value);
    }


    /*FUNCTIONS */
    private void addSubGoalsCheckbox(java.util.List<subGoal> subGoals) {
        for (subGoal subGoal : subGoals) {
            JCheckBox checkBox = new JCheckBox("<html><b>" + subGoal.getSubGoalName() +  "</b></html>");
            checkBox.setFont(new Font("Arial", Font.PLAIN, 14));
            subGoalsPanel.add(checkBox);
        }
        frame.revalidate();
        frame.repaint();
    }

    public void saveGoal(int panelID) {

        goal mainGoal = goalManager.getSingleGoal(panelID);
        String mainGoalName = mainGoalTitle.getText();
        String mainGoalDescriptionText = mainGoalDescription.getText();
        mainGoal.setGoalName(mainGoalName);
        mainGoal.setDescription(mainGoalDescriptionText);
        //save goal
        GoalManagerSerializationHelper.saveGoalManager(goalManager, Main.GOAL_MANAGER_FILE);
        frame.dispose();
    }

    private void checkAndCompleteGoals(int panelID) {
        // Get all components in the subGoalsPanel
        Component[] components = subGoalsPanel.getComponents();

        // Flag to track if all sub-goals are completed
        boolean allSubGoalsCompleted = true;

        // Iterate through each component
        for (Component component : components) {
            if (component instanceof JCheckBox checkBox) {
                // If any checkbox is not selected, set the flag to false and break the loop
                if (!checkBox.isSelected()) {
                    allSubGoalsCompleted = false;
                    break;
                }
            }
        }

        // If all sub-goals are completed, update the main goal and complete the goal
        if (allSubGoalsCompleted) {
            goal mainGoal = goalManager.getSingleGoal(panelID);

            // Mark all sub-goals as completed
            mainGoal.markAllSubGoalsAsCompleted();

            // Set the main goal status to true
            mainGoal.setStatus(true);

            // Save the updated goal manager
            GoalManagerSerializationHelper.saveGoalManager(goalManager, Main.GOAL_MANAGER_FILE);

            // Show completion message
            JOptionPane.showMessageDialog(frame, "You have completed this Goal!");

            // Redirect back to the dashboard
            // Close the view goal window
            frame.dispose();
            Dashboard dashboard = new Dashboard(GoalManagerSerializationHelper.loadGoalManager(Main.GOAL_MANAGER_FILE));
        } else {
            // Display message indicating that all sub-goals must be checked
            JOptionPane.showMessageDialog(frame, "Please complete all activities before completing the goal.");
        }
    }

}
