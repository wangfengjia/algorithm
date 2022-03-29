package com.leetcode.www.middle.array;

/**
 * leetcode416:分隔等和子集
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 */
public class PartitionEqualSubset {

    /**
     * 可以将这个问题转化为:给定一个包含正整数的非空数组，判断能否从数组中选出一些元素,使得这些元素之和等于整个数组所有元素之和的一半，这就是一个0-1背包问题
     * 动态规划
     *      1. 状态定义:dp[i][j]:表示从数组的[0..i]的下标范围内选取若干个正整数，是否存在一种选取方案使得被选取的正整数之和等于j
     *      2. 状态转移方程
     *          1. 如果不选取nums[i],则dp[i][j] = dp[i-1][j]
     *          2. 如果选取nums[i],则dp[i][j] = dp[i-1][j - nums[i]]
     *      3. 边界条件
     *          1. 如果不选取任何正整数，则对于0 <= i < n，则dp[i][0] = true
     *          2. 当i等于0时，只有一个正整数可以选取，则dp[0][nums[i]] = true
     * @param nums
     * @return
     */
    public boolean solution(int[] nums){

        int n = nums.length;
        if (n < 2){
            return false;
        }

        int sum = 0;
        int maxNum = 0;
        for (int num : nums){
            sum += num;
            maxNum = Math.max(maxNum, num);
        }

        if (sum % 2 != 0){
            return false;
        }

        int target = sum / 2;
        if (maxNum > target){
            return false;
        }

        boolean[][] dp = new boolean[n][target + 1];
        dp[0][0] = true;
        dp[0][nums[0]] = true;

        for (int i = 1; i < n; i++){
            // 第i个元素不选中
            for (int j = 0; j <= target; j++){
                if (dp[i-1][j]){
                    dp[i][j] = dp[i-1][j];
                }
            }
            // 第i个元素选中
            for (int j = 0; j <= (target - nums[i]); j++){
                if (dp[i-1][j]){
                    dp[i][j + nums[i]] = dp[i-1][j];
                }
            }
        }

        return dp[n-1][target];
    }


    public static void main(String[] args) {

        int[] nums = new int[]{1,2,2};
        PartitionEqualSubset sub = new PartitionEqualSubset();
        boolean ans = sub.solution(nums);
        System.out.println(ans);
    }
}
