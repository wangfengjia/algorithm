package com.leetcode.www.middle.dynamic;

/**
 * leetcode-1143:最长公共子序列
 * 给定两个字符串text1和text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * 一个字符串的子序列是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 */
public class LongestCommonSubsequence {


    /**
     * 动态规划:
     *      1. 状态定义:dp[i][j]表示s1[0:i]和s2[0:j]的最长公共子序列的长度，s1[0:i]表示s1的长度为i的前缀,s2[0:j]表示s2长度为j的前缀
     *      2. 状态转移方程:dp[i][j]可以由dp[i-1][j-1],dp[i-1][j],dp[i][j-1]三个状态转移得到
     *          1. 当s1[i-1] = s2[j-1]时，dp[i][j] = dp[i-1][j-1] + 1
     *          2. 当s1[i-1] != s2[j-1]时，dp[i][j] = max(dp[i-1][j], dp[i][j-1])
     *      3. 边界状态:当i或者j等于0时，dp[i][j] = 0
     * 复杂度分析
     *  时间复杂度:O(m*n),m,n分别表示字符串s1和s2的长度
     *  空间复杂度:O(m*n),m,n分别表示字符串s1和s2的长度
     * @param s1
     * @param s2
     * @return
     */
    public int solution(String s1, String s2){

        int m = s1.length();
        int n = s2.length();
        //需求求得dp[m][n]的状态值，所以状态数组大小是m+1和n+1
        int[][] dp = new int[m+1][n+1];
        for (int i = 1; i <= m; i++){
            char c1 = s1.charAt(i - 1);
            for (int j = 1; j <= n; j++){
                char c2 = s2.charAt(j - 1);
                if (c1 == c2){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {

        LongestCommonSubsequence subsequence = new LongestCommonSubsequence();
        String s1 = "abc";
        String s2 = "def";
        int ans = subsequence.solution(s1, s2);
        System.out.println(ans);
    }
}
