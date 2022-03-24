package com.leetcode.www.middle.matrix;

/**
 * 编写一个高效的算法来搜索mxn矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：每行的元素从左到右升序排列。每列的元素从上到下升序排列。
 *
 */
public class SearchMatrix {


    /**
     * Z字形查找:从矩阵的右上角(x,y)(一开始的时候x为0，y为n-1)开始搜索，判断matrix[x][y]和target之间的关系，同时由于每行和每列的元素都是递增的，所以
     *      1. 当matrix[x][y] == target,找到了目标值
     *      2. 当matrix[x][y] > target,则指针向左移动
     *      3. 当matrix[x][y] < target,则指针向下移动
     * 复杂度分析
     *      时间复杂度:O(m+n)。m,n分别为矩阵的行数和列数，搜索一遍矩阵
     *      空间复杂度:O(1)
     * @param matrix
     * @param target
     * @return
     */
    public boolean solution(int[][] matrix, int target){

        int rows = matrix.length;
        int cols = matrix[0].length;
        int x = 0;
        int y = cols - 1;
        while (x < rows && y >= 0){
            if (matrix[x][y] == target){
                return true;
            }
            if (matrix[x][y] > target){
                --y;
            }else {
                ++x;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        int[][] matrix = new int[][]{
                {1,4,7,11,15},
                {2,5,8,12,19},
                {3,6,9,16,22},
                {10,13,14,17,24},
                {18,21,23,26,30}
        };
        int target = 51;
        SearchMatrix searchMatrix = new SearchMatrix();
        boolean exists = searchMatrix.solution(matrix, target);
        System.out.println(exists);
    }
}
