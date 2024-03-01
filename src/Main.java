/**
 * author @Kiarash
 * 30 May 2023
 * Project: Sorting Algorithms Visualizer
 */

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        new GUI();
        // make the console app for checking the arrays
        Scanner mysc = new Scanner(System.in);
        System.out.println("Enter the size of the array: ");
        int size = mysc.nextInt();
        int[] data = new int[size];
        System.out.println("Enter the elements of the array: ");
        for (int i = 0; i < size; i++) {
            data[i] = mysc.nextInt();
        }
        System.out.println("Sorted Array in Ascending Order:");
        System.out.println(Arrays.toString(data));
    }
}
