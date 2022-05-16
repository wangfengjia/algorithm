package com.leetcode.www.middle.string;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * leetcode-1190:反转每对括号间的子串
 * 给出一个字符串s（仅含有小写英文字母和括号）。
 * 请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
 * 注意，您的结果中 不应 包含任何括号。
 */
public class ReverseParentheses {

    /**
     * 双端队列:实现步骤如下
     *      1. 从前往后遍历字符串，将不是')'的字符添加到队列尾部
     *      2. 当遇到')'字符时，从队列尾部取出字符，直到遇到'('为止，并对取出的字符串进行翻转
     *      3. 把翻转完成后的字符串添加到队列尾部
     *      4. 循环上述过程，直到原字符串全部取出
     * 复杂度分析
     *  时间复杂度:O(n^2),每个字符'('只会进入队列一次，而')'不会进入队列。普通字符进出队列的次数取决于右边的')',最坏情况下每个字符右边都是右括号，所以时间复杂度
     *           为O(n^2)
     *  空间复杂度:O(n),n是字符串s的长度
     * @param s
     * @return
     */
    public String solution(String s){

        int n = s.length();
        char[] chars = s.toCharArray();
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++){
            char c = chars[i];
            if (c == ')'){
                StringBuilder tmp = new StringBuilder();
                while (!deque.isEmpty()){
                    if (deque.peekLast() != '('){
                        tmp.append(deque.pollLast());
                    }else {
                        deque.pollLast();
                        for (int j = 0; j < tmp.length(); j++){
                            deque.addLast(tmp.charAt(j));
                        }
                        break;
                    }
                }
            }else {
                deque.addLast(c);
            }
        }

        StringBuilder builder = new StringBuilder();
        while (!deque.isEmpty()){
            builder.append(deque.pollFirst());
        }

        return builder.toString();
    }


    /**
     * 预处理括号:
     * 复杂度分析
     *  时间复杂度:O(n),n是字符串s的长度，预处理括号的时间复杂度为O(n),后续遍历字符串的时间复杂度也是O(n)
     *  空间复杂度:O(n),n是字符串s的长度，栈所使用的空间不会超过O(n)
     * @param s
     * @return
     */
    public String solutionV2(String s){

        int n = s.length();
        int[] pair = new int[n];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n; i++){
            if (s.charAt(i) == '('){
                stack.push(i);
            }else if (s.charAt(i) == ')'){
                Integer j = stack.pop();
                pair[i] = j;
                pair[j] = i;
            }
        }

        StringBuilder builder = new StringBuilder();
        int index = 0;
        int step = 1;
        while (index < n){
            if (s.charAt(index) == '(' || s.charAt(index) == ')'){
                index = pair[index];
                step = -step;
            }else {
                builder.append(s.charAt(index));
            }
            index += step;
        }

        return builder.toString();
    }

    public static void main(String[] args) {

        ReverseParentheses reverseParentheses = new ReverseParentheses();
        String s = "(u(love)i)";
        String ans = reverseParentheses.solution(s);
        System.out.println(ans);
    }
}
