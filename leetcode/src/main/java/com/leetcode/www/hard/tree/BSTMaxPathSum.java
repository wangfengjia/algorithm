package com.leetcode.www.hard.tree;

import com.leetcode.www.middle.tree.ValidBST;

/**
 * 给的一个根节点，求二叉搜索树中的最大路径和:路径被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次。
 * 该路径 至少包含一个节点，且不一定经过根节点。路径和 是路径中各节点值的总和。
 * 方法一(递归):由于一个节点在一个路径中只能出现一次，所以以任意节点为起点，路径只能是往左走或者是往右走，所以任意一个节点在最大路径和中的最大贡献值只能是
 *            max(左子节点的最大贡献值,右子节点的最大贡献值) + 节点值，这样去递归处理，就可以算出以每个节点为起点的最大贡献值。而每个节点的最大路径和
 *            会等于(这个节点本身的值 + 左子节点的最大贡献值(为正的时候) + 右子节点的最大贡献值(为正的时候)).同时维护一个全局变量maxSum，在遍历过程中
 *            更新maxSum的值。最大得到的maxSum就是二叉树中的最大路径和
 */
public class BSTMaxPathSum {

    public int maxSum = Integer.MIN_VALUE;

    public int solution(TreeNode root){

        maxGain(root);
        return maxSum;
    }

    public int maxGain(TreeNode root){

        if (root == null){
            return 0;
        }

        //递归计算左右子节点的最大贡献值，只有这个节点的贡献值大于0的时候才会选取这个子节点
        int leftGain = Math.max(maxGain(root.left), 0);
        int rightGain = Math.max(maxGain(root.right), 0);

        //节点的最大路径和取决于这个节点的值和左右子节点的最大贡献值
        int newPath = root.val + leftGain + rightGain;
        //更新答案
        maxSum = Math.max(maxSum, newPath);

        //返回节点的最大贡献值
        return root.val + Math.max(leftGain, rightGain);
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


        TreeNode root = new TreeNode(-10);
        TreeNode left = new TreeNode(9);
        TreeNode right = new TreeNode(20);
        TreeNode rightSon1 = new TreeNode(15);
        TreeNode rightSon2 = new TreeNode(7);

        root.left = left;
        root.right = right;
        right.left = rightSon1;
        right.right = rightSon2;

        BSTMaxPathSum bstMaxPathSum = new BSTMaxPathSum();
        int maxSum = bstMaxPathSum.solution(root);
        System.out.println(maxSum);
    }
}
