package com.leetcode.www.middle.tree;

/**
 * 验证是否是二叉搜索树:根据二叉搜索树的中序遍历结果是有序的来做
 * 时间复杂度O(n)
 */
public class ValidBST {

    public int preValue = Integer.MIN_VALUE;

    /**
     * 中序遍历
     * @param root
     * @return
     */
    public boolean solution(TreeNode root){

        return valid(root);
    }

    public boolean valid(TreeNode root){

        if (root == null){
            return true;
        }

        boolean left = valid(root.left);
        int val = root.val;
        if (val <= preValue){
            return false;
        }
        preValue = val;
        boolean right = valid(root.right);
        return left && right;
    }

    /**
     * 递归版
     * @param root
     * @return
     */
    public boolean solutionV2(TreeNode root){

        return validV2(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean validV2(TreeNode root, int lower, int upper){

        if (root == null){
            return true;
        }

        if (root.val <= lower || root.val >= upper){
            return false;
        }

        //递归遍历左右子树
        return validV2(root.left, lower, root.val) && validV2(root.right, root.val, upper);
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
