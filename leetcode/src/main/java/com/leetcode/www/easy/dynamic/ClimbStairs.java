package com.leetcode.www.easy.dynamic;

/**
 * leetcode-70:爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 */
public class ClimbStairs {


    /**
     * 动态规划版
     *      1. 状态定义:dp[i]表示到第阶有多少中方法
     *      2. 状态转移方程
     *          dp[i] = dp[i-1] + dp[i-2],由于一次可以爬1阶或者2阶，所以第i阶可以由第i-1阶或者第i-2阶到达
     *      3. 边界条件: dp[0] = 1, dp[1] = 1;
     *      4. 复杂度分析
     *          时间复杂度:O(n),n是楼梯的阶数
     *          空间复杂度:O(n),n为楼梯的阶数
     * @param n
     * @return
     */
    public int solution(int n){

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }


    public int solutionV2(int n){

        if (n <= 2){
            return n;
        }

        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }

    public int solutionV3(int n){

        if (n == 1){
            return 1;
        }
        if (n == 2){
            return 2;
        }

        return solutionV3(n-1) + solutionV3(n-2);
    }

    public static void main(String[] args) {

        ClimbStairs climbStairs = new ClimbStairs();
        int ans = climbStairs.solutionV3(3);
        System.out.println(ans);
    }
}
