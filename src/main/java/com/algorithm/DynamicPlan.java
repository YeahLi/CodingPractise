package com.algorithm;

public class DynamicPlan {
    // Nth Fibonacci
    // while -- O(n) O(1)
    public static int getNthFib(int n) {
        if (n == 1) {
            return 0;
        }

        if (n == 2) {
            return 1;
        }

        int prev2 = 0;
        int prev1 = 1;

        int i = 3;
        while (i < n) {
            int current = prev2 + prev1;
            prev2 = prev1;
            prev1 = current;
            i++;
        }

        return prev2 + prev1;
    }

    // Recursive -- O(n) O(n)
    public static int getNthFib2(int n) {
        if (n == 1) {
            return 0;
        }

        if (n == 2) {
            return 1;
        }

        int[] arr = new int[n + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = -1;
        }
        arr[0] = 0;
        arr[1] = 0;
        arr[2] = 1;


        return getNthFib(n, arr);
    }

    private static int getNthFib(int n, int[] arr) {
        int prev2 = arr[n - 2];
        if (prev2 == -1) {
            prev2 = getNthFib(n - 2, arr);
        }

        int prev1 = arr[n - 1];
        if (prev1 == -1) {
            prev1 = getNthFib(n - 1, arr);
        }

        arr[n] = prev1 + prev2;
        return arr[n];
    }
}
