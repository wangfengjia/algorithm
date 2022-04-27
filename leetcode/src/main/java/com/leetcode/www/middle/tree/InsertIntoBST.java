package com.leetcode.www.middle.tree;


import java.util.ArrayList;
import java.util.List;

/**
 * leetcode-701:二叉搜索树中的插入操作
 * 给定二叉搜索树（BST）的根节点root和要插入树中的值value，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据保证，新值和原始二叉搜索树中的任意节
 * 点值都不同。注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。
 */
public class InsertIntoBST {


    /**
     * 将新的节点插入到当前二叉搜索树的其中一个叶子节点上，利用深度优先搜索找到这个叶子节点，然后将新的节点作为这个叶子节点的左子节点或者右子节点
     * 复杂度分析
     *  时间复杂度:O(n),n为二叉搜索树的节点数目
     *  空间复杂度:O(1)
     * @param root
     * @param val
     * @return
     */
    public TreeNode solution(TreeNode root, int val){
        return dfs(root, val);
    }

    private TreeNode dfs(TreeNode root, int val){

        if (root == null){
            return new TreeNode(val);
        }

        if (root.val < val){
            root.right = dfs(root.right, val);
        }else {
            root.left = dfs(root.left, val);
        }

        return root;
    }

    List<Integer> inOrderResult;
    public List<Integer> inorder(TreeNode root){
        inOrderResult = new ArrayList<>();
        inorderDfs(root);
        return inOrderResult;
    }

    private void inorderDfs(TreeNode root){

        if (root == null){
            return;
        }

        inorderDfs(root.left);
        inOrderResult.add(root.val);
        inorderDfs(root.right);
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

        InsertIntoBST insertIntoBST = new InsertIntoBST();

        TreeNode root = new TreeNode(4);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(7);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(3);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;

        List<Integer> ans1 = insertIntoBST.inorder(root);
        System.out.println(ans1);

        TreeNode ans2 = insertIntoBST.solution(root, 5);
        List<Integer> ans3 = insertIntoBST.inorder(ans2);
        System.out.println(ans3);
    }
}
