package com.leetcode.www.middle.matrix;

import java.util.Arrays;

/**
 * leetcode-59:螺旋矩阵 II
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 */
public class GenerateMatrix {


    /**
     * 按层模拟:将矩阵看作是由若干层所组成，首先填入最外层元素，然后填入次外层元素，直到矩阵最内层的元素。对于每层,从左上角开始顺时针的顺序填入所有元素，假如当前层
     *         的左上角位于(top, left),右下角位于(bottom, right),按照如下顺序填入当前层的元素
     *          1. 从左向右填入上侧元素，从(top,left)到(top, right),
     *          2. 从上向下填入右侧元素,从(top+1,right)到(bottom,right)
     *          3. 对于left < right且top < bottom,则从右到左填入元素，即(bottom,right-1)到(bottom, left + 1),和从下到上填入元素，即(bottom,left)到(top+1,left)
     *         处理完每一层后，left和top加一，bottom和right减一，进入下一层
     * 复杂度分析
     *  时间复杂度:O(n^2),
     *  空间复杂度:O(1)
     * @param n
     * @return
     */
    public int[][] solution(int n){

        int num = 1;
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = n - 1;
        int[][] ans = new int[n][n];
        while (left <= right && top <= bottom){
            for (int col = left; col <= right; col++){
                ans[top][col] = num;
                ++num;
            }
            for (int row = top + 1; row <= bottom; row++){
                ans[row][right] = num;
                ++num;
            }
            if (left < right && top < bottom){
                for (int col = right - 1; col >= left + 1; col--){
                    ans[bottom][col] = num;
                    ++num;
                }
                for (int row = bottom; row >= top + 1; row--){
                    ans[row][left] = num;
                    ++num;
                }
            }

            ++left;
            ++top;
            --right;
            --bottom;
        }
        return ans;
    }

    public static void main(String[] args) {

        GenerateMatrix generateMatrix = new GenerateMatrix();
        int n = 7;
        int[][] ans = generateMatrix.solution(n);
        for (int[] arr : ans){
            System.out.println(Arrays.toString(arr));
        }
    }
}
