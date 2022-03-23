package com.leetcode.www.middle.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按任意顺序返回。数字和字母之间有映射关系
 * 思路(回溯):首先使用哈希表存储每个数字对应的所有可能的字母，然后进行回溯操作。回溯过程中维护一个字符串，表示已有的字母排列（如果未遍历完电话号码的所有数字，
 * 则已有的字母排列是不完整的）。该字符串初始为空。每次取电话号码的一位数字，从哈希表中获得该数字对应的所有可能的字母，并将其中的一个字母插入到已有的字母排列后面，
 * 然后继续处理电话号码的后一位数字，直到处理完电话号码中的所有数字，即得到一个完整的字母排列。然后进行回退操作，遍历其余的字母排列
 *
 */
public class LetterCombinations {

    public Map<Character, String> map;

    public List<String> solution(String digits){

        List<String> combinations = new ArrayList<>();
        if (digits.length() == 0){
            return combinations;
        }

        map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        backtrack(combinations, digits, 0, new StringBuffer());
        return combinations;
    }

    public void backtrack(List<String> combinations, String digits, int index, StringBuffer combination){

        if (index == digits.length()){
            combinations.add(combination.toString());
        }else {
            char c = digits.charAt(index);
            String s = map.get(c);
            for (int i = 0; i < s.length(); i++){
                combination.append(s.charAt(i));
                backtrack(combinations, digits, index + 1, combination);
                //撤销这一步的选择
                combination.deleteCharAt(combination.length() - 1);
            }
        }
    }

    public static void main(String[] args) {

        String digits = "23";
        LetterCombinations combinations = new LetterCombinations();
        List<String> stringList = combinations.solution(digits);
        System.out.println(stringList);
    }
}
