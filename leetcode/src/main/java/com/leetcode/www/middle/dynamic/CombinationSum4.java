package com.leetcode.www.middle.dynamic;

/**
 * leetcode-377:组合总和 Ⅳ
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 * 题目数据保证答案符合 32 位整数范围。
 */
public class CombinationSum4 {


    /**
     * 动态规划:
     *      1.状态定义:dp[i]表示选取的元素之和等于i的方案数
     *      2. 状态转移方程:由于数组nums中的每个元素可以选取多次，且需要考虑选取顺序，所以遍历1<=i<=target的每个i，对于每个i，遍历数组nums中的每个元素，如果
     *         这个元素小于等于i，则选中这个元素，因此状态转移方程为
     *         dp[i] = sum(dp[i-num])
     *      3. 边界条件:dp[0]=1，只有当不选取任何元素时，元素之和才等于0，所以dp[0] = 1;
     * @param nums
     * @param target
     * @return
     */
    public int solution(int[] nums, int target){

        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++){
            for (int num : nums){
                if (num <= i){
                    dp[i] += dp[i-num];
                }
            }
        }

        return dp[target];
    }

    public static void main(String[] args) {

        CombinationSum4 sum4 = new CombinationSum4();
        int[] nums = {1,2,3};
        int target = 4;
        int ans = sum4.solution(nums, target);
        System.out.println(ans);
    }
}
