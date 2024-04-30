import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Properties;

public class createGoal {
    // Attributes
    private JFrame frame;
    private JTextArea mainGoalDescription, mainGoalTitle;
    private JPanel subGoalsPanel;
    private JButton submitBtn;
    private JButton addSubGoalBtn;
    private JButton removeSubGoalBtn;
    private JButton backBtn;
    private final goalManager goalManager;
    private JDatePicker startDatePicker;
    private UtilDateModel dueDate;

    // Constructor with GoalManager parameter
    public createGoal(goalManager goalManager) {
        // Functions called at creation
        this.goalManager = goalManager;
        initialize();
        setupComponents();
        styleComponents();
        setupListeners();
        setVisible(true);
    }

    private void initialize() {
        // Frame and goal components
        frame = new JFrame("Create Goal");
        mainGoalTitle = new JTextArea();
        mainGoalDescription = new JTextArea();
        mainGoalDescription.setColumns(2);
        subGoalsPanel = new JPanel(new GridLayout(0, 1));
        submitBtn = new JButton("Submit");
        addSubGoalBtn = new JButton("Add Task");
        removeSubGoalBtn = new JButton("Remove Task");
        backBtn = new JButton("Back");

        // Initialize the date picker
        dueDate = new UtilDateModel();

        // Empty properties
        Properties properties = new Properties();
        JDatePanelImpl dueDatePanel = new JDatePanelImpl(dueDate, properties);

        // DateLabelFormatter is used to format the labels of the date picker
        startDatePicker = new JDatePickerImpl(dueDatePanel, new DateLabelFormatter());
    }

