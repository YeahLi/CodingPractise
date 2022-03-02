package com.template;

public class Sorting {
    public void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length-1);
    }

    private void quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }

        int left = start;
        int right = end;
        int privot = nums[(left + right)/2];

        while (left <= right) {
            //no equal here
            while (left <= right && nums[left] < privot) {
                left++;
            }

            while (left <= right && nums[right] > privot) {
                right--;
            }

            if (left <= right) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;

                left++;
                right--;
            }
        }
        quickSort(nums, start, right);
        quickSort(nums, left, end);
    }

    public void mergeSort(int[] nums) {
        int[] temp = new int[nums.length];
        mergeSort(nums, 0, nums.length-1, temp);
    }

    private void mergeSort(int[] nums, int start, int end, int[] temp) {
        if (start >= end) {
            return;
        }

        int mid = (start + end) / 2;
        mergeSort(nums, start, mid, temp);
        mergeSort(nums,mid+1, end, temp);
        merge(nums, start, end, temp);
    }

    private void merge(int[] nums, int start, int end, int[] temp) {
        int mid = (start + end) / 2;
        int i = start;
        int j = mid+1;
        int idx = start;
        while (i <= mid && j <= end) {
            if (nums[i] <= nums[j]) {
                temp[idx] = nums[i];
                i++;
            } else {
                temp[idx] = nums[j];
                j++;
            }
            idx++;
        }

        while (i <= mid && idx <= end) {
            temp[idx] = nums[i];
            idx++;
            i++;
        }

        while (j <= end && idx <= end) {
            temp[idx] = nums[j];
            idx++;
            j++;
        }

        for (int k = start; k <= end ; k++) {
            nums[k] = temp[k];
        }

    }
}
