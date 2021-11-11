package com.questions.amazon;

import java.util.ArrayList;
import java.util.List;

public class ReverseSubstring {
    public static String reverseSubString(String input, List<int[]> list) {
        String result = input;
        for (int[] range : list) {
            result = reverseSubString(result, range[0], range[1]);
        }
        return result;
    }

    private static String reverseSubString(String input, int begin, int end) {
        String subString = input.substring(begin, end + 1);
        StringBuffer sb = new StringBuffer(subString);
        sb.reverse();

        StringBuffer newString = new StringBuffer(input);
        newString.replace(begin, end + 1, sb.toString());
        return newString.toString();
    }

    public static void main(String[] args) {
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{0, 3});
        list.add(new int[]{1, 5});
        list.add(new int[]{3, 8});
        System.out.println(reverseSubString("abcdefghijk", list));
    }
}
