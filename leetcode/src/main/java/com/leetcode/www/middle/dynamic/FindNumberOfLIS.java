package com.leetcode.www.middle.dynamic;


/**
 * leetcode-673:最长递增子序列的个数
 * 给定一个未排序的整数数组nums，返回最长递增子序列的个数 。
 * 注意这个数列必须是严格递增的。
 */
public class FindNumberOfLIS {


    /**
     * 动态规划:leetcode-300进阶版本
     *  状态定义
     *      dp[i]表示以元素nums[i]结尾的最长上升子序列的长度
     *      cnt[i]表示以元素nums[i]结尾的最长上升子序列的个数
     *  状态转移方程
     *      dp[i] = dp[j] + 1,当0<= j < i && nums[i] > nums[j]时成立
     *      cnt[i]会等于所有dp[j] + 1=dp[i]的cnt[j]之和
     * 假设最长上升子序列的最大长度为maxLen,那么答案就是所有dp[i] = maxLen的i对应的cnt[i]之和
     *
     * 复杂度分析
     *  时间复杂度:O(n^2),n是数组nums的长度
     *  空间复杂度:O(n)
     * @param nums
     * @return
     */
    public int solution(int[] nums){

        int n = nums.length;
        int maxLen = 0;
        int ans = 0;
        int[] dp = new int[n];
        int[] cnt = new int[n];

        for (int i = 0; i < n; i++){
            dp[i] = 1;
            cnt[i] = 1;
            for (int j = 0; j < i; j++){
                if (nums[i] > nums[j]){
                    if (dp[j] + 1 > dp[i]){
                        dp[i] = dp[j] + 1;
                        cnt[i] = cnt[j];
                    }else if (dp[j] + 1 == dp[i]){
                        cnt[i] += cnt[j];
                    }
                }
            }

            if (dp[i] > maxLen){
                maxLen = dp[i];
                ans = cnt[i];
            }else if (dp[i] == maxLen){
                ans += cnt[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) {

        FindNumberOfLIS numberOfLIS = new FindNumberOfLIS();
        int[] nums = {2,2,2,2,2};
        int ans = numberOfLIS.solution(nums);
        System.out.println(ans);
    }
}
