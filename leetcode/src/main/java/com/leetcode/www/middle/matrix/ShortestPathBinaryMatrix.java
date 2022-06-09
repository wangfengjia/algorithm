package com.leetcode.www.middle.matrix;


import java.util.LinkedList;
import java.util.Queue;

/**
 * leetcode-1091:二进制矩阵中的最短路径
 * 给你一个 n x n 的二进制矩阵 grid 中，返回矩阵中最短 畅通路径 的长度。如果不存在这样的路径，返回 -1 。
 * 二进制矩阵中的 畅通路径 是一条从 左上角 单元格（即，(0, 0)）到 右下角 单元格（即，(n - 1, n - 1)）的路径，该路径同时满足下述要求：
     * 路径途经的所有单元格都的值都是 0 。
     * 路径中所有相邻的单元格应当在 8 个方向之一 上连通（即，相邻两单元之间彼此不同且共享一条边或者一个角）。
 * 畅通路径的长度 是该路径途经的单元格总数。
 */
public class ShortestPathBinaryMatrix {


    /**
     * BFS(广度优先遍历)
     * 最短路径问题可以使用BFS来解决
     * @param grid
     * @return
     */
    public int solution(int[][] grid){

        if (grid[0][0] != 0){
            return -1;
        }

        int rowNums = grid.length;
        int colNums = grid[0].length;
        if (rowNums == 1){
            return 1;
        }

        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{-1,1},{1,-1},{1,1}};
        boolean[][] visited = new boolean[rowNums][colNums];
        visited[0][0] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        int ans = 1;

        while (!queue.isEmpty()){

            int size = queue.size();
            for (int i = 0; i < size; i++){
                int[] pos = queue.poll();
                int row = pos[0];
                int col = pos[1];
                if (row == rowNums - 1 && col == colNums - 1){
                    return ans;
                }
                for (int[] dir : dirs){
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];
                    if (newRow >= 0 && newRow < rowNums && newCol >= 0 && newCol < colNums && grid[newRow][newCol] == 0 && !visited[newRow][newCol]){
                        visited[newRow][newCol] = true;
                        queue.offer(new int[]{newRow, newCol});
                    }
                }
            }
            ++ans;
        }

        return -1;
    }

    public static void main(String[] args) {

        ShortestPathBinaryMatrix matrix = new ShortestPathBinaryMatrix();
        int[][] grid = {
                {0,1},
                {1,0},
        };

        int ans = matrix.solution(grid);
        System.out.println(ans);
    }
}
