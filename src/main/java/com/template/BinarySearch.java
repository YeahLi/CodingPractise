package com.template;

/**
 * 使用条件:
 * 1. sorted list (30% - 40% 是二分)
 * 2. 面试官要求找一个比 O(n) 更小的时间复杂度算法的时候(99%)
 * 3. 找到数组一个分割位置,使得左半部分满足某个条件,右半部分不满足(100%)
 * 4. 找到一个first/last的值使得某个条件被满足(90%)
 */
public class BinarySearch {

    public static int binarySearch(int[] nums, int target) {
        //corner case 处理
        if (nums == null || nums.length < 1) {
            return -1;
        }

        int start = 0, end = nums.length-1;

        //要点1 : start + 1 < end
        while (start + 1 < end) {
            //要点2: mid = start + (end - start)/2
            int mid = start + (end - start)/2;

            //要点3: mid 不加 1 也不减 1
            if (nums[mid] < target) {
                start = mid;
            } else if (nums[mid] > target) {
                end = mid;
            } else {
                return mid;
            }
        }

        // 要点 4: 循环结束后单独处理 start 和 end
        if (nums[start] == target) {
            return start;
        }

        if (nums[end] == target) {
            return end;
        }

        return -1;
    }

    public int myBinarySearch(int[] nums, int target) {
        //corner case
        if (nums == null || nums.length < 1) {
            return -1;
        }

        int start = 0, end = nums.length-1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if(nums[mid] < target) {
                start = mid + 1;
            } else if(nums[mid] > target) {
                end = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        int result = binarySearch(nums, 5);
        System.out.println(result);
    }
}
