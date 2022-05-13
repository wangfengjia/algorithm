package com.leetcode.www.middle.dynamic;

/**
 * leetcode-740:删除并获得点数
 * 给你一个整数数组nums，你可以对它进行一些操作。
 * 每次操作中，选择任意一个nums[i]，删除它并获得nums[i]的点数。之后，你必须删除 所有 等于nums[i] - 1 和 nums[i] + 1的元素。
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 */
public class DeleteAndEarn {


    /**
     * 动态规划:根据题意，当我们选择nums[i]时，比nums[i]大小一个单位的数不能被选择。我们可以先对数组排序，从前往后处理，只需要考虑当前数和前一个数的关系即可，
     *         这样处理完成以后，每个数的前一位和后一位都被考虑到。这样这个问题就转化为了一个DP序列问题(选择某一个数，需要考虑前一个数的[大小/选择]状态)，就可以用动态
     *         规划来解决
     *         1. 状态定义:
     *              1. dp[i][0]代表数值i的数字不选择的最大价值
     *              2. dp[i][1]代表数值i的数字被选择的最大值
     *         2. 状态转移方程:为了计算方便，我们可以先对nums中出现的所有数值进行计数，使用数组cnt[]进行计数，cnt[i] = x表示数值i出现的次数为x
     *              1. 当数值i不被选中时，前一个数可以是"选中和不选中"，则dp[i][0] = max(dp[i-1][0], dp[i-1][1])
     *              2. 当数值i被选中时，前一个数不能被选中，此时dp[i][1] = dp[i-1][0] + i * cnt[i]
     * 复杂度分析
     *  时间复杂度:O(n)
     *  空间复杂度:O(n)
     * @param nums
     * @return
     */
    public int solution(int[] nums){

        int[] cnt = new int[10010];
        int n = nums.length;
        int max = 0;
        for (int num : nums){
            cnt[num]++;
            max = Math.max(num, max);
        }

        int[][] dp = new int[max+1][2];
        for (int i = 1; i <= max; i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
            dp[i][1] = dp[i-1][0] + i * cnt[i];
        }

        return Math.max(dp[max][0], dp[max][1]);
    }


    public static void main(String[] args) {

        DeleteAndEarn deleteAndEarn = new DeleteAndEarn();
        int[] nums = {3,4,2};
        int ans = deleteAndEarn.solution(nums);
        System.out.println(ans);
    }
}
