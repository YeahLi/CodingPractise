package com.algorithm;

import java.util.List;

public class RunnerAlgo {
    //Validate Subsequence
    public static boolean isValidSubsequence(List<Integer> array, List<Integer> sequence) {
        // Write your code here.
        int i = 0;
        int j = 0;
        while (i < array.size() && j < sequence.size()) {
            if (array.get(i) == sequence.get(j)) {
                j++;
            }
            i++;
        }
        return j == sequence.size();
    }
    //Sorted Squared Array
    //Palindrome Check
}
