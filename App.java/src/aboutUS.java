import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class aboutUS {
    private final String[] names = {
            "Jesse Mbekeani",
            "Jacqueline Zimba",
            "Chiposa Chiomba",
            "Anita Gwede",
            "Shameel Sheriff",
            "George Kalua",
            "Blessings Mteteka",
            "Kennedy Kachitanda",
            "Mzati Tembo"
    };

    public aboutUS() {
        JFrame frame = new JFrame("About Us");
        frame.setSize(900, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.BLACK);
        JLabel headerLabel = new JLabel("GROUP 4");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 36));
        headerPanel.add(headerLabel);

        JTextArea fillerText = new JTextArea("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        fillerText.setFont(new Font("Arial", Font.PLAIN, 18));
        fillerText.setEditable(false);
        fillerText.setLineWrap(true);
        fillerText.setWrapStyleWord(true);
        fillerText.setBackground(Color.WHITE);

        JPanel namesPanel = new JPanel();
        namesPanel.setLayout(new BoxLayout(namesPanel, BoxLayout.Y_AXIS));
        namesPanel.setBackground(Color.WHITE);
        for (String name : names) {
            JLabel nameLabel = new JLabel(name);
            nameLabel.setFont(new Font("Arial", Font.PLAIN, 24));
            nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            namesPanel.add(nameLabel);
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton backButton = new JButton("Go back to program");
        backButton.setPreferredSize(new Dimension(300, 50));
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //load dashboard
                Dashboard dashboard = new Dashboard(GoalManagerSerializationHelper.loadGoalManager(Main.GOAL_MANAGER_FILE));
                frame.dispose();
            }
        });
        buttonPanel.add(backButton);

        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(fillerText, BorderLayout.CENTER);
        panel.add(namesPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }

}
