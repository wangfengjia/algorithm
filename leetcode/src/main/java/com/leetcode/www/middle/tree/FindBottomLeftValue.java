package com.leetcode.www.middle.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * leetcode-513:找树左下角的值
 * 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
 * 假设二叉树中至少有一个节点。
 */
public class FindBottomLeftValue {


    /**
     * 使用层序遍历，使用一个变量ans来保存每层的第一个节点，最后这个变量对应的节点就是最左边的节点
     * 复杂度分析
     *  时间复杂度:O(N),N表示二叉树的节点数目，每个节点仅被访问一次
     *  空间复杂度:O(N),N表示二叉树的节点数目,队列所使用的空间
     * @param root
     * @return
     */
    public TreeNode solution(TreeNode root){

        if (root == null){
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode ans = null;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                if (i == 0){
                    ans = node;
                }
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
        }

        return ans;
    }


    int max = Integer.MIN_VALUE;
    int res = 0;
    /**
     * 深度优先搜索:先定义一个最大深度，记录当前访问到的最大深度，在深度优先搜索过程中，比较每个节点的depth和这个最大值的关系，比这个最大值大就更新这个最大深度
     *            和当前最大深度最左边节点的值
     * @param root
     * @return
     */
    public int solutionV2(TreeNode root){

        dfs(root, 0);
        return res;
    }

    private void dfs(TreeNode root, int depth){

        if (root == null){
            return;
        }
        if (depth > max){
            res = root.val;
            max = depth;
        }
        dfs(root.left, depth + 1);
        dfs(root.right, depth + 1);
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

        FindBottomLeftValue findBottomLeftValue = new FindBottomLeftValue();
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(7);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node2.left = node4;
        node2.right = node5;
        node4.left = node6;
        int ans = findBottomLeftValue.solutionV2(root);
        System.out.println(ans);
    }
}
