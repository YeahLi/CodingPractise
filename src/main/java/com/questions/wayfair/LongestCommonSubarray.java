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
        int[][] dp = new int[m+1][n+1];
        dp[0][0] = 0;

        int max = 0;
        int idx1 = -1;
        int idx2 = -1;

        for (int i = 1; i < m+1; i++) { //be careful about the initial value and range!
            for (int j = 1; j < n+1; j++) {
                if (user1[i-1].equals(user2[j-1])) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                    if(max < dp[i][j]) {
                        max = dp[i][j];
                        idx1 = i-1;
                        idx2 = j-1;
                    }
                }
            }
        }

        LinkedList<String> list = new LinkedList<>();
        while(idx1 >= 0 && idx2 >= 0 && user1[idx1].equals(user2[idx2])) {
            list.addFirst(user1[idx1]);
            idx1--;
            idx2--;
        }
        return list;
    }
}
