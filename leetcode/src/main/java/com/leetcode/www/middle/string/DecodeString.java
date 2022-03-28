package com.leetcode.www.middle.string;

import java.util.Collections;
import java.util.LinkedList;

/**
 * 给定一个经过编码的字符串，返回它解码后的字符串。编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，
 * 例如不会出现像3a或2[4]的输入。
 *
 */
public class DecodeString {

    public int ptr;
    /**
     * 使用栈,遍历这个字符串的字符，每个字符做不同的操作
     *      1. 当前字符为数位，解析出一个数字并进栈
     *      2. 当前字符是字母或者左括号时，直接进栈
     *      3. 当前字符是右括号时，开始出栈，一直到左括号为止，出栈序列反转后拼接成一个字符串，此时取出栈顶的数字，这个数字就是这个字符串出现的次数，根据这个次数
     *         和字符串构造出新的字符串并进栈
     *      4. 复杂度分析
     *          时间复杂度:O(S),S表示解码后得到的字符串的长度
     *          空间复杂度:O(S),S表示解码后得到的字符串长度
     * @param s
     * @return
     */
    public String solution(String s){

        LinkedList<String> stk = new LinkedList<>();
        ptr = 0;

        while (ptr < s.length()){

            char cur = s.charAt(ptr);
            //数字
            if (Character.isDigit(cur)){
                String digits = getDigits(s);
                stk.addLast(digits);
            }else if (Character.isLetter(cur) || cur == '['){
                stk.addLast(String.valueOf(s.charAt(ptr++)));
            }else {
                ++ptr;
                LinkedList<String> sub = new LinkedList<>();
                while (!"[".equals(stk.peekLast())){
                    sub.addLast(stk.removeLast());
                }
                Collections.reverse(sub);
                //左括号出栈
                stk.removeLast();
                //此时栈顶为字符串sub应出现的次数
                int reptTimes = Integer.parseInt(stk.removeLast());
                StringBuffer stringBuffer = new StringBuffer();
                String o = getString(sub);

                // 根据出现次数构造字符串
                while (reptTimes-- > 0){
                    stringBuffer.append(o);
                }
                stk.addLast(stringBuffer.toString());
            }
        }

        return getString(stk);
    }

    public String getDigits(String s){
        StringBuffer stringBuffer = new StringBuffer();
        while (Character.isDigit(s.charAt(ptr))){
            stringBuffer.append(s.charAt(ptr++));
        }

        return stringBuffer.toString();
    }

    public String getString(LinkedList<String> v){
        StringBuffer stringBuffer = new StringBuffer();
        for (String s : v){
            stringBuffer.append(s);
        }

        return stringBuffer.toString();
    }

    public static void main(String[] args) {

        DecodeString decodeString = new DecodeString();
        String s = "3[a]2[bc]";
        String s1 = "3[a2[c]]";
        String result = decodeString.solution(s1);
        System.out.println(result);
    }
}
