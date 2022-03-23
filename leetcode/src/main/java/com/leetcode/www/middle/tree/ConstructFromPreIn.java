package com.leetcode.www.middle.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据前序遍历和中序遍历的结果重构二叉树，
 * 思路:
 * 一棵树的前序遍历结果是[根节点，左子树前序遍历结果,右子树前序遍历结果]，中序遍历结果是[左子树中序遍历结果，根节点，右子树中序遍历结果]，这样就可以在中序遍历结果中
 * 查找根节点的位置，然后根据根节点的位置得到左子树的节点和右子树的节点。因此，从上往下构造树，遍历前序遍历结果数组，每个元素作为根节点(根据前序遍历的特点，
 * 数组前面的元素是是高度更高的子树的根节点)去中序遍历结果中得到左子树的节点和右子树的节点，然后递归重构左子树和右子树，最后得到二叉树
 *
 */
public class ConstructFromPreIn {

    public int rootIndex = 0;
    public int[] preorder;
    public int[] postorder;
    public Map<Integer, Integer> map = new HashMap<>();

    public TreeNode solution(int[] preorder, int[] postorder){

        this.preorder = preorder;
        this.postorder = postorder;
        for (int i = 0; i < postorder.length; i++){
            map.put(postorder[i], i);
        }

        return buildTree(0, preorder.length - 1);
    }

    public TreeNode buildTree(int left, int right){

        if (left > right){
            return null;
        }

        int rootVal = preorder[rootIndex++];
        TreeNode root = new TreeNode(rootVal);
        int mid = map.get(rootVal);
        root.left = buildTree(left, mid - 1);
        root.right = buildTree(mid + 1, right);
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
