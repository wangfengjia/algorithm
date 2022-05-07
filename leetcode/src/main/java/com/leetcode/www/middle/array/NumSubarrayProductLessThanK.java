package com.leetcode.www.middle.array;


/**
 * leetcode-713:乘积小于 K 的子数组
 * 给你一个整数数组 nums 和一个整数 k ，请你返回子数组内所有元素的乘积严格小于 k 的连续子数组的数目。
 */
public class NumSubarrayProductLessThanK {


    /**
     * 滑动窗口:使用left和right两个指针，初始值都是指向数组的第一个元素，同时使用prod来记录窗口内元素的乘积。right指针向右边移动，当prod >= k时，就移动left
     *         指针，直到prod<k,此时乘积小于k的子数组的数目为right-left+1
     * 复杂度分析
     *  时间复杂度:O(n),n表示数组的长度，数组中的每个元素被访问一次
     *  空间复杂度:O(1)
     * @param nums
     * @param k
     * @return
     */
    public int solution(int[] nums, int k){

        int n = nums.length;
        if (n == 0){
            return 0;
        }

        int left = 0;
        int right = 0;
        int ans = 0;
        int prod = 0;
        while (right < n){
            prod *= nums[right];
            while (left <= right && prod >= k){
                prod /= nums[left];
                ++left;
            }

            ans += right - left + 1;
            ++right;
        }

        return ans;
    }

    public static void main(String[] args) {

        NumSubarrayProductLessThanK lessThanK = new NumSubarrayProductLessThanK();
        int[] nums = {10,5,2,6};
        int k = 100;
        int ans = lessThanK.solution(nums, k);
        System.out.println(ans);

        int[] nums1 = {1,2,3};
        int k1 = 0;
        int ans1 = lessThanK.solution(nums1, k1);
        System.out.println(ans1);
    }
}
