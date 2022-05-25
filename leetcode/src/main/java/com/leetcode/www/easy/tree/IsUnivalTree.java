package com.leetcode.www.easy.tree;

/**
 * leetcode-965:单值二叉树
 * 如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。
 * 只有给定的树是单值二叉树时，才返回 true；否则返回 false。
 */
public class IsUnivalTree {

    private int curVal = -1;

    /**
     * 深度优先搜索
     * @param root
     * @return
     */
    public boolean solution(TreeNode root){

        return dfs(root);
    }

    private boolean dfs(TreeNode root){

        if (root == null){
            return true;
        }
        if (curVal == -1){
            curVal = root.val;
        }
        if (root.val != curVal){
            return false;
        }

        return dfs(root.left) && dfs(root.right);
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

    public static void main(String[] args) {

        IsUnivalTree tree = new IsUnivalTree();

        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(1);
        root.left = node2;
        root.right = node3;

        boolean ans = tree.solution(root);
        System.out.println(ans);
    }
}
