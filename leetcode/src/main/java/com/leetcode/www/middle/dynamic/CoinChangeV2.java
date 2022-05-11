package com.leetcode.www.middle.dynamic;

/**
 * leetcode-518:零钱兑换 II
 * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
 * 假设每一种面额的硬币有无限个。
 * 题目数据保证结果符合 32 位带符号整数。
 */
public class CoinChangeV2 {

    /**
     * 动态规划:
     *      1. 状态定义:dp[i]表示和为i的硬币组合数
     *      2. 状态转移方程:对于面额为coin的硬币，coin<=i<=amount,假如存在一种组合的金额之和会等于i-coin,则在这个组合中增加一个面额为coin的硬币，就
     *                    可以得到一个金额之和等于i的硬币组合。因此需要遍历coins，对于其中每一个面额的硬币，更新dp数组中每个大于或者等于此面额的组合数
     *                    dp[i] = sum(dp[i-coin])
     *      3. 边界条件:dp[0] = 1,只有当不选取硬币时，金额之和才为0,因此只有一种硬币组合
     * 复杂度分析
     *  时间复杂度:O(amount*n),n表示数组coins的长度
     *  空间复杂度:O(amount),dp数组所使用的空间
     * @param coins
     * @param amount
     * @return
     */
    public int solution(int[] coins, int amount){

        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins){
            for (int i = coin; i <= amount; i++){
                dp[i] += dp[i-coin];
            }
        }

        return dp[amount];
    }


    public static void main(String[] args) {

        CoinChangeV2 coinChangeV2 = new CoinChangeV2();
        int[] coins = {1,2,5};
        int amount = 5;
        int ans = coinChangeV2.solution(coins, amount);
        System.out.println(ans);
    }
}
