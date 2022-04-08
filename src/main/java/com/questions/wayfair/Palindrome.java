package com.questions.wayfair;

public class Palindrome {

    public static void main(String[] args) {
        System.out.println(isPalindrome("a"));
        System.out.println(isPalindrome("abc"));
        System.out.println(isPalindrome("aba"));
        System.out.println(isPalindrome("abba"));

        System.out.println(findLongestPalindrome("banana"));
        System.out.println(findLongestPalindrome("abc"));
        System.out.println(findLongestPalindrome("aaba"));
    }

    public static boolean isPalindrome(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        }

        int i = 0;
        int j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    /**
     * dp[i][j] represent if substring [i, j] is a palindrome.
     * dp[i][j] = true if s[i] == s[j] && dp[i+1][j-1] if palindrome (i+1 <= j-1)
     * else false
     */
    public static String findLongestPalindrome(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        int len = str.length();
        int maxLength = 1;
        int maxBegin = 0;

        boolean dp[][] = new boolean[len][len];

        for (int j = 0; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (str.charAt(i) != str.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                int length = j - i + 1;
                if (dp[i][j] && length > maxLength) {
                    maxLength = length;
                    maxBegin = i;
                }
            }
        }

        return str.substring(maxBegin, maxBegin + maxLength);
    }
}
