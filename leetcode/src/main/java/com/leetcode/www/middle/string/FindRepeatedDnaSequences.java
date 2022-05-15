package com.leetcode.www.middle.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * leetcode-187:重复的DNA序列
 * DNA序列由一系列核苷酸组成，缩写为'A','C','G'和'T'.。
     * 例如，"ACGAATTCCG"是一个 DNA序列 。
     * 在研究 DNA 时，识别 DNA 中的重复序列非常有用。
 * 给定一个表示 DNA序列 的字符串 s ，返回所有在 DNA 分子中出现不止一次的长度为10的序列(子字符串)。你可以按 任意顺序 返回答案。
 */
public class FindRepeatedDnaSequences {

    private final int L = 10;
    /**
     * 滑动窗口+哈希表:从左往右处理字符串s，使用滑动窗口得到每个以s[i]结尾的长度为10的子串，同时使用哈希表记录每个子串出现的次数，如果子串出现的次数超过1，就
     *              加入答案
     * 复杂度分析
     *  时间复杂度:O(NL),N是字符串s的长度
     *  空间复杂度:O(NL)
     * @param s
     * @return
     */
    public List<String> solution(String s){

        int n = s.length();
        List<String> ans = new ArrayList<>();
        Map<String, Integer> cntMap = new HashMap<>();
        for (int i = 0; i <= n - L; i++){
            String sub = s.substring(i, i + 10);
            Integer count = cntMap.getOrDefault(sub, 0);
            if (count == 1){
                ans.add(sub);
            }
            cntMap.put(sub, count+1);
        }

        return ans;
    }

    public static void main(String[] args) {

        FindRepeatedDnaSequences sequences = new FindRepeatedDnaSequences();
        String s = "AAAAAAAAAAAAA";
        List<String> ans = sequences.solution(s);
        System.out.println(ans);
    }
}
