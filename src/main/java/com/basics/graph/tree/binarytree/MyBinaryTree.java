package com.basics.graph.tree.binarytree;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class MyBinaryTree {

    TreeNode root;

    public MyBinaryTree(TreeNode node) {
        root = node;
    }

    static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        public TreeNode(int data) {
            val = data;
        }
    }

    public static void main(String[] args) {
        int[] treeArray = new int[]{0, 1, 2, 3, -1, -1, 4, 5, -1, -1, -1, 6, 7, -1, -1, -1, -1};
        MyBinaryTree binaryTree = new MyBinaryTree(treeArray);

        //DFS
        System.out.println("Binary tree: " + binaryTree.dfs());

        //serialize
        int[] serialize = binaryTree.serialize();
        System.out.println(Arrays.toString(serialize));
        for (int i = 0; i < serialize.length; i++) {
            assert serialize[i] == treeArray[i];
        }

        //lowest common ancestor
        System.out.println("lowest common ancestor of 4 and 7 is: " + binaryTree.lowestCommonAncestor(binaryTree.root, new TreeNode(4), new TreeNode(7)).val);
    }

    public MyBinaryTree(int[] treeArray) {
        if (treeArray == null || treeArray.length < 1) {
            root = null;
            return;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();

        int index = 0;
        queue.offer(new TreeNode(treeArray[index]));

        while (!queue.isEmpty()) {

            if (index % 2 != 0) {
                throw new RuntimeException("Invalid binary tree array");
            }

            TreeNode node = queue.poll();
            if (index == 0) {
                root = node;
            }

            int left = treeArray[++index];
            if (left == -1) {
                node.left = null;
            } else {
                node.left = new TreeNode(left);
                queue.offer(node.left);
            }

            int right = treeArray[++index];
            if (right == -1) {
                node.right = null;
            } else {
                node.right = new TreeNode(right);
                queue.offer(node.right);
            }
        }
    }

    public int[] serialize() {
        //BFS, 空节点用-1 表示
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        List<Integer> list = new LinkedList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);

            if (node.val == -1) {
                continue;
            }

            if (node.left != null) {
                queue.offer(node.left);
            } else {
                queue.offer(new TreeNode(-1));
            }

            if (node.right != null) {
                queue.offer(node.right);
            } else {
                queue.offer(new TreeNode(-1));
            }
        }

        int[] result = new int[list.size()];
        int i = 0;
        for (Integer data : list) {
            result[i] = data;
            i++;
        }
        return result;
    }

    //DFS
    public List<Integer> dfs() {
        List<Integer> result = new LinkedList<>();
        firstOrder(root, result);
        return result;
    }

    private void firstOrder(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }

        result.add(node.val);

        firstOrder(node.left, result);
        firstOrder(node.right, result);
    }


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if (root.val == p.val || root.val == q.val) {
            return root;
        }

        TreeNode leftFound = lowestCommonAncestor(root.left, p, q);
        TreeNode rightFound = lowestCommonAncestor(root.right, p, q);

        if (leftFound != null && rightFound != null) {
            return root;
        }

        if (leftFound != null) { //right is null
            return leftFound;
        }

        if (rightFound != null) { // left is null
            return rightFound;
        }

        return null; // both null
    }
}
