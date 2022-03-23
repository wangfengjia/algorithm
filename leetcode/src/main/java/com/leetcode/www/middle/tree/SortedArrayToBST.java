package com.leetcode.www.middle.tree;

/**
 * 将有序数组转换为高度平衡的二叉搜索树(每个节点的左右子树的高度差不超过1)
 * 思路:中序遍历，总是选择中间位置左边的元素作为根节点
 */
public class SortedArrayToBST {


    public TreeNode solution(int[] arr){

        return buildTree(arr, 0, arr.length - 1);
    }

    public TreeNode buildTree(int[] arr, int left, int right){

        if (left > right){
            return null;
        }

        //总是将中间位置左边的元素作为根节点
        int middle = (left + right) / 2;
        TreeNode root = new TreeNode(arr[middle]);
        root.left = buildTree(arr, left, middle - 1);
        root.right = buildTree(arr, middle + 1, right);

        return root;
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
