package com.leetcode.www.middle.array;

import java.util.Arrays;

/**
 * leetcode-611:有效三角形的个数
 * 给定一个包含非负整数的数组 nums ，返回其中可以组成三角形三条边的三元组个数。
 */
public class TriangleNumber {


    /**
     * 排序+双指针
     * 有效三角形的定义是任意两边之和大于第三边，任意两边之差小于第三边
     * 可以先对数组排序,然后固定最长的一条边,使用双指针扫描
     *  1. 如果nums[left] + nums[right] > nums[i],则num[left+1..right-1] + nums[right] > nums[i].满足条件的有right-left种，然后right指针左移
     *  2. 如果nums[left] + nums[right] <= nums[i],则left指针右移
     *
     * 复杂度分析
     *  时间复杂度:O(n^2),n是数组nums的长度
     *  空间复杂度:O(log^n),排序所需要的栈空间
     * @param nums
     * @return
     */
    public int solution(int[] nums){

        Arrays.sort(nums);
        int ans = 0;
        int n = nums.length;
        for (int i = n - 1; i >= 0; i--){
            int left = 0;
            int right = i - 1;
            while (left < right){
                if (nums[left] + nums[right] > nums[i]){
                    ans += right - left;
                    --right;
                }else {
                    ++left;
                }
            }
        }

        return ans;
    }


    public static void main(String[] args) {

        TriangleNumber number = new TriangleNumber();
        int[] nums = {2,2,3,4};
        int ans = number.solution(nums);
        System.out.println(ans);
    }
}
