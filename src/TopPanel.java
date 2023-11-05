/**
 * author @Kiarash
 * 30 May 2023
 * ICS4UE Final Project: Sorting Algorithms Visualizer
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import static javax.swing.JOptionPane.showMessageDialog;

public class TopPanel extends JPanel {
    private String selectedAlgorithm;
    private JButton bubbleSortButton;
    private JButton selectionSortButton;
    private JButton mergeSortButton;
    private JButton heapSortButton;
    private JButton generateArrayButton;
    private JButton sortButton;
    private JButton finishButton;
    private JButton arraySizeButton;
    private JButton sortingSpeedButton;
    private JLabel runtimeLabel;
    private JLabel projectInfoLabel;
    private JLabel projectTitleLabel;
    private JLabel creatorNameLabel;
    private JSlider arraySizeSlider;
    private JSlider sortingSpeedSlider;
    private int[] array;
    private int defaultDelayTime = 500;

    public TopPanel(Draw drawPanel) {
        this.setLayout(new GridLayout(3, 4));  // modify this as per your requirements
        // --- all the configuration for top panel --- \\
        // title
        projectTitleLabel = new JLabel("Sorting Algorithms Visualizer", SwingConstants.CENTER);
        projectTitleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        projectTitleLabel.setFont(projectTitleLabel.getFont().deriveFont(Font.ITALIC));
        projectTitleLabel.setForeground(Color.BLACK);
        projectTitleLabel.setBackground(Color.WHITE);
        projectTitleLabel.setOpaque(true);
        // creator Name
        creatorNameLabel = new JLabel("Dev. by Kiarash Sotoudeh", SwingConstants.CENTER);
        creatorNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        creatorNameLabel.setForeground(Color.BLACK);
        creatorNameLabel.setBackground(Color.WHITE);
        creatorNameLabel.setOpaque(true);
        // project info
        projectInfoLabel = new JLabel("Java Project", SwingConstants.CENTER);
        projectInfoLabel.setFont(new Font("Arial", Font.BOLD, 20));
        projectInfoLabel.setForeground(Color.BLACK);
        projectInfoLabel.setBackground(Color.WHITE);
        projectInfoLabel.setOpaque(true);
        // runtime
        runtimeLabel = new JLabel("Runtime: ", SwingConstants.CENTER);
        runtimeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        runtimeLabel.setForeground(Color.BLACK);
        runtimeLabel.setBackground(Color.WHITE);
        runtimeLabel.setOpaque(true);

        arraySizeButton = new JButton("Array size:");
        arraySizeButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMessageDialog(null, "Choose array size using the slider on the right from 1-100 elements: ");
            }
        });

        arraySizeSlider = new JSlider(JSlider.HORIZONTAL, 1, 100, 50);
        arraySizeSlider.setMajorTickSpacing(10);
        arraySizeSlider.setMinorTickSpacing(1);
        arraySizeSlider.setPaintTicks(true);
        arraySizeSlider.setPaintLabels(true);

        sortingSpeedButton = new JButton("Sorting speed:");
        sortingSpeedButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMessageDialog(null, "Choose sorting speed using the slider on the right from 1-1000 ms: ");
            }
        });

        sortingSpeedSlider = new JSlider(JSlider.HORIZONTAL, 1, 1000, 500);
        sortingSpeedSlider.addChangeListener(e -> {
            JSlider source = (JSlider)e.getSource();
            if (!source.getValueIsAdjusting()) {
                int sortingSpeed = Math.abs(1001 - source.getValue());
                // Change the sorting speed here
                SortingAlgorithms.setDelayTime(sortingSpeed);
            }
        });

        generateArrayButton = new JButton("Generate New Array");
        generateArrayButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int arraySize = arraySizeSlider.getValue();
                array = HelperMethods.generateRandomArray(arraySize);
                drawPanel.updateArray(array, Draw.getFirstIndex(), Draw.getSecondIndex(), Draw.getColor());
                System.out.println(Arrays.toString(array)); // print the array to the console
                System.out.println("Array size: " + arraySize);
            }
        });

        finishButton = new JButton("Finish Sorting");
        finishButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // open a Dialog box to ask user if they want to finish sorting
                int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to finish sorting?", "Warning", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    // set the speed to 0 to finish sorting immediately
                    SortingAlgorithms.setDelayTime(0);
                }
            }
        });

        sortButton = new JButton("Sort!");
        sortButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> {
                    SortingAlgorithms.setDelayTime(defaultDelayTime);
                    switch (selectedAlgorithm) {
                        case "BubbleSort":
                            SortingAlgorithms.BubbleSort(array, drawPanel);
                            break;
                        case "MergeSort":
                            SortingAlgorithms.MergeSort(array, drawPanel);
                            break;
                        case "SelectionSort":
                            SortingAlgorithms.SelectionSort(array, drawPanel);
                            break;
                        case "HeapSort":
                            SortingAlgorithms.HeapSort(array, drawPanel);
                            break;
                    }
                }).start();
            }
        });

        // --- sorting algorithm buttons --- \\
        bubbleSortButton = new JButton("Bubble Sort");
        bubbleSortButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedAlgorithm = "BubbleSort";
                runtimeLabel.setText("Runtime: O(n^2)");
            }
        });

        selectionSortButton = new JButton("Selection Sort");
        selectionSortButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedAlgorithm = "SelectionSort";
                runtimeLabel.setText("Runtime: O(n^2)");
            }
        });

        mergeSortButton = new JButton("Merge Sort");
        mergeSortButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedAlgorithm = "MergeSort";
                runtimeLabel.setText("Runtime: O(n log(n))");
            }
        });

        heapSortButton = new JButton("Heap Sort");
        heapSortButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedAlgorithm = "HeapSort";
                runtimeLabel.setText("Runtime: O(n log(n))");
            }
        });

        this.add(runtimeLabel);
        this.add(projectInfoLabel);
        this.add(projectTitleLabel);
        this.add(creatorNameLabel);
        this.add(finishButton); // empty label for better alignment --> pasue runtime label
        this.add(bubbleSortButton);
        this.add(selectionSortButton);
        this.add(mergeSortButton);
        this.add(heapSortButton);
        this.add(sortButton);
        this.add(generateArrayButton);
        this.add(arraySizeButton);
        this.add(arraySizeSlider);
        this.add(sortingSpeedButton);
        this.add(sortingSpeedSlider);
        // --- all the configuration for top panel ends here --- \\
    }
    public void setArray(int[] array) {
        this.array = array;
    }
}
