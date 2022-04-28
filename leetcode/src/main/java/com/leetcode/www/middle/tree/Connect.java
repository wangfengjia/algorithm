package com.leetcode.www.middle.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * leetcode-116:填充每个节点的下一个右侧节点指针
 * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有next 指针都被设置为 NULL。
 */
public class Connect {


    /**
     * 对树进行层序遍历,将同一层的节点放入队列里面，统计队列中现在元素的数量，再去处理next节点
     * @param root
     * @return
     */
    public TreeNode solution(TreeNode root){

        if (root == null){
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){

                //一次处理一个节点，取出队首
                TreeNode current = queue.poll();
                if (i < size - 1){
                    current.next = queue.peek();
                }
                if (current.left != null){
                    queue.add(current.left);
                }
                if (current.right != null){
                    queue.add(current.right);
                }
            }
        }

        return root;
    }

    public static class TreeNode{

        int val;
        TreeNode left;
        TreeNode right;
        TreeNode next;

        TreeNode(){}
        TreeNode(int val){
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right, TreeNode next){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
