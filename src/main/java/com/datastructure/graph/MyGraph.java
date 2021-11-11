package com.datastructure.graph;

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
        directedGraphDemo();
        undirectedGraphDemo();

        directedGraphCycle();
        undirectedGraphCycle();
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

            if (!this.isDirected) {
                this.edges[to][from] = edges.get(key);
            }
        }
    }

    public static void directedGraphDemo() {

        System.out.println("-------- Directed Graph Demo -----------");

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

        //dfs
        System.out.println("DFS: " + graph.dfs()); // A, B, D, F, E, C, G
        // 打印一个图所有的路径
        System.out.println("All paths" + graph.getAllPathsDirectedGraph('A'));
        // A->F 的所有路径
        System.out.println("A->F paths: " + graph.getAllPathsBetweenTwoVertices('A', 'F'));

        //bfs
        System.out.println("BFS: " + graph.bfs()); // A, B, C, D, E, F, G

        // islands: DFS 和 BFS 都可以做
        System.out.println("Number of sub-graphs: " + graph.numOfSubgraph()); //2

        // Connect problem: DFS 和 BFS 都可以做
        System.out.println("C->D connected? " + graph.isConnected('C', 'D')); //true
        System.out.println("C->D connected? " + graph.isConnected('D', 'C')); //false

        // Shortest path
        System.out.println("Dijkstra: " + graph.dijkstra('A', 'F')); //4

        // Topological sort
        System.out.println("Topology Sort: " + graph.topologicalSort());
    }

    public static void undirectedGraphDemo() {
        System.out.println("");
        System.out.println("-------- Undirected Graph Demo -----------");

        // undirected graph
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

        MyGraph graph = new MyGraph(vertices, edges, false);

        //dfs
        System.out.println("DFS: " + graph.dfs());

        // A->F 的所有路径
        System.out.println("A->F paths: " + graph.getAllPathsBetweenTwoVertices('A', 'F'));

        //bfs
        System.out.println("BFS: " + graph.bfs());

        // islands: DFS 和 BFS 都可以做
        System.out.println("Number of sub-graphs: " + graph.numOfSubgraph()); //2

        // Connect problem: DFS 和 BFS 都可以做
        System.out.println("C->D connected? " + graph.isConnected('C', 'D')); //true
        System.out.println("C->D connected? " + graph.isConnected('D', 'C')); //false

        // Shortest path
        // Dijkstra
        System.out.println("Dijkstra: " + graph.dijkstra('A', 'F'));
    }

    public static void directedGraphCycle() {
        System.out.println("");
        System.out.println("-------- Directed Graph Cycle -----------");

        //graph 3
        char[] vertices = new char[]{'A', 'B', 'C', 'D', 'E'};
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

        // Topological Sort is Cyclic
        try {
            System.out.print("Topology Sort - ");
            graph3.topologicalSort();
            System.out.println("The graph has no cycles");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // DFS isCyclic
        System.out.println("DFS has cycles: " + graph3.isCyclicDFSDirected()); //true

        // Union-Find is cyclic
        System.out.println("Union find cycles: " + graph3.isCyclicUnionFind());

        // Find all cycles
        System.out.println("All cycles: " + graph3.getAllCyclesDirected()); //[C->D->E, A->B->C, A->B->C->D->E, A->C->D->E, A->C]
    }

    public static void undirectedGraphCycle() {
        System.out.println("");
        System.out.println("-------- Undirected Graph Cycle -----------");

        // undirected graph
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

        MyGraph graph = new MyGraph(vertices, edges, false);

        // Topological Sort is Cyclic
        System.out.println("Topological has cycles: " + graph.isCyclicUndirectedGraphTopology());

        // DFS is cyclic
        System.out.println("DFS has cycles: " + graph.isCyclicUndirectedGraphDFS());

        // Union-Find is cyclic
        System.out.println("Union find cycles: " + graph.isCyclicUnionFind());

        // Find all cycles
    }

    public List<Character> dfs() {
        List<Character> result = new ArrayList<>();

        boolean[] visited = new boolean[vertices.length];

        while (true) {
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

    public List<String> getAllPathsBetweenTwoVertices(char startVertex, char endVertex) {
        List<List<Integer>> result = new ArrayList<>();
        int start = startVertex - 'A';
        int end = endVertex - 'A';

        List<Integer> tempPath = new ArrayList<>();
        boolean[] visited = new boolean[vertices.length]; //空间换时间,不用再 tempPath.contain(start)
        findAllPathsBetweenTwoVerticesDFSHelper(start, end, tempPath, visited, result);

        List<String> finalResult = new ArrayList<>();
        for (List<Integer> path : result) {
            List<String> stringPath = new ArrayList<>();
            path.stream().forEach(index -> stringPath.add(String.valueOf(vertices[index])));
            finalResult.add(String.join("->", stringPath));
        }
        return finalResult;
    }

    private void findAllPathsBetweenTwoVerticesDFSHelper(int i, int end, List<Integer> tempPath, boolean[] visited, List<List<Integer>> result) {
        visited[i] = true;

        if (i == end && tempPath.size() != 0) {
            tempPath.add(end);
            List<Integer> path = new ArrayList<>(tempPath);
            result.add(path);
            return;
        }

        tempPath.add(i);

        for (int j = 0; j < edges[i].length; j++) {
            if (!visited[j] && edges[i][j] != 0) { // 1. 去除环路, 2. 不让无向图往回走
                findAllPathsBetweenTwoVerticesDFSHelper(j, end, tempPath, visited, result);

                //backtrace
                visited[j] = false;
                tempPath.remove(tempPath.size() - 1);
            }
        }

    }

    /**
     * 找出以一个顶点为起点的所有路径,
     * Note: 只能用于有向图
     *
     * @param vertex
     * @return
     */
    public List<String> getAllPathsDirectedGraph(char vertex) {
        List<List<Integer>> result = new ArrayList<>();
        int start = vertex - 'A';

        List<Integer> tempPath = new ArrayList<>();
        getAllPathsDirectedGraphHelper(start, tempPath, result);

        List<String> finalResult = new ArrayList<>();
        for (List<Integer> path : result) {
            List<String> stringPath = new ArrayList<>();
            path.stream().forEach(index -> stringPath.add(String.valueOf(vertices[index])));
            finalResult.add(String.join("->", stringPath));
        }
        return finalResult;
    }

    private void getAllPathsDirectedGraphHelper(int i, List<Integer> tempPath, List<List<Integer>> result) {
        if (tempPath.contains(i)) { //遇到环路就停止
            tempPath.add(i); //for backtrace

            List<Integer> path = new ArrayList<>(tempPath);
            result.add(path);
            return;
        }

        tempPath.add(i);

        boolean isEndNode = true; //如果出度为 0 就添加路径
        for (int j = 0; j < edges[i].length; j++) {
            if (edges[i][j] != 0) {
                isEndNode = false;
                getAllPathsDirectedGraphHelper(j, tempPath, result);
                tempPath.remove(tempPath.size() - 1);
            }
        }
        if (isEndNode) {
            List<Integer> path = new ArrayList<>(tempPath);
            result.add(path);
        }
    }

    /**
     * Dijkstra（迪杰斯特拉）算法是典型的单源最短路径算法，用于计算某个顶点到其他所有顶点的最短路径。
     * Dijkstra（迪杰斯特拉）算法要求图中不存在负权边，即保证图中每条边的权重值为正。。
     *
     * @return
     */
    public Integer dijkstra(char startVertex, char endVertex) {
        int start = startVertex - 'A';
        int end = endVertex - 'A';

        // input validation
        if (start < 0 || start >= vertices.length || end < 0 || end >= vertices.length) {
            return -1;
        }

        // a. 初始化: 初始时，S只包含源点，即S＝{v}，v的距离为0。U包含除v外的其他顶点，即:U={其余顶点}，若v与U中顶点u有边，则<u,v>正常有权值，若u不是v的出边邻接点，则<u,v>权值为∞。
        boolean[] confirmedVertices = new boolean[vertices.length]; // true 为在 S 中, false 为在 U 中
        confirmedVertices[start] = true;

        int[] distances = new int[vertices.length];
        for (int i = 0; i < distances.length; i++) {
            if (i == start) {
                distances[i] = 0;
            } else if (edges[start][i] > 0) {
                distances[i] = edges[start][i];
            } else {
                distances[i] = Integer.MAX_VALUE;
            }
        }

        // 如果目标顶点没在在 S 中一直执行
        while (!confirmedVertices[end]) {
            // b. 从U中选取一个距离v最小的顶点k，把k，加入S中（该选定的距离就是v到k的最短路径长度）。
            int minIndex = -1;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < distances.length; i++) {
                if (!confirmedVertices[i] && distances[i] < min) {
                    min = distances[i];
                    minIndex = i;
                }
            }
            if (minIndex == -1) {// all vertices have been added into S
                break;
            }

            confirmedVertices[minIndex] = true; //加入到 S 中

            // c.以k为新考虑的中间点，修改U中各顶点的距离；若从源点v到顶点u的距离（经过顶点k）比原来距离（不经过顶点k）短，则修改顶点u的距离值。
            for (int i = 0; i < distances.length; i++) {
                if (!confirmedVertices[i] && edges[minIndex][i] > 0) {
                    int distantceToI = distances[minIndex] + edges[minIndex][i];
                    if (distantceToI < distances[i]) {
                        distances[i] = distantceToI;
                    }
                }
            }
        }

        return distances[end];
    }

    /**
     * 要能实现拓扑(Topology)排序，得有几个前提：
     * • 图必须是有向图
     * • 图里面没有环
     * 拓扑排序一般用来理清具有依赖关系的任务。
     *
     * @return
     */
    public List<Character> topologicalSort() {
        if (!isDirected) {
            return null;
        }

        List<Character> result = new ArrayList<>();

        // 1. Calculate in-degree of each vertex
        int[] inDegrees = new int[vertices.length];

        for (int i = 0; i < edges.length; i++) {
            int inDegree = 0;
            for (int j = 0; j < edges[i].length; j++) {
                if (edges[j][i] != 0) {
                    inDegree++;
                }
            }
            inDegrees[i] = inDegree;
        }

        // 2. find an unvisited vertex whose inDegree is 0, if no such vertex stop the loop
        while (true) {
            int index = findVertexWithZeroInDegree(inDegrees);
            if (index == -1) {
                break;
            }

            // set found vertex's in-degree as -1;
            inDegrees[index] = -1;
            result.add(vertices[index]);

            // 3. all connected vertex minus 1
            for (int i = 0; i < edges[index].length; i++) {
                if (edges[index][i] != 0 && inDegrees[i] != -1) { //inDegrees[i] != -1 means the vertex hasn't been visited.
                    inDegrees[i]--;
                }
            }
        }

        if (result.size() < vertices.length) {
            throw new RuntimeException("The graph has cycles");
        }

        return result;
    }

    private static int findVertexWithZeroInDegree(int[] inDegrees) {
        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Cycle problems - directed graph
     */

    /**
     * 如果一个顶点在环上,那么只需要 DFS 该顶点一次便可知是否有环, 时间复杂度为 O(n)
     *
     * @return
     */
    public boolean isCyclicDFSDirected() {
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
            boolean result = hasCyclePathDFSDirected(index, path, visited);

            if (result) {
                return true;
            }
        }

        return false;
    }

    private boolean hasCyclePathDFSDirected(int i, Set<Integer> path, boolean[] visited) {
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
                result = hasCyclePathDFSDirected(j, path, visited);
                // backtrace
                path.remove(j);
            }
            if (result) {
                return true;
            }
        }
        return false;
    }

    public Set<String> getAllCyclesDirected() {
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
            List<List<Integer>> result = findCyclesDirected(index, path, visited);
            cycles.addAll(result);
        }

        return transformCyclesToResult(cycles);
    }

    // Traverse every path to find if the path is a circle.
    private List<List<Integer>> findCyclesDirected(int i, List<Integer> path, boolean[] visited) {
        List<List<Integer>> result = new ArrayList<>();

        int index;
        if ((index = path.indexOf(i)) != -1) {
            List<Integer> list = new ArrayList<>(path.subList(index, path.size()));
            result.add(list);

            path.add(i);

            return result;
        }

        visited[i] = true;
        path.add(i);

        for (int j = 0; j < edges[i].length; j++) {
            if (edges[i][j] != 0) {
                List<List<Integer>> subResult = findCyclesDirected(j, path, visited);
                result.addAll(subResult);
                path.remove(path.size() - 1);
            }
        }
        return result;
    }

    private Set<String> transformCyclesToResult(List<List<Integer>> cycles) {
        Set<String> result = new HashSet<>();
        for (List<Integer> cycle : cycles) {
            List<String> chars = new ArrayList<>();
            ;
            for (int i : cycle) {
                char c = (char) ('A' + i);
                chars.add(String.valueOf(c));
            }
            String path = String.join("->", chars);
            result.add(path);
        }
        return result;
    }

    /**
     * Cycle problems - undirected graph
     */

    /**
     * 方法:
     * 1. 求出图中所有结点的度。即入度
     * 2. 找出一个度 <= 1 的结点。（独立结点的度为 0）
     * 3. 把相邻节点的度减一。
     * 4. 一直循环到找不到度小于 1 的结点
     * 5. 循环结束时判断已经访问的结点数是否等于 n。等于 n 说明全部结点都被访问过，无环；反之，则有环。
     *
     * @return
     */
    public boolean isCyclicUndirectedGraphTopology() {
        if (isDirected) {
            return false;
        }

        int[] degrees = new int[vertices.length];

        for (int i = 0; i < vertices.length; i++) {
            int count = 0;
            for (int j = 0; j < edges[i].length; j++) {
                if (edges[j][i] != 0) {
                    count++;
                }
            }
            degrees[i] = count;
        }

        Set<Integer> visited = new HashSet<>();

        while (true) {
            int index = -1;
            for (int i = 0; i < degrees.length; i++) {
                if (degrees[i] <= 1 && !visited.contains(i)) { //island node degree is 0
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                break;
            }

            visited.add(index);

            for (int i = 0; i < edges[index].length; i++) {
                if (!visited.contains(index) && edges[index][i] != 0) {
                    degrees[i]--;
                }
            }
        }

        return visited.size() < vertices.length;
    }

    /**
     * 如果在遍历的过程中，发现某个结点is visited，并且这个结点不是上一步访问的结点，那么存在环。
     * <p>
     * 结论: 无向图要想没有环只能是树形结构
     *
     * @return
     */
    public boolean isCyclicUndirectedGraphDFS() {
        boolean[] visited = new boolean[vertices.length];

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                List<Integer> path = new ArrayList<>();
                boolean result = hasCycleUndirectedGraphDFSHelper(i, -1, visited);
                if (result) {
                    return true;
                }
                break;
            }
        }

        return false;
    }

    private boolean hasCycleUndirectedGraphDFSHelper(int i, int prev, boolean[] visited) {
        if (visited[i]) { //无向图中,只要 visit 过就肯定有环
            return true;
        }

        visited[i] = true;

        for (int j = 0; j < edges[i].length; j++) {
            if (j != prev && edges[i][j] != 0) {
                boolean result = hasCycleUndirectedGraphDFSHelper(j, i, visited);
                if (result) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isCyclicUnionFind() {
        class Union {
            int[] parent;
            int count; //孤岛数量 -- vertex 数量

            public Union(int numOfNodes) {
                parent = new int[numOfNodes];
                count = numOfNodes;
                for (int i = 0; i < parent.length; i++) {
                    parent[i] = i;
                }
            }

            public int findRoot(int x) {
                int root = x;
                while (parent[root] != root) {
                    root = parent[root];
                }
                return root;
            }

            public void union(int rootX, int rootY) {
                parent[rootX] = rootY;
                count--;
            }
        }

        Union myUnion = new Union(vertices.length);

        for (int i = 0; i < edges.length; i++) {
            int end = isDirected ? edges[i].length : i;
            for (int j = 0; j < end; j++) {
                if (edges[i][j] != 0) { //i, j 就是冗余链接
                    int rootX = myUnion.findRoot(i);
                    int rootY = myUnion.findRoot(j);

                    if (rootX == rootY) {
                        return true;
                    }

                    myUnion.union(rootX, rootY);
                }
            }
        }
        return false;
    }
}
