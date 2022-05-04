package com.leetcode.www.middle.matrix;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * leetcode-542:01矩阵
 * 给定一个由 0 和 1 组成的矩阵 mat，请输出一个大小相同的矩阵，其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。两个相邻元素间的距离为 1 。如果这个元素
 * 值就是0,那么离它最近的0就是它自己
 *
 */
public class UpdateMatrix {

    /**
     * 动态规划:
     *  1. 状态定义:dp[i][j]表示matrix[i][j]到最近的0的距离
     *  2. 状态转移方程:由于dp[i][j]的状态由上下左右四个状态来决定(dp[i][j-1],dp[i-1][j],dp[i][j+1],dp[i+1][j]),因此状态转移方程为
     *     dp[i][j] = 1 + min(dp[i][j-1],dp[i-1][j],dp[i][j+1],dp[i+1][j])
     *     在实际处理递推的过程中，由于距离(i,j)最近的0，是在其的"左上，右上，左下，右下"四个角，所以可以从这四个角开始递推。同时，由于从四个角开始递推有一些
     *     重复计算，可以仅从左下角和右上角开始递推来消除这些重复计算
     *  3. 边界条件:当matrix[i][j] = 0时,dp[i][j] = 0
     * 复杂度分析
     *  时间复杂度:O(m*n),m,n分别表示矩阵的行数和列数
     *  空间复杂度:O(1),除了答案数组外，我们只需要常数空间存放若干变量
     * @param matrix
     * @return
     */
    public int[][] solutionV1(int[][] matrix){

        int rowNums = matrix.length;
        int colNums = matrix[0].length;
        int[][] dp = new int[rowNums][colNums];

        for (int row = 0; row < rowNums; row++){
            for (int col = 0; col < colNums; col++){
                dp[row][col] = matrix[row][col] == 0 ? 0 : 10000;
            }
        }

        //从左上角开始递推
        for (int row = 0; row < rowNums; row++){
            for (int col = 0; col < colNums; col++){
                if (row - 1 >= 0){
                    dp[row][col] = Math.min(dp[row][col], dp[row-1][col] + 1);
                }
                if (col - 1 >= 0){
                    dp[row][col] = Math.min(dp[row][col], dp[row][col - 1] + 1);
                }
            }
        }
        //从右下角开始递推
        for (int row = rowNums - 1; row >= 0; row--){
            for (int col = colNums - 1; col >= 0; col--){
                if (row + 1 < rowNums){
                    dp[row][col] = Math.min(dp[row][col], dp[row + 1][col] + 1);
                }
                if (col + 1 < colNums){
                    dp[row][col] = Math.min(dp[row][col], dp[row][col + 1] + 1);
                }
            }
        }


        return dp;
    }

    /**
     * 广度优先搜索:先将所有的0放入队列，然后从每个0开始一圈一圈的向1扩散(每个1都被离它最近的0扩散到)，并且使用一个数组来记录某个位置的1是否被访问过
     *            和使用一个数组来记录距离
     * 复杂度分析
     *  时间复杂度:O(mn),m和n分别表示矩阵的行数和列数
     *  空间复杂度:O(mn), m和n分别表示矩阵的行数和列数
     * @param matrix
     * @return
     */
    public int[][] solutionV2(int[][] matrix){

        int rowNums = matrix.length;
        int colNums = matrix[0].length;
        boolean[][] visited = new boolean[rowNums][colNums];
        int[][] dist = new int[rowNums][colNums];
        Queue<int[]> queue = new LinkedList<>();

        //将所有的0添加到初始队列
        for (int row = 0; row < rowNums; row++){
            for (int col = 0; col < colNums; col++){
                if (matrix[row][col] == 0){
                    queue.offer(new int[]{row, col});
                    visited[row][col] = true;
                }
            }
        }

        int[][] dirs = {{0, -1}, {0,1}, {-1, 0}, {1,0}};
        while (!queue.isEmpty()){
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            for (int i = 0; i < 4; i++){
                int newRow = row + dirs[i][0];
                int newCol = col + dirs[i][1];
                if (newRow >= 0 && newRow < rowNums && newCol >= 0 && newCol < colNums && !visited[newRow][newCol]){
                    dist[newRow][newCol] = dist[row][col] + 1;
                    visited[newRow][newCol] = true;
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {


        UpdateMatrix updateMatrix = new UpdateMatrix();
        int[][] matrix = {
                {0,0,0},
                {0,1,0},
                {1,1,1}
        };
        int[][] matrix1 = {
                {0,0,0},
                {0,1,0},
                {0,0,0}
        };
        int[][] ans = updateMatrix.solutionV1(matrix1);
        for (int[] items : ans){
            System.out.println(Arrays.toString(items));
        }

        System.out.println("=====================================================");
        int[][] ans2 = updateMatrix.solutionV2(matrix1);
        for (int[] items : ans2){
            System.out.println(Arrays.toString(items));
        }
    }
}
