//package com.template;
//
///**
// * 使用场景:
// * 1. 找满足某个条件的所有方案(99%)
// * 2. 二叉树 Binary Tree 的问题(90%) O(n)
// * 3. 组合问题(95%) O(2^n * n) 652
// *  - 问题模型: 求出所有满足条件的组合 subset问题
// *  - 判断条件: 组合中的元素时顺序无关的
// * 4. 排列问题(95%) 时间复杂度 O(n! * n)
// *  - 问题模型: 求出所有满足条件的"排列"
// *  - 判断条件: 组合中的元素是顺序"相关"的
// */
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 不要用 DFS 的场景
// * 1. 连通块问题 (一定要用 BFS, 否则会 Stack Overflow)
// * 2. Topology sort (否则 Stack Overflow)
// * 3. 一切 BFS 可以解决的问题
// */
//public class DFS {
//    public RetrunType dfs(参数列表) {
//        if (递归出口) {
//            记录答案;
//            return;
//        }
//
//        for (所有拆解可能性) {
//            修改所有的参数;
//            dfs(参数列表);
//            还原所有被修改过的参数; //backtrace
//        }
//
//        return something 如果需要的话,很多时候不需要 return 值除了 divide and conquer 的写法
//    }
//
//    private void dfs(int[] nums, int startPos, List<Integer> list, List<List<Integer>> result, boolean[] visited) {
//        if (list.size() > nums.length) { //startPos >= nums.length
//            result.add(new ArrayList<>(list));
//            return;
//        }
//
//        for (int i = startPos; i < nums.length; i++) {
//            if (visited[i]) {
//                continue;
//            }
//
//            list.add(i);
//            visited[i] = true;
//
//            dfs(nums, i+1, list, result, visited); //看要不要求重复使用,如果是传入 i
//
//            visited[i] = false;
//            list.remove(list.size() - 1);
//        }
//    }
//}
//
