package com.leetcode.www.middle.tree;

/**
 * 验证一棵树是不是二叉搜索树
 *
 * 思路1
 * 对树进行中序(左根右)遍历，利用一个属性保存被遍历到的节点的前置节点，通过判断被遍历到的节点的值是否大于前置节点的值，假如出现大于的情况就表示不是
 * 二叉搜索树(左子节点应该是小于父节点的)
 *
 * 思路2
 * 递归遍历二叉树，判断二叉树的每个节点的左子节点的值是否小于它，同时右子节点的值是否大于它
 */
public class ValidateBinarySearchTree {


    private static class TreeNode{

        private TreeNode left;
        private TreeNode right;
        private int value;

        public TreeNode(TreeNode left, TreeNode right, int value){
            this.left = left;
            this.right = right;
            this.value = value;
        }
    }


    public boolean isValid(TreeNode root, Integer min, Integer max){

        if (root == null){
            return true;
        }

        if (min != null && root.value <= min){
            return false;
        }

        if (max != null && root.value >= max){
            return false;
        }

        return isValid(root.left, min, root.value) && isValid(root.right, root.value, max);
    }
}
