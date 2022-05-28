package com.leetcode.www.middle.array;

/**
 * leetcode-1894:找到需要补充粉笔的学生编号
 * 一个班级里有n个学生，编号为 0到 n - 1。每个学生会依次回答问题，编号为 0的学生先回答，然后是编号为 1的学生，以此类推，直到编号为 n - 1的学生，然后老师会重复这个过程，重新从编号为 0的学生开始回答问题。
 * 给你一个长度为 n且下标从 0开始的整数数组chalk和一个整数k。一开始粉笔盒里总共有k支粉笔。当编号为i的学生回答问题时，他会消耗 chalk[i]支粉笔。如果剩余粉笔数量 严格小于chalk[i]，那么学生 i需要 补充粉笔。
 * 请你返回需要 补充粉笔的学生 编号。
 */
public class ChalkReplacer {

    /**
     * 前缀和+二分查找
     * 先通过预处理得到前缀和数组sum,将k对所有学生一次循环所消耗的总粉笔数(sum[n])进行取模，得到最后一轮开始前的粉笔数。然后对前缀和数组进行二分查找，
     * 找到最后一位满足粉笔要求的学生，往后一位学生就是答案
     * 复杂度分析
     *  时间复杂度:O(n),n是数组nums的长度,处理前缀和的时间复杂度是O(n),二分查找的时间复杂度是O(log^n),整体时间复杂度为O(n)
     *  空间复杂度:O(n),n是数组nums的长度
     * @param nums
     * @param k
     * @return
     */
    public int solution(int[] nums, int k){

        int n = nums.length;
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; i++){
            preSum[i] = preSum[i-1] + nums[i-1];
        }

        k = k % preSum[n];
        int left = 1;
        int right = n;
        while (left < right){
            int mid = (left + right) / 2;
            if (preSum[mid] <= k){
                left = mid;
            }else {
                right = mid - 1;
            }
        }

        return preSum[right] <= k ? right : right - 1;
    }

    public static void main(String[] args) {

        ChalkReplacer replacer = new ChalkReplacer();
        int[] nums = {3,4,1,2};
        int k = 25;
        int ans = replacer.solution(nums, k);
        System.out.println(ans);
    }
}
