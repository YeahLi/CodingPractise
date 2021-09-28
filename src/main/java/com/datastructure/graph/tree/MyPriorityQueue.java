package com.datastructure.graph.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 能保证每次取出的元素都是队列中优先级别最高的。优先级别可以是自定义的，例如，数据的数值越大，优先级越高；或者数据的数值越小，优先级越高。优先级别甚至可以通过各种复杂的计算得到。
 * <p>
 * 优先队列的本质是一个二叉堆结构。堆在英文里叫 Binary Heap，它是利用一个数组结构来实现的完全二叉树， 即二叉树的顺序存储。
 */
public class MyPriorityQueue {

    public static void main(String[] args) {
        // Top K 问题
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        System.out.println(findKthLargest(nums, k)); //5
        System.out.println(findKthSmallest(nums, k)); //3

        // 从一堆杂乱无章的数据当中按照一定的顺序（或者优先级）逐步地筛选出部分乃至全部的数据。
        // 空会议室问题
        // 取两个最小的合并再放入

        //按顺序找出前 K 大的数, 从小到大排列
        System.out.println(findKLargestNums(nums, k));
    }

    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); //默认为小顶堆
        for (int num : nums) {
            pq.offer(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.poll();
    }

    public static int findKthSmallest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> y - x); //大顶堆
        for (int num : nums) {
            pq.offer(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.poll();
    }

    public static List<Integer> findKLargestNums(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); //小顶堆
        for (int num : nums) {
            pq.offer(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        List<Integer> result = new LinkedList<>();
        while (!pq.isEmpty()) {
            result.add(pq.poll());
        }
        return result;
    }
}
