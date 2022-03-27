package com.leetcode.www.easy.dynamic;


import java.util.Arrays;

/**
 * leetcode338(比特位计数):给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
 */
public class CountBits {

    /**
     * 动态规划(最低有效位)
     *      1. 状态表示:dp[i]表示i的一比特数
     *      2. 状态转移方程:对于正整数x，将其二进制表示右移一位，等价于将其二进制表示的最低位去掉，得到的数为x/2，如果dp[x/2]的值已知，则可以得到dp[x]值有以下两种情况
     *          1. 如果x是偶数，则dp[x] = dp[x/2]
     *          2. 如果x是奇数，则dp[x] = dp[x/2] + 1
     *          以上两种情况可以合并为:dp[x]等于dp[x/2]再加上x除以2的余数，由于x/2可以通过x>>得到，并且x除以2的余数可以通过x & 1得到，所以dp[x]=dp[x>>1] + (x & 1)
     *       3. 复杂度分析
     *          时间复杂度:O(n),需要枚举n个数
     *          空间复杂度:O(1),除了返回的数组外，空间复杂度为常数
     * @param n
     * @return
     */
    public int[] solution(int n){

        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++){
            dp[i] = dp[i>>1] + (i & 1);
        }
        return dp;
    }

    public static void main(String[] args) {

        CountBits countBits = new CountBits();
        int n = 5;
        int[] bits = countBits.solution(n);
        System.out.println(Arrays.toString(bits));
    }
}
