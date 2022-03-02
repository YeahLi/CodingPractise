package com.basics.graph.tree.binarytree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MyBST {
    TreeNode root;

    static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        public TreeNode(int data) {
            val = data;
        }
    }

    public static void main(String[] args) {
        int[] treeArray = new int[]{5, 3, 4, 7, 9, 1, 4};

        MyBST bst = new MyBST(treeArray);

        int[] bstArr = bst.serialize();
        System.out.println(Arrays.toString(bstArr));

        bst = new MyBST(bstArr);
        System.out.println(bst.dfs());

        // remove a node
        bst.remove(new TreeNode(5));
        System.out.println(bst.dfs());
    }

    //序列化 与 反序列化
    public MyBST(int[] treeArray) {
        if (treeArray == null || treeArray.length == 0) {
            root = null;
        }

        root = new TreeNode(treeArray[0]);

        for (int i = 1; i < treeArray.length; i++) {
            TreeNode node = new TreeNode(treeArray[i]);
            add(node);
        }
    }

    public int[] serialize() {
        List<Integer> list = dfs();

        int[] result = new int[list.size()];
        int i = 0;
        for (Integer data : list) {
            result[i] = data;
            i++;
        }
        return result;
    }

    //中序遍历
    public List<Integer> dfs() {
        List<Integer> result = new LinkedList<>();
        midOrder(root, result);
        return result;
    }

    private void midOrder(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        midOrder(node.left, result);
        result.add(node.val);
        midOrder(node.right, result);
    }

    //add a node
    public void add(TreeNode node) {
        TreeNode parent = root;
        while (true) {
            if (node.val < parent.val) {
                if (parent.left != null) {
                    parent = parent.left;
                } else {
                    parent.left = node;
                    break;
                }
            } else {
                if (parent.right != null) {
                    parent = parent.right;
                } else {
                    parent.right = node;
                    break;
                }
            }
        }
    }

    //search
    public TreeNode search(int data) {
        TreeNode node = root;

        while (node != null) {
            if (data == node.val) {
                return node;
            } else if (data < node.val) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null;
    }

    //remove a node
    public void remove(TreeNode deleteNode) {

        TreeNode node = root;
        TreeNode parent = null;

        while (node != null) {
            if (deleteNode.val == node.val) {
                break;
            } else if (deleteNode.val < node.val) {
                parent = node;
                node = node.left;
            } else {
                parent = node;
                node = node.right;
            }
        }

        //case 1: leaf
        if (node.left == null && node.right == null) {
            if (parent.left == node) {
                parent.left = null;
            } else if (parent.right == node) {
                parent.right = null;
            }
        }

        //case 2: not leaf but only one subtree, just link to its next node
        else if (node.left != null && node.right == null) {
            TreeNode next = node.left;
            if (parent.left == node) {
                parent.left = next;
            } else if (parent.right == node) {
                parent.right = next;
            }
            node.left = null;
        } else if (node.right != null && node.left == null) {
            TreeNode next = node.right;
            if (parent.left == node) {
                parent.left = next;
            } else if (parent.right == node) {
                parent.right = next;
            }
            node.right = null;
        }

        //case 3: the node has both left and right subtree
        else {
            if (parent.left == node) {
                parent.left = findPrevious(node);
            } else if (parent.right == node) {
                parent.right = findPrevious(node);
            }

            node.left = null;
            node.right = null;
        }
    }

    private TreeNode findPrevious(TreeNode node) {
        TreeNode next = node.left;
        if (next.right == null) {
            node.left = next.left;
        } else {
            TreeNode nextParent = node;
            while (next.right != null) {
                nextParent = next;
                next = next.right;
            }

            if (next.left != null) {
                nextParent.right = next.left;
            }
        }

        next.left = node.left;
        next.right = node.right;

        return next;
    }
}
