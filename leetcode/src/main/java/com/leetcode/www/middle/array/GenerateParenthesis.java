package com.leetcode.www.middle.array;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode-22:括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 */
public class GenerateParenthesis {


    /**
     * 回溯法:
     * 可以生成所有2n个"("和")"字符构成的序列，然后检查每一个是否有效即可。暴力解法是使用递归，长度为n的序列就是在长度为n-1的序列前加上"("或")"。利用回溯法我们
     * 可以对这个方法进行改进:我们可以只在序列还是保持有效时才添加"("和")",而不是像暴力法每次都添加。可以通过跟踪到目前为止放置的左括号和右括号的数目来做到这一点，
     * 如果左括号不大于n，我们可以放一个左括号，如果右括号的数量小于左括号的数量，我们可以放一个右括号
     * @param n
     * @return
     */
    public List<String> solution(int n){

        List<String> ans = new ArrayList<>();
        backtrack(ans, new StringBuilder(), 0,0,n);

        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder curBuilder, int open, int close, int max){

        if (curBuilder.length() == 2 * max){
            ans.add(curBuilder.toString());
            return;
        }

        if (open < max){
            curBuilder.append('(');
            backtrack(ans, curBuilder, open + 1, close, max);
            curBuilder.deleteCharAt(curBuilder.length() - 1);
        }

        if (close < open){
            curBuilder.append(')');
            backtrack(ans, curBuilder, open, close + 1, max);
            curBuilder.deleteCharAt(curBuilder.length() - 1);
        }
    }

    public static void main(String[] args) {

        GenerateParenthesis parenthesis = new GenerateParenthesis();
        int n = 3;
        List<String> ans = parenthesis.solution(n);
        System.out.println(ans);
    }
}
