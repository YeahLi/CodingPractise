package com.questions.amazon;

import java.util.ArrayDeque;
import java.util.Deque;

public class FlipZeroOne {
    /**
     * @param S: a string
     * @return: the minimum number
     */
    public int minFlipsMonoIncr(String S) {
        // Write your code here.
        char[] chars = S.toCharArray();
        Deque<Integer> stack = new ArrayDeque<>();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (stack.isEmpty()) {
                stack.push(i);
            } else {
                char top = chars[stack.peek()];
                if (top == '1' && c == '0') { // when 0 is behind 1, we need to flip
                    stack.pop();
                    count++;
                } else {
                    stack.push(i);
                }
            }
        }
        return count;
    }
}
