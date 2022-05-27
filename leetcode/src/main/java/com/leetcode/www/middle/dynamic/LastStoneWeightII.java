package com.leetcode.www.middle.dynamic;

/**
 * leetcode-1049:最后一块石头的重量 II
 * 有一堆石头，用整数数组stones 表示。其中stones[i] 表示第 i 块石头的重量。
 * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为x 和y，且x <= y。那么粉碎的可能结果如下：
     * 如果x == y，那么两块石头都会被完全粉碎；
     * 如果x != y，那么重量为x的石头将会完全粉碎，而重量为y的石头新重量为y-x。
 * 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
 */
public class LastStoneWeightII {


    /**
     * 动态规划
     * 将这个堆划分为两个石子堆(正号堆/负号堆)
        * 将每次操作中重量较大的石子放到正号堆，代表这次操作中此石子重量在最终运算结果中运用+运算符
        * 将每次操作中重量较小的石子放到负号堆，代表这次操作中此石子重量在最终运算结果中运用-运算符
     * 这个问题就转化为了为数组stones里面的每个元素添加+/-号，使得形成的计算表达式结果绝对值最小(类似于leetcode-494)。由于想要计算表达式结果绝对值最小，所以需要将石子划分为
     * 差值最小的两个堆。其实就是对计算表达式中带-号的数值提取公因数-1，进一步转化为两堆石子重量总和相减，绝对值最小
     * 此时，就将这个问题转化为了完全背包问题:从stones数组中选择若干个元素，凑成总和不超过sum/2的最大值
     *
     * 动态规划实现
     *      状态定义:dp[i][j]表示前i个元素中，凑成总和不超过j的最大值
     *      状态转移方程:由于每个元素都有选和不选两种决策，因此
     *          dp[i][j] = max(dp[i-1][j], dp[i-1][j-stones[i-1]] + stones[i])
     * 复杂度分析
     *  时间复杂度:O(n * sum),n是数组stones的长度，sum是数组stones所有元素之和
     *  空间复杂度:O(n * sum),n是数组stones的长度，sum是数组stones所有元素之和
     * @param stones
     * @return
     */
    public int solution(int[] stones){


        int n = stones.length;
        int sum = 0;
        for (int stone : stones){
            sum += stone;
        }
        int t = sum / 2;
        int[][] dp = new int[n+1][t+1];
        for (int i = 1; i <= n; i++){
            int cur = stones[i-1];
            for (int j = 0; j <= t; j++){
                dp[i][j] = dp[i-1][j];
                if (j >= cur){
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j - cur] + cur);
                }
            }
        }

        return sum - dp[n][t] - dp[n][t];
    }


    public static void main(String[] args) {

        LastStoneWeightII weightII = new LastStoneWeightII();
        int[] stones = {31,26,33,21,40};
        int ans = weightII.solution(stones);
        System.out.println(ans);
    }
}
