package com.leetcode.www.middle.dynamic;

/**
 * leetcode-1035:不相交的线
 * 在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。
 * 现在，可以绘制一些连接两个数字 nums1[i]和 nums2[j]的直线，这些直线需要同时满足满足：
     * nums1[i] == nums2[j]
     * 且绘制的直线不与任何其他连线（非水平线）相交。
 * 请注意，连线即使在端点也不能相交：每个数字只能属于一条连线。
 * 以这种方法绘制线条，并返回可以绘制的最大连线数。
 */
public class MaxUncrossedLines {


    /**
     * 假设有k条不想交的直线分别连接着数组nums1和数组nums2中k对相等的元素，而且这k对相等的元素在两个数组中的相对顺序是一致的，因此这k对元素组成的序列即为数组
     * nums1和nums2的公共子序列。要计算k的最大值，就是需要计算两个数组的最长公共子序列，转化为了第leetcode-1143题，是一个二维动态规划问题
     *
     * 动态规划
     *      状态定义:dp[i][j]表示nums1[0:i]和nums2[0:j]的最长公共子序列的长度。nums1[0:i]表示数组nums1的长度为i的前缀，nums2[0:j]表示数组nums2的长度为j的前缀
     *      状态转移方程:dp[i][j]可以由dp[i-1][j-1],dp[i-1][j],dp[i][j-1]三个状态转移而来
     *          1. 当nums1[i-1] == nums2[j-1]时,dp[i][j] = dp[i-1][j-1] + 1
     *          2. 当nums1[i-1] != nums2[j-1]时，dp[i][j] = max(dp[i][j-1],dp[i-1][j])
     *      边界条件:当i或者j等于0时，dp[i][j] = 0
     * @param nums1
     * @param nums2
     * @return
     */
    public int solution(int[] nums1, int[] nums2){

        int m = nums1.length;
        int n = nums2.length;
        int[][] dp = new int[m+1][n+1];

        for (int i = 1; i <= m; i++){
            int n1 = nums1[i-1];
            for (int j = 1; j <= n; j++){
                int n2 = nums2[j-1];
                if (n1 == n2){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[m][n];
    }


    public static void main(String[] args) {

        MaxUncrossedLines lines = new MaxUncrossedLines();
        int[] nums1 = {2,5,1,2,5};
        int[] nums2 = {10,5,2,1,5,2};
        int ans = lines.solution(nums1, nums2);
        System.out.println(ans);
    }
}
