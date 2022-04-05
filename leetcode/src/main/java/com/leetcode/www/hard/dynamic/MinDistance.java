package com.leetcode.www.hard.dynamic;

/**
 * leetcode-72:编辑距离
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数。可以对一个单词进行三种操作:插入、删除、替换一个字符
 */
public class MinDistance {


    /**
     * 由于对一个单词可以有3种操作，题目给定了2个单词A,B，这样就能够有6种操作。但是由于对单词A删除一个字符和对单词B添加一个字符是等价的;对单词B删除一个字符和对单词
     * A添加一个字符是等价的;对单词A替换一个字符和对单词B替换一个字符是等价的。因此本质不同的操作只有3种:在单词A插入一个字符，对单词B插入一个字符，修改单词A的一个字符
     * 动态规划解法:
     *      1. 状态定义:dp[i][j]表示A的前i个字符和B的前j个字符之间的编辑距离
     *      2. 状态转移方程
     *          有如下几种状态转移的方式
     *          1. dp[i][j-1] -> dp[i][j]:dp[i][j-1]为A的前i个字符和B的前j-1个字符的编辑距离，对于B的第j个字符，我们可以在A的末尾添加一个相同的字符，
     *             此时dp[i][j] = dp[i][j-1] + 1
     *          2. dp[i-1][j] -> dp[i][j]:dp[i-1][j]为A的前i-1个字符和B的前j个字符的编辑距离，对于A的第i个字符，我们可以在B的末尾添加一个相同的字符，
     *             此时dp[i][j] = dp[i-1][j]+1
     *          3. dp[i-1][j-1] -> dp[i][j]:dp[i-1][j-1]为A的前i-1个字符和B的前j-1个字符的编辑距离。对于B的第j个字符，我们可以修改A的第i个字符，使它们相同。
     *             这边也有两种情况:当A的第i个字符和B的第j个字符相同时，dp[i][j] = dp[i-1][j-1];当它们俩不相同时，dp[i][j] = dp[i-1][j-1] + 1
     *          由上可知状态转移方程为
     *          1. 当A和B的最后一个字符相同时，dp[i][j] = min(dp[i][j-1] + 1, dp[i-1][j] + 1, dp[i-1][j-1]) = 1 + min(dp[i][j-1], dp[i-1][j], dp[i-1][j-1] - 1)
     *          2. 当A和B的最后一个字符不同时，dp[i][j] = min(dp[i][j-1] + 1, dp[i-1][j] + 1, dp[i-1][j-1] + 1) = 1+min(dp[i][j-1], dp[i-1][j], dp[i-1][j-1])
     * 复杂度分析
     *  1. 时间复杂度:O(mn),m为word1的长度，n为word2的长度
     *  2. 空间复杂度:O(mn), 状态数组dp所占用的空间
     * @param word1
     * @param word2
     * @return
     */
    public int soution(String word1, String word2){


        int n = word1.length();
        int m = word2.length();

        // 有一个字符串为空
        if (n * m == 0){
            return n + m;
        }

        int[][] dp = new int[n + 1][m + 1];
        //边界状态初始化
        for (int i = 0; i < n; i++){
            dp[i][0] = i;
        }
        for (int j = 0; j < m; j++){
            dp[0][j] = j;
        }

        for (int i = 1; i < n + 1; i++){
            for (int j = 1; j < m + 1; j++){
                // B上添加A的第i个字符
                int left = dp[i-1][j] + 1;
                // A上添加B的第j个字符
                int right = dp[i][j - 1] + 1;
                int leftDown = dp[i-1][j-1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)){
                    ++leftDown;
                }

                dp[i][j] = Math.min(left, Math.min(right, leftDown));
            }
        }

        return dp[n][m];
    }

    public static void main(String[] args) {

        MinDistance minDistance = new MinDistance();
        String word1 = "intention";
        String word2 = "execution";
        int ans = minDistance.soution(word1, word2);
        System.out.println(ans);
    }
}
