package com.leetcode.www.middle.tree;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * leetcode-814:二叉树剪枝
 * 给你二叉树的根结点root，此外树的每个结点的值要么是 0 ，要么是 1 。
 * 返回移除了所有不包含 1 的子树的原二叉树。
 * 节点 node 的子树为 node 本身加上所有 node 的后代。
 */
public class PruneTree {

    /**
     * 递归
     * 复杂度分析
     *  时间复杂度:O(N),N是树中节点数目
     *  空间复杂度:O(H),H是树的高度，递归时使用的栈空间大小
     * @param root
     * @return
     */
    public TreeNode solution(TreeNode root){

        return dfs(root) ? root : null;
    }

    /**
     * 判断root为根的子树中是否包含1,只有当root节点的值不等于1且左右子树中也不包含1的时候才返回false。当root节点的左子树或者右子树不包含1时，就将root节点
     * 的left或者right指针置空
     * @param root
     * @return
     */
    private boolean dfs(TreeNode root){

        if (root == null){
            return false;
        }
        boolean left = dfs(root.left);
        boolean right = dfs(root.right);
        if (!left){
            root.left = null;
        }
        if (!right){
            root.right = null;
        }

        return root.val == 1 || left || right;
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

        PruneTree pruneTree = new PruneTree();

        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(0);
        TreeNode node3 = new TreeNode(0);
        TreeNode node4 = new TreeNode(1);

        root.right = node2;
        node2.left = node3;
        node2.right = node4;

        TreeNode ans = pruneTree.solution(root);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(ans);
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }

            System.out.println(list);
        }
    }
}
