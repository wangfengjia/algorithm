package com.leetcode.www.middle.tree;


import java.util.*;

/**
 *
 * 层级遍历二叉树，将同一层的节点的值放入一个数组里面，返回一个二维数组
 * * Given a binary tree, return the level order traversal of its nodes' values.
 *  * (ie, from left to right, level by level).
 * For example:
 * Given binary tree {3,9,20,#,#,15,7},
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7a
 *
 * return its level order traversal as:
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 * @author wangyongchun
 * @date 2019/09/19 20:54
 */
public class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root){

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null){
            return result;
        }

        List<Integer> values = new ArrayList<Integer>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();

        queue.offer(root);
        queue.offer(null);

        while (queue.size() > 0){

            TreeNode treeNode = queue.poll();
            if (treeNode == null){
                result.add(new ArrayList<Integer>(values));
                values.clear();
                if (queue.size() == 0){
                    break;
                }
                queue.offer(null);
                continue;
            }

            values.add(treeNode.value);
            if (treeNode.left != null){
                queue.add(treeNode.left);
            }

            if (treeNode.right != null){
                queue.add(treeNode.right);
            }
        }

        return result;
    }

    public static class TreeNode{

        public int value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int value){
            this.value = value;
        }
    }

    public static void main(String[] args){

        System.out.println("test");

        TreeNode t0 = new TreeNode(3);
        TreeNode t1 = new TreeNode(9);
        TreeNode t2 = new TreeNode(20);
        TreeNode t3 = new TreeNode(15);
        TreeNode t4 = new TreeNode(7);

        t0.left = t1;
        t0.right = t2;
        t2.left = t3;
        t2.right = t4;

        BinaryTreeLevelOrderTraversal binaryTreeLevelOrderTraversal = new BinaryTreeLevelOrderTraversal();
        List<List<Integer>> lists = binaryTreeLevelOrderTraversal.levelOrder(t0);

        for (int i = 0; i < lists.size(); i++){
            System.out.println(Arrays.toString(lists.get(i).toArray()));
        }
    }
}
