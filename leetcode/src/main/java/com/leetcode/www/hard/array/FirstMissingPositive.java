package com.leetcode.www.hard.array;

/**
 * leetcode-41:缺失的第一个正数
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 */
public class FirstMissingPositive {

    /**
     * 对于长度为N的数组，在数组中没有出现的最小正整数只能在[1,N+1]中，这是因为如果[1,N]都出现了,那么答案就是N+1,否则答案是[1,N]中没有出现的最小正整数。这样我们
     * 可以将数组转换成哈希表,核心思路是:遍历数组每个元素，将小于等于N的元素值减去一作为下标，放回数组中，并且打上标记(标记方式是为一个负数)，表示这个正整数已经
     * 存在数组中，在处理过程中，需要将元素值小于0的元素复制为N+1,这样处理完成后，再去遍历数组，碰到第一个大于0的元素的下标加上1就是没有出现的最小正整数。
     * 复杂度分析
     *  时间复杂度:O(n),n为数组的长度
     *  空间复杂度:O(1)
     * @param nums
     * @return
     */
    public int solution(int[] nums){

        int n = nums.length;
        for (int i = 0; i < n; i++){
            if (nums[i] <= 0){
                nums[i] = n + 1;
            }
        }

        for (int i = 0; i < n; i++){
            int num = Math.abs(nums[i]);
            if (num <= n){
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }

        for (int i = 0; i < n; i++){
            if (nums[i] > 0){
                // i+1的原因是在上一步的循环中，往数组中放元素是num-1
                return i + 1;
            }
        }

        return n+1;
    }

    public static void main(String[] args) {

        FirstMissingPositive positive = new FirstMissingPositive();
        int[] nums = {4,5,6};
        int ans = positive.solution(nums);
        System.out.println(ans);
    }
}
