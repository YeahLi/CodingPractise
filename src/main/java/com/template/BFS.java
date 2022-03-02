//package com.template;
//
///**
// * 使用条件:
// * 1. Topology sort(100%)
// * 2. 出现连通块的关键词(100%)
// * 3. 分层遍历(100%)
// * 4. 简单图最短路径(100%)
// * 5. 给定一个变换规则, 从初始状态变到终止状态最少几步(100%)
// */
//
//import java.util.ArrayDeque;
//import java.util.ArrayList;
//import java.util.Deque;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
////O(n+m) O(n)
//public class BFS {
//
//    static class Node {
//        int value;
//    }
//
//    //keypoint 1: 添加入队列时就要标记该node 已经被访问过. 以免以后重复加入队列
//    ReturnType bfs(Node node) {
//        Deque<Node> queue = new ArrayDeque<>();
//        //hashmap 有两个作用, 一个是记录一个点是否被丢进队列了,避免重复访问
//        //二是记录 startNode 到其他所有节点的最短距离
//        //如果只求连通性的话,可以换成 HashSet 就行
//        //node 做 key 的时候比较的是内存地址
//        Map<Node, Integer> distance = new HashMap<>();
//
//        queue.offer(node);
//        map.put(node, 0);
//        int count = 1;
//        //while 队列不空,不停地从队列里拿出一个点,拓展邻居节点放到队列中去
//        while (!queue.isEmpty()) {
//            Node n = queue.poll();
//
//            //如果有明确的终点可以在这里加终点的判断
//            if (node 是终点) {
//                break or return something
//            }
//
//            for(Node neighbour : node.neighbours()) {
//                //跳过已经 visit 过的 node
//                if(distance.containsKey(neighbour)) {
//                    continue;
//                }
//
//                queue.offer(neighbour);
//                distance.put(neighbour, distance.get(node)+1);
//            }
//        }
//
//        //如果需要返回所有点离起点的距离, 就 return hashmap
//        return distance;
//
//        //如果需要返回所有连通的节点, 就 return Hashmap 里的所有点
//        return distance.keySet();
//
//        //如果需要返回离终点的最短距离
//        return distance.get(endNode);
//    }
//
//    List<Integer> topologySort(int[][] dependencies) {
//        //1. build up a graph and calculate indegrees
//        Map<Integer, List<Integer>> graph = new HashMap<>(); //key is node id, value is a list of out nodes
//        Map<Integer, Integer> indegreeMap = new HashMap<>(); // key is node id
//
//        for (int[] dependecy : dependencies) {
//            if (graph.containsKey(dependecy[1])) {
//                List<Integer> list = graph.get(dependecy[1]);
//                list.add(dependecy[0]);
//            } else {
//                List<Integer> list = new ArrayList<>();
//                list.add(dependecy[0]);
//                graph.put(dependecy[1], list);
//            }
//
//            int indegree = indegreeMap.getOrDefault(dependecy[0], 0);
//            indegreeMap.put(dependecy[0], indegree+1);
//
//            if (!indegreeMap.containsKey(dependecy[1])) {
//                indegreeMap.put(dependecy[1], 0);
//            }
//        }
//
//        //2. Add nodes whose indegree is zero into a queue
//        Deque<Integer> queue = new ArrayDeque<>();
//        for (int key : indegreeMap.keySet()) {
//            if (indegreeMap.get(key) == 0) {
//                queue.offer(key);
//            }
//        }
//
//        //3. BFS queue
//        List<Integer> result = new ArrayList<>();
//        while (!queue.isEmpty()) {
//            int node = queue.poll();
//
//            result.add(node);
//
//            for (int neighour : graph.get(node)) {
//                int indegree = indegreeMap.get(neighour) - 1;
//                indegreeMap.put(neighour, indegree);
//                if (indegree == 0) {
//                    queue.offer(neighour);
//                }
//            }
//        }
//
//        if (result.size() < indegreeMap.size()) {
//            return new ArrayList<>();
//        }
//        return result;
//    }
//}
