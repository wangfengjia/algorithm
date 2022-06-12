package com.leetcode.www.middle.matrix;

/**
 * leetcode-1254:统计封闭岛屿的数目
 * 二维矩阵 grid由 0（土地）和 1（水）组成。岛是由最大的4个方向连通的 0组成的群，封闭岛是一个完全 由1包围（左、上、右、下）的岛。
 * 请返回 封闭岛屿 的数目。
 */
public class ClosedIsland {

    private int val;
    /**
     * DFS:深度优先搜索
     * 类似于leetcode-200题，区别是这个题目是要求封闭岛屿，也就是通过深度优先搜索到底矩阵边界时，当前岛屿就不是封闭岛屿，使用一个全局变量来处理\
     *
     * 复杂度分析
     *  时间复杂度:O(m*n),m和n分别表示矩阵的行数和列数
     *  空间复杂度:O(m*n),m和n分别表示矩阵的行数和列数,深度优先搜索过程中栈所使用的空间,栈的深度为O(m*n)
     * @param grid
     * @return
     */
    public int solution(int[][] grid){

        int rowNums = grid.length;;
        int colNums = grid[0].length;

        int ans = 0;
        for (int row = 0; row < rowNums; row++){
            for (int col = 0; col < colNums; col++){
                if (grid[row][col] == 0){
                    val = 1;
                    dfs(grid, rowNums, colNums, row, col);
                    ans += val;
                }
            }
        }


        return ans;
    }

    private void dfs(int[][] grid, int rowNums, int colNums, int row, int col){

        //到底矩阵边界，则表示当前岛屿不是封闭岛屿
        if (row < 0 || row >= rowNums || col < 0 || col >= colNums){
            val = 0;
            return;
        }
        if (grid[row][col] != 0){
            return;
        }
        grid[row][col] = 1;
        dfs(grid, rowNums, colNums, row-1, col);
        dfs(grid, rowNums, colNums, row+1, col);
        dfs(grid, rowNums, colNums, row, col-1);
        dfs(grid, rowNums, colNums, row, col+1);
    }

    public static void main(String[] args) {

        ClosedIsland closedIsland = new ClosedIsland();
        int[][] grid = {
                {1,1,1,1,1,1,1,0},
                {1,0,0,0,0,1,1,0},
                {1,0,1,0,1,1,1,0},
                {1,0,0,0,0,1,0,1},
                {1,1,1,1,1,1,1,0}
        };

        int ans = closedIsland.solution(grid);
        System.out.println(ans);
    }
}
