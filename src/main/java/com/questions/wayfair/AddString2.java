package com.questions.wayfair;

public class AddString2 {
    public static void main(String[] args) {
        System.out.println(addNums("5", "5"));
        System.out.println(addNums(null, "5"));
        System.out.println(addNums("123", "987"));
        System.out.println(addNums("11", "123"));
        System.out.println(addNums("456", "77"));
        System.out.println(addNums("0", "0"));
        System.out.println(addNums("123456789", "987654321"));
        System.out.println(addNums("500", "500"));
        System.out.println(addNums("5,000", "5000"));
        System.out.println(addNums("5,000", "5,000"));
    }

    public static String addNums(String num1, String num2) {
        if (num1 == null || num1.isEmpty()) {
            num1 = "0";
        }

        if (num2 == null || num2.isEmpty()) {
            num2 = "0";
        }

        StringBuilder sb = new StringBuilder();
        int carry = 0;

        int i = num1.length() - 1;
        int j = num2.length() - 1;

        int count = 0;

        while (i >= 0 || j >= 0) {
            char c1 = '0';
            char c2 = '0';

            if (i >= 0) {
                c1 = num1.charAt(i);
            }
            if (j >= 0) {
                c2 = num2.charAt(j);
            }

            if (c1 == ',' || c2 == ',') {
                sb.append(',');
                if (c1 == ',') {
                    i--;
                }
                if (c2 == ',') {
                    j--;
                }
                continue;
            }

            if (count != 0 && count % 3 == 0 && sb.charAt(sb.length() - 1) != ',') {
                sb.append(',');
                continue;
            }

            int a = Character.getNumericValue(c1);
            int b = Character.getNumericValue(c2);

            int sum = a + b + carry;
            carry = sum / 10;
            sb.append(sum % 10);
            count++;

            i--;
            j--;
        }
        //handle carry
        if (carry != 0) {
            if (count != 0 && count % 3 == 0 && sb.charAt(sb.length() - 1) != ',') {
                sb.append(',');
            }
            sb.append(carry);
            count++;
        }

        //return result
        return sb.reverse().toString();
    }
}
