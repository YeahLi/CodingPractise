package com.questions.coursera;

import java.util.ArrayList;
import java.util.List;

public class textAlignment {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int left = 0, right = 0;
        int length = 0;

        List<String> result = new ArrayList<>();

        while (right < words.length) {
            int requiredSpaces = right - left;

            if (length + words[right].length() + requiredSpaces <= maxWidth) {
                length += words[right].length();
                right++;
                continue;
            }

            //take [left, right)
            int spaceSlots = right - left - 1;

            StringBuilder sb = new StringBuilder();
            sb.append(words[left]);
            //one word case
            if (spaceSlots == 0) {
                //sb.append(" ".repeat(maxWidth - sb.length()));
            }
            //multiple word case
            else {
                int avgSpaces = (maxWidth - length) / spaceSlots;
                int additionSpaces = (maxWidth - length) % spaceSlots;

                for (int i = left + 1; i < right; i++) {
                    //sb.append(" ".repeat(avgSpaces));
                    if (additionSpaces > 0) {
                        sb.append(" ");
                        additionSpaces--;
                    }
                    sb.append(words[i]);
                }
            }

            //add this line to result
            result.add(sb.toString());
            //move left and clean length
            left = right;
            length = 0;
        }

        //handle right==words.length case
        StringBuilder sb = new StringBuilder();
        sb.append(words[left]);
        for (int i = left + 1; i < right; i++) {
            sb.append(" ").append(words[i]);
        }
        //sb.append(" ".repeat(maxWidth - sb.length()));
        result.add(sb.toString());

        return result;
    }
}
