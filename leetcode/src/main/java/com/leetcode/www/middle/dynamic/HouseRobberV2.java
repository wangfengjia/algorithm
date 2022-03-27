package com.leetcode.www.middle.dynamic;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode337(打家劫舍III):小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为root。除了root之外，每栋房子有且只有一个“父“房子与之相连。
 * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。给定二叉树的root。
 * 返回在不触动警报的情况下，小偷能够盗取的最高金额。
 *
 */
public class HouseRobberV2 {

    Map<TreeNode, Integer> choosed = new HashMap<>();
    Map<TreeNode, Integer> ignored = new HashMap<>();

    /**
     * 动态规划:可以将这个问题转化这样一个问题:一棵二叉树，每个节点都有权重值，每个节点有两个选择:选中和不选中，问在不同时选中父子节点的情况下，能选中的节点的
     *         最大权值和是多少
     *         1. 状态定义:
     *              f(o)表示在选择o节点的情况下，这个节点的子树上被选择的节点的最大权值和
     *              g(o)表示在不选择o节点的情况下，这个节点的子树上被选择的节点的最大权值和
     *         2. 状态转移方程:
     *              当需要做出选择是否选中当前节点时，这个操作的最大权值和由上一个状态得到，上一个状态就是他的左右子节点的状态
     *              f(o) = g(o.left) + g(o.right)。在o节点被选中时，它的左右子节点不能被选中，所以子树上被选中点的最大权值和为l和r不被选中的最大权值和相加
     *              g(o) = max(f(l), g(l)) + max(f(r), g(r))。在o节点不选中的情况下，它的左右子节点可以选中或者不选中，然后取左右子节点选中和不选中两个
     *                     选择下的最大值之和
     *
     *          3. 复杂度分析
     *              时间复杂度:O(n),n为二叉树节点的数量，由于对二叉树做了一次后序遍历，所以时间复杂度为O(n)
     *              空间复杂度:O(n),哈希表所占用的空间
     * @param root
     * @return
     */
    public int solution(TreeNode root){

        if (root == null){
            return 0;
        }

        // 进行深度优先遍历，所以是先处理高度更低的节点，后处理高度更大的节点，root节点是最后处理，因此能选中节点的最大权值和就是
        // max(choosed[root], ignored[root]),也就是小偷能够盗取的最大金额
        dfs(root);
        return Math.max(choosed.getOrDefault(root, 0), ignored.getOrDefault(root, 0));
    }

    public void dfs(TreeNode root){

        if (root == null){
            return;
        }

        dfs(root.left);
        dfs(root.right);
        choosed.put(root, (root.val + ignored.getOrDefault(root.left, 0) + ignored.getOrDefault(root.right, 0)));
        ignored.put(root, Math.max(choosed.getOrDefault(root.left, 0), ignored.getOrDefault(root.left, 0))
                + Math.max(choosed.getOrDefault(root.right, 0), ignored.getOrDefault(root.right, 0)));
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

        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(1);

        root.left = node1;
        root.right = node2;
        node1.right = node3;
        node2.right = node4;

        HouseRobberV2 houseRobberV2 = new HouseRobberV2();
        int maxValue = houseRobberV2.solution(root);
        System.out.println(maxValue);
    }
}
