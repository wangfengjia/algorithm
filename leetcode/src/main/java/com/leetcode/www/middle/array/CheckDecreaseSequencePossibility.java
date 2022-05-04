package com.leetcode.www.middle.array;

/**
 * leetcode-665:非递减数列
 * 给你一个长度为n的整数数组nums，请你判断在最多改变1 个元素的情况下，该数组能否变成一个非递减数列。
 * 我们是这样定义一个非递减数列的：对于数组中任意的i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
 */
public class CheckDecreaseSequencePossibility {

    /**
     * 遍历数组，去比较nums[i]和nums[i+1],如果nums[i]>nums[i+1],就 将nums[i]修改为nums[i+1]。同时需要保证nums[i-1]<=nums[i],即nums[i-1]<=nums[i+1],
     * 如果不成立，则将nums[i+1]修改为nums[i],修改过程中统计修改的次数，如果修改次数超过1，则返回false
     * 复杂度分析
     *  时间复杂度:O(n),n表示数组的长度
     *  空间复杂度:O(1)
     * @param nums
     * @return
     */
    public boolean solution(int[] nums){

        int n = nums.length;
        int cnt = 0;
        for (int i = 0; i < n-1; i++){
            int x = nums[i];
            int y = nums[i+1];
            if (x > y){
                cnt++;
                if (cnt > 1){
                    return false;
                }
                // 当nums[i+1] < nums[i-1]时，将nums[i+1]修改为nums[i]
                if (i > 0 && nums[i-1] > y){
                    nums[i+1] = nums[i];
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {

        CheckDecreaseSequencePossibility possibility = new CheckDecreaseSequencePossibility();
        int[] nums = {4,2,3};
        boolean ans = possibility.solution(nums);
        System.out.println(ans);
    }
}
