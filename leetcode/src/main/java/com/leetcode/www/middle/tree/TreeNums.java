package com.leetcode.www.middle.tree;

/**
 * leetcode-96:不同的二叉搜索树
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
 */
public class TreeNums {


    /**
     * 给定一个有序序列1...n，为了构建出一颗二叉搜索树。我们可以遍历每个数字i，将这个数字作为树根，将1...(i-1)序列作为左子树，将i+1..n序列作为右子树，接着我们
     * 可以按照同样的方式递归构建左右子树,在这个构建过程中，由于根的值不同，所以每棵二叉搜索树是唯一的
     * 动态规划
     *      状态定义:dp[i]表示长度为i的序列能够构成的不同二叉搜索树的个数
     *      状态转移方程:对于长度为i的有序序列1..n,遍历每一个数，这样就是分成了长度为j-1(1...j-1)和i-j(j..i)的两个序列分别作为左右子树，所以对于j为根的所有
     *                 二叉搜索树的集合会等于左子树集合和右子树集合的笛卡尔积
     *                 dp[i] = sum(dp[j-1] * dp[i-j]),j在1..i之间选择
     *      边界条件:
     *          dp[0] = 0;dp[1] = 1
     *      复杂度分析
     *          时间复杂度:O(n^2),n表示二叉搜索树的节点个数,dp状态数组有n个状态需要求解，每个状态求解需要O(n)的时间复杂度，所以总时间复杂度为O(n^2)
     *          空间复杂度:O(n),状态数组所消耗的空间
     * @param n
     * @return
     */
    public int solution(int n){

        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++){
            for (int j = 1; j <= i; j++){
                dp[i] += dp[j-1] * dp[i-j];
            }
        }

        return dp[n];
    }


    public static void main(String[] args) {

        TreeNums treeNums = new TreeNums();
        int ans = treeNums.solution(3);
        System.out.println(ans);
    }
}
