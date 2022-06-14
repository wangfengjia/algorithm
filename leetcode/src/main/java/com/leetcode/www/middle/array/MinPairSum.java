package com.leetcode.www.middle.array;

import java.util.Arrays;

/**
 * leetcode-1877:数组中最大数对和的最小值
 * 一个数对(a,b)的 数对和等于a + b。最大数对和是一个数对数组中最大的数对和。
    * 比方说，如果我们有数对(1,5)，(2,3)和(4,4)，最大数对和为max(1+5, 2+3, 4+4) = max(6, 5, 8) = 8。
 * 给你一个长度为 偶数n的数组nums，请你将 nums中的元素分成 n / 2个数对，使得：
     * nums中每个元素恰好在 一个数对中，且
     * 最大数对和的值 最小。
 * 请你在最优数对划分的方案下，返回最小的 最大数对和。
 */
public class MinPairSum {


    /**
     * 排序+贪心
     * 先将数组nums排序，遍历数组，每次取第k大和第k小的元素数对,计算它们的和，并维护最大值
     *
     * 复杂度分析
     *  时间复杂度:O(nlog^n),n是数组nums的长度，排序的时间复杂度为O(nlog^n)
     *  空间复杂度:O(log^n),排序的栈空间开销
     * @param nums
     * @return
     */
    public int solution(int[] nums){

        Arrays.sort(nums);
        int len = nums.length;
        int ans = 0;
        for (int i = 0; i < len; i++){
            ans = Math.max(ans, nums[i] + nums[len - i - 1]);
        }

        return ans;
    }

    public static void main(String[] args) {

        MinPairSum minPairSum = new MinPairSum();
        int[] nums = {3,5,4,2,4,6};
        int ans = minPairSum.solution(nums);
        System.out.println(ans);
    }
}
