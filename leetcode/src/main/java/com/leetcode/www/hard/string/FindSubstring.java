package com.leetcode.www.hard.string;

import java.util.*;

/**
 * leetcode-30:串联所有单词的子串
 * 给定一个字符串s和一些长度相同的单词words 。找出 s 中恰好可以由words 中所有单词串联形成的子串的起始位置。注意子串要与words 中的单词完全匹配，中间不能有
 * 其他字符 ，但不需要考虑words中单词串联的顺序。
 */
public class FindSubstring {


    /**
     * 滑动窗口:由于words数组里面的字符串的长度相等，可以使用一个滑动窗口，这个滑动窗口的right指针每次移动word数组里面字符串的长度。然后去比较移动right指针
     * 产生的字符串w在words数组里面是否存在，比较的方法是:利用哈希表h1去统计words中每个单词出现的次数，然后用字符串w来这个哈希表查找。同时使用另外一个哈希表h2
     * 来统计在滑动窗口中，每个字符串出现的次数，用于保证words中的每个字符串在滑动窗口中出现一次
     * @param s
     * @param words
     * @return
     */
    public List<Integer> solution(String s, String[] words){

        List<Integer> ans = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0){
            return ans;
        }

        Map<String, Integer> wordsMap = new HashMap<>();
        for (String word : words){
            wordsMap.put(word, wordsMap.getOrDefault(word, 0) + 1);
        }

        int oneWordLength = words[0].length();
        int wordNum = words.length;

        // 只需要遍历oneWordLength种起点:0..oneWordLength-1,这是由于滑动窗口右指针每次移动的长度是oneWordLength，s字符串后续的下标为窗口left指针都可以
        //通过0..oneWordLength-1下标每次移动oneWordLength到达
        for (int i = 0; i < oneWordLength; i++){
            int left = i;
            int right = i;
            int count = 0;
            Map<String, Integer> windowsMap = new HashMap<>();
            while (right + oneWordLength <= s.length()){
                String w = s.substring(right, right + oneWordLength);
                right += oneWordLength;
                if (!wordsMap.containsKey(w)){
                    left = right;
                    count = 0;
                    windowsMap.clear();
                }else {
                    windowsMap.put(w, windowsMap.getOrDefault(w, 0) + 1);
                    ++count;
                    while (windowsMap.getOrDefault(w, 0) > windowsMap.getOrDefault(w, 0)){
                        String tw = s.substring(left, left + oneWordLength);
                        --count;
                        windowsMap.put(tw, windowsMap.getOrDefault(tw, 0) - 1);
                        left += oneWordLength;
                    }
                    if (count == wordNum){
                        ans.add(left);
                    }
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {

        FindSubstring substring = new FindSubstring();
        String s = "barfoothefoobarman";
        String[] words = {"foo", "bar"};
        List<Integer> ans = substring.solution(s, words);
        System.out.println(Arrays.toString(ans.toArray()));
    }
}
