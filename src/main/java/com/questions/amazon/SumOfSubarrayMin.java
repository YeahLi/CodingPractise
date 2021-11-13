package com.questions.amazon;

import java.util.Arrays;

public class SumOfSubarrayMin {
    /**
     * @param A: an array
     * @return: the sum of subarray minimums
     */
    public int sumSubarrayMins(int[] A) {
        // Write your code here.
        int sum = 0;

        for (int num = 1; num <= A.length; num++) {
            int i = 0;
            int j = i + num;
            while (j <= A.length) {
                int[] subarray = Arrays.copyOfRange(A, i, j);
                sum += findMin(subarray);
                i++;
                j++;
            }
        }

        return sum;
    }

    private int findMin(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int num : arr) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }
}
