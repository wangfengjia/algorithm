package com.leetcode.www.hard.dynamic;

import java.util.Arrays;

/**
 * 给定一个整数数组prices，它的第i个元素prices[i]是一支给定的股票在第i天的价格。设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 方法一(动态规划):
 *      1. 状态表示:
 *          buy[i][j]:表示第i天结束时，恰好进行了j笔交易，并且当前手上持有一支股票，在这种情况下的最大利润
 *          sell[i][j]:表示第i天结束时，恰好进行了j笔交易，并且当前手上不持有股票，在这种情况下的最大利润
*       2. 状态转移方程
 *          buy[i][j] = max(buy[i-1][j], sell[i-1][j] - prices[i]):第i天结束时持有一支股票有两种情况:第i-1天不持有然后第i天买入;河第i-1天持有然后第i天不交易
 *          sell[i][j] = max(sell[i-1][j], buy[i-1][j-1] + prices[i]):第i天结束时出现手上不持有股票有两种情况:第i-1天不持有股票并且第i天不买入股票;
 *          和第i-1天持有股票，然后第i天卖出，买卖本来是一对的，这样就可以把交易次数算到买卖操作之一，然后交易次数减一
*       3. 边界条件
 *          1. buy[0][1..k],由于只有prices[0]唯一的股价，所以不能进行任何的交易，就可以将buy[0][1..k]设置为非常小的值，表示不合法的状态。而对于buy[0][0],
 *             它的值为-prices[0],即我们在第0天以prices[0]的价格买入股票是唯一满足手上持有股票的方法
*           2. sell[0][1..k]设置为一个非常小的值，表示不合法的状态，而sell[0][0]的值为0，即我们在第一天不做任何事是唯一满足手上不持有股票的方法
 *          3. 因为n天最多只能进行n/2笔交易(因为一天只能要么卖出，要么买入，或者休息，只有一对买入卖出才算一笔交易，所以一笔交易至少2天)，当k > n/2的值就没有任何
 *             意义，所以我们去min(k,n/2)进行动态规划
*        4. 复杂度
 *          时间复杂度:O(nmin(n,k)),其中n为数组的prices的大小，即我们使用二重循环进行动态规划需要的时间复杂度
 *          空间复杂度:O(nmin(n,k)),进行动态规划的二维数组
 */
public class MaxProfit {

    public int solution(int k, int[] prices){

        if (prices.length == 0){
            return 0;
        }

        int length = prices.length;
        k = Math.min(k, length/2);
        int[][] buy = new int[length][k + 1];
        int[][] sell = new int[length][k+1];

        buy[0][0] = -prices[0];
        sell[0][0] = 0;
        for (int j = 1; j <= k; j++){
            buy[0][k] = sell[0][k] = Integer.MIN_VALUE;
        }

        for (int i = 1; i < length; i++){
            buy[i][0] = Math.max(buy[i-1][0], sell[i-1][0] - prices[i]);
            for (int j = 1; j <= k; j++){
                buy[i][j] = Math.max(buy[i-1][j], sell[i-1][j] - prices[i]);
                //做了卖出操作后算一次交易
                sell[i][j] = Math.max(sell[i-1][j], buy[i-1][j-1] + prices[i]);
            }
        }

//        return sell[length-1][k];
        return Arrays.stream(sell[length - 1]).max().getAsInt();
    }


    public static void main(String[] args) {


        int[] prices = new int[]{3,2,6,5,0,3};
        int k = 2;
        MaxProfit maxProfit = new MaxProfit();
        int profit = maxProfit.solution(k, prices);
        System.out.println(profit);
    }
}
