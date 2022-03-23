package com.leetcode.www.middle.dynamic;

/**
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。说明：每次只能向下或者向右移动一步。
 * 方法一(动态规划):由于只能向下或者向右移动，所以第一列和第一行的路径是唯一的，因此第一行和第一列的元素的最小路径和就是对应路径上面元素之和。对于不是第一行和第一列的元素，
 *                它的最小路径和dp[i][j] = min(dp[i - 1][j], dp[i][j - 1]) + nums[i][j]
 *                时间复杂度:O(mn)，m和n分别是网格的行数和列数，需要对整个网格遍历一遍
 *                空间复杂度:O(mn),m和n分别是网格的行数和列数。创建一个二维数组dp，和网格大小相同
 */
public class MinPathSum {

    public int solution(int[][] nums){

        if (nums == null || nums.length == 0 || nums[0].length == 0){
            return 0;
        }

        int row = nums.length;
        int col = nums[0].length;
        int[][] dp = new int[row][col];

        dp[0][0] = nums[0][0];
        for (int i = 1; i < row; i++){
            dp[i][0] = dp[i - 1][0] + nums[i][0];
        }

        for (int j = 1; j < col; j++){
            dp[0][j] = dp[0][j - 1] + nums[0][j];
        }

        for (int i = 1; i < row; i++){
            for (int j = 1; j < col; j++){
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + nums[i][j];
            }
        }

        return dp[row - 1][col - 1];
    }
}
