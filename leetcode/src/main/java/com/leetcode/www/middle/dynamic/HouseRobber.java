package com.leetcode.www.middle.dynamic;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷
 * 闯入，系统会自动报警。给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * 方法一:动态规划
 *      1. 状态定义:dp[i]表示前i间能偷窃到的最大金额
 *      2. 状态转移方程: 小偷在每个房间有两种选择，偷窃或者不偷窃
 *          偷窃时:由于不能连续偷窃，所以偷窃总金额为前k-2个房间的最高金额加上当前房间的金额。另外一种理解方式是:当前状态dp[i]基于前一个状态得到dp[i-1],由于不能
 *                连续偷窃，所以偷窃这个房屋之后的总金额会等于 不偷窃第i-1个房间后前k-1个房间的最高总金额(而这个最高总金额会等于前i-2房间的最高金额)，再加上第i
 *                个房屋的金额
 *          不偷窃时:偷窃总金额等于前k-1个房间的最高金额
 *          dp[i] = max(dp[i-2]+nums[i], dp[i-1])
 *      3. 边界条件
 *          dp[0] = nums[0],只有一个房间，则偷窃这个房屋
 *          dp[1] = max(nums[0],nums[1])，有两个房屋，偷窃金额更大的那个房屋
*       4. 复杂度
 *          时间复杂度:O(n),只需要遍历一遍数组
 *          空间复杂度:O(n),dp数组所消耗的空间
 */
public class HouseRobber {


    public int solution(int[] nums){

        if (nums == null || nums.length == 0){
            return 0;
        }
        int length = nums.length;
        if (length == 1){
            return nums[0];
        }

        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++){
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
        }

        return dp[length - 1];
    }

    public static void main(String[] args) {

        int[] nums = new int[]{2,7,9,3,1};
        HouseRobber rob = new HouseRobber();
        int max = rob.solution(nums);
        System.out.println(max);
    }
}
