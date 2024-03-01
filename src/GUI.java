/**
 * author @Kiarash
 * 30 May 2023
 * ICS4UE Final Project: Sorting Algorithms Visualizer
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class GUI extends JFrame implements ActionListener {
    private TopPanel topPanel;
    private Draw drawPanel;
    private int[] array;

    public GUI() {
        this.setTitle("Sorting Algorithms Visualizer by Kiarash Sotoudeh");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // make the app fullscreen

        // draw the arrays panel
        drawPanel = new Draw(HelperMethods.generateRandomArray(50)); // draw & arrays panel
        // draw the top panel
        topPanel = new TopPanel(drawPanel);
        topPanel.setArray(drawPanel.getArray());
        System.out.println(Arrays.toString(drawPanel.getArray()));

        // add the top panel and draw panel to the frame
        this.add(topPanel, BorderLayout.NORTH);
        this.add(drawPanel, BorderLayout.CENTER);

        // last statement: make them visible
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}