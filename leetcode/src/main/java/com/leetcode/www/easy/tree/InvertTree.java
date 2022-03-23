package com.leetcode.www.easy.tree;

/**
 * 翻转二叉搜索树
 */
public class InvertTree {

    public TreeNode invert(TreeNode root){

        if (root == null){
            return null;
        }

        TreeNode left = invert(root.left);
        TreeNode right = invert(root.right);
        root.left = right;
        root.right = left;
        return root;
    }


    public static class TreeNode{

        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){}
        TreeNode(int val){
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
