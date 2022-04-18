package com.leetcode.www.middle.array;

/**
 * leetcode-209:长度最小的子数组
 * 给定一个含有n个正整数的数组和一个正整数 target 。找出该数组中满足其和 ≥ target 的长度最小的 连续子数组[numsl, numsl+1, ..., numsr-1, numsr] ，并返
 * 回其长度。如果不存在符合条件的子数组，返回 0 。
 */
public class MinSubArrayLen {

    /**
     * 滑动窗口:使用两个指针start和end来表示子数组的开始和结束的位置，和使用变量sum来存储子数组的和。初始的时候start和end都指向0，然后end往数组右边移动，在这
     *         过程中将nums[end]加到sum，当sum大于等于target时，更新子数组的最小长度(此时子数组的长度为end-start+1);然后通过向右移动start指针来缩小这个滑动
     *         窗口的大小，直到sum < target,在这个缩小滑动窗口的过程中同样更新子数组的最小长度
     * 复杂度分析
     *  时间复杂度:O(n),其中n是数组的长度，指针start和end在每个元素上最多移动一次
     *  空间复杂度:O(1)
     * @param nums
     * @param target
     * @return
     */
    public int solution(int[] nums, int target){

        int n = nums.length;
        if (n == 0){
            return 0;
        }
        int start = 0;
        int end = 0;
        int sum = 0;
        int ans = Integer.MAX_VALUE;

        while (end < n){
            sum += nums[end];
            //start指针往右边移动后，sum还是大于等于target的话，则start指针向右移动过滤掉的元素为起点的满足条件的子数组的长度不会小于当前的end-start+1
            //所以可以通过start指针向右移动来过滤元素
            while (sum >= target){
                ans = Math.min(ans, (end - start + 1));
                sum -= nums[start];
                ++start;
            }
            ++end;
        }

        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public static void main(String[] args) {

        MinSubArrayLen subArrayLen = new MinSubArrayLen();
        int[] nums = {2,3,1,2,4,3};
        int target = 7;
        int ans = subArrayLen.solution(nums, target);
        System.out.println(ans);
    }
}
