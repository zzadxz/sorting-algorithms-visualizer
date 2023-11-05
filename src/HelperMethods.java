/**
 * author @Kyle
 * 30 May 2023
 * ICS4UE Final Project: Sorting Algorithms Visualizer
 */

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class HelperMethods {

    // --- helper methods --- \\
    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(100); // Generate random numbers between 0-99
        }
        return array;
    }
    public ArrayList<Integer> createArray() {
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 1; i < 51; i++) {
            nums.add(9 * i);
        }
        Collections.shuffle(nums);
        System.out.println(nums);
        return nums;
    }
}
