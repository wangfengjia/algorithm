package com.leetcode.www.middle.tree;


import java.util.LinkedList;
import java.util.Queue;

/**
 * leetcode-1609:奇偶树
 * 如果一棵二叉树满足下述几个条件，则可以称为 奇偶树 ：
     * 二叉树根节点所在层下标为 0 ，根的子节点所在层下标为 1 ，根的孙节点所在层下标为 2 ，依此类推。
     * 偶数下标 层上的所有节点的值都是 奇 整数，从左到右按顺序 严格递增
     * 奇数下标 层上的所有节点的值都是 偶 整数，从左到右按顺序 严格递减
 * 给你二叉树的根节点，如果二叉树为 奇偶树 ，则返回 true ，否则返回 false 。
 */
public class IsEvenOddTree {

    /**
     * 广度优先搜索(BFS)
     *
     * 复杂度分析
     *  时间复杂度:O(n),n是二叉树的节点数，每个节点会被访问一次
     *  空间复杂度:O(n),队列所使用的空间
     * @param root
     * @return
     */
    public boolean solution(TreeNode root){

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;

        while (!queue.isEmpty()){
            int size = queue.size();
            int prev = level % 2 == 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            for (int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                int val = node.val;
                //奇数下标层节点值是偶数，偶数下标层节点值是奇数
                if (level % 2 == val % 2){
                    return false;
                }
                if ((level % 2 == 0 && val <= prev) || (level % 2 != 0 && val >= prev)){
                    return false;
                }
                prev = val;
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
        }

        return true;
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

        IsEvenOddTree tree = new IsEvenOddTree();
        TreeNode root = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(7);

        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;

        boolean ans = tree.solution(root);
        System.out.println(ans);
    }
}
