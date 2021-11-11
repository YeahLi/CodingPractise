package com.algorithm;

import java.util.Arrays;

public class CoinChange {
    /**
     * 1. build up an array arr which can change from 1 to max(arr)
     * 2. If num = currentMaxChange(arr)+1, then it can change from 1 to currentMaxChange(arr) + num
     *
     * @param coins
     * @return
     */
    public int nonConstructibleChange(int[] coins) {
        if (coins == null || coins.length == 0) {
            return 1;
        }
        // Sort by accending
        Arrays.sort(coins);

        int currentMaxChange = 0;
        for (int coin : coins) {
            if (currentMaxChange + 1 < coin) {
                return currentMaxChange + 1;
            }
            currentMaxChange += coin;
        }
        return currentMaxChange + 1;
    }
}
