package com.basics.linear;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyString {
    public static void main(String[] args) {
        String string = "abcd1234";

        /**
         * 1. int length()
         * 2. char charAt(index)
         * 3. String substring(start, end)
         * 4. boolean contains(subString)
         * 5. boolean equalsIgnoreCase(compareStr)
         * 6. int compareIgnoreCase(compareStr)
         * 7. int indexOf(String)
         * 8. String toLowerCase()
         *    String toUpperCase()
         * 9. String trim()
         */
        stringDemo(string);

        /**
         * 1. boolean matches(regex)
         * 2. String replaceAll(regex, replacement)
         *    String replace(target c1, replacement c2)
         *    String replace(target, replacement)
         * 3. String[] split(splitter)
         * 4. Pattern.compile(regex)
         *    pattern.matcher(str)
         *    matcher.find() matcher.group()
         */
        stringAndRegEx(string);

        /**
         * 1. String.join(charSequence)
         * 2. String.format
         */
        stringClassDemo();

        /**
         * 1. String <-> byte array
         * 2. String <-> char array
         * 3. String <-> number
         */
        stringTypeTransferDemo(string);

        /**
         * 1. append, insert, delete, remove
         * 2. toString()
         * 3. StringBuffer reverse()
         */
        stringBufferDemo();
    }

    public static void stringDemo(String string) {
        // 1. length
        string.length();

        // 2. get a character
        char c = string.charAt(3);

        // 3. substring
        String str1 = string.substring(0, 4);
        String str2 = string.substring(4);

        // 4. contains
        string.contains(str2);

        // 5. equals
        str1.equals(str2);
        str1.equalsIgnoreCase(str2);

        // 6. compare
        str1.compareTo(str2);
        str1.compareToIgnoreCase(str2);

        // 7. indexOf
        int index = string.indexOf(c);
        index = string.indexOf("1234");

        String findString = str2;
        assert(!findString.isEmpty());
        index = 0;
        int count = 0;
        while(index < string.length() && (index = string.indexOf(findString)) >= 0) {
            count++;
            index = index + str2.length();
        }
        System.out.println(count);

        // 8. toLowerCase() and toUpperCase()
        string.toLowerCase();
        string.toUpperCase();

        // 9. trim
        string.trim();
    }

    public static void stringAndRegEx(String string) {
        // 1. matches
        boolean isMatch = string.matches("\\w+\\d[4]");
        System.out.println(isMatch);

        // 2. replace
        String newString = string.replace('1', '2');

        newString = string.replace("cd", "de");
        System.out.println(newString);

        newString = string.replaceAll("\\w", "2");
        System.out.println(newString); //22222222
        String replaceFirst = string.replaceFirst("\\w", "2");
        System.out.println(replaceFirst); //2bcd1234

        newString = string.replaceAll("\\w+", "2");
        System.out.println(newString); //2
        replaceFirst = string.replaceFirst("\\w+", "2");
        System.out.println(replaceFirst); //2

        newString = string.replaceAll("\\w*", "2");
        System.out.println(newString); //22
        replaceFirst = string.replaceFirst("\\w*", "2");
        System.out.println(replaceFirst); //2

        // 3. split
        String[] strArr = "how are you".split("\\s");
        System.out.println(Arrays.toString(strArr));

        // 4. Find all matched substrings
        Pattern pattern = Pattern.compile("[a-z]{2}");
        Matcher matcher = pattern.matcher(string);

        List<String> result = new ArrayList<>();
        while(matcher.find()) {
            result.add(matcher.group());
        }
        System.out.println(result);
        System.out.println(result.size());
    }

    public static void stringTypeTransferDemo(String string) {
        // String <-> byte array
        byte[] bytes = new byte[]{97, 98, 99, 100};
        String s = new String(bytes);

        bytes = string.getBytes(StandardCharsets.UTF_8);

        // String <-> char array
        char[] chars = new char[]{'a', 'b', 'c'};
        s = new String(chars);
        s = chars.toString();

        chars = s.toCharArray();

        // String <-> Number
        String numString = "123";
        int num = Integer.parseInt(numString);

        numString = String.valueOf(num);
    }

    public static void stringBufferDemo() {
        StringBuffer stringBuffer = new StringBuffer("abcd1234");
        //append
        stringBuffer.append('5');
        //delete
        stringBuffer.delete(2, 3);
        //insert
        stringBuffer.insert(2, 'f');
        //replace
        stringBuffer.replace(0, 3, "efgh");
        //reverse
        stringBuffer.reverse();
        //toString
        stringBuffer.toString();
        //length
        stringBuffer.length();
    }

    public static void stringClassDemo() {
        //join
        String[] howAreYou = new String[]{"How", "are", "you", "?"};
        String myStr = String.join(" ", howAreYou);
        System.out.println(myStr);

        //format
        String formatStr = String.format("My name is %s, I am %d years old.", "Henry", 32);
        System.out.println(formatStr);
    }
}
