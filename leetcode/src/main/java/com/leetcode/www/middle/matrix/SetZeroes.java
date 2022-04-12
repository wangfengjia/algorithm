package com.leetcode.www.middle.matrix;

import java.util.Arrays;

/**
 * leetcode73-矩阵置零
 * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 */
public class SetZeroes {


    /**
     * 标记数组:使用标记数组来记录每一行和每一列是否存在0，先遍历一遍矩阵，维护两个标记数组，然后再遍历一遍矩阵，对于元素matrix[i][j]，如果其所在行或者列存在0，
     *         则将这个元素置为0
     * 复杂度分析
     *  1. 时间复杂度: O(mn),m,n分别是矩阵的行数和列数
     *  2. 空间复杂度: O(m+n),m,n分别是矩阵的行数和列数，用来记录每一行或者每一列是否存在0
     * @param matrix
     */
    public void solution(int[][] matrix){

        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[] rowArr = new boolean[rows];
        boolean[] colArr = new boolean[cols];

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if (matrix[i][j] == 0){
                    rowArr[i] = colArr[j] = true;
                }
            }
        }

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if (rowArr[i] || colArr[j]){
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * 可以使用第一行和第一列来代替上一种解法的标记数组，这样可以实现O(1)的空间复杂度。这样会导致第一行和第一列被修改，无法记录它们是否原来存在0.因此使用两个
     * 标记变量和记录第一行和第一列是否存在0
     * 复杂度分析
     *  时间复杂度:O(mn)。m,n分别是矩阵的行数和列数
     *  空间复杂度:O(1)
     * @param matrix
     */
    public void solutionV2(int[][] matrix){

        int rowNums = matrix.length;
        int colNums = matrix[0].length;
        boolean firstRowFlag = false;
        boolean firstColFlag = false;

        for (int col = 0; col < colNums; col++){
            if (matrix[0][col] == 0){
                firstColFlag = true;
            }
        }
        for (int row = 0; row < rowNums; row++){
            if (matrix[row][0] == 0){
                firstColFlag = true;
            }
        }

        for (int row = 1; row < rowNums; row++){
            for (int col = 1; col < colNums; col++){
                if (matrix[row][col] == 0){
                    matrix[row][0] = 0;
                    matrix[0][col] = 0;
                }
            }
        }

        for (int row = 1; row < rowNums; row++){
            for (int col = 1; col < colNums; col++){
                if (matrix[row][0] == 0 || matrix[0][col] == 0){
                    matrix[row][col] = 0;
                }
            }
        }

        if (firstRowFlag){
            for (int col = 0; col < colNums; col++){
                matrix[0][col] = 0;
            }
        }
        if (firstColFlag){
            for (int row = 0; row < rowNums; row++){
                matrix[row][0] = 0;
            }
        }
    }


    public static void main(String[] args) {

        SetZeroes setZeroes = new SetZeroes();
        int[][] matrix = {
                {0,1,2,0},
                {3,4,5,2},
                {1,3,1,5}
        };

        setZeroes.solutionV2(matrix);
        for (int[] nums : matrix){
            System.out.println(Arrays.toString(nums));
        }
    }
}
