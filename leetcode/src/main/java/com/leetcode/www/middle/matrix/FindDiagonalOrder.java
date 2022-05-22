package com.leetcode.www.middle.matrix;

import java.util.Arrays;

/**
 * leetcode-498:对角线遍历
 * 给你一个大小为 m x n 的矩阵 mat ，请以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素。
 */
public class FindDiagonalOrder {


    /**
     * 模拟:
     *      1. 使用一个变量direction表示当前对角线的方向，初始值为1，表示方向向上
     *      2. 假设当前对角线首部为matrix[i][j],根据方向遍历这个对角线
     *          1. 如果是向上的对角线，下一个元素就是matrix[i-1][j+1]
     *          2. 如果是向下的对角线，下一个元素就是matrix[i+1][j-1]
     *      3. 遍历当前对角线的元素，直到矩阵的边界。然后寻找下一条对角线的首部
     *          1. 下一条向上对角线的首部:如果当前对角线的尾部元素不在矩阵的最后一行，则下一条向上对角线的头部就是当前尾部的正下方元素。否则下一条向上对角线
     *             的首部是当前尾部的右侧元素
     *          2. 下一条向下对角线的首部:如果当前对角线的尾部元素不在矩阵的最后一列，则下一条向下对角线的头部就是当前尾部的右侧元素。否则下一条向下对角线
     *             的首部就是当前尾部的下方元素
     * 复杂度分析
     *  时间复杂度:O(rowNums * colNums),每个元素只被访问一次
     *  空间复杂度:O(1),返回的数组不算空间复杂度
     *
     * @param matrix
     * @return
     */
    public int[] solution(int[][] matrix){


        int rowNums = matrix.length;
        int colNums = matrix[0].length;

        int row = 0;
        int col = 0;
        int[] ans = new int[rowNums * colNums];
        int ansIndex = 0;

        int direction = 1;

        while (row < rowNums && col < colNums){

            ans[ansIndex++] = matrix[row][col];
            int newRow = row + (direction == 1 ? -1 : 1);
            int newCol = col + (direction == 1 ? 1 : -1);
            //切换到下一条对角线的首部
            if (newRow < 0 || newRow == rowNums || newCol < 0 || newCol == colNums){
                if (direction == 1){
                    if (col == colNums - 1){
                        ++row;
                    }else {
                        ++col;
                    }
                }else {
                    if (row == rowNums - 1){
                        ++col;
                    }else {
                        ++row;
                    }
                }

                //flip the direction
                direction = 1 - direction;
            }else {
                row = newRow;
                col = newCol;
            }
        }


        return ans;
    }

    public static void main(String[] args) {

        FindDiagonalOrder diagonalOrder = new FindDiagonalOrder();
        int[][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };

        int[] ans = diagonalOrder.solution(matrix);
        System.out.println(Arrays.toString(ans));
    }
}
