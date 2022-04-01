package com.leetcode.www.easy.tree;


import java.util.ArrayList;

/**
 * leetcode-543:二叉树的直径
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 */
public class DiameterOfBinaryTree {

    public int ans;
    /**
     * 深度优先搜索:一条路径的长度为这条路径经过的节点数减一，所以求直径等效于求路径经过的节点数的最大值减一。而任意一条路径可以看作由某个节点为起点，其左儿子和
     *            右儿子深度遍历的路径得到
     * @param root
     * @return
     */
    public int solution(TreeNode root){

        ans = 1;
        dfs(root);
        return ans - 1;
    }

    public int dfs(TreeNode root){

        if (root == null){
            return 0;
        }

        int left = dfs(root.left);
        int right = dfs(root.right);
        ans = Math.max(ans, (left + right + 1));

        //返回根节点的子树的最大深度
        return Math.max(left, right) + 1;
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

        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);

        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;

        DiameterOfBinaryTree diameter = new DiameterOfBinaryTree();
        int ans = diameter.dfs(root);
        System.out.println(ans);
    }
}
