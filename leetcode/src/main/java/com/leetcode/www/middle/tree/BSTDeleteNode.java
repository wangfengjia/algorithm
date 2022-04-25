package com.leetcode.www.middle.tree;


import java.util.ArrayList;
import java.util.List;

/**
 * leetcode-450:删除二叉搜索树中的节点
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的key对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 * 一般来说，删除节点可分为两个步骤：
     * 首先找到需要删除的节点；
     * 如果找到了，删除它。
 *
 */
public class BSTDeleteNode {


    /**
     * 递归:核心思路是:进行深度优先搜索，找到目标节点后，假如目标节点是叶子节点，直接删除，假如目标节点不是叶子节点，找到一个节点的值去替代这个目标节点。然后对
     *     这个新的节点进行以上步骤，知道新的节点是null或者是新的节点是叶子节点。步骤如下
     *     1. 目标节点是叶子节点，直接删除
     *     2. 目标节点不是叶子节点，且目标节点的右子节点不为空，则用这个节点的后继节点来替代目标节点。后继节点是目标节点的右子树中最左的节点，也就是中序遍历得到的
     *     值序列中，目标节点的下一个值，然后递归向下删除后继节点
     *     3. 目标节点不是叶子节点，且目标节点的左子节点不为空和目标节点的右子节点为空，则用这个节点的前驱节点来替代目标节点，前驱节点就是中序遍历得到的值序列中，
     *     目标节点的前一个值，也就是目标节点的左子树中最右的节点，然后递归向下删除前驱节点
     * 复杂度分析
     *  时间复杂度:O(log^n),n为二叉搜索树的高度
     *  空间复杂度:O(n),n为二叉搜索树的高度，递归时栈所使用的空间
     * @param root
     * @param key
     * @return
     */
    public TreeNode solution(TreeNode root, int key){

        return dfs(root, key);
    }

    private TreeNode dfs(TreeNode root, int key){

        if (root == null){
            return null;
        }

        if (root.val < key){
            root.right = dfs(root.right, key);
        } else if (root.val > key){
            root.left = dfs(root.left, key);
        }else {
            if (root.left == null && root.right == null){
                root = null;
            }else if (root.right != null){
                root.val = successor(root);
                root.right = dfs(root.right, root.val);
            }else {
                root.val = predecessor(root);
                root.left = dfs(root.left, root.val);
            }
        }


        return root;
    }

    private int successor(TreeNode root){
        root = root.right;
        while (root.left != null){
            root = root.left;
        }

        return root.val;
    }

    private int predecessor(TreeNode root){

        root = root.left;
        while (root.right != null){
            root = root.right;
        }

        return root.val;
    }

    List<Integer> inorderResult;
    public List<Integer> inorder(TreeNode root){

        inorderResult = new ArrayList<>();
        inorderDfs(root);
        return inorderResult;
    }

    private void inorderDfs(TreeNode root){

        if (root == null){
            return;
        }

        inorderDfs(root.left);
        inorderResult.add(root.val);
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

        BSTDeleteNode deleteNode = new BSTDeleteNode();

        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(6);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(7);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.right = node5;

        List<Integer> inorderResult1 = deleteNode.inorder(root);
        System.out.println(inorderResult1);

        TreeNode newRoot = deleteNode.solution(root, 3);
        List<Integer> ans = deleteNode.inorder(newRoot);
        System.out.println(ans);
    }
}
