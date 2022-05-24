package com.leetcode.www.middle.matrix;


import java.util.Arrays;

/**
 * leetcode-1706:球会落何处
 * 用一个大小为 m x n 的二维网格 grid 表示一个箱子。你有 n 颗球。箱子的顶部和底部都是开着的。
 * 箱子中的每个单元格都有一个对角线挡板，跨过单元格的两个角，可以将球导向左侧或者右侧。
     * 将球导向右侧的挡板跨过左上角和右下角，在网格中用 1 表示。
     * 将球导向左侧的挡板跨过右上角和左下角，在网格中用 -1 表示。
 * 在箱子每一列的顶端各放一颗球。每颗球都可能卡在箱子里或从底部掉出来。如果球恰好卡在两块挡板之间的 "V" 形图案，或者被一块挡导向到箱子的任意一侧边上，就会卡住。
 * 返回一个大小为 n 的数组 answer ，其中 answer[i] 是球放在顶部的第 i 列后从底部掉出来的那一列对应的下标，如果球卡在盒子里，则返回 -1 。
 */
public class FindBall {


    /**
     * 模拟
     * 对于每个球，从上往下判断球位置的移动方向。在对应的位置，如果挡板向右偏，则向右移动，如果挡板向左偏，则向左移动。若移动过程中碰到挡板或者V型，则球会停止
     * 移动，卡在箱子里
     * 复杂度分析
     *  时间复杂度:O(m*n),m和n分别是矩阵的行数和列数
     *  空间复杂度:O(1)
     * @param grid
     * @return
     */
    public int[] solution(int[][] grid){

        int colNums = grid[0].length;
        int[] ans = new int[colNums];
        for (int i = 0; i < colNums; i++){
            //球的初始列
            int col = i;
            for (int[] row : grid){
                int dir = row[col];
                col += dir;
                if (col < 0 || col == colNums || row[col] !=  dir){
                    col = -1;
                    break;
                }
            }
            ans[i] = col;
        }

        return ans;
    }

    public static void main(String[] args) {

        FindBall findBall = new FindBall();
        int[][] grid = {
                {1,1,1,-1,-1},
                {1,1,1,-1,-1},
                {-1,-1,-1,1,1},
                {1,1,1,1,-1},
                {-1,-1,-1,-1,-1}
        };

        int[] ans = findBall.solution(grid);
        System.out.println(Arrays.toString(ans));
    }
}
