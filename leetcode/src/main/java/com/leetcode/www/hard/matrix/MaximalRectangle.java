package com.leetcode.www.hard.matrix;

import java.util.Deque;
import java.util.LinkedList;

/**
 * leetcode-85:最大矩形
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 */
public class MaximalRectangle {


    /**
     *
     * 将二维二进制的矩阵拆分成一系列的柱状图，为了计算矩形的最大面积，转换成计算每个柱状图中的最大面积，并找到全局最大值,可以使用leetcode-84单调栈的做法
     * 将矩阵转换为一系列的柱状图的方法:枚举每一个元素，统计其左边有多少个连续的1，并用left数组记录下来，这样每一列转换为柱状图
     * 复杂度分析
     *  1. 时间复杂度:O(mn),其中m,n分别是矩阵的行数和列数，这是遍历矩阵的时间复杂度
     *  2. 空间复杂度:O(mn),m,n分别为矩阵的行数和列数。left数组所占用的空间，left数组与矩阵等大
     * @param matrix
     * @return
     */
    public int solution(char[][] matrix){

        int m = matrix.length;
        if (m == 0){
            return 0;
        }

        int n = matrix[0].length;
        int[][] left = new int[m][n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (matrix[i][j] == '1'){
                    //统计每个元素左边有多少个连续的1
                    left[i][j] = j == 0 ? 0 : left[i][j-1] + 1;
                }
            }
        }

        int ret = 0;
        //对于每一列，使用基于柱状图的方法
        for (int j = 0; j < n; j++){
            int[] up = new int[m];
            int[] down = new int[m];

            Deque<Integer> stack = new LinkedList<>();
            for (int i = 0; i < m; i++){
                if (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]){
                    stack.pop();
                }
                up[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(i);
            }

            stack.clear();
            for (int i = m - 1; i >= 0; i--){
                if (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]){
                    stack.pop();
                }
                down[i] = stack.isEmpty() ? m : stack.peek();
                stack.push(i);
            }

            for (int i = 0; i < m; i++){
                int height = left[i][j];
                int width = down[i] - up[i] - 1;
                ret = Math.max(ret, (height * width));
            }
        }

        return ret;
    }
}
