package com.questions.amazon;

import java.util.Arrays;

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

        if (coupon.length() % 2 != 0) {
            return false;
        }

        if (meetSecondRule(coupon)) {
            return true;
        }

        for (int i = 2; i < coupon.length(); i += 2) {
            String part1 = coupon.substring(0, i);
            String part2 = coupon.substring(i);

            if (isValidCoupon(part1) && isValidCoupon(part2)) {
                return true;
            }
        }

        return false;
    }

    private static boolean meetSecondRule(String str) {
        int i = 0;
        int j = str.length() - 1;

        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        if (i == j) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] coupons = new String[]{"ab", "bbaa", "abab", "abba", "abbaacca", "dabbadacca", "a"};
        System.out.println("Input: " + Arrays.toString(coupons));
        System.out.println("Output: " + Arrays.toString(validateCoupons(coupons)));
    }
}
