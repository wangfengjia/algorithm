package com.leetcode.www.middle.array;

import java.util.Arrays;

/**
 * leetcode-34:在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。如果数组中不存在目标值 target，返回[-1, -1]。
 * 使用时间复杂度为O(N)的解法
 */
public class SearchRange {


    /**
     * 使用二分查找
     * @param nums
     * @return
     */
    public int[] solution(int[] nums, int target){

        //数组中第一个大于等于target的下标
        int leftIndex = binarySearch(nums, target, true);
        //数组中第一个大于target的下标再减一
        int rightIndex = binarySearch(nums, target, false) - 1;

        //可能target不存在，所以查找完需要做校验
        if (leftIndex <= rightIndex && rightIndex < nums.length && nums[leftIndex] == target && nums[rightIndex] == target){
            return new int[]{leftIndex, rightIndex};
        }

        return new int[]{-1,-1};
    }

    public int binarySearch(int[] nums, int target, boolean lower){

        int left = 0;
        int right = nums.length - 1;
        int ans = nums.length;

        while (left <= right){

            int mid = (left + right) / 2;
            //low为true时表示查找第一个大于等于target的下标，而low为false的时候是查找第一个大于target的下标
            if (nums[mid] > target || (lower && nums[mid] >= target)){
                right = mid - 1;
                ans = mid;
            }else {
                left = mid + 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {

        SearchRange searchRange = new SearchRange();
        int[] nums = new int[]{5,7,7,8,8,10};
        int target = 8;
        int[] ans = searchRange.solution(nums, target);
        System.out.println(Arrays.toString(ans));
    }
}
