package com.leetcode.www.middle.string;

import java.util.ArrayList;
import java.util.List;

/**
 *leetcode-784:字母大小写全排列
 * 给定一个字符串 s ，通过将字符串 s 中的每个字母转变大小写，我们可以获得一个新的字符串。
 * 返回 所有可能得到的字符串集合 。以 任意顺序 返回输出。
 */
public class LetterCasePermutation {

    /**
     * 回溯
     * @param s
     * @return
     */
    public List<String> solution(String s){

        List<String> res = new ArrayList<>();
        char[] charArr = s.toCharArray();
        dfs(charArr, 0, res);
        return res;
    }

    private void dfs(char[] charArr, int index, List<String> res){

        if (index == charArr.length){
            res.add(new String(charArr));
            return;
        }

        // 当前字符不转变大小写
        dfs(charArr, index + 1, res);
        //当前字符转变大小写
        if (Character.isLetter(charArr[index])){
            charArr[index] ^= 1 << 5;
            dfs(charArr, index + 1, res);
        }
    }

    public static void main(String[] args) {

        LetterCasePermutation permutation = new LetterCasePermutation();
        String s = "a1b2";
        List<String> ans = permutation.solution(s);
        System.out.println(ans);
    }
}
