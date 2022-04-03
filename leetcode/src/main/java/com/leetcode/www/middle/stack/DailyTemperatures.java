package com.leetcode.www.middle.stack;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * leetcode-739:每日温度
 * 给定一个整数数组temperature，表示每天的温度，返回一个数组answer，其中answer[i]是指在第 i 天之后，才会有更高的温度。如果气温在这之后都不会升高，请在该
 * 位置用0 来代替。
 *
 */
public class DailyTemperatures {


    /**
     * 单调栈:可以维护一个存储下标的单调栈，从栈低到栈顶的下标在温度列表对应的温度是单调递减，如果一个下标在单调栈里面，则表示尚未找到下一次温度更高的下标。
     * 我们可以通过遍历温度列表来维护这个单调栈，遍历过程中有以下几种处理情况(preIndex表示栈顶元素，i表示当前处理的元素)
     *      1. 当栈为空时，直接将i进栈
     *      2. 如果栈不为空时，比较nums[i]和nums[preIndex]的大小。如果nums[i] > nums[preIndex]，则将preIndex从栈中移除，并将preIndex的等待天数赋值为
     *         i-preIndex。重复这些操作直到栈为空或者是栈顶元素的温度小于等于当前温度，然后将i进栈
     * 复杂度分析
     *  1. 时间复杂度:O(N),N表示温度列表的长度，需要遍历一次温度列表
     *  2. 空间复杂度:O(N),N表示温度列表的长度，单调栈所占用的空间
     * @param nums
     * @return
     */
    public int[] solution(int[] nums){

        int n = nums.length;
        int[] ans = new int[n];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n; i++){
            int current = nums[i];
            while (!stack.isEmpty() && current > nums[stack.peek()]){
                int preIndex = stack.pop();
                ans[preIndex] = i - preIndex;
            }

            stack.push(i);
        }

        return ans;
    }


    public static void main(String[] args) {

        DailyTemperatures dailyTemperatures = new DailyTemperatures();
        int[] nums = new int[]{73,74,75,71,69,72,76,73};
        int[] ans = dailyTemperatures.solution(nums);
        System.out.println(Arrays.toString(ans));
    }
}
