package com.leetcode.www.easy.dynamic;

/**
 * 买卖股票最佳时机问题
 * 方法一(动态规划):用T[i][k]来表示第i天结束时，最多进行k次交易的情况下可以获得的最大收益。为了得到状态转移方程，可以将T[i][k]关联到它的子问题，例如T[i-1][k],
 *                T[i][k-1], T[i-1][k-1]，对这些子问题进行求解，就可以得到状态转移方程。
 *                对这些子问题求解的办法是:看第i天可能进行的操作，只有三个可能的操作:买入，卖出，休息，同时题目要求是不能同时进行多次交易，就是如果觉得在第i天
 *                买入，在买入之前必须持有0股票，如果决定在第i天卖出，在卖出之前必须持有1股票。因此需要对T[i][k]的定义分成两项
 *                  1. T[i][k][0]表示第i天结束时，最多进行k次交易且在进行操作后持有0股票的情况下可以获得最大收益
 *                  2. T[i][k][1]表示在第i天结束时，最多进行k次交易且在进行操作后持有1份股票的情况下可以获得最大收益
 *                把T[i][k]的定义分成上面两项后，可以得到基准情况和状态转移方程
 *                基准情况
 *                  T[-1][k][0] = 0,T[-1][k][1] = -Infinity
 *                  T[i][0][0] = 0, T[i][0][1] = -Infinity
 *                状态转移方程(每次交易包含两次成对操作:买入和卖出，只有买入操作会改变允许的最大交易次数)
 *                  //T[i][k][0] 表示第i天结束时，恰好进行j笔交易，并且手中不持有股票
 *                  T[i][k][0] = max(T[i-1][k][0], T[i-1][k][1] + prices[i])
 *                  //T[i][k][1] 表示第i天结束后，恰好进行了j笔交易，并且手中不持有股票
 *                  T[i][k][1] = max(T[i-1][k][1], T[i-1][k-1][0] - prices[i]),买入操作会使用一次交易
 */
public class MaxProfit {

    /**
     * leetcode-121
     * k = 1(要做一次交易)的情况,每天有两个未知变量T[i][1][0], T[i][1][1]
     * T[i][1][0] = max(T[i-1][1][0], T[i-1][1][1] + prices[i])
     * //由于在这种情况下，需要做一次买入操作，需要一次交易次数，所以前一天的最大交易次数只有k-1
     * T[i][1][1] = max(T[i-1][1][1], T[i-1][0][0] - price[i]) = max(T[i-1][1][1], -prices[i])(T[i-1][0][0] = 0)
     * 由于k等于1，k-1等于0，就是最大交易数量为0，T[i][0][0] = 0,所以可以把状态转移方程中k的维度拿去
     * @return
     */
    public int version1(int[] prices){

        if (prices == null || prices.length == 0){
            return 0;
        }

        int length = prices.length;
        int[][] dp = new int[length][2];
        dp[0][0] = 0;
        //买入
        dp[0][1] = -prices[0];
        for (int i = 1; i < length; i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], -prices[i]);
        }

        //结束时持有0份股票肯定比持有1份股票收益大
        return dp[length-1][0];
    }


    /**
     * leetcode-123,k=2,表示最多只能完成两笔交易，这样的条件下就会有4个未知变量T[i][1][0], T[i][1][1], T[i][2][0], T[i][2][1],状态转移方程为
     * T[i][2][0] = max(T[i-1][2][0], T[i-1][2][1] + prices[i])
     * T[i][2][1] = max(T[i-1][2][1], T[i-1][1][0] - price[i])
     * T[i][1][0] = max(T[i-1][1][0], T[i-1][1][1] + prices[i])
     * T[i][1][1] = max(T[i-1][1][1], T[i-1][0][0] - prices[i]),由于 T[i-1][0][0] = 0，所以=>max(T[i-1][1][1], -prices[i])
     * @param prices
     * @return
     */
    public int version2(int[] prices){

        if (prices == null || prices.length == 0){
            return 0;
        }

        int length = prices.length;
        int[][][] dp = new int[length][3][2];
        dp[0][1][0] = 0;
        dp[0][1][1] = -prices[0];
        dp[0][2][0] = 0;
        dp[0][2][1] = -prices[0];

        for (int i = 1; i < length; i++){
            dp[i][2][0] = Math.max(dp[i-1][2][0], dp[i-1][2][1] + prices[i]);
            dp[i][2][1] = Math.max(dp[i-1][2][1], dp[i-1][1][0] - prices[i]);
            dp[i][1][0] = Math.max(dp[i-1][1][0], dp[i-1][1][1] + prices[i]);
            dp[i][1][1] = Math.max(dp[i-1][1][1], -prices[i]);
        }

        return dp[length - 1][2][0];
    }













}
