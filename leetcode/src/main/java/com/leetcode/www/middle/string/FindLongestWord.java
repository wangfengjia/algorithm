package com.leetcode.www.middle.string;

import java.util.*;

/**
 * leetcode-524:通过删除字母匹配到字典里最长单词
 * 给你一个字符串 s 和一个字符串数组 dictionary ，找出并返回dictionary 中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。
 * 如果答案不止一个，返回长度最长且字母序最小的字符串。如果答案不存在，则返回空字符串。
 */
public class FindLongestWord {


    /**
     * 排序+双指针+贪心:题目要求的是要找到dict中为s的子序列，且长度最长和字典序最小的字符串，因此我们可以先对字典进行排序，排序的规则是
     *              1. 长度不同的字符串，先按照字符串长度排倒序
     *              2. 长度相同的字符串，按照字典序升序排序
     *           排完序以后，利用"双指针"来查找，在dict中第一个找到满足条件的字符串就是答案
     *              1. 使用left和right两个指针分别表示检查到s和dict[i]中的哪个字符
     *              2. 如果s[left] != dict[i][right],则left指针右移;如果s[left]=dict[i][right],则left和right指针都右移
     *              3. 对dict中的每个字符串重复第2个步骤，直到找到第一个满足条件的字符串
     * 复杂度分析
     *  时间复杂度:O(mlog^m + m * n),n表示字符串s的长度，m表示dict的长度，O(mlog^m)是排序的时间复杂度，O(m*n)是利用双指针进行匹配的时间复杂度
     *  空间复杂度:O(log^m),m表示dict的长度，排序所使用的栈空间
     * @param s
     * @param dict
     * @return
     */
    public String solution(String s, List<String> dict){

        Collections.sort(dict, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                if (a.length() != b.length()){
                    return b.length() - a.length();
                }
                return a.compareTo(b);
            }
        });

        String ans = "";
        int sLen = s.length();
        for (String s1 : dict){
            int left = 0;
            int right = 0;
            int s1Len = s1.length();
            while (left < sLen && right < s1Len){
                if (s.charAt(left) == s1.charAt(right)){
                    ++right;
                }
                ++left;
            }
            if (right == s1Len){
                ans = s1;
                break;
            }
        }

        return ans;
    }


    /**
     * 动态规划:由于在上一个方法中，花费了大量的时间在s中找到下一个满足条件的字符，我们可以通过预处理得到:对于s中的每一个位置，从这个位置起始往后每一个字符第一次
     *         出现的位置，可以通过动态规划的方法来实现预处理
     *         1. 状态定义:dp[i][j]表示字符串s中从第i个位置开始往后字符j第一次出现的位置
     *         2. 状态转移方程:
     *              1. s[i] = j时,dp[i][j] = i
     *              2. s[i] != j时，j出现的位置就会在i+1往后，所以dp[i][j] = dp[i+1][j],因此需要倒过来进行动态规划，从后往前枚举i
     *         3. 边界条件:dp[m][..] = m,这样让dp[m-1][...]正常进行状态转移，如果dp[i][j] = m,则表示从位置i开始往后不存在字符j,m表示s的长度
     * 复杂度分析
     *  时间复杂度:O(m×∣Σ∣+d×n),d表示list的长度，Σ表示字符集，本题中只有英文小写字母，所以Σ等于26，m表示字符串s的长度，n表示list中字符串的平均长度，
     *           预处理的时间复杂度为O(m×∣Σ∣),判断d个字符串是否是s的字符串的时间复杂度为O(d*n)
     *  空间复杂度:O(m×∣Σ∣),dp数组所使用的空间
     * @param s
     * @param list
     * @return
     */
    public String solutionV2(String s, List<String> list){

        int m = s.length();
        int[][] dp = new int[m+1][26];
        Arrays.fill(dp[m], m);

        for (int i = m - 1; i >= 0; i--){
            for (int j = 0; j < 26; j++){
                if (s.charAt(i) == j + 'a'){
                    dp[i][j] = i;
                }else {
                    dp[i][j] = dp[i+1][j];
                }
            }
        }

        String ans = "";
        for (String s1 : list){
            boolean match = true;
            int j = 0;
            for (int i = 0; i < s1.length(); i++){
                if (dp[j][s1.charAt(i) - 'a'] == m){
                    match = false;
                    break;
                }
                j = dp[j][s1.charAt(i) - 'a'] + 1;
            }
            if (match){
                if (s1.length() > ans.length() || (s1.length() == ans.length() && s1.compareTo(ans) < 0)){
                    ans = s1;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {

        FindLongestWord findLongestWord = new FindLongestWord();
        String s = "abpcplea";
        List<String> dict = new ArrayList<>();
        dict.add("a");
        dict.add("b");
        dict.add("c");
        dict.add("e");

        String ans = findLongestWord.solution(s, dict);
        System.out.println(ans);
    }
}
