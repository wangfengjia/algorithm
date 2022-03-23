package com.leetcode.www.middle.matrix;

/**
 * 给定一个 n×n 的二维矩阵matrix 表示一个图像。请你将图像顺时针旋转 90 度。你必须在 原地 旋转图像，
 * 这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 * 思路1:顺时针旋转90度，元素原来的位置和新的位置的规律是:对于矩阵中第i行第j列的元素，旋转后，它的新位置是倒数第i列的第j个位置，公式是 matrix[row][col] => matrix[col][n-row-1]
 * 思路2:思路1没有实现原地旋转，可以变换一个思路，用翻转代替旋转，就是先水平翻转然后主对角线翻转，这两个操作以后得到matrix_new[col][n-row-1] = matrix[row][col],
 *      和思路一得到的公司是一样的
 *      时间复杂度:O(N^2),其中N是matrix的边长，对于每一次翻转操作，我们都需要枚举矩阵中一半的元素
 *      空间复杂度:O(1),原地翻转，所以不需要新的空间
 */
public class PictureRotate {

    public void solutionV1(int[][] matrix){

        int n = matrix.length;
        int[][] matrix_new = new int[n][n];

        for (int row = 0; row < n; row++){
            for (int col = 0; col < n; col++){
                matrix_new[col][n - row - 1] = matrix[row][col];
            }
        }

        for (int row = 0; row < n; row++){
            for (int col = 0; col < n; col++){
                matrix[row][col] = matrix_new[row][col];
            }
        }
    }

    public void solutionV2(int[][] matrix){

        int n = matrix.length;

        //水平翻转 matrix[row][col] = matrix[n - row - 1][col]
        for (int row = 0; row < n / 2; row++){
            for (int col = 0; col < n; col++){
                int tmp = matrix[row][col];
                matrix[row][col] = matrix[n - row - 1][col];
                matrix[n - row - 1][col] = tmp;
            }
        }

        //对角线翻转 matrix[row][col] = matrix[col][row]
        for (int row = 0; row < n; row++){
            for (int col = 0; col < row; col++){
                int tmp = matrix[row][col];
                matrix[row][col] = matrix[col][row];
                matrix[col][row] = tmp;
            }
        }
    }
}
