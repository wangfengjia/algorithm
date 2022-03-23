package com.leetcode.www.easy.matrix;

/**
 * 编写一个高效的算法来判断m x n矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 *
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 *
 * 示例 1：
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * 输出：true
 *
 * 思路
 *若将矩阵每一行拼接在上一行的末尾，则会得到一个升序数组，我们可以在该数组上二分找到目标元素。
 *代码实现时，可以二分升序数组的下标，将其映射到原矩阵的行和列上。
 *
 * 复杂度分析
 * 时间复杂度：O(logmn)，其中 mm 和 nn 分别是矩阵的行数和列数。
 * 空间复杂度：O(1)。
 *
 * 思路
 *
 * 由于每行的第一个元素大于前一行的最后一个元素，且每行元素是升序的，所以每行的第一个元素大于前一行的第一个元素，因此矩阵第一列的元素是升序的。
 * 我们可以对矩阵的第一列的元素二分查找，找到最后一个不大于目标值的元素(这个元素 < target < 列的下一个元素)，然后在该元素所在行中二分查找目标值是否存在。
 *
 */
public class SearchTwoDimensionMatrix {

    public boolean searchMatrixV1(int[][] matrix, int target){

        int m = matrix.length;
        int n = matrix[0].length;
        int low = 0;
        int high = m * n - 1;

        while (low <= high){
            int middle = (high - low) / 2 + low;
            int x = matrix[middle / n][middle % n];
            if (x < target){
                low = middle + 1;
            }else if (x > target){
                high = middle - 1;
            }else {
                return true;
            }
        }

        return false;
    }

    public boolean searchMatrixV2(int[][] matrix, int target){
        int rowIndex= binarySearchFirstColumn(matrix, target);
        if (rowIndex < -1){
            return false;
        }

        return binarySearchRow(matrix[rowIndex], target);
    }

    public int binarySearchFirstColumn(int[][] matrix, int target){

        int low = -1;
        int high = matrix.length - 1;
        while (low < high){
            int mid = (high - low + 1) / 2 + low;
            if (matrix[mid][0] <= target){
                low = mid;
            }else {
                high = mid - 1;
            }
        }

        return low;
    }

    public boolean binarySearchRow(int[] row, int target){

        int low = 0;
        int high = row.length - 1;
        while (low <= high){
            int mid = (high - low) / 2 + low;
            if (row[mid] == target){
                return true;
            }else if (row[mid] < target){
                low = mid + 1;
            }else {
                high = mid - 1;
            }
        }

        return false;
    }
}
