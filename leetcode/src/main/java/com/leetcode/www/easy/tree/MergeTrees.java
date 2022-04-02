package com.leetcode.www.easy.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * leetcode-617:合并二叉树
 * 给你两棵二叉树： root1 和 root2 。想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。你需要将这两棵树合并成一棵
 * 新二叉树。合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null 的节点将直接作为新二叉树的节点。返回合并后的二叉树。
 * 注意: 合并过程必须从两个树的根节点开始。
 *
 */
public class MergeTrees {


    public TreeNode solution(TreeNode root1, TreeNode root2){

//        return dfs(root1, root2);
        return bfs(root1, root2);
    }


    /**
     * 深度优先搜索合并二叉树:从根节点开始同时遍历二叉树，并对相应的节点进行合并，存在三种不同的情况，每种情况有不同的合并方式
     *                    1. 如果两个二叉树对应的节点为空，则合并后的二叉树对应的节点为空
     *                    2. 如果两个二叉树对应的节点只有一个为空，则合并后的二叉树的对应节点为其中的非空节点
     *                    3. 如果两个二叉树对应的节点都不为空，则合并后的二叉树的对应节点的值为两个二叉树的对应节点之和
     * 复杂度分析
     *  1. 时间复杂度:O(min(m,n)),m,n表示两个二叉树的节点个数，在深度优先搜索时，只有两个二叉树对应的节点不为空时才会对这个节点进行合并操作，因此被访问到的
     *              节点数不会超过较小的二叉树的节点数
     *  2. 空间复杂度:O(min(m,n)),m,n表示二叉树的节点个数。空间复杂度取决于递归调用的层数，递归调用的层数不会超过较小的二叉树的最大高度，最坏情况下，
     *              二叉树的高度等于节点数目
     * @param root1
     * @param root2
     * @return
     */
    public TreeNode dfs(TreeNode root1, TreeNode root2){


        if (root1 == null){
            return root2;
        }
        if (root2 == null){
            return root1;
        }

        TreeNode merged = new TreeNode(root1.val + root2.val);
        merged.left = dfs(root1.left, root2.left);
        merged.right = dfs(root1.right, root2.right);

        return merged;
    }


    public TreeNode bfs(TreeNode root1, TreeNode root2){

        if (root1 == null){
            return root2;
        }
        if (root2 == null){
            return root1;
        }

        TreeNode merged = new TreeNode(root1.val + root2.val);
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue.offer(merged);
        queue1.offer(root1);
        queue2.offer(root2);

        while (!queue1.isEmpty() && !queue2.isEmpty()){

            TreeNode node = queue.poll();
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();
            TreeNode left1 = node1.left;
            TreeNode left2 = node2.left;
            TreeNode right1 = node1.right;
            TreeNode right2 = node2.right;

            if (left1 != null || left2 != null){
                if (left1 != null && left2 != null){
                    TreeNode newLeft = new TreeNode(left1.val + left2.val);
                    node.left = newLeft;
                    queue.offer(newLeft);
                    queue1.offer(left1);
                    queue2.offer(left2);
                }else if (left1 != null){ // 另外一个原始二叉树的左子树为空，则另外一个原始二叉树的左子树为合并后二叉树的左子树，不再需要遍历，也就不需要加到队列
                    node.left = left1;
                }else {
                    node.left = left2;
                }
            }

            if (right1 != null || right2 != null){
                if (right1 != null && right2 != null){
                    TreeNode newRight = new TreeNode(right1.val + right2.val);
                    node.right = newRight;
                    queue.offer(newRight);
                    queue1.offer(right1);
                    queue2.offer(right2);
                } else if (right1 != null){
                    node.right = right1;
                }else {
                    node.right = right2;
                }
            }
        }

        return merged;
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
