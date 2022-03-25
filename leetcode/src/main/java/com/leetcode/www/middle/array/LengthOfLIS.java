package com.leetcode.www.middle.array;

/**
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，
 * [3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 */
public class LengthOfLIS {


    /**
     * 动态规划版本
     *      1. 状态定义:dp[i]表示以第i个元素结尾的最长上升子序列的长度。nums[i]是必须做选择的
     *      2. 状态转移方程:dp[i] = max(dp[j]) + 1，且nums[i] > nums[j]。dp[j]表示dp[0....i-1]的最长递增子序列的长度
     *      3. 边界条件: dp[0] = 1;
     * @param nums
     * @return
     */
    public int solution(int[] nums){

        if (nums.length == 0){
            return 0;
        }

        int length = nums.length;
        int[] dp = new int[length];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < length; i++){
            //此元素自己作为最长递增子序列的长度
            dp[i] = 1;
            for (int j = 0; j < i; j++){
                if (nums[i] > nums[j]){
                    // dp[j] + 1表示以第j个位置结尾的最长递增子序列的长度加上nums[i]
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(dp[i], max);
        }

        return max;
    }

    public static void main(String[] args) {

        int[] nums = new int[]{10,9,2,5,3,7,101,18};
        LengthOfLIS lengthOfLIS = new LengthOfLIS();
        int len = lengthOfLIS.solution(nums);
        System.out.println(len);
    }
}
