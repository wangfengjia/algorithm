package com.leetcode.www.middle.dynamic;

/**
 * 给你一个由'1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 * 方法一(深度优先搜索):可以将二维网格看成一个无向图，竖直或者水平相邻的1有边相连，通过扫描整个二维网络。如果一个位置是1，则以其为起点进行深度优先搜索，
 *                   在搜索过程中，每个搜索到的1都会被重新标记为0。最终岛屿的数量就是进行深度优先搜索的次数
 *                   复杂度
 *                      时间复杂度:O(MN),其中M和N分别是行数和列数
 *                      空间复杂度:O(MN),在最坏情况下，整个网格均为陆地，深度优先搜索的深度达到MN
 */
public class NumIslands {

    public int solution(char[][] grid){

        if (grid == null || grid.length == 0){
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int numsIslands = 0;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if (grid[i][j] == '1'){
                    ++numsIslands;
                    dfs(grid, i, j);
                }
            }
        }

        return numsIslands;
    }

    public void dfs(char[][] grid, int row, int col){

        int rowNumbers = grid.length;
        int colNumbers = grid[0].length;

        //深度优先搜索终结条件
        if (row < 0 || col < 0 || row >= rowNumbers || col >= colNumbers || grid[row][col] == '0'){
            return;
        }

        //将遍历到的'1'置为'0'，这样在后续的遍历过程中不会再作为深度优先搜索的起点
        grid[row][col] = '0';
        dfs(grid, row - 1, col);
        dfs(grid, row + 1, col);
        dfs(grid, row, col - 1);
        dfs(grid, row, col + 1);
    }


    public static void main(String[] args) {


        char[][] grid = new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'},
        };
        NumIslands numIslands = new NumIslands();
        int islandNums = numIslands.solution(grid);
        System.out.println(islandNums);
    }
}
