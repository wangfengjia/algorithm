package com.leetcode.www.hard.dynamic;

/**
 * leetcode-115:不同的子序列
 * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
 * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE"是"ABCDE"的一个子序列，而"AEC"不是）
 * 题目数据保证答案符合 32 位带符号整数范围。
 */
public class NumDistinct {

    /**
     * 动态规划:字符串s1的长度为m,字符串s2的长度为n,核心思路是:在字符串s1中选择字符，去匹配s2的字符，s1照着s2来挑选，逐字符考察选与不选，分别到达什么状态
     *      1. 状态定义:dp[i][j]表示在s1[i:]的子序列中s2[j:]出现的个数，s1[i:]表示s1从下标i到末尾的子字符串,s2[j:]表示s2从下标j到末尾的子字符串
     *      2. 状态转移方程:i<m && j <n时
     *          1. 当s1[i]=s2[j]时，dp[i][j]分为两部分
     *              1. s1[i]与s2[j]匹配，则考虑s2[j+1]作为s1[i+1]的子序列，则dp[i][j] = dp[i+1][j+1]
     *              2. s1[i]不和s2[j]匹配，则考虑s2[j:]作为s1[i+1:]的子序列,dp[i][j] = dp[i+1][j]
     *            因此dp[i][j] = dp[i+1][j+1] + dp[i+1][j]
     *          2. 当s1[i]!=s2[j]时，s1[i]不能和s2[j]匹配，则只考虑s2[j:]作为s1[i+1]的子序列，dp[i][j] = dp[i+1][j]
     *      3. 边界情况
     *          1. 当j=n时，s2[j:]为空字符串，由于空字符串是任何字符串的子序列，因此对任意的0<=i<=m,dp[i][n] = 1
     *          2. 当i=m时，s1[i:]为空字符串，s2[j:]为非空字符串,由于非空字符串不会是空字符串的子序列，因此对于任意的0<=j<n,dp[m][j]=0
     * @param s1
     * @param s2
     * @return
     */
    public int solution(String s1, String s2){

        int m = s1.length();
        int n = s2.length();
        if (n > m){
            return 0;
        }

        int[][] dp = new int[m+1][n+1];
        for (int i = 0; i <= m; i++){
            dp[i][n] = 1;
        }

        for (int i = m - 1; i >= 0; i--){
            char sChar = s1.charAt(i);
            for (int j = n - 1; j >= 0; j--){
                char tChar = s2.charAt(j);
                if (sChar == tChar){
                    dp[i][j] = dp[i+1][j+1] + dp[i+1][j];
                }else {
                    dp[i][j] = dp[i+1][j];
                }
            }
        }

        return dp[0][0];
    }


    public static void main(String[] args) {

        NumDistinct numDistinct = new NumDistinct();
        String s1 = "babgbag";
        String s2 = "bag";
        int ans = numDistinct.solution(s1, s2);
        System.out.println(ans);

    }
}
