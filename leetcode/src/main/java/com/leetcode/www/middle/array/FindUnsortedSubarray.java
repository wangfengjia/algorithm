package com.leetcode.www.middle.array;

import java.util.Arrays;

/**
 * leetcode-581:最短无序连续子数组
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。请你找出符合题意的最短子数组，并输出它的长度。
 *
 */
public class FindUnsortedSubarray {

    /**
     * 排序:将数组nums分为3段，numsA,numsB,numsC,当对numsB进行排序后，整个数组将变为有序，因此numsA和numsB本来就是有序的。题目要求是找到最短的numsB，那就是找到
     *      最大的numsA和numsC。因此我们可以将原数组排序后再和原数组进行比较，取最长的相同前缀numsA和最长的相同后缀numsC，这样我们就得到了最短的numsB
     * 复杂度分析
     *  时间复杂度:O(nlogn),数组排序所需的时间复杂度
     *  空间复杂度:O(n),n为数组的长度，需要一个额外的数组来保存排序后的数组
     * @param nums
     * @return
     */
    public int solution(int[] nums){

        if (isSorted(nums)){
            return 0;
        }

        int[] numsSorted = new int[nums.length];
        System.arraycopy(nums, 0, numsSorted, 0, nums.length);
        Arrays.sort(numsSorted);

        int left = 0;
        while (nums[left] == numsSorted[left]){
            ++left;
        }

        int right = nums.length - 1;
        while (nums[right] == numsSorted[right]){
            --right;
        }

        return right - left + 1;
    }

    public boolean isSorted(int[] nums){
        for (int i = 1; i < nums.length; i++){
            if (nums[i] < nums[i-1]){
                return false;
            }
        }

        return true;
    }


    /**
     * 假设numsB在nums对应的区间为[left,right],根据题意知道,numsB和numsC中任意一个数都大于等于numsA中的任意一个数。因此我们从右向左枚举数组，对于枚举到的每个数，
     * 我们可以更新minn，直到枚举到一个元素，使得条件不成立
     * 复杂度分析
     *  1. 时间复杂度:O(n),数组的长度，只需要遍历一次数组
     *  2. 空间复杂度:O(1),使用常量的空间来保存若干变量
     * @param nums
     * @return
     */
    public int solutionV2(int[] nums){

        int n = nums.length;
        int maxE = Integer.MIN_VALUE;
        int minE = Integer.MAX_VALUE;
        int left = -1;
        int right = -1;
        for (int i = 0; i < n; i++){

            //找到numsB的第一个元素
            if (maxE > nums[i]){
                right = i;
            }else {
                maxE = nums[i];
            }

            // 找到numsA的最后一个元素
            if (minE < nums[n - i - 1]){
                left = n - i -1;
            }else {
                minE = nums[n - i - 1];
            }
        }

        // right等于-1时候表示数组nums初始时就是有序的
        return right == -1 ? 0 : right - left + 1;
    }

    public static void main(String[] args) {

        int[] nums = new int[]{2,6,4,8,10,9,15};
        FindUnsortedSubarray subarray = new FindUnsortedSubarray();
        int ans = subarray.solutionV2(nums);
        System.out.println(ans);
    }
}
