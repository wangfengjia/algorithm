package com.leetcode.www.middle.tree;


import java.util.LinkedList;
import java.util.Queue;

/**
 * leetcode-662:二叉树最大宽度
 * 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
 * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
 */
public class WidthOfBinaryTree {


    /**
     * BFS
     * 使用广度优先搜索，同时使用一个队列来记录每一个节点对应的位置索引
     * 复杂度分析
     *  时间复杂度:O(n),n是二叉树的节点数目，每个节点和对应的位置索引进出队列一次
     *  空间复杂度:O(n),n是二叉树的节点数目
     * @param root
     * @return
     */
    public int solution(TreeNode root){

        if (root == null){
            return 0;
        }

        int ans = 0;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> posQueue = new LinkedList<>();
        nodeQueue.offer(root);
        posQueue.offer(1);

        while (!nodeQueue.isEmpty()){

            int size = nodeQueue.size();
            int left = -1;
            int right = -1;
            for (int i = 0; i < size; i++){
                TreeNode node = nodeQueue.poll();
                int pos = posQueue.poll();
                if (i == 0){
                    left = pos;
                }
                right = pos;
                if (node.left != null){
                    nodeQueue.offer(node.left);
                    posQueue.offer(pos * 2);
                }
                if (node.right != null){
                    nodeQueue.offer(node.right);
                    posQueue.offer(pos * 2 + 1);
                }
            }

            ans = Math.max(ans, right - left + 1);
        }

        return ans;
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

        WidthOfBinaryTree binaryTree = new WidthOfBinaryTree();

        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(9);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node3.right = node5;
        node4.left = node6;
        node5.right = node7;

        int ans = binaryTree.solution(root);
        System.out.println(ans);
    }
}
