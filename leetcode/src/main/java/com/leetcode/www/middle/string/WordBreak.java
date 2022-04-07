package com.leetcode.www.middle.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * leetcode-139. 单词拆分
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。注意：不要求字典中出现的单词全部都使用，并且字典中的
 * 单词可以重复使用。
 */
public class WordBreak {

    /**
     * 动态规划
     *      1. 状态定义:dp[i]表示字符串s的前i个字符组成的字符串s[0..i-1]能否被空格拆成若干个字典中出现的单词
     *      2. 状态转移方程:s[0..i-1]有i个分隔点j，分隔后将字符串分成2个字符串，分别是s[0...j-1]组成的字符串s1,和s[j..i-1]组成的字符串s2,如果s1和s2
     *         在字典中都可以找到，那么s1和s2拼接而成的字符串就是合法的。同时，由于计算到dp[i]时，我们已经知道dp[0..i-1]的值，所以字符串s1是否合法可以通过
     *         由dp[j]得知，然后再判断s[j..i-1]是否合法即可，
     *              dp[i] = dp[j] && check(s[j..i-1])
     *      3. 边界条件:dp[0] = true
     * @param s
     * @param words
     * @return
     */
    public boolean solution(String s, List<String> words){

        Set<String> wordsSet = new HashSet<>(words);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++){
            for (int j = 0; j < i; j++){
                if (dp[j] && wordsSet.contains(s.substring(j, i))){
                    dp[i] = true;
                }
            }
        }

        return dp[s.length()];
    }


    public static void main(String[] args) {

        WordBreak wordBreak = new WordBreak();
        String s = "leetcode";
        List<String> words = new ArrayList<>();
        words.add("let");
        words.add("code");

        boolean ans = wordBreak.solution(s, words);
        System.out.println(ans);
    }
}