    private void setupComponents() {
        // Main frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);

        // Panels
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel for goal title and description
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.setBackground(Color.WHITE);

        // Title
        JLabel titleLabel = new JLabel("<html><span style='font-size: 16pt; font-weight: bold;'>  Title: </span></html>");
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        inputPanel.add(titleLabel);
        mainGoalTitle.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(mainGoalTitle);

        // Description
        JLabel descriptionLabel = new JLabel("<html><span style='font-size: 16pt; font-weight: bold;'>  Description: </span></html>");
        descriptionLabel.setHorizontalAlignment(SwingConstants.LEFT);
        inputPanel.add(descriptionLabel);
        mainGoalDescription.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(mainGoalDescription);

        // Start Date
        JLabel startDateLabel = new JLabel("<html><span style='font-size: 16pt; font-weight: bold;'>  Due Date: </span></html>");
        startDateLabel.setHorizontalAlignment(SwingConstants.LEFT);
        inputPanel.add(startDateLabel);
        inputPanel.add((Component) startDatePicker);

        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // Panel to hold sub-goal fields and buttons
        JPanel subGoalsControlPanel = new JPanel(new BorderLayout());
        subGoalsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), "<html><span style='font-size: 16pt; font-weight: bold;'>  Tasks: </span></html>"));
        subGoalsPanel.setLayout(new GridLayout(0, 1));

        // Add sub-goal panel to the sub-goal container or control panel
        subGoalsControlPanel.add(subGoalsPanel, BorderLayout.CENTER);

        // Panel to hold sub-goal buttons
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonsPanel.add(backBtn);
        buttonsPanel.add(addSubGoalBtn);
        buttonsPanel.add(removeSubGoalBtn);
        subGoalsControlPanel.add(buttonsPanel, BorderLayout.SOUTH);

        // Adding panels to main panel
        mainPanel.add(subGoalsControlPanel, BorderLayout.CENTER);
        submitBtn.setPreferredSize(new Dimension(100, 40));
        mainPanel.add(submitBtn, BorderLayout.SOUTH);

        // Add main panel to frame
        frame.add(mainPanel);
    }

    private void styleComponents() {
        // Add borders to mainGoalTitle and mainGoalDescription
        mainGoalTitle.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Add a simple gray line border
        mainGoalDescription.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Add a simple gray line border

        // Set line wrapping and word wrapping
        mainGoalTitle.setLineWrap(true);
        mainGoalTitle.setWrapStyleWord(true);
        mainGoalDescription.setLineWrap(true);
        mainGoalDescription.setWrapStyleWord(true);

        mainGoalTitle.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Add a simple gray line border
        mainGoalDescription.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Add a simple gray line border

        // Set line wrapping and word wrapping
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

        addSubGoalBtn.setFont(buttonFont);
        addSubGoalBtn.setForeground(buttonTextColor);
        addSubGoalBtn.setBackground(buttonBackgroundColor);
        addSubGoalBtn.setPreferredSize(buttonSize);
        addSubGoalBtn.setMargin(buttonMargin);

        removeSubGoalBtn.setFont(buttonFont);
        removeSubGoalBtn.setForeground(buttonTextColor);
        removeSubGoalBtn.setBackground(buttonBackgroundColor);
        removeSubGoalBtn.setPreferredSize(buttonSize);
        removeSubGoalBtn.setMargin(buttonMargin);

        backBtn.setFont(buttonFont);
        backBtn.setForeground(buttonTextColor);
        backBtn.setBackground(buttonBackgroundColor);
        backBtn.setPreferredSize(buttonSize);
        backBtn.setMargin(buttonMargin);
    }


    private void setupListeners() {
        // Action listeners for the sub-goal buttons
        addSubGoalBtn.addActionListener(e -> addSubGoalField());

        removeSubGoalBtn.addActionListener(e -> removeSubGoalField());

        // Action listener for submit button
        submitBtn.addActionListener(e -> {
            // Exception listener to check if input on main goals is empty
            try {
                // Retrieve user input from text fields
                String mainGoalName = mainGoalTitle.getText();
                String mainGoalDescriptionText = mainGoalDescription.getText();

                // Convert the dueDate value into a Date
                Date dueDate = (Date) startDatePicker.getModel().getValue();

                // If statement to check if Goal details are empty
                // If yes, throw error
                if (mainGoalName.isEmpty() || mainGoalDescriptionText.isEmpty() || dueDate == null) {
                    throw new IllegalArgumentException("Please fill in all fields.");
                }

                // Create main goal instance
                goal mainGoal = new goal();

                // Set main goal data
                mainGoal.setGoalName(mainGoalName);
                mainGoal.setDescription(mainGoalDescriptionText);

                // before submitting date should add error handlers

                // Check if the due date is not today or before today
                Date today = new Date();
                if (dueDate == null || dueDate.before(today)) {
                    throw new IllegalArgumentException("Due date must be today or a future date.");
                }
                mainGoal.setDueDate(dueDate);

                // Iterate through each sub-goal to get input
                for (int i = 0; i < subGoalsPanel.getComponentCount(); i++) {
                    JPanel subGoalPanel = (JPanel) subGoalsPanel.getComponent(i);
                    JTextField subGoalTitleField = (JTextField) subGoalPanel.getComponent(1);
                    String subGoalTitleText = subGoalTitleField.getText();
                    if (!subGoalTitleText.isEmpty()) {

                        // Exception handler to check if task is not empty
                        // Create a new instance of task for each panel
                        subGoal subGoal = new subGoal();
                        subGoal.setSubGoalName(subGoalTitleText);
                        subGoal.setStatus(false);
                        mainGoal.setSubGoals(subGoal);

                    } else {
                        // Throw this error if it is empty
                        throw new IllegalArgumentException("Please fill in Added activity.");
                    }
                }

                // Add the main goal to the GoalManager list
                goalManager.addGoal(mainGoal);

                // save state of the thingy
                GoalManagerSerializationHelper.saveGoalManager(goalManager, Main.GOAL_MANAGER_FILE);

                // load the data into serialization
                GoalManagerSerializationHelper.saveGoalManager(goalManager, Main.GOAL_MANAGER_FILE);

                // Bring user back to dashboard
                Dashboard dashboard = new Dashboard(
                        GoalManagerSerializationHelper.loadGoalManager(Main.GOAL_MANAGER_FILE));

                // Dispose the window
                frame.dispose();
            } catch (IllegalArgumentException ex) {
                // Exception handler for empty input
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //go to dashboard
                Dashboard dashboard = new Dashboard(GoalManagerSerializationHelper.loadGoalManager(Main.GOAL_MANAGER_FILE));
                frame.dispose();
            }
        });

    }

    // set frame to visible
    private void setVisible(boolean value) {
        frame.setVisible(value);
    }

    // Method to add a task field dynamically
    private void addSubGoalField() {
        // Make a new panel
        JPanel subGoalPanel = new JPanel(new GridLayout(1, 2));
        subGoalPanel.setBorder(BorderFactory.createEtchedBorder());
        JLabel titleLabel = new JLabel("<html><span style='font-size: 16pt; font-weight: bold;'>  Task: </span></html>");
        subGoalPanel.add(titleLabel);
        JTextField subGoalTitleField = new JTextField();
        subGoalPanel.add(subGoalTitleField);

        // Add the sub-goal panel to the sub-goals panel
        subGoalsPanel.add(subGoalPanel);

        // this acts like hitting refresh
        subGoalsPanel.revalidate();
        subGoalsPanel.repaint();
    }

    // Method to remove the last added task
    private void removeSubGoalField() {

        // Check if there are any tasks
        int componentCount = subGoalsPanel.getComponentCount();
        if (componentCount > 0) {
            // Remove the last added task
            subGoalsPanel.remove(componentCount - 1);

            // this acts like hitting refresh
            subGoalsPanel.revalidate();
            subGoalsPanel.repaint();
        }
    }
}
