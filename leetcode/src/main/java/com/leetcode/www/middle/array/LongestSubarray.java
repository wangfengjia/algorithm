package com.leetcode.www.middle.array;


import java.util.Deque;
import java.util.LinkedList;

/**
 * leetcode-1438:绝对差不超过限制的最长连续子数组
 * 给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。
 * 如果不存在满足条件的子数组，则返回 0 。
 */
public class LongestSubarray {


    /**
     * 滑动窗口+单调队列
     * 枚举每一个元素作为右端点，找到其对应的左端点，满足区间的最大值和最小值之差不超过limit。
     * 使用一个单调递增的队列维护当前区间的最小值，同时使用一个单调递减的队列维护当前区间的最大值。然后计算两个队列的队首的差值，就可以知道当前区间是否满足条件
     *
     * 复杂度分析
     *  时间复杂度:O(n),n是数组nums的长度,最多遍历数组两次，两个单调队列入队出队次数都是O(n)
     *  空间复杂度:O(n),n是数组的长度，最坏情况下单调队列和数组一样大
     * @param nums
     * @param limit
     * @return
     */
    public int solution(int[] nums, int limit){

        //单调递增队列
        Deque<Integer> queueMin = new LinkedList<>();
        //单调递减队列
        Deque<Integer> queueMax = new LinkedList<>();
        int n = nums.length;
        int left = 0;
        int right = 0;
        int ans = 0;
        while (right < n){
            while (!queueMin.isEmpty() && queueMin.peekLast() > nums[right]){
                queueMin.pollLast();
            }
            while (!queueMax.isEmpty() && queueMax.peekLast() < nums[right]){
                queueMax.pollLast();
            }
            queueMin.offerLast(nums[right]);
            queueMax.offerLast(nums[right]);
            while (!queueMin.isEmpty() && !queueMax.isEmpty() && queueMax.peekFirst() - queueMin.peekFirst() > limit){
                if (nums[left] == queueMin.peekFirst()){
                    queueMin.pollFirst();
                }
                if (nums[left] == queueMax.peekFirst()){
                    queueMax.pollFirst();
                }
                ++left;
            }
            ans = Math.max(ans, right - left + 1);
            ++right;
        }

        return ans;
    }


    public static void main(String[] args) {

        LongestSubarray subarray = new LongestSubarray();
        int[] nums = {8,2,4,7};
        int k = 4;
        int ans = subarray.solution(nums, k);
        System.out.println(ans);
    }
}
