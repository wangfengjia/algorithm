package com.leetcode.www.middle.dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode-368:最大整除子集
 * 给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i], answer[j]) 都应当满足：
     * answer[i] % answer[j] == 0 ，或
     * answer[j] % answer[i] == 0
 * 如果存在多个有效解子集，返回其中任何一个均可。
 */
public class LargestDivisibleSubset {


    /**
     * 根据整除关系的传递性，即如果a|b,b|c,那么a/c,就可以知道
     *  1. 如果整数a是整除子集S的最小整数b的约数，那么可以将a添加到s中得到一个更大的整除子集
     *  2. 如果整数c是整除子集S的最大整数d的倍数，那么可以将c添加到s中得到一个更大的整除子集
     * 从以上两点可以知道问题状态转移的特点，可以使用动态规划来解决。利用动态规划得到最大的整除子集的长度，然后利用这个长度倒推得到一个目标子集
     * 动态规划实现
     *      状态定义:dp[i]表示数组nums在升序排列的前提下，以nums[i]为最大整数的整除子集的大小
     *      状态转移方程:dp[i] = max(dp[j]+1,dp[i]), 0<=j<=i-1 && (nums[i] % nums[j] = 0)
     *      初始状态:由于nums[i]必须被选择，因此任意的0<=i<=n-1,初始时dp[i] = 1
     * 倒推得到目标子集:由于最大整除子集不一定包含nums数组的最大值，遍历dp数组，得到最大整除子集的长度maxSize和此最大整除子集的最大整数maxVal,使用如下方式倒推
     *      1. 倒序遍历dp数组，直到找到dp[i] = maxSize为止，将nums[i]加入到结果集中，此时maxValue会等于nums[i]
     *      2. 将maxSize减一，继续倒序遍历找到dp[i] = maxSize,且maxVal能整除nums[i]的i为止，将此时的nums[i]加到结果集中，并且将maxVal更新为此时的nums[i]
     *      3. 重复上述步骤，直到maxSize等于0，此时的结果集就是目标结果集
     * 复杂度分析
     *  时间复杂度:O(n^2),n是数组nums的长度，排序的时间复杂度为O(nlog^n),计算dp数组的时间复杂度为O(n^2),倒序遍历得到一个目标子集,时间复杂度为O(n)
     *  空间复杂度:O(n),n是数组nums的长度，dp数组所消耗的空间
     * @param nums
     * @return
     */
    public List<Integer> solution(int[] nums){

        Arrays.sort(nums);
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int maxSize = 1;
        int maxValue = dp[0];

        for (int i = 1; i < n; i++){
            for (int j = 0; j < i; j++){
                if (nums[i] % nums[j] == 0){
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }

            if (dp[i] > maxSize){
                maxSize = dp[i];
                maxValue = nums[i];
            }
        }

        //倒推获得最大整除子集
        List<Integer> ans = new ArrayList<>();
        if (maxSize == 1){
            ans.add(nums[0]);
            return ans;
        }

        for (int i = n - 1; i >= 0 && maxSize > 0; i--){
            if (dp[i] == maxSize && maxValue % nums[i] == 0){
                ans.add(nums[i]);
                maxSize--;
                maxValue = nums[i];
            }
        }

        return ans;
    }

    public static void main(String[] args) {

        LargestDivisibleSubset subset = new LargestDivisibleSubset();
        int[] nums = {1,2,3};
        List<Integer> ans = subset.solution(nums);
        System.out.println(ans);
    }
}
