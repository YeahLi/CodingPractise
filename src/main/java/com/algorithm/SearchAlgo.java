package com.algorithm;

public class SearchAlgo {
    // 1. binary search
    // a. recursive -- O(logN) O(logN)
    public static int binarySearch(int[] array, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = left + right;
        if (target == array[mid]) {
            return mid;
        } else if (target < array[mid]) {
            return binarySearch(array, target, left, mid - 1);
        } else {
            return binarySearch(array, target, mid + 1, right);
        }
    }

    // b. while -- O(logN) O(1)
    public static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (target == array[mid]) {
                return mid;
            } else if (target < array[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }

    // 2. Find Closest Value in BST
    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
        }
    }

    // while -- Avg: O(logN) O(1) Worst: O(N) O(1)
    public static int findClosestValueInBst(BST tree, int target) {
        int closest = Integer.MAX_VALUE;
        int min = Integer.MAX_VALUE;

        BST node = tree;
        while (node != null) {
            int diff = Math.abs(target - node.value);
            if (diff < min) {
                min = diff;
                closest = node.value;
            }
            if (target < node.value) {
                node = node.left;
            } else if (target > node.value) {
                node = node.right;
            } else {
                return target;
            }
        }
        return closest;
    }

    // Recursive -- Avg: O(logN) O(logN) Worst: O(N) O(N)
    public static int findClosestValueInBst(BST tree, int target, int closest) {
        if (Math.abs(tree.value - target) < closest) {
            closest = Math.abs(tree.value - target);
        }
        if (target < tree.value && tree.left != null) {
            return findClosestValueInBst(tree.left, target, closest);
        } else if (target > tree.value && tree.right != null) {
            return findClosestValueInBst(tree.right, target, closest);
        } else {
            return closest;
        }
    }


}
