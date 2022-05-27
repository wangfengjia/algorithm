package com.leetcode.www.middle.array;


import java.util.Arrays;

/**
 * leetcode-945:使数组唯一的最小增量
 * 给你一个整数数组 nums 。每次 move 操作将会选择任意一个满足 0 <= i < nums.length 的下标 i，并将nums[i] 递增1。
 * 返回使 nums 中的每个值都变成唯一的所需要的最少操作次数。
 */
public class MinIncrementForUnique {


    /**
     * 排序
     * 先对数组排序，然后遍历数组，如果当前元素小于等于它前一个元素，则将其变为前一个数加1
     * 复杂度分析
     *  时间复杂度:O(nlog^n),n是数组nums的长度,排序所需的时间复杂度
     *  空间复杂度:O(1)
     * @param nums
     * @return
     */
    public int solution(int[] nums){

        int n = nums.length;
        if (n == 0){
            return 0;
        }

        Arrays.sort(nums);

        int preNum = nums[0];
        int ans = 0;
        for (int i = 1; i < n; i++){
            if (nums[i] >= preNum + 1){
                preNum = nums[i];
            }else {
                ans += preNum + 1 - nums[i];
                preNum++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {

        MinIncrementForUnique unique = new MinIncrementForUnique();
        int[] nums = {3, 2, 1, 2, 1, 7};
        int ans = unique.solution(nums);
        System.out.println(ans);
    }
}
