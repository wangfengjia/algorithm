package com.leetcode.www.middle.array;


/**
 * leetcode-413:等差数列划分
 * 如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 *  例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
 * 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。
 * 子数组 是数组中的一个连续序列。
 */
public class NumberOfArithmeticSlices {


    /**
     * 动态规划
     *      状态定义:dp[i]表示以nums[i]结尾的等差数组的子数组个数
     *      状态转移方程:
     *          dp[i] = dp[i-1] + 1(nums[i]-nums[i-1] == nums[i-1]-nums[i-2]),nums[i-1]-nums[i-2]是nums[i-1]结尾的等差数组的等差值，如果
     *          nums[i]-nums[i-1] == num[i-1]-nums[i-2],那么nums[i]就可以和以nums[i-1]结尾的等差数列形成新的等差数列
     *      边界条件:至少要有3个元素才能算的上等差数列，所以dp[0]和dp[1]会等于0
     * 复杂度分析
     *  时间复杂度:O(n),n为数组的长度
     *  空间复杂度:O(n),n为数组的长度，状态数组所消耗的空间
     * @param nums
     * @return
     */
    public int solution(int[] nums){

        int n = nums.length;
        if (n < 3){
            return 0;
        }

        int[] dp = new int[n];
        dp[0] = 0;
        dp[1] = 0;
        int ans = 0;
        for (int i = 2; i < n; i++){
            if (nums[i]-nums[i-1] == nums[i-1] - nums[i-2]){
                dp[i] = dp[i - 1] + 1;
                ans += dp[i];
            }
        }

        return ans;
    }

    public static void main(String[] args) {

        NumberOfArithmeticSlices slices = new NumberOfArithmeticSlices();
        int[] nums = {1};
        int ans = slices.solution(nums);
        System.out.println(ans);
    }
}
