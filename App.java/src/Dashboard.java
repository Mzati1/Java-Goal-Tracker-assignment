import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class Dashboard {
    // Dashboard attributes
    private JFrame frame;
    private JPanel navigationPanel, mainContent, footerPanel;
    private JLabel quoteLabel;
    private JButton addGoalBtn, statsPaneBtn, settingsBtn, completedGoalsBtn, aboutUsBtn;
    private Timer timer;
    private final goalManager goalManager;

    // Constructor
    // Takes goalManager so we can have shared goals across instances
    public Dashboard(goalManager goalManager) {
        this.goalManager = goalManager;
        initialize();
        setupComponents();
        addListeners();
        frame.setVisible(true);
        frame.repaint();
    }

    // METHODS
    private void initialize() {
        frame = new JFrame("Goal Planner");
        navigationPanel = new JPanel();
        mainContent = new JPanel();
        footerPanel = new JPanel();
        quoteLabel = new JLabel("QUOTE HERE");
        addGoalBtn = new JButton("Add Goal");
        statsPaneBtn = new JButton("Progress");
        settingsBtn = new JButton("Settings");
        aboutUsBtn = new JButton("About Us");
        completedGoalsBtn = new JButton("Completed");
        timer = new Timer();
    }

    private void setupComponents() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLayout(new BorderLayout());
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);

        setupNavigationPanel();
        setupFooterPanel();
        setupMainContent();

        frame.add(navigationPanel, BorderLayout.WEST);
        frame.add(footerPanel, BorderLayout.SOUTH);
        frame.add(mainContent, BorderLayout.CENTER);
    }

    private void setupNavigationPanel() {
        // Create a panel to hold the navigation buttons
        JPanel navButtonsPanel = new JPanel();
        navButtonsPanel.setLayout(new BoxLayout(navButtonsPanel, BoxLayout.Y_AXIS));
        navButtonsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));

        // Style for the navigation buttons
        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        Color buttonTextColor = new Color(0, 0, 0);
        Color buttonBackgroundColor = new Color(64, 106, 213);
        Dimension buttonSize = new Dimension(180, 40);
        Insets buttonMargin = new Insets(10, 20, 10, 20);


        /*ADDING COMPONENTS*/

        // New Goal button
        addGoalBtn.setFont(buttonFont);
        addGoalBtn.setForeground(buttonTextColor);
        addGoalBtn.setBackground(buttonBackgroundColor);
        addGoalBtn.setPreferredSize(buttonSize);
        addGoalBtn.setMargin(buttonMargin);
        navButtonsPanel.add(addGoalBtn);

        // Completed Goals button
        completedGoalsBtn.setFont(buttonFont);
        completedGoalsBtn.setForeground(buttonTextColor);
        completedGoalsBtn.setBackground(buttonBackgroundColor);
        completedGoalsBtn.setPreferredSize(buttonSize);
        completedGoalsBtn.setMargin(buttonMargin);
        navButtonsPanel.add(completedGoalsBtn);

        // Progress button
        statsPaneBtn.setFont(buttonFont);
        statsPaneBtn.setForeground(buttonTextColor);
        statsPaneBtn.setBackground(buttonBackgroundColor);
        statsPaneBtn.setPreferredSize(buttonSize);
        statsPaneBtn.setMargin(buttonMargin);
        navButtonsPanel.add(statsPaneBtn);

        // Settings button
        settingsBtn.setFont(buttonFont);
        settingsBtn.setForeground(buttonTextColor);
        settingsBtn.setBackground(buttonBackgroundColor);
        settingsBtn.setPreferredSize(buttonSize);
        settingsBtn.setMargin(buttonMargin);
        navButtonsPanel.add(settingsBtn);


        //About us Button
        aboutUsBtn.setFont(buttonFont);
        aboutUsBtn.setForeground(buttonTextColor);
        aboutUsBtn.setBackground(buttonBackgroundColor);
        aboutUsBtn.setPreferredSize(buttonSize);
        aboutUsBtn.setMargin(buttonMargin);
        navButtonsPanel.add(aboutUsBtn);

        // Add the navigation buttons panel to the main navigation panel
        navigationPanel.add(navButtonsPanel);
    }

    private void setupFooterPanel() {
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.Y_AXIS));
        footerPanel.add(Box.createVerticalStrut(20));

        // Center align the quote label
        quoteLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        // Set the text color to black
        quoteLabel.setForeground(Color.BLACK);

        // Add the quote label to the footer panel
        footerPanel.add(quoteLabel);

        // Create watermark label
        JLabel watermarkLabel = new JLabel("GROUP_4");
        watermarkLabel.setHorizontalAlignment(JLabel.CENTER);
        // Set watermark color to gray
        watermarkLabel.setForeground(Color.GRAY);
        // Set watermark font
        watermarkLabel.setFont(new Font("Arial", Font.ITALIC, 12));

        // Add the watermark label to the footer panel
        footerPanel.add(watermarkLabel);
    }

    private void setupMainContent() {
        // Initialize the main content panel
        mainContent = new JPanel();
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));

        // Check if there are no goals
        if (goalManager.getLengthOfArray() == 0) {
            // Display welcome message and instruction
            JLabel welcomeLabel = new JLabel("Welcome to Goal Tracker App");
            welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
            welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainContent.add(Box.createVerticalGlue());
            mainContent.add(welcomeLabel);
            JLabel instructionLabel = new JLabel("Add a goal to get started");
            instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainContent.add(instructionLabel);
            mainContent.add(Box.createVerticalGlue());
        } else {
            // Add incomplete goals to the main content panel and start notifications
            boolean hasIncompleteGoals = false;
            for (int i = 0; i < goalManager.getLengthOfArray(); i++) {
                goal singleGoal = goalManager.getSingleGoal(i);
                if (!singleGoal.getGoalStatus()) {
                    // Creating goal panel only if the goal is incomplete
                    JPanel goalPanel = createGoalPanel("Goal " + (i + 1), i, singleGoal);
                    mainContent.add(goalPanel);
                    hasIncompleteGoals = true;
                }
            }

            // If no incomplete goals found, Just show a Welcome message
            if (!hasIncompleteGoals) {
                JLabel welcomeLabel = new JLabel("Welcome to Goal Tracker App");
                welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
                welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                mainContent.add(Box.createVerticalGlue());
                mainContent.add(welcomeLabel);
                JLabel instructionLabel = new JLabel("Add a goal to get started");
                instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                mainContent.add(instructionLabel);
                mainContent.add(Box.createVerticalGlue());
            }
        }

        // put the main content panel inside a scroll pane
        JScrollPane scrollPane = new JScrollPane(mainContent);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // the preferred size of the scroll pane to try to make a fixed size for the added panels
        scrollPane.setPreferredSize(new Dimension(900, 400));

        // Add the scroll pane to the frame
        frame.add(scrollPane, BorderLayout.CENTER);
    }


    private JPanel createGoalPanel(String panelName, int panelId, goal singleGoal) {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Check if singleGoal is not null
        if (singleGoal != null) {
            // Goal title
            JLabel titleLabel = new JLabel(singleGoal.getGoalName());
            titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
            titleLabel.setBounds(10, 10, 300, 30);
            panel.add(titleLabel);

            // Status and due date
            JLabel statusLabel = new JLabel("Status: " + (singleGoal.getGoalStatus() ? "Completed" : "Incomplete"));
            statusLabel.setFont(new Font("Arial", Font.BOLD, 15));
            statusLabel.setBounds(10, 50, 300, 20);
            panel.add(statusLabel);

            JLabel dueDateLabel = new JLabel("Due Date: " + singleGoal.getDueDate());
            dueDateLabel.setFont(new Font("Arial", Font.BOLD, 15));
            dueDateLabel.setBounds(10, 80, 300, 20);
            panel.add(dueDateLabel);

            // Calculate time remaining
            long timeRemaining = singleGoal.getDueDateUnformatted().getTime() - System.currentTimeMillis();
            long daysRemaining = timeRemaining / (60 * 60 * 1000 * 24);
            JLabel timeRemainingLabel = new JLabel("Days Remaining: " + daysRemaining + " Days");
            timeRemainingLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            timeRemainingLabel.setBounds(10, 110, 300, 20);
            panel.add(timeRemainingLabel);
        }

        // Set the bounds for the panel
        panel.setBounds(0, 0, 350, 150);

        // Add mouse listener to panel
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Redirect to views page, passing goal manager and the id of the
                // panel which is also goal id in some sense

                viewGoal viewsPage = new viewGoal(goalManager, panelId);
                frame.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(new Color(0xF3F8FF));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBackground(Color.WHITE);
            }
        });

        return panel;
    }


    private void addListeners() {
        // for the timer that handles quotes ( every 1m changing )
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                RandomQuoteGenerator quotes = new RandomQuoteGenerator();
                quoteLabel.setText(quotes.getQoute());
            }
        }, 0, 60 * 1000);

        // for create goal button
        addGoalBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // show create goal window
                createGoal createNewGoal = new createGoal(goalManager);

                // dispose dashboard
                frame.dispose();
            }
        });

        statsPaneBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // show progress page
                ProgressPanel progressPanel = new ProgressPanel(goalManager.getInstance());
                frame.dispose();
            }
        });

        // Add action listener for settings button
        settingsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // dispose dashboard
                SettingsPanel settings = new SettingsPanel();
                frame.dispose();
            }
        });

        // Add action listener for completed goals button
        completedGoalsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Show completed goals page
                CompletedGoalsPanel completedGoalsPanel = new CompletedGoalsPanel(GoalManagerSerializationHelper.loadGoalManager(Main.GOAL_MANAGER_FILE));
                frame.dispose();
            }
        });

        aboutUsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Show completed goals page
                aboutUS aboutUsPage = new aboutUS();
                frame.dispose();

            }
        });

        // kill the timer when the window is closed( in case its still running)
        // apparently this is good practice
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                timer.cancel();
            }
        });
    }
}
