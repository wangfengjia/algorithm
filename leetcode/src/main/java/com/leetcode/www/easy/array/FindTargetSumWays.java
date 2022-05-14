package com.leetcode.www.easy.array;

/**
 * leetcode494
 * 给你一个整数数组 nums 和一个整数 target 。向数组中的每个整数前添加'+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 *
 */
public class FindTargetSumWays {


    /**
     * 数组元素的和为sum,添加-号的元素和为neg，那么其余添加+号的元素sum-neg,则target = (sum - neg) - neg,则neg = (sum - target) / 2，这个问题就转化为了
     * 在数组里面选择若干个元素，使这些元素之和等于neg，计算选取元素的方案数，这个其实就是0~1背包问题
     * 动态规划解法
     *      1. 状态定义:dp[i][j]表示在数组nums前i个元素中选择若干个元素，使这些元素之和等于j的方案数
     *      2. 状态转移方程:
     *          1. 当不选择元素i时，dp[i][j] = dp[i-1][j];
     *          2. 当nums[i] <= target时，可以选择nums[i]，此时dp[i][j] = dp[i-1][j-nums[i]]
     *          总结:当元素i不可选时，dp[i][j] = dp[i-1][j];当元素i可选时:dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i]]
     *       3.边界条件:dp[0][0] = 1
     *
     * @param nums
     * @param target
     * @return
     */
    public int solution(int[] nums, int target){

        int sum = 0;
        for (int ele : nums){
            sum += ele;
        }

        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0){
            return 0;
        }

        int n = nums.length;
        int neg = diff / 2;
        int[][] dp = new int[n+1][neg + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= n; i++){
            for (int j = 0; j <= neg; j++){
                //不选中
                dp[i][j] = dp[i-1][j];
                //当nums[i]可选并选中
                if (nums[i-1] <= j){
                    dp[i][j] += dp[i-1][j - nums[i-1]];
                }
            }

        }

        return dp[n][neg];
    }

    public static void main(String[] args) {

        FindTargetSumWays findTargetSumWays = new FindTargetSumWays();
        int[] nums = new int[]{1,1,1,1,1};
        int target = 3;
        int ans = findTargetSumWays.solution(nums, target);
        System.out.println(ans);
    }
}
