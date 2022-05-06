package com.leetcode.www.easy.array;

/**
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 */
public class MaxSubArray {


    /**
     * 动态规划版，状态转移方程:f(i) = max((f(i-1) + nums[i]), nums[i]),f(i)表示以第i个元素结尾的连续子数组最大和
     * @param arr
     * @return
     */
    public int solution(int[] arr){

        int max = 0;
        int pre = 0;
        for (int i = 0; i < arr.length; i++){

            pre = Math.max(pre + arr[i], arr[i]);
            max = Math.max(pre, max);
        }

        return max;
    }
}
