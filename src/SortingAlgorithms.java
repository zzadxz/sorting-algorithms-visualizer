/**
 * author @Kyle
 * 30 May 2023
 * ICS4UE Final Project: Sorting Algorithms Visualizer
 */

import java.awt.*;

public class SortingAlgorithms {
    public static int delayTime = 50; // the default delay time in milliseconds
    // Bubble Sort: Time O(n^2)
    public static void BubbleSort(int[] array, Draw drawPanel) {
        int size = array.length;
        for (int i = 0; i < size - 1; i++) {
            for (int k = 0; k < size - i - 1; k++) {
                drawPanel.updateArray(array, k, k + 1, Color.YELLOW); // highlight the elements to be compared
                delay(delayTime);
                if (array[k] > array[k + 1]) {
                    // Swap and repaint
                    int temp = array[k];
                    array[k] = array[k + 1];
                    array[k + 1] = temp;
                    drawPanel.updateArray(array, k, k + 1, Color.GREEN); // highlight swapped elements
                    delay(delayTime); // adjust delay time as per your need
                }
                drawPanel.updateArray(array, -1, -1, Color.PINK); // reset the color
            }
        }
        drawPanel.updateArray(array, -1, -1, Color.PINK); // reset the color
    }

    // Selection Sort: Time O(n^2)
    public static void SelectionSort(int[] array, Draw drawPanel){
        int size = array.length;
        for (int i = 0; i < size - 1; i++) {
            // find the index of the minimum element in the unsorted part of the array
            int minIndex = i;
            for (int k = i + 1; k < size; k++) {
                drawPanel.updateArray(array, minIndex, k, Color.YELLOW); // highlight the elements to be compared
                delay(delayTime); // adjust delay time as per your need
                if (array[k] < array[minIndex]) {
                    minIndex = k;
                }
            }
            // swap the found minimum element with the first element of the unsorted part
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
            drawPanel.updateArray(array, minIndex, i, Color.GREEN); // highlight swapped elements
            delay(delayTime); // adjust delay time as per your need
        }
        drawPanel.updateArray(array, -1, -1, Color.PINK); // reset the color
    }

    // Marge Sort: Time O(nlogn)
    public static void MergeSort(int[] array, Draw drawPanel) {
        int length = array.length;
        if (length < 2) return; // base case

        int middle = length / 2;
        int[] leftArray = new int[middle];
        int[] rightArray = new int[length - middle];
        for (int i = 0; i < middle; i++){
            leftArray[i] = array[i];
        }
        for (int i = middle; i < length; i++){
            rightArray[i - middle] = array[i];
        }
        MergeSort(leftArray, drawPanel);
        MergeSort(rightArray, drawPanel);
        merge(leftArray, rightArray, array, drawPanel);
    }
    private static void merge(int[] leftArray, int[] rightArray, int[] array, Draw drawPanel) { // helper method for MergeSort
        int leftSize = leftArray.length;
        int rightSize = rightArray.length;
        int i = 0, l = 0, r = 0;
        // check the condition for merging
        while (l < leftSize && r < rightSize){
            if (leftArray[l] <= rightArray[r]){
                array[i] = leftArray[l];
                i++;
                l++;
            } else {
                array[i] = rightArray[r];
                i++;
                r++;
            }
            drawPanel.updateArray(array, l, r, Color.GREEN); // update graphics
            delay(delayTime); // delay for visualization
        }
        while (l < leftSize){
            array[i] = leftArray[l];
            i++;
            l++;
            drawPanel.updateArray(array, l, -1, Color.GREEN); // update graphics
            delay(delayTime); // delay for visualization
        }
        while (r < rightSize){
            array[i] = rightArray[r];
            i++;
            r++;
            drawPanel.updateArray(array, -1, r, Color.GREEN); // update graphics
            delay(delayTime); // delay for visualization
        }
        drawPanel.updateArray(array, -1, -1, Color.PINK); // reset the color
    }

    // Heap Sort: Time O(nlogn)
    public static void HeapSort(int[] array, Draw drawPanel) {
        int length = array.length;
        for (int i = length / 2 - 1; i >= 0; i--) {
            heapify(array, length, i, drawPanel);
        }
        for (int i = length - 1; i >= 0; i--) {
            drawPanel.updateArray(array, 0, i, Color.YELLOW);
            delay(delayTime);
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            drawPanel.updateArray(array, 0, i, Color.GREEN);
            delay(delayTime);
            drawPanel.updateArray(array, -1, -1, Color.PINK);
            heapify(array, i, 0, drawPanel);
        }
    }
    private static void heapify(int[] array, int heapSize, int i, Draw drawPanel) {
        int largest = i;
        int leftChildIdx  = 2 * i + 1;
        int rightChildIdx  = 2 * i + 2;
        if (leftChildIdx  < heapSize && array[leftChildIdx ] > array[largest]) {
            largest = leftChildIdx ;
        }
        if (rightChildIdx  < heapSize && array[rightChildIdx ] > array[largest]) {
            largest = rightChildIdx ;
        }
        if (largest != i) {
            drawPanel.updateArray(array, i, largest, Color.YELLOW);
            delay(delayTime);
            swap(array, i, largest);
            drawPanel.updateArray(array, i, largest, Color.GREEN);
            delay(delayTime);
            drawPanel.updateArray(array, -1, -1, Color.PINK);
            heapify(array, heapSize, largest, drawPanel);
        }
    }

    // --- helper methods for sorting algorithms --- \\
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    private static void delay(int milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void setDelayTime(int delayTime) {
        SortingAlgorithms.delayTime = delayTime;
    }
}