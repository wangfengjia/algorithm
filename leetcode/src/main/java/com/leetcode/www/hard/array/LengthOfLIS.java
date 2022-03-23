package com.leetcode.www.hard.array;


import java.util.Arrays;

/**
 * leetcode300，给定一个无序数组，求最长上升子序列的长度
 * 1.状态定义dp[i] = length 下标为i的元素的最长上升子序列的长度
 * 2.状态转移方程dp[i] = Interger.max(dp[j] + 1, dp[i])。0 ~ i中小于data[j]的元素的最长上升子序列的长度加1的最大值
 */
public class LengthOfLIS {


    public int lengthOfLIS(int[] items){

        int length = items.length;
        if (length == 0){
            return 0;
        }

        int[] dp = new int[length];
        //初始化时每个元素单独一个元素时最长上升子序列
        Arrays.fill(dp, 1);

        for (int i = 1; i < length; i++){
            int currNum = items[i];
            for (int j = 0; j < i; j++){
                if (currNum > items[j]){
                    dp[i] = Integer.max(dp[j] + 1, dp[i]);
                }
            }
        }


        int maxLIS = dp[0];
        for (int i = 1; i < length; i++){
            if (dp[i] > maxLIS){
                maxLIS = dp[i];
            }
        }

        return maxLIS;
    }



    public static void main(String[] args){

        int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};
        LengthOfLIS lengthOfLIS = new LengthOfLIS();
        int length = lengthOfLIS.lengthOfLIS(arr);
        System.out.println(length);
    }
}
