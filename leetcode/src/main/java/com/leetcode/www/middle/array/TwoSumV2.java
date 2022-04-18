package com.leetcode.www.middle.array;

import java.util.Arrays;

/**
 * leetcode-167:两数之和 II - 输入有序数组
 * 给你一个下标从 1 开始的整数数组numbers ，该数组已按 非递减顺序排列，请你从数组中找出满足相加之和等于目标数target 的两个数。如果设这两个数分别是
 * numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length。以长度为 2 的整数数组 [index1, index2] 的形式返回这两个
 * 整数的下标 index1 和 index2。你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
 *
 * 你所设计的解决方案必须只使用常量级的额外空间。
 */
public class TwoSumV2 {


    /**
     * 双指针:左指针初始时指向数组第一个元素的位置，右指针指向数组的最后一个元素的位置，移动指针的方式有以下几种情况
     *       1. 当nums[left] + nums[right] < target时，左指针往右边移动
     *       2. 当nums[left]+nums[right]>target时，右指针往左边移动
     *       3. 指针移动的截止条件是left < right
     * 复杂度分析
     *  时间复杂度:O(n),n为数组的长度，两个指针移动的总次数最多n次
     *  空间复杂度:O(1)
     * @param nums
     * @param target
     * @return
     */
    public int[] solution(int[] nums, int target){

        int left = 0;
        int right = nums.length - 1;
        while (left < right){
            int curSum = nums[left] + nums[right];
            if (curSum == target){
                return new int[]{left + 1, right + 1}; //数组下标从1开始，所以返回的时候需要加1
            }else if (curSum < target){
                ++left;
            }else {
                --right;
            }
        }

        return new int[]{-1,-1};
    }

    public static void main(String[] args) {

        TwoSumV2 twoSumV2 = new TwoSumV2();
        int[] nums = {2,7,11,15};
        int target = 9;
        int[] ans = twoSumV2.solution(nums, target);
        System.out.println(Arrays.toString(ans));
    }
}
