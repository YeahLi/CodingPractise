package com.questions.amazon;

import java.util.PriorityQueue;

public class AmazonFulfillmentBuilder {

    /**
     * O(NlogN)
     *
     * @param parts
     * @return
     */
    public static int minTimeToAssembleAllParts(int[] parts) {
        if (parts.length <= 1) {
            return 0;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int partSize : parts) {
            pq.offer(partSize);
        }

        int result = 0;
        while (pq.size() > 1) {
            int part1 = pq.poll();
            int part2 = pq.poll();
            int newPart = part1 + part2;
            result += newPart;
            pq.offer(newPart);
        }

        return result;
    }


    public static void main(String[] args) {
        System.out.println(minTimeToAssembleAllParts(new int[]{8, 4, 6, 12}));
    }
}
