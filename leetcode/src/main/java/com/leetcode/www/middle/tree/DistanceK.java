package com.leetcode.www.middle.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * leetcode-863:二叉树中所有距离为 K 的结点
 * 给定一个二叉树（具有根结点root），一个目标结点target，和一个整数值 k 。
 * 返回到目标结点 target 距离为 k 的所有结点的值的列表。 答案可以以 任何顺序 返回。
 */
public class DistanceK {

    private Map<Integer, TreeNode> parents = new HashMap<>();
    private List<Integer> ans = new ArrayList<>();
    /**
     * 深度優先搜索+哈希表:要找到与目标节点target距离为k的所有节点，可以以target节点为根节点，进行深度优先搜索，在搜索过程中，除了搜索左右儿子节点以外，
     *                  还可以顺着父节点向上搜索，找到所有深度为k的节点。同时，为了实现能够顺着父节点向上搜索，需要使用一个哈希表来维护每个节点的父节点，
     *                  由于每个节点的值是唯一的，所以哈希表的key可以是节点值。此外，为了避免在深度有点搜索过程中重复访问节点，递归时额外传入来源节点from，
     *                  在递归前比较目标节点和来源节点是够相同，不同的情况才进行递归
     * 复杂度分析
     *  时间复杂度:O(n),n是二叉树节点个数，需要执行两次深度优先搜索，每次时间复杂度为O(n)
     *  空间复杂度:O(n),n是二叉树节点个数，深度优先搜索需要O(n)的栈空间
     * @param root
     * @param target
     * @param k
     * @return
     */
    public List<Integer> solution(TreeNode root, TreeNode target, int k){

        //找到所有节点的父节点
        findParent(root);
        //从target出发，深度优先搜索，找到所有深度为k的节点
        dfs(target, null, 0, k);
        return ans;
    }

    private void findParent(TreeNode root){

        if (root.left != null){
            parents.put(root.left.val, root);
            findParent(root.left);
        }
        if (root.right != null){
            parents.put(root.right.val, root);
            findParent(root.right);
        }
    }

    private void dfs(TreeNode root, TreeNode from, int depth, int k){

        if (root == null){
            return;
        }
        if (depth == k){
            ans.add(root.val);
            return;
        }

        if (root.left != from){
            dfs(root.left, root, depth+1, k);
        }
        if (root.right != from){
            dfs(root.right, root, depth+1, k);
        }
        if (parents.get(root.val) != from){
            dfs(parents.get(root.val), root, depth + 1, k);
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

    public static void main(String[] args) {

        DistanceK distanceK = new DistanceK();

        TreeNode root = new TreeNode(3);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(6);
        TreeNode node5 = new TreeNode(2);
        TreeNode node6 = new TreeNode(0);
        TreeNode node7 = new TreeNode(8);
        TreeNode node8 = new TreeNode(7);
        TreeNode node9 = new TreeNode(4);

        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node5.left = node8;
        node5.right = node9;


        List<Integer> ans = distanceK.solution(root, node2, 2);
        System.out.println(ans);
    }
}
