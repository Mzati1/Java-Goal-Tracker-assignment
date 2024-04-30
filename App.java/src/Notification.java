import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Notification {
    private static final long DURATION = 5000; // until notification closes itself
    public static long DELAY = 180000; // delay before the first notification shows (start after 3 minutes)
    public static long PERIOD = 180000; // interval between notifications (5 minutes in milliseconds)

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new NotificationTask(), DELAY, PERIOD);
    }

    static class NotificationTask extends TimerTask {
        @Override
        public void run() {
            String notificationMessage = buildNotificationMessage();
            // Create a custom parent frame positioned at the top-left corner of the screen
            JFrame parentFrame = new JFrame();
            parentFrame.setUndecorated(true); // Remove window decorations
            parentFrame.setLocation(0, 0); // Set location to top-left corner
            parentFrame.setSize(1, 1); // Set size to almost invisible

            // Display a notification dialog with the custom parent frame
            JOptionPane.showMessageDialog(parentFrame, notificationMessage, "Notification", JOptionPane.INFORMATION_MESSAGE);

            // Schedule a task to close the dialog after the specified duration
            Timer closeTimer = new Timer();
            closeTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    // Find the active dialog and dispose of it
                    for (java.awt.Window window : java.awt.Window.getWindows()) {
                        if (window instanceof java.awt.Dialog && ((java.awt.Dialog) window).isModal()) {
                            window.dispose();
                            break;
                        }
                    }
                    // Dispose the parent frame
                    parentFrame.dispose();
                }
            }, DURATION);
        }

        // This method builds the notification message, which is a combination of fixed text and generated quote
        private String buildNotificationMessage() {
            RandomQuoteGenerator quoteGenerator = new RandomQuoteGenerator();
            String notificationQuote = quoteGenerator.getQoute();
            String defaultMessage = "You still have Goals to Complete! \n";
            return defaultMessage + notificationQuote;
        }
    }
}
