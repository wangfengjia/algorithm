package com.leetcode.www.middle.tree;

/**
 * leetcode-129:求根节点到叶节点数字之和
 * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 *   例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 * 叶节点 是指没有子节点的节点
 */
public class SumNumbers {


    /**
     * 深度优先搜索:每个节点对应一个数字，这个数字会等于其父节点的数字乘以10再加上这个节点的值。只要计算出每个叶子节点对应的数字，然后计算所有叶子节点对应的数字和
     *            即可得到结果。利用深度优先搜索得到到达每个叶子节点的所有路径
     * 复杂度分析
     *  时间复杂度:O(n),n为二叉树节点的个数，每个节点被访问一次
     *  空间复杂度:O(n),n为二叉树节点的个数，递归调用栈所消耗的空间
     * @param root
     * @return
     */
    public int solution(TreeNode root){

        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int prevSum){

        if (root == null){
            return 0;
        }

        int sum = prevSum * 10 + root.val;
        //叶子节点
        if (root.left == null && root.right == null){
            return sum;
        }else {
            return dfs(root.left, sum) + dfs(root.right, sum);
        }
    }

    int res = 0;
    public int solutionV2(TreeNode root){

        dfsV2(root, 0);
        return res;
    }

    private void dfsV2(TreeNode root, int preSum){

        int sum = preSum * 10 + root.val;
        if (root.left == null && root.right == null){
            res += sum;
        }else {
            if (root.left != null){
                dfsV2(root.left, sum);
            }
            if (root.right != null){
                dfsV2(root.right, sum);
            }
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

        SumNumbers sumNumbers = new SumNumbers();

        TreeNode root = new TreeNode(4);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(0);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(1);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;

        int ans1 = sumNumbers.solution(root);
        int ans2 = sumNumbers.solutionV2(root);
        System.out.println(ans1);
        System.out.println(ans2);
    }
}
