package com.template;

import java.util.ArrayList;

/**
 * O(n) O(1)
 * 使用条件:
 * 1. 滑动窗口: 这类题目脱离不开主串（主数组）和子串（子数组）的关系，要求的时间复杂度往往是 O(n)，空间复杂度往往是常数级的。之所以是滑动窗口，是因为，遍历的时候，两个指针一前一后夹着的子串（子数组）类似一个窗口，这个窗口大小和范围会随着前后指针的移动发生变化。
 * 2. 时间复杂度要求 O(n) (80% 是双指针)
 * 3. 要求原地操作,不能使用额外空间(80%)
 * 4. 有子数组 subarray/子字符串 substring 的关键词(50%)
 * 5. 有回文 Palindrome关键词(50%)
 */
public class TwoPointers {
    // 相向双指针(partition in quicksort)
    public void partition(int[] A, int start, int end) {
        if (start >= end) {
            return;
        }

        int left = start, right = end;

        //key point 1: pivot is the value, not the index
        int pivot = A[(start + end) / 2];

        //key point 2: every time you compare left & right, it should be left <= right not left < right
        while (left <= right) {

            //no equal here
            while (left <= right && A[left] < pivot) {
                left++;
            }

            while (left <= right && A[right] > pivot) {
                right--;
            }

            if (left <= right) {
                int temp = A[left];
                A[left] = A[right];
                A[right] = temp;
                left++;
                right--;
            }
        }
    }

    // 背向双指针 (find k closest value to target )
    public int[] findKClosestValues(int[] A, int idx, int K, int target) {
        int[] result = new int[K];
        int count = 0;

        int left = idx;
        int right = idx+1;

        while (count < K) {
            if (left < 0) {
                result[count] = A[right];
                right++;
            } else if (right >= A.length) {
                result[count] = A[left];
                left--;
            } else {
                int diffLeft = target - A[left];
                int diffRight = A[right] - target;
                if (diffLeft <= diffRight) {
                    result[count] = left;
                    left--;
                } else {
                    result[count] = right;
                    right++;
                }
            }

            count++;
        }
        return result;
    }

    //同向双指针: 窗口问题, reverse link list
    public void sameDirection(int[] A) {
        boolean condition = true;
        int j = 0;
        for (int i = 0; i < A.length; i++) {
            //不满足则循环到满足搭配为止
            while (j < A.length && !condition) {
                j++;
            }

            if (condition) {
                //处理 i, j 这次搭配
            }
        }
    }

    //合并双指针: merge sort
    ArrayList<Integer> merge(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        // 需要 new 一个新的 list, 而不是在 list1 或者 list2 上直接改动
        ArrayList<Integer> newList = new ArrayList<>(list1.size() + list2.size());
        int i = 0;
        int j = 0;

        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i) <= list2.get(j)) {
                newList.add(list1.get(i));
                i++;
            } else {
                newList.add(list2.get(j));
                j++;
            }
        }

        while (i < list1.size()) {
            newList.add(list1.get(i));
            i++;
        }

        while (j < list2.size()) {
            newList.add(list2.get(j));
            j++;
        }

        return newList;
    }
}
