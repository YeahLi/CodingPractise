package com.questions.coursera;

public class SubstringMatch {

    public static boolean exactMatch(String test, String query) {
        return test.contains(query);
    }

    public static boolean wildCardMatch(String test, String query) {
        for (int i = 0; i < test.length(); i++) {
            if (test.charAt(i) == query.charAt(0) || query.charAt(0) == '.') {
                boolean result = wildCardStringMatch(test, query, i, 0);
                if (result) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean wildCardStringMatch(String test, String query, int i, int j) {
        if(j >= query.length()) {
            return true;
        }

        if (i >= test.length() && j < query.length()) {
            return false;
        }

        boolean isCharMatch = test.charAt(i) == query.charAt(j) || query.charAt(j) == '.';
        if (isCharMatch) {
            return wildCardStringMatch(test, query, i+1, j+1);
        }

        return false;
    }

    public static boolean regexMatch(String test, String query) {
        for (int i = 0; i < test.length(); i++) {
            if(test.charAt(i) == query.charAt(0) || query.charAt(0) == '.') {
                boolean result = regexStringMatch(test,query, i, 0);
                if(result) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean regexStringMatch(String test, String query, int i, int j) {
        if(j >= query.length()) {
            return true;
        }

        if(i >= test.length() && j < query.length()) {
            return false;
        }

        boolean hasQuestion = j + 1 < query.length() && query.charAt(j+1) == '?';
        if (hasQuestion && j + 2 < query.length() && query.charAt(j+2) == '?') {
            return false;
        }

        boolean isCharMatch = test.charAt(i) == query.charAt(j) || query.charAt(j) == '.';
        if (hasQuestion) {
            return (isCharMatch && regexStringMatch(test, query, i+1, j+2)) || regexStringMatch(test, query, i, j+2);
        }

        if (isCharMatch) {
            return regexStringMatch(test, query, i+1, j+1);
        }
        return false;
    }

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        String text = "apple";
        String query = "ppl";
        System.out.println(regexMatch(text, query));//true

        query = "p.l";
        System.out.println(regexMatch(text, query));//true

        query = "p.a";
        System.out.println(regexMatch(text, query));//false

        query = "p?ppl";
        System.out.println(regexMatch(text, query));//true

        query = "p?pl";
        System.out.println(regexMatch(text, query));//true

        query = "pp?pl";
        System.out.println(regexMatch(text, query));//true

        query = "p.?l";
        System.out.println(regexMatch(text, query));//true

        query = "p.????l";
        System.out.println(regexMatch(text, query));//false

        query = ".?";
        System.out.println(regexMatch(text, query));//true
    }
}
