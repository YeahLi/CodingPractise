package com.algorithm;

public class SortAlgo {
    //SelectionSort: 每次选取最小的
    // Best: O(n^2) time | O(1) space
    // Avg: O(n^2) time | O(1) space
    // Worst: O(n^2) time | O(1) space
    // 稳定排序: 两个相等的元素 A 和 B, 如果 A在 B前,那么排序后 A 还在 B 前.
    public static int[] selectionSort(int[] array) {
        // Write your code here.
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        return array;
    }

    //BubbleSort: 交换排序 -- 每次通过两两交换把最大的放到末尾
    //Best: O(n) time | O(1) space -- already sorted
    //Avg: O(n^2) time | O(1) space
    //Worst: O(n^2) time | O(1) space
    public static int[] bubbleSort(int[] array) {
        // Write your code here.
        boolean isSorted = false;
        int count = 0;
        while (!isSorted) {
            isSorted = true;
            for (int j = 1; j < array.length - count; j++) {
                if (array[j - 1] > array[j]) {
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                    isSorted = false;
                }
            }
            count++;
        }

        return array;
    }

    //InsertionSort: 插入排序 -- 每次在已排序数组的尾部插入 array[i] 倒向比较 array[i] 与 array[i-1 .. 0], 如果小就交换,不小就停止交换
    public static int[] insertionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j >= 1; j--) {
                if (array[j] < array[j - 1]) {
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                } else {
                    break;
                }
            }
        }
        return array;
    }
}
