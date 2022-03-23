package com.leetcode.www.middle.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 *      1. 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 *      2. 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 * 方法一:先进行前序遍历，得到一个数组，然后去构造链表，时间复杂度为0(1)
 * 方法二:
 *      1. 前两种方法都借助前序遍历，前序遍历过程中需要使用栈存储节点。有没有空间复杂度是 O(1)O(1) 的做法呢？注意到前序遍历访问各节点的顺序是根节点、左子树、右子树。
 *      如果一个节点的左子节点为空，则该节点不需要进行展开操作。如果一个节点的左子节点不为空，则该节点的左子树中的最后一个节点被访问之后，该节点的右子节点被访问。该节点的左子树中最后一个被访问的节点是左子树中的最右边的节点，也是该节点的前驱节点。因此，问题转化成寻找当前节点的前驱节点。
 *      2. 具体做法是，对于当前节点，如果其左子节点不为空，则在其左子树中找到最右边的节点，作为前驱节点，将当前节点的右子节点赋给前驱节点的右子节点，
 *      然后将当前节点的左子节点赋给当前节点的右子节点，并将当前节点的左子节点设为空。对当前节点处理结束后，继续处理链表中的下一个节点，直到所有节点都处理结束。
 */
public class BSTToLinkedList {


    /**
     * 方法一的解法
     * @param root
     * @return
     */
    public void solutionV1(TreeNode root){

        List<TreeNode> list = new ArrayList<>();
        preorderTraversal(root, list);
        for (int i = 1; i < list.size(); i++){
            TreeNode prev = list.get(i - 1);
            TreeNode curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }


    public void preorderTraversal(TreeNode root, List<TreeNode> list){
        if (root == null){
            return;
        }

        list.add(root);
        preorderTraversal(root.left, list);
        preorderTraversal(root.right, list);
    }

    /**
     * 方法二:寻找一个节点的右子节点的前驱节点，前驱节点是这个节点的左子树的最右边的节点，假如左子树没有右节点，则前驱节点是这个节点的左子节点本身
     * @param root
     */
    public void solutionV2(TreeNode root){

        TreeNode curr = root;
        while (curr != null){
            if (curr.left != null){
                TreeNode next = curr.left;
                TreeNode predecessor = next;
                while (predecessor != null){
                    predecessor = predecessor.right;
                }
                predecessor.right = curr.right;
                curr.right = next;
                curr.left = null;
            }
            curr = curr.right;
        }
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
