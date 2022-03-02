//package com.template;
//
///**
// * 使用条件:
// * 二叉树相关问题(99%)
// * 可以一分为二去分别处理之后再合并结果(100%)
// * 数组相关问题 (10%)
// */
////O(n) O(n)
//public class BinaryTreeDivide {
//    public ResultType divideConquer(TreeNode node) {
//        //一般处理 node==null 就够了
//        if (node == null) {
//            return ...;
//        }
//
//        //处理左子树
//        ResultType leftResult = divideConquer(node.left);
//
//        //处理右子树
//        ResultType rightResult = divideConquer(node.right);
//
//        //合并答案
//        ResultType result = mergeLeftAndRight();
//        return result;
//    }
//
//    private void midOrderTraverse(TreeNode root, List<TreeNode> list) {
//        if(root == null) {
//            return;
//        }
//
//        midOrderTraverse(root.left, list);
//        list.add(root);
//        midOrderTraverse(root.right, list);
//    }
//}
