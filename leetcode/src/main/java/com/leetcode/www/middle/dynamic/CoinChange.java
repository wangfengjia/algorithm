package com.leetcode.www.middle.dynamic;


import java.util.Arrays;

/**
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount，表示总金额。计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成
 * 总金额，返回-1 。你可以认为每种硬币的数量是无限的。
 *
 */
public class CoinChange {

    /**
     * 动态规划
     *      1. 状态定义:dp[i]表示组成金额i所需的最小硬币数量
     *      2. 状态转移方程: dp[i] = min(dp[i-coin[j]]) + 1。要求dp[i]时，金额i可以通过i - coins[j]再加上当前遍历的coins[j]值得到，
     *                     这样dp[i] = min(dp[i - coins[j]] + 1),j的范围是[0..coins.length-1]
     *      3. 边界条件:dp[0] = 0;
     *      4. 复杂度分析
     *          1. 时间复杂度:O(Sn),其中S表示金额，n表示硬币的数量。我们一共需要计算O(S)个状态，对于每个状态，需要去遍历n个硬币来转移状态，所以需要O(Sn)时间复杂度
     *          2. 空间复杂度:O(S),数组dp所需要的空间
     * @param coins
     * @return
     */
    public int solution(int[] coins, int amount){

        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++){
            for (int j = 0; j < coins.length; j++){
                if (coins[j] <= i){
                    int coinNumber = dp[i - coins[j]] + 1;
                    dp[i] = Math.min(dp[i], coinNumber);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {

        int[] coins = new int[]{1,2,5};
        int amount = 11;
        CoinChange coinChange = new CoinChange();
        int numbers = coinChange.solution(coins, amount);
        System.out.println(numbers);
    }

}
