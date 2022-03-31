package com.leetcode.www.middle.tree;


/**
 * leetcode538:把二叉搜索树转换为累加树
 * 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node的新值等于原树中大于或等于node.val的值之和。
 *
 */
public class ConvertBST {

    public int sum = 0;
    /**
     * 由于二叉搜索树的中序遍历结果是单调递增的有序序列，如果我们反序中序遍历就可以得到一个单调递减的有序序列。由于题目中的要求是将每个节点的值修改为原来的节点值
     * 加上所有大于他的节点的和，这样我们就可以通过反序中序遍历这课二叉搜索树，遍历过程中记录节点的和,并不断更新当前遍历到的节点的节点值
     * 复杂度分析
     *  1. 时间复杂度: O(N),N为二叉搜索树的节点数，每一个节点恰好被遍历一次
     *  2. 空间复杂度: O(N),递归过程中栈的开销
     * @param root
     * @return
     */
    public TreeNode solution(TreeNode root){

        if (root != null){
            solution(root.right);
            sum += root.val;
            root.val = sum;
            solution(root.left);
        }

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
