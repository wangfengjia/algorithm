package com.leetcode.www.easy.tree;

/**
 * 二叉搜索树的最大深度
 * 思路:使用深度优先搜索，我们可以先知道左右子树的最大深度分别为l和r,那么这个二叉树的最大深度为max(l,r) + 1(这个节点本身)
 */
public class BSTMaxDepth {

    public int solution(TreeNode root){

        if (root == null){
            return 0;
        }

        return Math.max(solution(root.left), solution(root.right)) + 1;
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
