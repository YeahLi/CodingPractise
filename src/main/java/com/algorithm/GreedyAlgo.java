package com.algorithm;

import java.util.Arrays;

public class GreedyAlgo {
    // Minimum waiting time - O(nlog(n)) O(1)
    // Class photos
    // Tandem Bicycle
    public int tandemBicycle(int[] redShirtSpeeds, int[] blueShirtSpeeds, boolean fastest) {
        // Write your code here.
        Arrays.sort(redShirtSpeeds);
        Arrays.sort(blueShirtSpeeds);

        int result = 0;
        for (int i = 0; i < redShirtSpeeds.length; i++) {
            int j = fastest ? redShirtSpeeds.length - 1 - i : i;
            int max = Math.max(redShirtSpeeds[i], blueShirtSpeeds[j]);
            result += max;
        }

        return result;
    }
}
