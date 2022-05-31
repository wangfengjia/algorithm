package com.leetcode.www.middle.dynamic;

import java.util.Arrays;

/**
 * leetcode-931:下降路径最小和
 * 给你一个 n x n 的 方形 整数数组matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。
 * 下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列
 * （即位于正下方或者沿对角线向左或者向右的第一个元素）。具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1) 。
 */
public class MinFallingPathSum {


    /**
     * 动态规划
     *  状态定义:dp[i][j]表示从位置为(i,j)的元素开始的下降路径最小和
     *  状态转移方程:根据题目要求，位置(i,j)可以下降到(i+1,j-1),(i+1,j),(i+1,j+1)三个位置，所以状态转移方程为
     *      dp[i][j] = matrix[i][j] + min(dp[i+1][j-1], dp[i+1][j],dp[i+1][j+1])
     * 可以在原数组上面计算dp[i][j],由于matrix最后一行的值就是dp值，因此我们从倒数第二行开始，从下往上动态规划
     *
     * 复杂度分析
     *  时间复杂度:O(n^2),n是matrix的边长
     *  空间复杂度:O(1),没有使用额外的dp数组
     * @param matrix
     * @return
     */
    public int solution(int[][] matrix){

        int n = matrix.length;
        for (int row = n - 2; row >= 0; row--){
            for (int col = 0; col < n; col++){
                int best = matrix[row + 1][col];
                if (col > 0){
                    best = Math.min(best, matrix[row + 1][col - 1]);
                }
                if (col + 1 < n){
                    best = Math.min(best, matrix[row + 1][col + 1]);
                }
                matrix[row][col] += best;
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int col : matrix[0]){
            if (col < ans){
                ans = col;
            }
        }
        return ans;
    }



    public static void main(String[] args) {

        MinFallingPathSum pathSum = new MinFallingPathSum();
        int[][] matrix = {
                {2,1,3},
                {6,5,4},
                {7,8,9}
        };
        int ans = pathSum.solution(matrix);
        System.out.println(ans);
    }
}
