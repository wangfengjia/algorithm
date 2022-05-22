package com.leetcode.www.middle.matrix;

/**
 * leetcode-1020:飞地的数量
 * 给你一个大小为 m x n 的二进制矩阵 grid ，其中 0 表示一个海洋单元格、1 表示一个陆地单元格。
 * 一次 移动 是指从一个陆地单元格走到另一个相邻（上、下、左、右）的陆地单元格或跨过 grid 的边界。
 * 返回网格中 无法 在任意次数的移动中离开网格边界的陆地单元格的数量。
 */
public class NumEnclaves {

    /**
     * 深度优先搜索:以网格边界上的每个陆地单元格为起点进行深度优先搜索，这个过程完成以后，所有和网格边界相连的陆地单元格都被访问到。然后遍历整个网格，如果一个
     *            陆地单元格没有被访问到，则这个陆地单元格不和网格的边界相连，则是飞地
     * 复杂度分析
        时间复杂度:O(m*n),m和n分别表示矩阵的行数和列数。在深度优先搜索过程中，每个点至多被标记过一次
        空间复杂度:O(m*n),m和n分别表示矩阵的行数和列数，主要是深度优先搜索的栈的开销
     * @param matrix
     * @return
     */
    public int solution(int[][] matrix){

        int rowNums = matrix.length;
        int colNums = matrix[0].length;
        boolean[][] visited = new boolean[rowNums][colNums];

        //第一列和最后一列
        for (int row = 0; row < rowNums; row++){
            dfs(matrix, row, rowNums, 0, colNums, visited);
            dfs(matrix, row, rowNums, colNums - 1, colNums, visited);
        }
        //第一行和最后一行
        for (int col = 0; col < colNums; col++){
            dfs(matrix, 0, rowNums, col, colNums, visited);
            dfs(matrix, rowNums-1, rowNums, col, colNums, visited);
        }

        int ans = 0;
        for (int row = 0; row < rowNums; row++){
            for (int col = 0; col < colNums; col++){
                if (matrix[row][col] == 1 && !visited[row][col]){
                    ++ans;
                }
            }
        }

        return ans;
    }


    private void dfs(int[][] grid, int row, int rowNums, int col, int colNum, boolean[][] visited){

        if (row < 0 || row >= rowNums || col < 0 || col >= colNum || grid[row][col] == 0 || visited[row][col]){
            return;
        }
        visited[row][col] = true;
        dfs(grid, row-1, rowNums, col, colNum, visited);
        dfs(grid, row+1, rowNums, col, colNum, visited);
        dfs(grid, row, rowNums, col-1, colNum, visited);
        dfs(grid, row, rowNums, col+1, colNum, visited);
    }

    public static void main(String[] args) {

        NumEnclaves numEnclaves = new NumEnclaves();
        int[][] matrix = {
                {0,0,0,0},
                {1,0,1,0},
                {0,1,1,0},
                {0,0,0,0}
        };

        int ans = numEnclaves.solution(matrix);
        System.out.println(ans);
    }
}
