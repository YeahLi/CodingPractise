package com.basics.graph.tree.binarytree.binaryheap;

import java.util.ArrayList;
import java.util.Comparator;
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
        //按顺序找出前 K 大的数, 从小到大排列
        System.out.println(findKLargestNums(nums, k));

        // 从一堆杂乱无章的数据当中按照一定的顺序（或者优先级）逐步地筛选出部分乃至全部的数据。
        // 空会议室问题: 给定一系列会议的起始时间和结束时间，求最少需要多少个会议室就可以让这些会议顺利召开。
        // Greedy Algorithm: 取两个最小的合并再放入
        int[][] intervals = new int[3][];
        intervals[0] = new int[]{0, 30};
        intervals[1] = new int[]{5, 10};
        intervals[2] = new int[]{15, 20};
        System.out.println("Num of meeting rooms: " + minMeetingRooms(intervals));

        intervals = new int[2][];
        intervals[0] = new int[]{7, 10};
        intervals[1] = new int[]{2, 4};
        System.out.println("Num of meeting rooms: " + minMeetingRooms(intervals));
    }

    public static int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            throw new RuntimeException("Invalid input");
        }

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
        if (nums == null || nums.length < k) {
            throw new RuntimeException("Invalid input");
        }

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
        if (nums == null || nums.length < k) {
            throw new RuntimeException("Invalid input");
        }

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

    public static int minMeetingRooms(int[][] intervals) {
        class Interval {
            final int start;
            final int end;

            public Interval(int start, int end) {
                this.start = start;
                this.end = end;
            }
        }

        // Input validation
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        // data transformation
        List<Interval> intervalList = new ArrayList<>();
        for (int[] interval : intervals) {
            Interval i = new Interval(interval[0], interval[1]);
            intervalList.add(i);
        }

        // 1. 将一系列会议按照起始时间排序
        intervalList.sort(Comparator.comparingInt(interval -> interval.start));

        // 2. 让第一个会议在第一个会议室进行, pq 按照结束时间排序
        PriorityQueue<Interval> pq = new PriorityQueue<>(Comparator.comparingInt(interval -> interval.end));
        pq.offer(intervalList.get(0));

        // 3. 从第二个会议开始, 我们每次从 pq 中取出结束最早的 interval1, 用 interval1.end 与 intervals[i].start 比较:
        //  如果新会议在 interval1 结束后开始, 合并这两个会议放入 pq 中 ==> 可以共用一个会议室
        //  如果新会议在 interval1 结束前开始, 那么需要一间新的会议室, 把新会议直接放入 pq. 同时不要忘了放回 interval1
        for (int i = 1; i < intervalList.size(); i++) {
            Interval interval1 = pq.poll();
            Interval interval2 = intervalList.get(i);

            if (interval1.end <= interval2.start) {
                Interval newInterval = new Interval(interval1.start, interval2.end);
                pq.offer(newInterval);
            } else {
                pq.offer(interval2);
                pq.offer(interval1); //记得把 1 也要放回去哦
            }
        }

        // 4. pq 的大小即为需要会议室的数量
        return pq.size();
    }
}
