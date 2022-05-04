package com.leetcode.www.middle.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * leetcode-763:划分字母区间
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。返回一个表示每个字符串片段的长度的列表。
 */
public class PartitionLabels {

    /**
     * 使用哈希表统计每个字符最后出现的位置，再遍历一遍数组，对于每个字符在哈希表中得到最后出现的位置，同时维护一个已经枚举过的字符的最后一个位置的最大值，当枚举
     * 到的下标等于这个最大值时，就分隔为一个片段,在枚举过程中统计每个片段的长度
     * 复杂度分析
     *  时间复杂度:O(n), n表示字符串的长度
     *  空间复杂度:O(|Σ∣),Σ是字符串中的字符集。
     * @param s
     * @return
     */
    public List<Integer> solution(String s){

        Map<Character, Integer> lastPosMap = new HashMap<>();
        int length = s.length();
        for (int i = 0; i < length; i++){
            char c = s.charAt(i);
            lastPosMap.put(c, i);
        }

        List<Integer> ans = new ArrayList<>();
        int len = 0;
        int curMaxPos = 0;
        for (int i = 0; i < length; i++){
            char c = s.charAt(i);
            Integer pos = lastPosMap.get(c);
            curMaxPos = Math.max(pos, curMaxPos);
            ++len;
            if (curMaxPos == i){
                ans.add(len);
                len = 0;
            }
        }
        return ans;
    }

    public static void main(String[] args) {

        PartitionLabels partitionLabels = new PartitionLabels();
        String s = "ababcbacadefegdehijhklij";
        List<Integer> ans = partitionLabels.solution(s);
        System.out.println(ans);
    }
}
