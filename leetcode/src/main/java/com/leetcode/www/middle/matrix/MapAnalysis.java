package com.leetcode.www.middle.matrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * leetcode-1162:地图分析
 * 你现在手里有一份大小为n x n的 网格 grid，上面的每个 单元格 都用0和1标记好了。其中0代表海洋，1代表陆地。
 * 请你找出一个海洋单元格，这个海洋单元格到离它最近的陆地单元格的距离是最大的，并返回该距离。如果网格上只有陆地或者海洋，请返回-1。
 * 我们这里说的距离是「曼哈顿距离」（Manhattan Distance）：(x0, y0) 和(x1, y1)这两个单元格之间的距离是|x0 - x1| + |y0 - y1|。
 */
public class MapAnalysis {


    /**
     * 广度优先遍历
     * 题目需要求出距离陆地区域最远的海洋区域，其实就是从陆地1开始，要扩散多少次，才能把所有的海洋区域覆盖。
     * 因此可以把所有陆地1添加到队列中,进行广度优先遍历，看看多少步之后没有海洋可以扩散，这个步数就是答案
     *
     * 复杂度分析
     *  时间复杂度:O(n^2),n是方格的边长，二维表格里面所有的元素都会被访问一遍
     *  空间复杂度:O(n^2),最坏情况下，二维表格里全都是陆地，全部元素会进入队列
     * @param grid
     * @return
     */
    public int solution(int[][] grid){


        int[][] dirs = {{-1,0}, {1,0},{0,-1},{0,1}};
        int n = grid.length;
        Queue<int[]> queue = new LinkedList<>();
        //把所有陆地添加到队列里
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 1){
                    queue.add(new int[]{i,j});
                }
            }
        }

        int size = queue.size();
        if (size == 0 || size == n * n){
            return -1;
        }

        boolean[][] visited = new boolean[n][n];
        int step = 0;
        while (!queue.isEmpty()){
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++){
                int[] cell = queue.poll();
                for (int j = 0; j < 4; j++){
                    int newX = cell[0] + dirs[j][0];
                    int newY = cell[1] + dirs[j][1];
                    if (newX >= 0 && newX < n && newY >= 0 && newY < n && !visited[newX][newY] && grid[newX][newY] == 0){
                        visited[newX][newY] = true;
                        queue.add(new int[]{newX, newY});
                    }
                }
            }
            ++step;
        }

        //由于最后一步没有可扩散的区域了，但是step还是加1了，所以退出循环时候应该减去1
        return step - 1;
    }


    public static void main(String[] args) {

        MapAnalysis analysis = new MapAnalysis();
        int[][] grid = {
                {1,0,0},
                {0,0,0},
                {0,0,0}
        };
        int ans = analysis.solution(grid);
        System.out.println(ans);
    }
}
