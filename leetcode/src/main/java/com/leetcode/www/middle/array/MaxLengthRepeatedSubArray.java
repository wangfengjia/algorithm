package com.leetcode.www.middle.array;


import javax.swing.*;

/**
 * leetcode-718:最长重复子数组
 * 给两个整数数组 nums1 和 nums2，返回 两个数组中 公共的 、长度最长的子数组的长度 。
 */
public class MaxLengthRepeatedSubArray {

    /**
     * 滑动窗口:利用暴力法解决这个问题的时候，重复子数组的起始元素在两个数组中的位置不同，可以通过两个数组对齐的方式来得到重复子数组的开始位置。对齐的方式有两种
     *         1. A保持不变，B的首元素与A中某个元素对齐
     *         2. B保持不变，A中的首元素和B中的某个元素对齐
     * 复杂度分析
     *  时间复杂度:O((m+n)*min(m,n))
     *  空间复杂度:O(1)
     * @param nums1
     * @param nums2
     * @return
     */
    public int solution(int[] nums1, int[] nums2){

        int aLen = nums1.length;
        int bLen = nums2.length;
        int ret = 0;

        for (int i = 0; i < aLen; i++){
            int len = Math.min(bLen, aLen - i);
            int maxLen = maxLength(nums1, nums2, i, 0, len);
            ret = Math.max(ret, maxLen);
        }

        for (int i = 0; i < bLen; i++){
            int len = Math.min(aLen, bLen - i);
            int maxLen = maxLength(nums1, nums2, 0, i, len);
            ret = Math.max(ret, maxLen);
        }
        return ret;
    }

    private int maxLength(int[] aArr, int[] bArr, int aIndex, int bIndex, int len){

        int ret = 0;
        int k = 0;

        for (int i = 0; i < len; i++){
            if (aArr[aIndex + i] == bArr[bIndex + i]){
                k += 1;
            }else {
                k = 0;
            }
            ret = Math.max(ret, k);
        }

        return ret;
    }

    /**
     * 动态规划:
     *      1. 状态定义:dp[i][j]表示num1[i..n]和nums2[j..n]的最长公共前缀
     *      2. 状态转移方程:
     *          1. 如果nums[i]=nums[j],则dp[i][j] = dp[i+1][j+1] + 1,由于dp[i][j]的值从dp[i+1][j+1]转移得到，所以我们需要倒过来计算
     *          2. 如果nums[i] != nums[j],则dp[i][j] = 0;
     * 复杂度分析
     *  时间复杂度:O(m*n)
     *  空间复杂度:O(m*n)
     * @param nums1
     * @param nums2
     * @return
     */
    public int solutionV2(int[] nums1, int[] nums2){


        int m = nums1.length;
        int n = nums2.length;
        int[][] dp = new int[m+1][n+1];
        int ans = 0;
        for (int i = m - 1; i >= 0; i--){
            for (int j = n - 1; j >= 0; j--){
                if (nums1[i] == nums2[j]){
                    dp[i][j] = dp[i+1][j+1] + 1;
                }else {
                    dp[i][j] = 0;
                }
                ans = Math.max(dp[i][j], ans);
            }
        }

        return ans;
    }

    public static void main(String[] args) {

        MaxLengthRepeatedSubArray subArray = new MaxLengthRepeatedSubArray();
        int[] nums1 = {1,2,3,2,1};
        int[] nums2 = {3,2,1,4,7};

        int ans1 = subArray.solution(nums1, nums2);
        int ans2 = subArray.solutionV2(nums1, nums2);

        System.out.println(ans1);
        System.out.println(ans2);
    }
}
