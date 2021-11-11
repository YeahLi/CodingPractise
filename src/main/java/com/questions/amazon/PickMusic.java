package com.questions.amazon;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PickMusic {
    static class Pair {
        int index1;
        int index2;

        int length1;
        int length2;

        int maxLength;

        public Pair(int index1, int index2, int length1, int length2) {
            this.index1 = index1;
            this.index2 = index2;
            this.length1 = length1;
            this.length2 = length2;
            this.maxLength = Math.max(length1, length2);
        }
    }

    public static int[] pickMusic(int driveDuration, int[] songTime) {
        Map<Integer, Integer> indexMap = new HashMap<>();

        Set<Pair> pairs = new HashSet<>();
        for (int i = 0; i < songTime.length; i++) {
            int num = driveDuration - songTime[i] - 30;

            if (indexMap.containsKey(num)) {
                Pair pair = new Pair(indexMap.get(num), i, num, songTime[i]);
                pairs.add(pair);
            }

            if (!indexMap.containsKey(songTime[i])) {
                indexMap.put(songTime[i], i);
            }
        }
        Pair pair = Collections.max(pairs, Comparator.comparingInt(pair2 -> pair2.maxLength));

        int[] result = new int[2];
        result[0] = pair.index1;
        result[1] = pair.index2;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(pickMusic(90, new int[]{20, 40, 50, 10})));
    }
}
