package com.questions.wayfair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CommonAncestor {
    public static void main(String[] args) {
        int[][] input = {{1,4}, {1,5}, {2,5}, {3,6}, {6,7}};

        List<List<Integer>> res = getZeroAndOneParentNodes(input);
        res.forEach(System.out::println);

        System.out.println(hasCommonAncestor(input, 4, 5)); //true
        System.out.println(hasCommonAncestor(input, 5, 7)); //false
        System.out.println(hasCommonAncestor(input, 3, 7)); //true
        System.out.println(hasCommonAncestor(input, 5, 1)); //true
        System.out.println(hasCommonAncestor(input, 4, 2)); //false

        System.out.println(find(5));//[1,2]
        System.out.println(find(7));//[3]
    }

    public static List<List<Integer>> getZeroAndOneParentNodes(int[][] input) {
        //return indegree equals 0 and 1
        //1. build indegree map
        Map<Integer, Integer> indegreeMap = new HashMap<>();
        for (int[] edge : input) {
            int parent = edge[0];
            int child = edge[1];

            if (!indegreeMap.containsKey(parent)) {
                indegreeMap.put(parent, 0);
            }

            int count = indegreeMap.getOrDefault(child, 0);
            indegreeMap.put(child, count + 1);
        }

        //2. find out required data
        List<Integer> noParentList = new ArrayList<>();
        List<Integer> oneParentList = new ArrayList<>();

        for (int node : indegreeMap.keySet()) {
            int count = indegreeMap.get(node);
            if (count == 0) {
                noParentList.add(node);
            } else if (count == 1) {
                oneParentList.add(node);
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        res.add(noParentList);
        res.add(oneParentList);
        return res;
    }

    public static Map<Integer, Set<Integer>> parentMap;
    public static boolean hasCommonAncestor(int[][] input, int x, int y) {
        buildParentMap(input);

        Set<Integer> parentX = find(x);
        Set<Integer> parentY = find(y);
        parentX.retainAll(parentY);
        return parentX.size() > 0;
    }

    private static void buildParentMap(int[][] input) {
        parentMap = new HashMap<>();
        for (int[] edge : input) {
            int parent = edge[0];
            int child = edge[1];

            if (!parentMap.containsKey(parent)) {
                Set<Integer> set = new HashSet<>();
                set.add(parent);
                parentMap.put(parent, set);
            }

            Set<Integer> set = parentMap.getOrDefault(child, new HashSet<>());
            set.remove(child);
            set.add(parent);
            parentMap.put(child, set);
        }
    }

    //dfs
    public static Set<Integer> find(int x) {
        if (!parentMap.containsKey(x)) {
            Set<Integer> set = new HashSet<>();
            set.add(x);
            return set;
        }

        if (parentMap.get(x).contains(x)) {
            return parentMap.get(x);
        }

        Set<Integer> parentSet = parentMap.get(x);
        Set<Integer> set = new HashSet<>();
        for (int parent : parentSet) {
            set.addAll(find(parent));
        }
        parentMap.put(x, set);
        return set;
    }
}
