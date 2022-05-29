package com.leetcode.www.middle.string;


import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * leetcode-767:重构字符串
 * 给定一个字符串 s ，检查是否能重新排布其中的字母，使得两相邻的字符不同。
 * 返回 s 的任意可能的重新排列。若不可行，返回空字符串 "" 。
 */
public class ReorganizeString {


    /**
     * 基于最大堆的贪心
     *
     * 题目分析
     * 重构字符串时，需要根据每个字母在字符串中次数处理每个字母放置的位置。如果出现次数最多的字母在重新排布后不相邻，则可以重新排布字母使得相邻的字母都不相同。
     * 如果出现次数最多的字母过多，则无法重新排布字母使得相邻的字母都不相同，每个字母最多只能出现(n+1)/2次，超过这个次数则无法重新排布字母使得相同的
     *
     * 实现
     * 使用最大堆存储字母，堆顶为出现次数最多的字母。首先统计每个字母的出现次数，然后将出现次数大于0的字母加到最大堆。当最大堆里面的元素个数大于1时，每次从最大堆
     * 取出两个字母，拼接到重构的字符串，然后将这两个字母出现的次数减去1，再将出现次数大于0的字母重新加入最大堆。
     * 如果最大堆为空，则已经完成字符串的重构，如果最大堆剩下1个元素，则取出最后一个字母,拼接到重构的字符串
     *
     * 复杂度分析
     *  时间复杂度:O(nlog^∣Σ∣ + ∣Σ∣),n是字符串s的长度，Σ是字符集，在这个题目中字符集为所有小写字母，所以∣Σ∣ = 26
     *  空间复杂度:O(∣Σ∣),不统计存储最终答案字符串需要的空间,空间复杂度主要取决于统计每个字母出现的次数的数组和优先级队列
     * @param s
     * @return
     */
    public String solution(String s){

        if (s.length() < 2){
            return s;
        }

        int[] charCnt = new int[26];
        int maxCnt = 0;
        int len = s.length();

        for (int i = 0; i < len; i++){
            charCnt[s.charAt(i) - 'a']++;
            maxCnt = Math.max(maxCnt, charCnt[s.charAt(i) - 'a']);
        }

        if (maxCnt > (len + 1) / 2){
            return "";
        }

        PriorityQueue<Character> queue = new PriorityQueue<>(new Comparator<Character>() {
            @Override
            public int compare(Character letter1, Character letter2) {
                return charCnt[letter2 - 'a'] - charCnt[letter1 - 'a'];
            }
        });

        //将出现次数大于0的字母添加到最大堆
        for (char c = 'a'; c <= 'z'; c++){
            if (charCnt[c - 'a'] > 0){
                queue.offer(c);
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        while (queue.size() > 1){
            char c1 = queue.poll();
            char c2 = queue.poll();
            stringBuilder.append(c1);
            stringBuilder.append(c2);
            int c1Index = c1 - 'a';
            int c2Index = c2 - 'a';
            charCnt[c1Index]--;
            charCnt[c2Index]--;
            if (charCnt[c1Index] > 0){
                queue.offer(c1);
            }
            if (charCnt[c2Index] > 0){
                queue.offer(c2);
            }
        }
        if (queue.size() > 0){
            stringBuilder.append(queue.poll());
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {

        ReorganizeString reorganizeString = new ReorganizeString();
        String s = "aab";
        String ans = reorganizeString.solution(s);
        System.out.println(ans);
    }
}
