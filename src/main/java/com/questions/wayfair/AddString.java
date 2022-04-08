package com.questions.wayfair;

public class AddString {
    public static void main(String[] args) {
        System.out.println(addNums("5", "5"));
        System.out.println(addNums("123", "987"));
        System.out.println(addNums("11", "123"));
        System.out.println(addNums("456", "77"));
        System.out.println(addNums("0", "0"));
        System.out.println(addNums("123456789", "987654321"));
        System.out.println(addNums("123,456,789", "987,654,321"));
        System.out.println(addNums("500", "500"));
        System.out.println(addNums("5,000", "5000"));
        System.out.println(addNums("5,000", "5,000"));
    }

    public static String addNums(String num1, String num2) {
        if ((num1 == null || num1.isEmpty()) && (num2 == null || num2.isEmpty())) {
            return "0";
        }

        int i = num1.length() - 1;
        int j = num2.length() - 1;

        int carry = 0;
        int digitCount = 0;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0) {
            char c1 = '0';
            char c2 = '0';
            if (i >= 0) {
                c1 = num1.charAt(i);
            }
            if (j >= 0) {
                c2 = num2.charAt(j);
            }
            // the string contains ","
            if (c1 == ',' || c2 == ',') {
                if (c1 == ',') {
                    i--;
                }
                if (c2 == ',') {
                    j--;
                }
                continue;
            }
            // format the result
            if (digitCount != 0 && digitCount % 3 == 0 && sb.charAt(sb.length() - 1) != ',') {
                sb.append(",");
                continue;
            }

            i--;
            j--;

            int sum = Character.getNumericValue(c1) + Character.getNumericValue(c2) + carry;
            carry = sum / 10;
            int digit = sum % 10;
            sb.append(digit);
            digitCount++;
        }

        if (carry != 0) {
            if (digitCount != 0 && digitCount % 3 == 0) {
                sb.append(",");
            }
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
}
