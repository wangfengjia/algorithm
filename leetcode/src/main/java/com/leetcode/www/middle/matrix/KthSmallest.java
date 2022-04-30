package com.leetcode.www.middle.matrix;

/**
 * leetcode-378:有序矩阵中第 K 小的元素
 * 给你一个n x n矩阵matrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
 * 请注意，它是 排序后 的第 k 小元素，而不是第 k 个 不同 的元素。
 * 你必须找到一个内存复杂度优于O(n2) 的解决方案。
 */
public class KthSmallest {


    /**
     * 二分查找:由于这个矩阵是有序的，这个矩阵的最大值为matrix[n-1][n-1],最小值为matrix[0][0],分别记作min和max，任取一个数mid，left<=mid<=right矩阵中
     *        大于mid的数就和不大于mid的数分别形成了两个板块,沿着一条锯齿线将这个矩阵分开。我们沿着这条锯齿线走一遍就可以计算出这两个板块的大小，也就计算出了
     *        矩阵中不大于mid的个数了。通过这个性质，我们可以假设答案x，min<=x<=max，这样就确定了二分查找的上下界。对于每个猜测的答案mid，计算矩阵中有多少数
     *        不大于mid
     *          1. 如果数量不小于k，那么最终答案x不大于mid
     *          2. 如果数量小于k，那么最终答案x大于mid
     *        通过如上的处理就可以计算出最终的结果x
     * 复杂度分析
     *  时间复杂度:O(nlog(max-min)),二分查找进行的次数为O(log(max-min)),每次操作的时间复杂度为O(n)
     *  空间复杂度为O(1)
     * @param matrix
     * @param k
     * @return
     */
    public int solution(int[][] matrix, int k){

        int n = matrix.length;
        int min = matrix[0][0];
        int max = matrix[n-1][n-1];

        while (min < max){
            int mid = min + ((max - min) >> 1);
            if (check(matrix, mid, k, n)){
                max = mid;
            }else {
                min = mid + 1;
            }
        }

        return min;
    }


    private boolean check(int[][] matrix, int mid, int k, int n){

        int i = n - 1;
        int j = 0;
        int nums = 0;

        while (i >= 0 && j < n){
            if (matrix[i][j] <= mid){
                nums += i + 1;
                ++j;
            }else {
                --i;
            }
        }

        return nums >= k;
    }

    public static void main(String[] args) {

        KthSmallest kthSmallest = new KthSmallest();
        int[][] matrix = {
                {1,5,9},
                {10,11,13},
                {12,13,15}
        };
        int k = 8;
        int ans = kthSmallest.solution(matrix, k);
        System.out.println(ans);
    }
}
