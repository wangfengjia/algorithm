package com.leetcode.www.middle.matrix;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode-1034:边界着色
 * 给你一个大小为 m x n 的整数矩阵 grid ，表示一个网格。另给你三个整数row、col 和 color 。网格中的每个值表示该位置处的网格块的颜色。
 * 两个网格块属于同一 连通分量 需满足下述全部条件：
     * 两个网格块颜色相同
     * 在上、下、左、右任意一个方向上相邻
 * 连通分量的边界 是指连通分量中满足下述条件之一的所有网格块：
     * 在上、下、左、右任意一个方向上与不属于同一连通分量的网格块相邻
     * 在网格的边界上（第一行/列或最后一行/列）
 * 请你使用指定颜色color 为所有包含网格块grid[row][col] 的 连通分量的边界 进行着色，并返回最终的网格grid 。
 *
 */
public class ColorBorder {


    /**
     * 深度优先搜索
     * 利用深度优先搜索找到位置(row,col)所在的连通分量，同时在搜索的时候判断当前的网格是否属于边界。如果属于边界，就把这个边界加到一个用来存储所有边界的数组中，
     * 搜索完毕后，对所有边界节点进行着色
     *
     * 复杂度分析
     *  时间复杂度:O(m*n),m和n分别表示矩阵的行数和列数，最坏情况下需要访问矩阵中的每一个点
     *  空间复杂度:O(m*n),使用一个和grid一样大小的矩阵来存储每个点是否被访问过
     * @param grid
     * @param row
     * @param col
     * @param color
     * @return
     */
    public int[][] solution(int[][] grid, int row, int col, int color){

        int rowNums = grid.length;
        int colNums = grid[0].length;
        int originColor = grid[row][col];
        boolean[][] visited = new boolean[rowNums][colNums];
        List<int[]> boarders = new ArrayList<>();
        dfs(grid, row, col, visited, boarders, originColor);

        for (int i = 0; i < boarders.size(); i++){
            int[] boarder = boarders.get(i);
            int x = boarder[0];
            int y = boarder[1];
            grid[x][y] = color;
        }

        return grid;
    }


    private void dfs(int[][] grid, int x, int y, boolean[][] visited, List<int[]> borders, int originColor){

        int rowNums = grid.length;
        int colNums = grid[0].length;
        boolean isBoarder = false;
        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
        for (int i = 0; i < 4; i ++){
            int[] dir = dirs[i];
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (!(newX >= 0 && newX < rowNums && newY >= 0 && newY < colNums && grid[newX][newY] == originColor)){
                isBoarder = true;
            }else if (!visited[newX][newY]){ // 走到这一个分支就表示newX和newY是和x,y同一个连通分量
                visited[newX][newY] = true;
                dfs(grid, newX, newY, visited, borders, originColor);
            }
        }

        if (isBoarder){
            borders.add(new int[]{x, y});
        }
    }


    public static void main(String[] args) {

        ColorBorder border = new ColorBorder();
        int[][] grid = {
                {1,2,2},
                {2,3,2}
        };
        int row = 0;
        int col = 1;
        int color = 3;

        int[][] ans = border.solution(grid, row, col, color);
        for (int[] e : ans){
            System.out.println(Arrays.toString(e));
        }
    }
}
