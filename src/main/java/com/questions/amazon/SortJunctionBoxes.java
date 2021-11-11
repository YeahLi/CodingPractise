package com.questions.amazon;

import java.util.ArrayList;
import java.util.List;

public class SortJunctionBoxes {
    public static List<String> sortBoxes(List<String> boxList) {
        List<String> oldBoxes = new ArrayList<>();
        List<String> newBoxes = new ArrayList<>();

        // separate old boxes and new boxes
        for (String identifier : boxList) {
            if (isNewBoxes(identifier)) {
                newBoxes.add(identifier);
            } else {
                oldBoxes.add(identifier);
            }
        }

        // sort old boxes according to the rule
        oldBoxes.sort((str1, str2) -> {
            String[] words1 = str1.split(" ");
            String[] words2 = str2.split(" ");

            int length = Math.min(words1.length, words2.length);
            for (int i = 1; i < length; i++) {
                int compareResult = words1[i].compareTo(words2[i]);
                if (compareResult != 0) {
                    return compareResult;
                }
            }

            if (words1.length != words2.length) {
                return words1.length - words2.length;
            } else {
                return words1[0].compareTo(words2[0]);
            }

        });

        // add new boxes into sorted old boxes
        for (String newBox : newBoxes) {
            oldBoxes.add(newBox);
        }

        // return oldBoxes
        return oldBoxes;
    }

    private static boolean isNewBoxes(String identifier) {
        String[] words = identifier.split(" ");
        try {
            Integer.parseInt(words[1]);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        List<String> boxList = new ArrayList<>();
        String box1 = "ykc 82 01";
        String box2 = "eo first qpx";
        String box3 = "09z cat hamster";
        String box4 = "06f 12 25 6";
        String box5 = "az0 first qpx";
        String box6 = "236 cat dog rabbit snake";
        boxList.add(box1);
        boxList.add(box2);
        boxList.add(box3);
        boxList.add(box4);
        boxList.add(box5);
        boxList.add(box6);

        System.out.println("Input: " + boxList);
        System.out.println("Output: " + sortBoxes(boxList));
    }
}
