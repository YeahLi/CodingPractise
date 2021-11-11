package com.questions.amazon;

import java.util.Arrays;

public class LetterFrequency {
    public static int[] getFrequency(String encode) {
        int[] result = new int[26];
        char[] encodeChars = encode.toCharArray();

        StringBuffer decodeStr = new StringBuffer();

        int i = 0;
        int j = 0;

        // decode numbers to letters
        while (i < encodeChars.length) {
            while (j < encodeChars.length) {
                if (encodeChars[j] == '#') {
                    break;
                }
                j++;
            }

            int target = (j >= encodeChars.length) ? j : j - 2;
            int k = i;
            while (k < target) {
                char c = encodeChars[k];
                if (c == '(') {
                    while (encodeChars[k] != ')') {
                        decodeStr.append(encodeChars[k]);
                        k++;
                    }
                    decodeStr.append(encodeChars[k]);
                } else {
                    int num = Integer.valueOf(String.valueOf(c));
                    char letter = (char) ('a' + num - 1);
                    decodeStr.append(letter);
                }
                k++;
            }

            if (target < encodeChars.length) {
                int num = Integer.parseInt(encode.substring(target, j));
                char letter = (char) ('a' + num - 1);
                decodeStr.append(letter);
            }

            i = j + 1;
            j = j + 1;
        }

        char[] decodeChars = decodeStr.toString().toCharArray();
        System.out.println("decodeChars:" + Arrays.toString(decodeChars));

        //Calculate frequencies
        char prev = '#';
        StringBuffer numStr = new StringBuffer();
        for (int k = 0; k < decodeChars.length; k++) {
            char c = decodeChars[k];
            if (c >= 'a' && c <= 'z') {
                prev = c;
                result[c - 'a']++;
            } else if (Character.isDigit(c)) {
                numStr.append(c);
            } else if (c == '(') {
                numStr = new StringBuffer();
            } else if (c == ')') {
                int num = Integer.valueOf(numStr.toString());
                result[prev - 'a'] += num - 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String encode = "1226#24#";
        System.out.println("result: " + Arrays.toString(getFrequency(encode)));

        String encode1 = "1(2)23(3)";
        System.out.println("result: " + Arrays.toString(getFrequency(encode1)));

        String encode2 = "2110#(2)";
        System.out.println("result: " + Arrays.toString(getFrequency(encode2)));

        String encode3 = "23#(2)24#25#26#23#(3)";
        System.out.println("result: " + Arrays.toString(getFrequency(encode3)));
    }
}
