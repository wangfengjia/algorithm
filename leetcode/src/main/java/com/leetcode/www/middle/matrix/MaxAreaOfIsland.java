package com.leetcode.www.middle.matrix;

/**
 * leetcode-695:岛屿的最大面积
 * 给你一个大小为 m x n 的二进制矩阵 grid 。
 * 岛屿是由一些相邻的1(代表土地) 构成的组合，这里的「相邻」要求两个1必须在水平或者竖直的四个方向上相邻。你可以假设grid 的四个边缘都被 0（代表水）包围着。
 * 岛屿的面积是岛上值为 1 的单元格的数目。
 * 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
 */
public class MaxAreaOfIsland {


    /**
     * 深度优先搜索:
     *      1. 以每块土地为起点进行深度优先遍历，在每块土地上，以4个方向探索与之相连的每一块土地，这样得到的土地总数就是包含这块土地的连通形状的面积
     *      2. 为了确保每块土地只被访问一次，被访问过的土地置为0，
     *      3. 求得最大连通形状面积
     * 复杂度分析
     *  时间复杂度:O(m*n),m,n分别表示给定的网格的行数和列数，每个网格最多被访问一次
     *  空间复杂度:O(m*n),m,n分别表示给定的网格的行数和列数，递归的最大深度可能是整个网格的大小，因此最大可能使用O(m*n)的栈空间
     * @param grid
     * @return
     */
    public int solution(int[][] grid){

        int rowNums = grid.length;
        int colNums = grid[0].length;
        int ans = 0;
        for (int row = 0; row < rowNums; row++){
            for (int col = 0; col < colNums; col++){
                if (grid[row][col] == 1){
                    int currentAns = dfs(grid, row, col, rowNums, colNums);
                    ans = Math.max(currentAns, ans);
                }

            }
        }

        return ans;
    }


    private int dfs(int[][] grid, int row, int col, int rowNums, int colNums){

        if (row < 0 || row >= rowNums || col < 0 || col >= colNums || grid[row][col] == 0){
            return 0;
        }

        grid[row][col] = 0;
        int ans = 1;
        //与之相邻的四块土地为起点进行深度深度优先遍历
        int[][] direcs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int i = 0; i < 4; i++){
            ans += dfs(grid, row + direcs[i][0], col + direcs[i][1], rowNums, colNums);
        }

        return ans;
    }


    public static void main(String[] args) {

        MaxAreaOfIsland maxAreaOfIsland = new MaxAreaOfIsland();
        int[][] grid = {
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}
        };

        int ans = maxAreaOfIsland.solution(grid);
        System.out.println(ans);
    }
}
