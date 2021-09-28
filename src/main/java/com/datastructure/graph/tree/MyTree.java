package com.datastructure.graph.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyTree {

    private TreeNode root;

    public MyTree(TreeNode node) {
        this.root = node;
    }

    /**
     * Tree 的反序列化
     *
     * @param directedGraph
     */
    public MyTree(int[][] directedGraph) {
        //拓扑排序

        //1. 统计出每个node的 indegree
        int[] inDegrees = new int[directedGraph.length];
        for (int i = 0; i < directedGraph.length; i++) { //列
            int inDegree = 0;
            for (int j = 0; j < directedGraph.length; j++) { //行
                if (directedGraph[j][i] != 0) {
                    inDegree++;
                }
            }
            inDegrees[i] = inDegree;
        }

        //如果是树, 没有孤岛, 应该只有一个indegree=0 的 node
        int zeroIndegreeCount = 0;
        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) {
                zeroIndegreeCount++;
            }
        }
        if (zeroIndegreeCount > 1) {
            throw new RuntimeException("This is not a tree: the graph not connected, number of nodes have zero indegree = " + zeroIndegreeCount);
        }

        Map<Integer, TreeNode> map = new HashMap<>(); //实质就是树的 hashtable 表示法

        while (true) {
            //2. find a node whose indegree is 0
            int index = -1;
            for (int i = 0; i < inDegrees.length; i++) {
                if (inDegrees[i] == 0) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                break;
            }

            //3. delete the node
            //a. mark the node as visited
            inDegrees[index] = -1;

            //b. Find node by index from hashmap, if the node doesn't exist then the node is the root.
            TreeNode node;
            if (map.isEmpty()) {
                node = new TreeNode(index);
                map.put(index, node);
                root = node;
            }

            node = map.get(index);

            //c. remove all out edges of this node
            for (int i = 0; i < directedGraph[index].length; i++) {
                if (inDegrees[i] != -1 && directedGraph[index][i] != 0) {
                    // c1. update indegrees
                    inDegrees[i]--;

                    // c2. add these nodes into children of index node and into the map as well
                    TreeNode child = new TreeNode(i);
                    node.getChildren().add(child);
                    map.put(i, child);
                }
            }
        }

        if (map.size() < directedGraph.length) {
            throw new RuntimeException("This is not a tree, it has a cycle");
        }
    }

    /**
     * 没环没孤岛: DFS, Union-Find(需要转换为图), Topological(需要转换为图)
     * 后两者需要把树转化为图
     *
     * @return
     */
    public boolean isTree() {
        Set<Integer> visited = new HashSet<>();
        return isTreeHelper(root, visited);
    }

    private boolean isTreeHelper(TreeNode node, Set<Integer> visited) {
        if (visited.contains(node.getData())) {
            return false;
        }

        visited.add(node.getData());

        boolean result = true;
        for (TreeNode child : node.getChildren()) {
            result = isTreeHelper(child, visited);
            if (!result) {
                break;
            }
        }
        return result;
    }

    public List<Integer> bfs() {
        List<Integer> result = new LinkedList<>();

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            result.add(node.getData());

            for (TreeNode child : node.getChildren()) {
                queue.offer(child);
            }
        }
        return result;
    }

    public List<Integer> dfs() {
        List<Integer> result = new LinkedList<>();
        dfsHelper(root, result);
        return result;
    }

    private void dfsHelper(TreeNode node, List<Integer> result) {
        result.add(node.getData());
        for (TreeNode child : node.getChildren()) {
            dfsHelper(child, result);
        }
    }

    public int getHeight() {
        return getDepth(root);
    }

    private int getDepth(TreeNode node) {
        int maxSubDepth = 0;
        for (TreeNode child : node.getChildren()) {
            int depth = getDepth(child);
            if (maxSubDepth < depth) {
                maxSubDepth = depth;
            }
        }
        return maxSubDepth + 1;
    }

    /**
     * BFS, 无论哪种方式都要把树从链式存储回归到 hashtable 存储.
     *
     * @return
     */
    public int[][] serialize() {
        //需要把链式存储转换为 HashTable 存储
        Map<Integer, List<Integer>> tree = new HashMap<>();

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            List<TreeNode> children = node.getChildren();
            List<Integer> childList = new LinkedList<>();
            for (TreeNode child : children) {
                childList.add(child.getData());
                queue.offer(child);
            }
            tree.put(node.getData(), childList);
        }

        int[][] treeArray = new int[tree.size()][tree.size()];
        for (Integer key : tree.keySet()) {
            List<Integer> childList = tree.get(key);
            for (Integer child : childList) {
                treeArray[key][child] = 1;
            }
        }

        return treeArray;
    }

    /**
     * 解法 1: DFS
     * Note: 没有父节点指针 O(N)
     */
    public TreeNode lowestCommonAncestorDFS(TreeNode n1, TreeNode n2) {
        if (n1 == n2) {
            return n1;
        }

        TreeNode result = findLowestCommonAncestor(root, n1, n2);
        if (result == null) {
            throw new RuntimeException("Algorithm is wrong");
        }
        return result;
    }

    /**
     * 寻找 node 的子树是否有 n1 或 n2,
     * 如果有两个子树返回结果,那么 node 就是他们的最近共同祖先.
     * 如果只有一个子树返回结果不为空, 那么:
     * 1. node 的子节点是 n1, n2 的共同祖先,
     * 如果 n1,n2 互不为各自祖先, 此时 result != n1 并且 result != n2
     * 2. node 的父节点是 n1, n2 的共同祖先, 此时 result = n1 或 result = n2
     * 如果返回结果全为空, 那么 node 和其所有子节点都不是 n1, n2 的共同祖先
     *
     * @param node
     * @param n1
     * @param n2
     * @return
     */
    private TreeNode findLowestCommonAncestor(TreeNode node, TreeNode n1, TreeNode n2) {
        if (node.getData() == n1.getData() || node.getData() == n2.getData()) {
            return node;
        }

        int count = 0; //记录n1, n2 是否在一个子树, count=1 是, count=2 不是
        TreeNode result = null;
        for (TreeNode child : node.getChildren()) {
            TreeNode foundNode = findLowestCommonAncestor(child, n1, n2);
            if (foundNode != null) {
                count++;
                result = foundNode;
                //如果 n1, n2 都在 node 下, node 就是最近祖先,  程序提前结束
                if (count == 2) {
                    return node;
                }
            }
        }

        return result;
    }

    /**
     * 解法 2: BFS 把各个节点 父节点存储在 hashtable 中
     * Note: 没有父节点指针 O(N)
     */
    public TreeNode lowestCommonAncestorBFS(TreeNode n1, TreeNode n2) {
        Map<Integer, TreeNode> parentMap = new HashMap<>();

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        parentMap.put(root.getData(), null);

        while (!queue.isEmpty()) {
            TreeNode parent = queue.poll();

            for (TreeNode child : parent.getChildren()) {
                parentMap.put(child.getData(), parent);
                queue.offer(child);
            }
        }

        Deque<TreeNode> n1ParentsStack = getParentStack(parentMap, n1);
        Deque<TreeNode> n2ParentsStack = getParentStack(parentMap, n2);

        TreeNode parent = null;
        while (!n1ParentsStack.isEmpty() && !n2ParentsStack.isEmpty()) {
            TreeNode node1 = n1ParentsStack.pop();
            TreeNode node2 = n2ParentsStack.pop();
            if (node1.getData() == node2.getData()) {
                parent = node1;
            } else {
                break;
            }
        }
        return parent;
    }

    private Deque<TreeNode> getParentStack(Map<Integer, TreeNode> prentMap, TreeNode node) {
        Deque<TreeNode> result = new ArrayDeque<>();
        TreeNode n = node;
        while (n != null) {
            result.push(n);
            n = prentMap.get(n.getData());
        }
        return result;
    }

    static class TreeNode {
        private int data;
        private List<TreeNode> children;

        public TreeNode(int data) {
            this.data = data;
            this.children = new LinkedList<>();
        }

        public int getData() {
            return data;
        }

        public List<TreeNode> getChildren() {
            return children;
        }
    }

    public static void main(String[] args) {
        int[][] graph = new int[10][10];
        graph[0][1] = 1;
        graph[0][2] = 1;
        graph[0][3] = 1;
        graph[1][4] = 1;
        graph[2][5] = 1;
        graph[3][6] = 1;
        graph[3][7] = 1;
        graph[5][8] = 1;
        graph[8][9] = 1;

        // 1. 树的反序列化
        MyTree myTree = new MyTree(graph);

        // 2. 树的遍历
        List<Integer> bfsResult = myTree.bfs();
        System.out.println(bfsResult);
        List<Integer> dfsResult = myTree.dfs();
        System.out.println(dfsResult);
        assert bfsResult.size() == dfsResult.size();

        // 3. 求深度
        System.out.println("height = " + myTree.getHeight());
        assert myTree.getHeight() == 5;

        // 4. 求最小共同祖先
        TreeNode n1 = new TreeNode(5);
        TreeNode n2 = new TreeNode(9);
        System.out.println("Ancestor of 9,5 is " + myTree.lowestCommonAncestorDFS(n1, n2).getData()); // 5
        System.out.println("Ancestor of 9,5 is " + myTree.lowestCommonAncestorBFS(n1, n2).getData());

        n1 = new TreeNode(4);
        System.out.println("Ancestor of 4,9 is " + myTree.lowestCommonAncestorDFS(n1, n2).getData()); // 0
        System.out.println("Ancestor of 4,9 is " + myTree.lowestCommonAncestorBFS(n1, n2).getData());

        n1 = new TreeNode(6);
        n2 = new TreeNode(7);
        System.out.println("Ancestor of 6,7 is " + myTree.lowestCommonAncestorDFS(n1, n2).getData()); // 3
        System.out.println("Ancestor of 6,7 is " + myTree.lowestCommonAncestorBFS(n1, n2).getData());

        // 5. isTree
        System.out.println(myTree.isTree());

        // 5. 序列化
        int[][] graph2 = myTree.serialize();

        assert graph.length == graph2.length;
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                assert graph[i][j] == graph2[i][j];
            }
        }
    }
}
