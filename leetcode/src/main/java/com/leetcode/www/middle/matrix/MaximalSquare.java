package com.leetcode.www.middle.matrix;

/**
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 * 方法一(动态规划): 在一个矩阵里面，要使以顶点(x,y)为右下角的四边形是正方形，则需要存在分别以(x-1,y),(x,y-1),(x-1,y-1)为右下角的正方形，用f(x,y)表示以(x,y)
 *                 为右下角的正方形的最大边长
 *                 1. 状态定义:f(x,y)表示以(x,y)为右下角的正方形最大边长
 *                 2. 状态转移方程:在一个矩阵里面，要使以顶点(x,y)为右下角的四边形是正方形，则需要存在分别以(x-1,y),(x,y-1),(x-1,y-1)为右下角的正方形,所以可以
 *                    得到状态转移方程为
 *                      1. 当matrix[x][y] = 0时，dp[x][y] = 0;
 *                      2. 当matrix[x][y] = 1时
 *                          dp[x][y] = 1;当x或者y等于0的时候，就是第一行或者第一列的时候
 *                          dp[x][y] = min(dp[i-1][j],[i][j-1],[i][j]) + 1
 *                 3. 边界条件:当x或者y等于0，且matrix[x][y] = 1时，dp[x][y] = 1
 */
public class MaximalSquare {


    public int solution(int[][] matrix){

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }

        int max = 0;
        int rowNums = matrix.length;
        int colNums = matrix[0].length;
        int[][] dp = new int[rowNums][colNums];
        for (int row = 0; row < rowNums; row++){
            for (int col = 0; col < colNums; col++){
                if (matrix[row][col] == 1){
                    if (row == 0 || col == 0){
                        dp[row][col] = 1;
                    }else {
                        dp[row][col] = Math.min(Math.min(dp[row-1][col], dp[row][col-1]),dp[row-1][col-1]) + 1;
                    }
                    max = Math.max(max, dp[row][col]);
                }
            }
        }

        return max * max;
    }

    public static void main(String[] args) {

        int[][] matrix = new int[][]{{1,0,1,0,0},{1,0,1,1,1},{1,1,1,1,1},{1,0,0,1,0}};
        MaximalSquare maximalSquare = new MaximalSquare();
        int maxSquare = maximalSquare.solution(matrix);
        System.out.println(maxSquare);
    }
}
