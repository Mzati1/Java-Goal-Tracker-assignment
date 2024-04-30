import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class ProgressPanel extends JPanel {

    private final JFrame frame;
    private final goalManager goalManager;

    public ProgressPanel(goalManager goalManager) {
        this.goalManager = GoalManagerSerializationHelper.loadGoalManager(Main.GOAL_MANAGER_FILE);
        frame = new JFrame("Progress Panel Example");
        frame.setSize(new Dimension(850, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.setLocationRelativeTo(null);
        display();
        setupComponents();
    }

    public void display() {
        frame.setVisible(true);
    }

    public void setupComponents() {
        setLayout(new BorderLayout());

        // Create a sample dataset for the pie chart
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        double completed = goalManager.getCompletedGoals_Progress();
        pieDataset.setValue("Completed", completed);
        double remaining = goalManager.getIncompletedGoals_Progress();
        pieDataset.setValue("Remaining", remaining);

        // Create a pie chart
        JFreeChart pieChart = ChartFactory.createPieChart(
                "Progress Chart", // Chart title
                pieDataset, // Dataset
                true, // Include legend
                true, // Include tooltips
                false // Exclude URLs
        );

        // Create a chart panel to hold the pie chart
        ChartPanel pieChartPanel = new ChartPanel(pieChart);
        pieChartPanel.setPreferredSize(new Dimension(400, 300));

        // Create a dataset for the overall progress graph
        DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
        barDataset.addValue(completed, "Goals", "Completed");
        barDataset.addValue(remaining, "Goals", "Remaining");

        // Create a bar chart
        JFreeChart barChart = ChartFactory.createBarChart(
                "Overall Progress", // Chart title
                "Status", // X-axis label
                "Number of Goals", // Y-axis label
                barDataset // Dataset
        );

        // Create a chart panel to hold the bar chart
        ChartPanel barChartPanel = new ChartPanel(barChart);
        barChartPanel.setPreferredSize(new Dimension(400, 300));

        // Create a label to display the overall progress
        double totalGoals = goalManager.getLengthOfArray();
        double progressPercentage = totalGoals == 0 ? 0 : (completed / totalGoals * 100);
        JLabel progressLabel = new JLabel(String.format("Overall Progress: %.2f%%", progressPercentage));

        // this message is the one displayed to tell user progress
        // verbally
        String remainingMessage = progressPercentage == 100 ? "You've achieved all your goals! 🏆" + "      "
                : progressPercentage >= 60 ? "You're almost there! 👋" + "      "
                        : progressPercentage >= 50 ? "You're halfway there! 💪" + "      "
                                : "You still have work to do 💼" + "      ";
        JLabel remainingLabel = new JLabel(remainingMessage);
        remainingLabel.setForeground(progressPercentage == 100 ? Color.GREEN
                : progressPercentage >= 60 ? Color.ORANGE : progressPercentage >= 50 ? Color.ORANGE : Color.RED);

        // Create a back button
        JButton backButton = new JButton("Back");

        // Style the back button
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(64, 106, 213));
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        backButton.setOpaque(true);

        // Add action listener to the back button
        backButton.addActionListener(e -> {
            // Go back to dashboard
            Dashboard dashboard = new Dashboard(goalManager);
            frame.dispose();
        });

        // Add components to the ProgressPanel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(backButton);

        JPanel progressPanel = new JPanel(new BorderLayout());
        progressPanel.setBackground(Color.WHITE);
        progressPanel.add(progressLabel, BorderLayout.WEST);
        progressPanel.add(remainingLabel, BorderLayout.EAST);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(progressPanel, BorderLayout.CENTER);
        southPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(southPanel, BorderLayout.SOUTH);
        add(pieChartPanel, BorderLayout.WEST);
        add(barChartPanel, BorderLayout.EAST);
    }
}
