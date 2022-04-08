package com.questions.wayfair;

import java.util.LinkedList;
import java.util.List;

public class LongestCommonSubarray {
    public static void main(String[] args){
        String[] user1 = {"hi", "bye", "hello", "leetcode", "start", "end"};
        String[] user2 = {"hi", "stop", "leetcode", "start", "end", "bye"};
        List<String> repeatingStrings = repeatPattern(user1, user2);
        for(String str : repeatingStrings) {
            System.out.println(str); //"leetcode", "start", "end"
        }
    }
    private static List<String> repeatPattern(String[] user1, String[] user2){
        /* dp[i][j] represent the length of common subfix of subarray from 0 to i-1 for user1 and the subfix of subarray from 0 to j-1 for user2
         * if user1[i-1] != user2[j-1], dp[i][j] doesn't have common subfix and its value is 0;
         * else dp[i][j] = 1 + dp[i-1][j-1]
         *
         * We need to find the max length pairs i and j, then add the common item from tail to head
         */

        int m = user1.length;
        int n = user2.length;

        int idx1 = -1;
        int idx2 = -1;
        int maxLength = 0;

        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 0;

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (user1[i - 1].equals(user2[j - 1])) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLength) {
                        maxLength = dp[i][j];
                        idx1 = i - 1;
                        idx2 = j - 1;
                    }
                }
            }
        }

        //find the common subarray
        LinkedList<String> res = new LinkedList<>();
        while (idx1 >= 0 && idx2 >= 0 && user1[idx1] == user2[idx2]) {
            res.addFirst(user1[idx1]);
            idx1--;
            idx2--;
        }
        return res;
    }
}
