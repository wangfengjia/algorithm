package com.leetcode.www.middle.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给你二叉树的根节点 root ，返回其节点值的层序遍历.(即逐层地，从左到右访问所有节点）。
 * 思路(核心是使用队列):首先根节点入列，然后当队列不为空的时候，队列里面的节点就是同一层的(输出的结果是在同一个数组)，然后依次从队列中取出数量等于队列长度个元素
 *                   进行拓展，然后进入进入下一次迭代
 */
public class BSTLevelOrder {


    public List<List<Integer>> solution(TreeNode root){

        List<List<Integer>> ans = new ArrayList<>();
        if (root == null){
            return ans;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            List<Integer> level = new ArrayList<>();
            int currentSize = queue.size();
            for (int i = 1; i <= currentSize; i++){
                TreeNode currentNode = queue.poll();
                level.add(currentNode.val);
                if (currentNode.left != null){
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null){
                    queue.offer(currentNode.right);
                }
            }

            ans.add(level);
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
}
