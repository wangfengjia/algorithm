package com.leetcode.www.middle.dynamic;

/**
 * leetcode-343:整数拆分
 * 给定一个正整数 n，将其拆分为 k 个 正整数 的和（k >= 2），并使这些整数的乘积最大化。 返回 你可以获得的最大乘积 。
 */
public class IntegerBreak {


    /**
     * 动态规划
     *      1. 状态定义:dp[i]表示将正整数拆分成至少两个正整数之和后，这些正整数的最大成绩
     *      2. 状态转移方程:当i>=2,假设对i拆分出的第一个正整数为j(1 <= j < i),则有两种方案
     *          1.将i拆分成j和i-j之和，且i-j不再拆分，此时的乘积为j*(i-j)
     *          2.将i拆分成j和i-j之和，且i-j继续拆分成多个正整数，此时的乘积为j*dp[i-j]
     *          则状态转移方程为dp[i] = max(j*(i-j), j*dp[i-j]),并且1<=j<i-j
     *      3. 边界条件:0不是正整数，1是最小的正整数，都不可拆分
     *          dp[0] = 0;
     *          dp[1] = 0;
     * @param n
     * @return
     */
    public int solution(int n){

        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 0;

        for (int i = 2; i <= n; i++){
            for (int j = 1; j < i; j++){
                int cur = Math.max(j * (i - j), j * dp[i-j]);
                dp[i] = Math.max(cur, dp[i]);
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {

        IntegerBreak integerBreak = new IntegerBreak();
        int n = 10;
        int ans = integerBreak.solution(n);
        System.out.println(ans);
    }
}
