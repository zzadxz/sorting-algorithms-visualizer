/**
 * author @Kyle
 * 30 May 2023
 * ICS4UE Final Project: Sorting Algorithms Visualizer
 */

import javax.swing.*;
import java.awt.*;

public class Draw extends JPanel implements Runnable {
    private int[] array;
    private static int firstIndex = -1;
    private static int secondIndex = -1;
    private static Color color;

    public Draw(int[] array) {
        this.array = array;
        this.setLayout(new GridLayout());
    }

    public void run() {
        repaint();
    }

    public void updateArray(int[] array, int firstIndex, int secondIndex, Color color) {
        this.array = array;
        this.firstIndex = firstIndex;
        this.secondIndex = secondIndex;
        this.color = color;
        SwingUtilities.invokeLater(this);
    }

    public static int getFirstIndex() {
        return firstIndex;
    }

    public static int getSecondIndex() {
        return secondIndex;
    }

    public static Color getColor() {
        return color;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (array != null) {
            int width = getWidth();
            int height = getHeight();
            int barWidth = width / array.length;
            int border = 2; // border thickness
            int spacing = 2; // spacing between rectangles
            for (int i = 0; i < array.length; i++) {
                int barHeight = (int) ((double) array[i] / (double) 100 * height);

                // Draw border
                g.setColor(Color.pink.darker()); // choose a darker color for the border
                g.fillRect(i * barWidth, height - barHeight - border, barWidth, barHeight + border);

                // Set color depending on the current index
                if (i == firstIndex || i == secondIndex) {
                    g.setColor(color);
                } else {
                    g.setColor(Color.PINK);
                }

                // Draw rectangle (main bar)
                g.fillRect(i * barWidth + spacing/2, height - barHeight, barWidth - spacing, barHeight - border);

                // Draw the number at the bottom of each bar
                int fontSize = calculateFontSize(barWidth);
                g.setFont(new Font("Serif", Font.PLAIN, fontSize));
                g.setColor(Color.BLACK);
                String number = String.valueOf(array[i]);
                int stringWidth = g.getFontMetrics().stringWidth(number);
                g.drawString(number, i * barWidth + barWidth / 2 - stringWidth / 2, height - 5);
            }
        }
    }

    private int calculateFontSize(int barWidth) { // returns an appropriate font size based on the width of the bar.
        int fontSize = Math.max(barWidth / 3, 1);
        int maxFontSize = 50; // Set the maximum font size here
        if (fontSize > maxFontSize) {
            fontSize = maxFontSize;
        }
        return fontSize;
    }

    public int[] getArray() {
        return array;
    }
}
