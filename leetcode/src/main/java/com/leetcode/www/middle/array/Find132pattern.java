package com.leetcode.www.middle.array;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * leetcode-456:132模式
 * 给你一个整数数组 nums ，数组中共有 n 个整数。132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。
 * 如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。
 */
public class Find132pattern {


    /**
     * 单调栈
     * 从后往前维护一个单调递减的栈，同时使用一个变量k来记录所有出栈元素的最大值。当遍历到一个元素时，判断这个元素和栈顶元素的大小关系，如果大于栈顶元素，
     * 则栈顶元素不断出栈，直到栈顶元素大于等于这个元素，同时使用变量k来记录所有出栈元素的最大值。如果遍历到的元素会小于k，则找到了符合条件的(i,j,k)
     * 证明
     * 这样做的本质是，通过维护单调递减来确保已经找到了(j,k),也就是j > k,也就是132的结构中找到了32，只要在遍历过程中有一个元素小于k，那么这个元素必然也会小于
     * j，这就是132结构中的i
     *
     * 复杂度分析
     *  时间复杂度:O(n),n是数组nums的长度
     *  时间复杂度:O(n),n是数组nums的长度
     * @param nums
     * @return
     */
    public boolean solution(int[] nums){

        int n = nums.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int k = Integer.MIN_VALUE;
        for (int i = n - 1; i >= 0; i--){

            if (nums[i] < k){
                return true;
            }
            while (!stack.isEmpty() && stack.peekLast() < nums[i]){
                k = stack.pollLast();
            }
            stack.addLast(nums[i]);
        }

        return false;
    }

    public static void main(String[] args) {

        Find132pattern pattern = new Find132pattern();
        int[] nums = {1,2,3,4};
        boolean ans = pattern.solution(nums);
        System.out.println(ans);
    }
}
