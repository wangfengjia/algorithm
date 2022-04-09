package com.leetcode.www.hard.string;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * leetcode76-最小覆盖子串
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * 注意：
 *      对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 *      如果 s 中存在这样的子串，我们保证它是唯一的答案。
 */
public class MinimumWindowSubstring {


    private Map<Character, Integer> originMap = new HashMap<>();
    private Map<Character, Integer> targetMap = new HashMap<>();
    /**
     * 滑动窗口:使用left和right指针构成的滑动窗口在origin字符串中查找target，查找的方法是:right指针不断向右边扩展，当窗口包含target字符串时，判断能否收缩
     * 这个滑动窗口(通过移动left指针)。判断窗口是否包含target字符串的方法是:利用哈希表h1统计好target字符串中每个字符出现的次数，和利用哈希表h2统计滑动窗口中
     * 每个字符串出现的次数，只要target字符串中每个字符在哈希表h2中出现的次数都不小于在h1中出现的次数，则这个窗口包含目标字符串
     * @param origin
     * @param target
     * @return
     */
    public String solution(String origin, String target){

        int tLen = target.length();
        for (int i = 0; i < tLen; i++){
            char c = target.charAt(i);
            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
        }

        int left = 0;
        int right = -1;
        int ansLeft = -1;
        int ansRight = -1;
        int sLen = origin.length();
        int len = Integer.MAX_VALUE;

        while (right < sLen){
            ++right;
            if (right < sLen && targetMap.containsKey(origin.charAt(right))){
                originMap.put(origin.charAt(right), originMap.getOrDefault(origin.charAt(right), 0) + 1);
            }

            //尝试能不能收缩窗口
            while (check() && left <= right){
                //更小的窗口
                if (right - left + 1 < len){
                    len = right - left + 1;
                    ansLeft = left;
                    ansRight = left + len;
                }
                if (targetMap.containsKey(origin.charAt(left))){
                    originMap.put(origin.charAt(left), originMap.getOrDefault(origin.charAt(left), 0) - 1);
                }
                ++left;
            }
        }

        return ansLeft == -1 ? "" : origin.substring(ansLeft, ansRight);
    }

    private boolean check(){

        Iterator<Map.Entry<Character, Integer>> iterator = targetMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Character, Integer> next = iterator.next();
            Character key = next.getKey();
            Integer value = next.getValue();
            if (originMap.getOrDefault(key, 0) < value){
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        MinimumWindowSubstring windowSubstring = new MinimumWindowSubstring();
        String origin = "ADOBECODEBANC";
        String target = "ABC";
        String ans = windowSubstring.solution(origin, target);
        System.out.println(ans);
    }
}
