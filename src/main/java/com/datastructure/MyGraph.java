package com.datastructure;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyGraph {

    public static void main(String[] args) {
        directedMapDemo();

    }

    private char[] vertices;
    private int[][] edges;
    private boolean isDirected;

    public MyGraph(char[] vertices, Map<String, Integer> edges, boolean isDirected) {
        this.isDirected = isDirected;
        this.vertices = vertices;
        this.edges = new int[this.vertices.length][this.vertices.length];

        for (String key : edges.keySet()) {
            char[] chars = key.toCharArray();
            int from = chars[0] - 'A';
            int to = chars[3] - 'A';

            this.edges[from][to] = edges.get(key);

            if (!isDirected) {
                this.edges[to][from] = edges.get(key);
            }
        }
    }

    public static void directedMapDemo() {
        // directed map
        char[] vertices = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        Map<String, Integer> edges = new HashMap<>();
        edges.put("A->B", 1);
        edges.put("A->C", 1);
        edges.put("B->D", 2);
        edges.put("B->E", 4);
        edges.put("C->B", 1);
        edges.put("C->E", 3);
        edges.put("D->F", 1);
        edges.put("E->F", 1);

        MyGraph graph = new MyGraph(vertices, edges, true);

        List<Character> result = graph.dfs();
        System.out.println(result); // A, B, D, F, E, C, G

        result = graph.bfs(); // A, B, C, D, E, F, G
        System.out.println(result);

        System.out.println("Number of sub-graphs: " + graph.numOfSubgraph()); //2

        System.out.println(graph.isConnected('C', 'D'));
        System.out.println(graph.isConnected('D', 'C'));


        // Cycle problems
        System.out.println(graph.isCyclic()); //false

        //graph 2
        Map<String, Integer> edges2 = new HashMap<>();
        edges2.put("A->B", 1);
        edges2.put("A->C", 1);
        edges2.put("C->D", 1);
        edges2.put("C->B", 1);

        MyGraph graph2 = new MyGraph(vertices, edges2, true);
        System.out.println(graph2.isCyclic()); //false

        //graph 3
        Map<String, Integer> edges3 = new HashMap<>();
        edges3.put("A->B", 1);
        edges3.put("B->C", 1);
        edges3.put("C->A", 1);
        edges3.put("C->D", 1);
        edges3.put("D->E", 1);
        edges3.put("E->C", 1);
        edges3.put("E->A", 1);

        edges3.put("A->C", 1);

        MyGraph graph3 = new MyGraph(vertices, edges3, true);
        System.out.println(graph3.isCyclic()); //true

        System.out.println(graph3.getAllCycles()); //[C->D->E, A->B->C, A->B->C->D->E, A->C->D->E, A->C]
    }

    public List<Character> dfs() {
        List<Character> result = new ArrayList<>();

        boolean[] visited = new boolean[vertices.length];

        while(true) {
            // Find a unvisited vertex
            int index = -1;
            for (int i = 0; i < visited.length; i++) {
                if (!visited[i]) {
                    index = i;
                    break;
                }
            }
            // If all vertices have been visited, stop the program
            if (index == -1) {
                break;
            }

            //do DFS
            dfsHelper(result, visited, index);
        }

        return result;
    }

    private void dfsHelper(List<Character> result, boolean[] visited, int i) {
        if (visited[i]) {
            return;
        }
        visited[i] = true;        //DFS 访问元素时更新 visit 数组并添加到结果集
        result.add(vertices[i]);

        for (int j = 0; j < vertices.length; j++) {
            if (edges[i][j] != 0) {
                dfsHelper(result, visited, j);
            }
        }
    }

    public List<Character> bfs() {
        List<Character> result = new ArrayList<>();
        boolean[] visited = new boolean[vertices.length];

        while(true) {
            int index = -1;

            for (int i = 0; i < visited.length; i++) {
                if (!visited[i]) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                break;
            }

            //bfs
            Deque<Integer> queue = new ArrayDeque<>();
            queue.offer(index);                          //BFS 加入 queue 前更新 visit 数组
            visited[index] = true;

            while (!queue.isEmpty()) {
                int i = queue.poll();

                result.add(vertices[i]);                // BFS 访问元素时添加到结果集

                for (int j = 0; j < vertices.length; j++) {
                    if (edges[i][j] != 0 && !visited[j]) {
                        queue.offer(j);
                        visited[j] = true;
                    }
                }
            }
        }

        return result;
    }

    public boolean isConnected(char from, char to) {
        int fromIndex = from - 'A';
        int toIndex = to - 'A';

        boolean[] visited = new boolean[vertices.length];
        return isConnectedHelper(fromIndex, toIndex, visited);
    }

    private boolean isConnectedHelper(int index, int toIndex, boolean[] visited) {
        if (index == toIndex) {
            return true;
        }

        if (visited[index]) {
            return false;
        }

        visited[index] = true;

        boolean result = false;
        for (int i = 0; i < edges[index].length; i++) {
            if (edges[index][i] != 0) {
                result = isConnectedHelper(i, toIndex, visited);
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    public int numOfSubgraph() {
        int count = 0;
        boolean[] visited = new boolean[vertices.length];

        while (true) {
            int index = -1;
            for (int i = 0; i < visited.length; i++) {
                if (!visited[i]) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                break;
            }

            count++;

            //bfs
            Deque<Integer> queue = new ArrayDeque<>();
            queue.offer(index);
            visited[index] = true;

            while (!queue.isEmpty()) {
                int i = queue.poll();
                for (int j = 0; j < edges[i].length; j++) {
                    if (edges[i][j] != 0 && !visited[j]) {
                        queue.offer(j);
                        visited[j] = true;
                    }
                }
            }
        }

        return count;
    }

    public int Dijkstra() {
        return 0;
    }

    public List<Character> topologicalSort() {
        return null;
    }

    public boolean isCyclic() {
        // When should a path has cycle? a node in the middle of a path has been visited.

        boolean[] visited = new boolean[vertices.length];

        while (true) {
            int index = -1;
            for (int i = 0; i < visited.length; i++) {
                if (!visited[i]) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                break;
            }

            //dfs
            Set<Integer> path = new HashSet<>();
            boolean result = isCyclePath(index, path, visited);

            if(result) {
                return true;
            }
        }

        return false;
    }

    private boolean isCyclePath(int i, Set<Integer> path, boolean[] visited) {
        if (path.contains(i)) {
            return true;
        }

        if (visited[i]) {
            return false; //不是没有环, 而是如果有环,其中一个已经检测出来了
        }

        visited[i] = true;
        path.add(i);

        boolean result = false;
        for (int j = 0; j < edges[i].length; j++) {
            if (edges[i][j] != 0) {
                result = isCyclePath(j, path, visited);
                // backtrace
                path.remove(j);
            }
            if (result) {
                return true;
            }
        }
        return false;
    }

    public Set<String> getAllCycles() {
        List<List<Integer>> cycles = new ArrayList<>();

        boolean[] visited = new boolean[vertices.length];

        while (true) {
            int index = -1;
            for (int i = 0; i < visited.length; i++) {
                if (!visited[i]) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                break;
            }

            List<Integer> path = new ArrayList<>();

            //dfs
            List<List<Integer>> result = findCycles(index, path, visited);
            cycles.addAll(result);
        }

        return transformCyclesToResult(cycles);
    }

    private List<List<Integer>> findCycles(int i, List<Integer> path, boolean[] visited) {
        List<List<Integer>> result = new ArrayList<>();

        int index;
        if ( (index = path.indexOf(i)) != -1) {
            List<Integer> list = new ArrayList<>(path.subList(index, path.size()));
            result.add(list);

            path.add(i);

            return result;
        }

        visited[i] = true;
        path.add(i);

        for (int j = 0; j < edges[i].length; j++) {
            if (edges[i][j] != 0) {
                List<List<Integer>> subResult = findCycles(j, path, visited);
                result.addAll(subResult);
                path.remove(path.size() - 1);
            }
        }
        return result;
    }

    private Set<String> transformCyclesToResult(List<List<Integer>> cycles) {
        Set<String> result = new HashSet<>();
        for (List<Integer> cycle : cycles) {
            List<String> chars = new ArrayList<>();;
            for (int i : cycle) {
                char c = (char) ('A' + i);
                chars.add(String.valueOf(c));
            }
            String path = String.join("->", chars);
            result.add(path);
        }
        return result;
    }
}
