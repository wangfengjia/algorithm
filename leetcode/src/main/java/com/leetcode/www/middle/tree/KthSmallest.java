package com.leetcode.www.middle.tree;

/**
 * leetcode230-二叉搜索树中第K小的元素
 * 给定一个二叉搜索树的根节点 root，和一个整数 k，请你设计一个算法查找其中第k个最小元素（从 1 开始计数）。
 */
public class KthSmallest {

    private int count = 0;
    private int ans = -1;

    /**
     * 对二叉搜索树进行中序遍历得到的值序列是有序，可以在中序遍历过程中得到第k小的最小元素
     * 复杂度分析
     *  时间复杂度:O(log^n)
     *  空间复杂度:O(log^n)
     * @param root
     * @param k
     * @return
     */
    public int solution(TreeNode root, int k){

        dfs(root, k);
        return ans;
    }

    private void dfs(TreeNode root, int k){

        if (root == null || ans != -1){
            return;
        }
        dfs(root.left, k);
        ++count;
        if (count == k){
            ans = root.val;
        }
        dfs(root.right, k);
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

        KthSmallest kthSmallest = new KthSmallest();

        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(2);
        root.left = node1;
        root.right = node2;
        node1.right = node3;

        int k = 1;
        int ans = kthSmallest.solution(root, k);
        System.out.println(ans);
    }
}
