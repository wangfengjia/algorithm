package com.leetcode.www.middle.tree;


import java.util.HashMap;
import java.util.Map;

/**
 * leetcode437:路径总和
 * 给定一个二叉树的根节点 root，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。路径不需要从根节点开始，也不需要在叶子节点结束，
 * 但是路径方向必须是向下的（只能从父节点到子节点）。
 */
public class PathSum {


    /**
     * 利用深度优先搜索:将每个节点作为根节点去进行深度优先搜索，在搜索过程中取统计路径和，并且更新目标路径数量
     * 复杂度分析
     *  1. 时间复杂度:O(N^2),N为二叉树节点的个数。对于每一个节点，求以该节点为起点的路径数目，则需要遍历以这个节点为根节点的子树的所有节点，因此求路径所花费
     *      *              的最大时间为O(N),我们会对每一个节点都求一次以该节点为起点的路径数目，则时间复杂度为O(N^2)
     *  2. 空间复杂度:O(N),深度优先搜索所需要的栈空间。
     * @param root
     * @param target
     * @return
     */
    public int solutionV1(TreeNode root, int target){

        if (root == null){
            return 0;
        }

        //每个节点作为根节点进行深度优先搜索
        int ret = rootSum(root, target);
        ret += solutionV1(root.left, target);
        ret += solutionV1(root.right, target);

        return ret;
    }

    public int rootSum(TreeNode root, int targetSum){

        if (root == null){
            return 0;
        }

        int ret = 0;
        if (root.val == targetSum){
            ret++;
        }


        ret += rootSum(root.left, targetSum - root.val);
        ret += rootSum(root.right, targetSum - root.val);

        return ret;
    }


    /**
     * 前缀和:在方法一种会存在很多重复计算,我们可以利用哈希表来保存前缀和，减少重复计算。前缀和是由根节点到当前节点的路径上所有节点的和。可以利用前序遍历来得到
     * 除了根节点以外的其他节点的前缀和。最后在已保存的路径前缀和中查找是否存在前缀和等于当前节点到根节点的前缀和cur减去targetSum(假如从很节点root到节点node
     * 的前缀和为cur，我们在已经保存的前缀和中去查找是否存在前缀和等于(cur-target)。假如从根节点root到节点node的路径上存在节点pi到根节点的前缀和为cur-targetSum,
     * 则节点pi+1到节点node的路径上所有节点的和一定是targetSum)
     * 复杂度分析
     *  1. 时间复杂度:O(N),N为二叉树中节点的数量，利用前缀和只需遍历一次二叉树即可
     *  2. 空间复杂度:O(N),深度优先搜索所需要的栈空间
     * @return
     */
    public int solutionV2(TreeNode root, int targetSum){

        if (root == null){
            return 0;
        }

        Map<Long, Integer> prefix = new HashMap<>();
        prefix.put(0L, 1);
        return dfs(root, prefix, 0, targetSum);
    }

    public int dfs(TreeNode root, Map<Long, Integer> prefix, long cur, int targetSum){

        if (root == null){
            return 0;
        }

        int ret = 0;
        cur += root.val;

        ret = prefix.getOrDefault((cur - targetSum), 0);
        //维护当前节点的前缀和
        prefix.put(cur, prefix.getOrDefault(cur, 0) + 1);
        ret += dfs(root.left, prefix, cur, targetSum);
        ret += dfs(root.right, prefix, cur, targetSum);
        //当前节点深度搜索完成以后，将这个节点的前缀和从哈希表中拿去，否则会使用到对这个节点的兄弟节点的遍历过程中，这样就不是一个合法的路径
        prefix.put(cur, prefix.getOrDefault(cur, 0) - 1);

        return ret;
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

        TreeNode root = new TreeNode(10);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(-3);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(2);
        TreeNode node6 = new TreeNode(11);
        TreeNode node7 = new TreeNode(3);
        TreeNode node8 = new TreeNode(-2);
        TreeNode node9 = new TreeNode(1);

        root.left = node1;
        root.right = node2;
        node1.left = node4;
        node1.right = node5;
        node2.right = node6;
        node4.left = node7;
        node4.right = node8;
        node5.right = node9;

        PathSum pathSum = new PathSum();
        int target = 8;
        int ans = pathSum.solutionV2(root, target);
        System.out.println(ans);
    }
}
