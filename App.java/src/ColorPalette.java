import java.awt.*;

public class ColorPalette {
    private Color primaryColor;
    private Color secondaryColor;
    private Color textColor;
    private Color backgroundColor;
    private Color hoverColor;

    public ColorPalette() {
        // Set default colors
        primaryColor = new Color(64, 106, 213); // Blue
        secondaryColor = Color.WHITE;
        textColor = Color.BLACK;
        backgroundColor = Color.WHITE;
        hoverColor = new Color(200, 200, 200); // Light gray
    }

    public ColorPalette(Color primaryColor, Color secondaryColor, Color textColor, Color backgroundColor, Color hoverColor) {
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        this.hoverColor = hoverColor;
    }

    // Getters and setters
    public Color getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(Color primaryColor) {
        this.primaryColor = primaryColor;
    }

    public Color getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(Color secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getHoverColor() {
        return hoverColor;
    }

    public void setHoverColor(Color hoverColor) {
        this.hoverColor = hoverColor;
    }
}
