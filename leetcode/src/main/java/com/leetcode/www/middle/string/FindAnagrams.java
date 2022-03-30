package com.leetcode.www.middle.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode438:找到字符串中所有字母异位词
 * 给定两个字符串s和p，找到s中所有p的异位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 */
public class FindAnagrams {

    /**
     * 滑动窗口:字符串p的异位词的长度是和字符串p的长度一样的，因此可以在字符串s中构造一个和字符串p的长度相同的滑动窗口，并在滑动中维护窗口中每种字母的数量;
     *         当窗口中每种字母的数量和字符串p中每种字符的数量相同时，则当前窗口为字符串p的异位词
     * 复杂度分析:
     *  时间复杂度：O(m+(n−m)×Σ)，其中n为字符串s的长度，m为字符串p的长度，Σ为所有可能的字符数。我们需要O(m) 来统计字符串p中每种字母的数量；需要O(m)来初始化
     *            滑动窗口；需要判断n−m+1 个滑动窗口中每种字母的数量是否与字符串p中每种字母的数量相同，每次判断需要O(Σ) 。因为s和p仅包含小写字母，Σ=26。
     *  空间复杂度：O(Σ)。用于存储字符串 pp 和滑动窗口中每种字母的数量。
     * @param s
     * @param p
     * @return
     */
    public List<Integer> solution(String s, String p){

        List<Integer> ans = new ArrayList<>();
        int sLen = s.length();
        int pLen = p.length();

        if (sLen < pLen){
            return ans;
        }

        int[] sCount = new int[26];
        int[] pCount = new int[26];
        for (int i = 0; i < pLen; i++){
            ++sCount[s.charAt(i) - 'a'];
            ++pCount[s.charAt(i) - 'a'];
        }

        if (Arrays.equals(sCount, pCount)){
            ans.add(0);
        }

        //滑动窗口向右滑动
        for (int i = 0; i < sLen - pLen; i++){
            --sCount[s.charAt(i) - 'a'];
            ++sCount[s.charAt(i + pLen) - 'a'];
            if (Arrays.equals(sCount, pCount)){
                ans.add(i+1);
            }
        }

        return ans;
    }

    public static void main(String[] args) {

        FindAnagrams findAnagrams = new FindAnagrams();
        String s = "cbaebabacd";
        String p = "abc";
        List<Integer> ans = findAnagrams.solution(s, p);
        System.out.println(ans);
    }
}
