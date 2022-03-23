package com.leetcode.www.middle.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据中序遍历和后序遍历的结果重构二叉树，思路类似于根据前序遍历和中序遍历构造二叉树，
 * 区别是
 *  1.遍历根节点是从后序遍历结果的最后一个元素往前遍历(后面的元素是高度更高的子树的根节点)
 *  2.先构造右子树再构造左子树，这是由于上面第一个区别知道，右子树的根节点先遍历到
 */
public class ConstructFromInPost {

    public int rootIndex;
    public int[] inorder;
    public int[] postorder;
    public Map<Integer, Integer> map = new HashMap<>();

    public TreeNode solution(int[] inorder, int[] postorder){

        this.inorder = inorder;
        this.postorder = postorder;
        this.rootIndex = postorder.length - 1;

        for (int i = 0; i < inorder.length; i++){
            map.put(inorder[i], i);
        }

        return buildTree(0, inorder.length - 1);
    }

    /**
     * right - left + 1就是需要构建的这棵树的节点数量
     * @param left
     * @param right
     * @return
     */
    public TreeNode buildTree(int left, int right){

        if (left > right){
            return null;
        }

        int rootVal = postorder[rootIndex--];
        TreeNode root = new TreeNode(rootVal);
        int mid = map.get(rootVal);
        //先构造右子树
        root.right = buildTree(mid + 1, right);
        root.left = buildTree(left, mid - 1);
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
