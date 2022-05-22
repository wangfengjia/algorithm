package com.leetcode.www.middle.dynamic;

/**
 * leetcode-845:数组中的最长山脉
 * 把符合下列属性的数组 arr 称为 山脉数组 ：
     * arr.length >= 3
     * 存在下标 i（0 < i < arr.length - 1），满足
     * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
     * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * 给出一个整数数组 arr，返回最长山脉子数组的长度。如果不存在山脉子数组，返回 0 。
 */
public class LongestMountain {

    /**
     * 动态规划:按照山脉数组的定义:左侧山脚到山顶的序列是单调递增的，从山顶到右侧山脚是单调递减的。因此可以遍历数组中的每个元素作为山顶可以向左右两侧可以扩展的元素
     *         数目。再得到每个元素向左和向右扩展元素数目之和，最后得到最长的山脉数组的长度
     *      状态定义
     *          1. left[i]:表示可以向左最多可以扩展的元素数目
     *          2. right[i]:表示可以向右最多可以扩展的元素数目
     *      状态转移方程
     *          1.当nums[i] > nums[i-1]时，left[i] = left[i-1] + 1，否则left[i]=0
     *          2.当nums[i] > nums[i+1]时，right[i]=right[i+1]+1,否则right[i] = 0
     * 复杂度分析
     *  时间复杂度:O(n),n是数组nums的长度
     *  空间复杂度:O(n),n是数组nums的长度，数组left和right需要使用的空间
     * @param nums
     * @return
     */
    public int solution(int[] nums){

        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];

        for (int i = 1; i < n; i++){
            left[i] = nums[i] > nums[i-1] ? left[i-1] + 1: 0;
        }
        for (int i = n - 2; i >= 0; --i){
            right[i] = nums[i] > nums[i+1] ? right[i+1] + 1: 0;
        }

        int ans = 0;
        for (int i = 0; i < n; i++){
            ans = Math.max(ans, left[i] + right[i] + 1);
        }

        return ans;
    }


    public static void main(String[] args) {

        LongestMountain longestMountain = new LongestMountain();
        int[] num = {2,1,4,7,3,2,5};
        int ans = longestMountain.solution(num);
        System.out.println(ans);
    }
}

