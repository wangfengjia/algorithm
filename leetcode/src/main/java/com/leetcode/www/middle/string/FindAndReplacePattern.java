package com.leetcode.www.middle.string;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * leetcode-890:查找和替换模式
 * 你有一个单词列表words和一个模式pattern，你想知道 words 中的哪些单词与模式匹配。
 * 如果存在字母的排列 p，使得将模式中的每个字母 x 替换为 p(x) 之后，我们就得到了所需的单词，那么单词与模式是匹配的。
 * （回想一下，字母的排列是从字母到字母的双射：每个字母映射到另一个字母，没有两个字母映射到同一个字母。）
 * 返回 words 中与给定模式匹配的单词列表。
 * 你可以按任何顺序返回答案。
 */
public class FindAndReplacePattern {


    /**
     * 哈希表
     * 根据题意，我们需要构造从字母到字母的映射，即word的每个字母需要映射到pattern对应的每个字母，并且pattern的每个字母需要映射到word的对应字母。
     * 可以通过遍历这两个字符串，用一个哈希表记录word的每个字母x需要映射到pattern的哪个字母上，如果x已有映射，则需要检查对应的字母是否相同。只有word中的
     * 相同字母映射到pattern的相同字母才成立
     *
     * 复杂度分析
     *  时间复杂度:O(m*n),m是数组words的长度，n是pattern的长度。对于每个word需要O(n)的时间来检查其是否和pattern匹配
     *  空间复杂度:O(n),哈希表需要O(n)的空间
     * @param words
     * @param pattern
     * @return
     */
    public List<String> solution(String[] words, String pattern){

        List<String> ans = new ArrayList<>();
        for (String word : words){
            if (match(word, pattern) && match(pattern, word)){
                ans.add(word);
            }
        }

        return ans;
    }


    private boolean match(String word, String pattern){

        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < word.length(); i++){
            char x = word.charAt(i);
            char y = pattern.charAt(i);
            if (!map.containsKey(x)){
                map.put(x, y);
            }else if (map.get(x) != y){ // word中的同一个字母必须映射到pattern的同一个字母
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        FindAndReplacePattern replacePattern = new FindAndReplacePattern();
        String[] words = {"abc","deq","mee","aqq","dkd","ccc"};
        String pattern = "abb";
        List<String> ans = replacePattern.solution(words, pattern);
        System.out.println(ans);
    }
}
