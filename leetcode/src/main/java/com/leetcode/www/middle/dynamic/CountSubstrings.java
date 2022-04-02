package com.leetcode.www.middle.dynamic;

/**
 * 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。 回文字符串 是正着读和倒过来读一样的字符串。子字符串 是字符串中的由连续字符组成的一个序列。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 *
 */
public class CountSubstrings {


    /**
     * 动态规划
     *      1. 状态定义:dp[i][j]表示字符串在区间[i,j]的字符串是否是一个回文串
     *      2. 状态转移方程
     *          1. 当s[i] == s[j] && ((j - i) < 2 || dp[i+1][j-1])时，dp[i][j] = true。长度小于等于2且相等，那肯定是一个回文串。当子串s[i+1..j-1]
     *          是一个回文串，并且s[i] == s[j],那么子串s[i..j]也是一个回文串。例如"aba"是一个回文串,则"aabaa"也是一个回文串
     *      3. 复杂度分析
     *          时间复杂度:O(N^2),两层遍历
     *          空间复杂度:O(N^2),状态数组所消耗的空间
     * @param s
     * @return
     */
    public int solution(String s){

        boolean[][] dp = new boolean[s.length()][s.length()];
        int ans = 0;

        for (int i = 0; i < s.length(); i++){
            for (int j = 0; j <= i; j++){
                if ((s.charAt(i) == s.charAt(j)) && ((j - i < 2) || dp[i+1][j-1])){
                    dp[i][j] = true;
                    ++ans;
                }
            }
        }

        return ans;
    }

    /**
     * 通过枚举出所有的回文串就可以得到有多少数量的回文串，枚举回文串可以通过中心扩展法来实现
     * 中心扩展:枚举每一个可能的回文中心，然后用两个指针分别分别向左右两边拓展，当两个指针指向的字符相同时就继续扩展，否则就停止。当回文串长度为奇数时，回文中心就是
     *         一个字符，当回文串长度为偶数时，回文中心就是两个字符。长度为n的字符串会生成2n-1组回文中心(left,right),其中left = i / 2,right=left+(i % 2)
     *         这样我们只需要从0到2n-2遍历i就可以得到所有的回文中心,这样就能够把奇数长度和偶数长度两种情况统一起来
     * 复杂度分析
     *  1. 时间复杂度:O(N^2)
     *  2. 空间复杂度:O(1)
     * @param s
     * @return
     */
    public int solutionV2(String s){

        int n = s.length();
        int ans = 0;
        for (int i = 0; i < 2 * n - 1; i++){

            int left = i / 2;
            int right = i / 2 + i % 2;
            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)){
                --left;
                ++right;
                ++ans;
            }
        }

        return ans;
    }
    public static void main(String[] args) {

        CountSubstrings substrings = new CountSubstrings();
        String s = "aaa";
        int ans = substrings.solutionV2(s);
        System.out.println(ans);
    }
}
