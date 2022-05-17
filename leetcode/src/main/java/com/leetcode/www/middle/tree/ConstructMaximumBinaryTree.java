package com.leetcode.www.middle.tree;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * leetcode-654:最大二叉树
 * 给定一个不重复的整数数组nums 。最大二叉树可以用下面的算法从nums 递归地构建:
     * 创建一个根节点，其值为nums 中的最大值。
     * 递归地在最大值左边的子数组前缀上构建左子树。
     * 递归地在最大值 右边 的子数组后缀上构建右子树。
 * 返回nums 构建的 最大二叉树 。
 */
public class ConstructMaximumBinaryTree {

    /**
     * 递归创建
     * 复杂度分析
     *  时间复杂度:O(n^2),n是数组nums的长度，一般情况下，每个元素的递归创建的复杂度为O(log^n),总的为O(nlog^n),最坏情况下,数组nums有序，总的复杂度为O(n^2)
     *  空间复杂度:O(n),递归调用深度为n。平均情况下，长度为n的数组递归调用深度为O(log^n)
     * @param nums
     * @return
     */
    public TreeNode solution(int[] nums){

        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int left, int right){

        if (left > right){
            return null;
        }

        int maxIndex = maxIndex(nums, left, right);
        TreeNode root = new TreeNode(nums[maxIndex]);
        root.left = build(nums, left, maxIndex - 1);
        root.right = build(nums, maxIndex + 1, right);

        return root;
    }

    private int maxIndex(int[] nums, int left, int right){

        int maxIndex = left;
        for (int i = left; i <= right; i++){
            if (nums[maxIndex] < nums[i]){
                maxIndex = i;
            }
        }

        return maxIndex;
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

        ConstructMaximumBinaryTree binaryTree = new ConstructMaximumBinaryTree();
        int[] nums = {3,2,1,6,0,5};
        TreeNode root = binaryTree.solution(nums);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> ansList = new ArrayList<>();
        while (!queue.isEmpty()){

            int size = queue.size();
            for (int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                ansList.add(node.val);
                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right != null){
                    queue.add(node.right);
                }
            }
        }

        System.out.println(ansList);
    }


}
