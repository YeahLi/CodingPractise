package com.questions.amazon;

public class SwapZeroOne {
    /**
     * @param nums: an Integer array
     * @return: return the minimum number of swaps.
     */
    public int SwapZeroOne(int[] nums) {
        // Write your code here
        int i = 0;
        int j = i + 1;
        int count = 0;
        while (i < nums.length) {
            if (nums[i] == 1) {
                if (j <= i) {
                    j = i + 1;
                }
                while (j < nums.length) {
                    if (nums[j] == 0) {
                        int swaps = j - i;
                        count += swaps;
                        swap(nums, i, j);
                        break;
                    }
                    j++;
                }
                if (j == nums.length) {
                    break;
                }
            }
            i++;
        }
        return count;
    }

    private void swap(int[] nums, int start, int end) {
        for (int i = end; i > start; i--) {
            int j = i - 1;
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
