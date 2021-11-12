package com.questions.amazon;

import java.util.ArrayDeque;
import java.util.Deque;

public class DecreaseRating {
    public static int countDecreaseRatings(int[] ratings) {
        Deque<Integer> stack = new ArrayDeque<>();
        int count = 0;
        for (int rating : ratings) {
            if (!stack.isEmpty()) {
                int top = stack.peek();
                if (rating >= top) {
                    stack.clear();
                }
            }
            stack.push(rating);
            count += stack.size();
        }
        return count;
    }

    public static void main(String[] args) {
        int[] ratings = {4, 3, 5, 4, 3};
        System.out.println(countDecreaseRatings(ratings));
    }
}
