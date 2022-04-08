package com.questions.coursera;

public class SubstringMatch {

    public static boolean regexMatch(String test, String query) {
        for (int i = 0; i < test.length(); i++) {
            if (subStringMatch(test, query, i, 0)) {
                return true;
            }
        }
        return false;
    }

    private static boolean subStringMatch(String test, String query, int i, int j) {
        if (j >= query.length()) {
            return true;
        }

        if (query.charAt(j) == '?') {
            return false;
        }

        boolean hasQuestion = j + 1 < query.length() && query.charAt(j + 1) == '?';
        boolean isMatch = i < test.length() && (test.charAt(i) == query.charAt(j) || query.charAt(j) == '.');

        if (hasQuestion) {
            return subStringMatch(test, query, i, j + 2) || (isMatch && subStringMatch(test, query, i + 1, j + 2));
        }

        if (isMatch) {
            return subStringMatch(test, query, i + 1, j + 1);
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
