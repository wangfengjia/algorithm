package com.leetcode.www.middle.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * leetcode-669:修剪二叉搜索树
 * 给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。通过修剪二叉搜索树，使得所有节点的值在[low, high]中。修剪树 不应该改变保留在树中的元素
 * 的相对结构 (即，如果没有被移除，原有的父代子代关系都应当保留)。 可以证明，存在唯一的答案。
 * 所以结果应当返回修剪好的二叉搜索树的新的根节点。注意，根节点可能会根据给定的边界发生改变。
 */
public class TrimBST {


    /**
     * 使用DFS来实现
     * 复杂度分析
     *  时间复杂度:O(N),N是给定树的节点数目，最多访问每个节点一次
     *  时间复杂度:O(N),N是给定树的节点数目，递归调用的栈空间在最坏情况下可能和N一样大
     * @param root
     * @param low
     * @param high
     * @return
     */
    public TreeNode solution(TreeNode root, int low, int high){

        return dfs(root, low, high);
    }

    private TreeNode dfs(TreeNode root, int low, int high){

        if (root == null){
            return null;
        }
        //root节点的值小于low时，root节点的左子树都是不满足条件的节点，将root节点的右子树中第一个满足条件的节点返回到上一层，同时通过这个操作将root节点和
        //root节点的右子树剪枝掉了
        if (root.val < low){
            return dfs(root.right, low, high);
        }
        if (root.val > high){
            return dfs(root.left, low, high);
        }

        //递归处理root节点的左子树和右子树
        root.left = dfs(root.left, low, high);
        root.right = dfs(root.right, low, high);
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


    public static void main(String[] args) {


        TrimBST bst = new TrimBST();
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(0);
        TreeNode node3 = new TreeNode(2);

        root.left = node2;
        root.right = node3;

        int low = 1;
        int high = 2;
        TreeNode ans = bst.solution(root, low, high);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> eles = new ArrayList<>();
            for (int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                eles.add(node.val);
                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right != null){
                    queue.add(node.right);
                }
            }
            System.out.println(eles);
        }
    }
}
