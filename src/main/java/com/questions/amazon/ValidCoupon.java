package com.questions.amazon;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class ValidCoupon {
    public static int[] validateCoupons(String[] discounts) {
        int[] result = new int[discounts.length];

        for (int i = 0; i < discounts.length; i++) {
            String coupon = discounts[i];
            if (isValidCoupon(coupon)) {
                result[i] = 1;
            } else {
                result[i] = 0;
            }
        }

        return result;
    }

    private static boolean isValidCoupon(String coupon) {

        if (coupon.isEmpty()) {
            return true;
        }

        char[] chars = coupon.toCharArray();
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : chars) {
            if (stack.isEmpty()) {
                stack.push(c);
                continue;
            }

            char top = stack.peek();
            if (top == c) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String[] coupons = new String[]{"ab", "bbaa", "abab", "abba", "abbaacca", "dabbadacca", "a", "dbbaad"};
        System.out.println("Input: " + Arrays.toString(coupons));
        System.out.println("Output: " + Arrays.toString(validateCoupons(coupons)));
    }
}
