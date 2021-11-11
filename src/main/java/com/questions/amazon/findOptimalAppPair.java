package com.questions.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class findOptimalAppPair {

    //nlog(n)
    public static List<List<Integer>> applicaitonPairs(int deviceCapacity, List<List<Integer>> foregroundAppList, List<List<Integer>> backgroundAppList) {
        List<List<Integer>> result = new ArrayList<>();

        // sort foreground and background app lists
        Comparator<List<Integer>> comparator = Comparator.comparingInt(app -> app.get(1));
        foregroundAppList.sort(comparator);
        backgroundAppList.sort(comparator);

        // two sum and binary search to find the optimal number;
        int maxMem = Integer.MIN_VALUE;
        for (List<Integer> foregroundApp : foregroundAppList) {
            int target = deviceCapacity - foregroundApp.get(1);
            int searchResult = binarySearchForOptimalId(backgroundAppList, target); //background app index

            if (searchResult == -1) {
                continue;
            } else {
                List<Integer> backgroundApp = backgroundAppList.get(searchResult);
                int mem = backgroundApp.get(1) + foregroundApp.get(1);
                if (mem > maxMem) {
                    maxMem = mem;
                    result.clear();

                    List<Integer> pair = new ArrayList<>();
                    pair.add(foregroundApp.get(0));
                    pair.add(backgroundApp.get(0));
                    result.add(pair);
                } else if (mem == maxMem) {
                    List<Integer> pair = new ArrayList<>();
                    pair.add(foregroundApp.get(0));
                    pair.add(backgroundApp.get(0));
                    result.add(pair);
                }
            }
        }

        // if no available pair, add an empty list
        if (result.isEmpty()) {
            List<Integer> pair = new ArrayList<>();
            result.add(pair);
        }
        return result;
    }

    private static int binarySearchForOptimalId(List<List<Integer>> appList, int target) {
        int low = 0;
        int high = appList.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (appList.get(mid).get(1) < target) {
                low = mid + 1;
            } else if (appList.get(mid).get(1) > target) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        if (high < 0) {
            return -1;
        } else {
            return high;
        }
    }

    public static void main(String[] args) {
        testExample1();
        testExample2();
        testExample3();
    }

    public static void testExample1() {
        Integer[] foregroundApp1 = new Integer[]{1, 2};
        Integer[] foregroundApp2 = new Integer[]{2, 4};
        Integer[] foregroundApp3 = new Integer[]{3, 6};
        List<List<Integer>> foregroundAppList = new ArrayList<>();
        foregroundAppList.add(Arrays.asList(foregroundApp1));
        foregroundAppList.add(Arrays.asList(foregroundApp2));
        foregroundAppList.add(Arrays.asList(foregroundApp3));

        Integer[] backgroundApp1 = new Integer[]{1, 2};
        List<List<Integer>> backgroundAppList = new ArrayList<>();
        backgroundAppList.add(Arrays.asList(backgroundApp1));

        System.out.println(applicaitonPairs(7, foregroundAppList, backgroundAppList));
    }

    public static void testExample2() {
        Integer[] foregroundApp1 = new Integer[]{1, 3};
        Integer[] foregroundApp2 = new Integer[]{2, 5};
        Integer[] foregroundApp3 = new Integer[]{3, 7};
        Integer[] foregroundApp4 = new Integer[]{4, 10};
        List<List<Integer>> foregroundAppList = new ArrayList<>();
        foregroundAppList.add(Arrays.asList(foregroundApp1));
        foregroundAppList.add(Arrays.asList(foregroundApp2));
        foregroundAppList.add(Arrays.asList(foregroundApp3));
        foregroundAppList.add(Arrays.asList(foregroundApp4));

        Integer[] backgroundApp1 = new Integer[]{1, 2};
        Integer[] backgroundApp2 = new Integer[]{2, 3};
        Integer[] backgroundApp3 = new Integer[]{3, 4};
        Integer[] backgroundApp4 = new Integer[]{4, 5};
        List<List<Integer>> backgroundAppList = new ArrayList<>();
        backgroundAppList.add(Arrays.asList(backgroundApp1));
        backgroundAppList.add(Arrays.asList(backgroundApp2));
        backgroundAppList.add(Arrays.asList(backgroundApp3));
        backgroundAppList.add(Arrays.asList(backgroundApp4));

        System.out.println(applicaitonPairs(10, foregroundAppList, backgroundAppList));
    }

    public static void testExample3() {
        Integer[] foregroundApp1 = new Integer[]{2, 7};
        Integer[] foregroundApp2 = new Integer[]{3, 14};
        List<List<Integer>> foregroundAppList = new ArrayList<>();
        foregroundAppList.add(Arrays.asList(foregroundApp1));
        foregroundAppList.add(Arrays.asList(foregroundApp2));

        Integer[] backgroundApp1 = new Integer[]{2, 10};
        Integer[] backgroundApp2 = new Integer[]{3, 14};
        List<List<Integer>> backgroundAppList = new ArrayList<>();
        backgroundAppList.add(Arrays.asList(backgroundApp1));
        backgroundAppList.add(Arrays.asList(backgroundApp2));

        System.out.println(applicaitonPairs(16, foregroundAppList, backgroundAppList));
    }
}
