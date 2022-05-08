package com.leetcode.www.middle.string;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * leetcode-151:颠倒字符串中的单词
 * 给你一个字符串s，颠倒字符串中单词的顺序。
 * 单词是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 * 返回单词顺序颠倒且单词之间用单个空格连接的结果字符串。
 * 注意：输入字符串s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
 *
 */
public class ReverseWords {


    /**
     * 先删除字符串头尾空白字符和中间多余的空白字符，再翻转整个字符串，最后翻转每个单词
     * 复杂度分析
     *  时间复杂度:O(n),n为字符串s的长度
     *  空间复杂度:O(n), 需要O(N)来存储字符串
     * @param s
     * @return
     */
    public String solutionV1(String s){

        //去除空白字符
        StringBuilder builder = trimSpaces(s);

        //翻转整个字符串
        reverse(builder, 0, builder.length() - 1);

        //翻转每个单词
        reverseWord(builder);

        return builder.toString();
    }


    private StringBuilder trimSpaces(String s){

        int left = 0;
        int right = s.length() - 1;

        //去除字符串开头的空白字符
        while (left <= right && s.charAt(left) == ' '){
            ++left;
        }
        //去除字符串尾部的空白字符
        while (left <= right && s.charAt(right) == ' '){
            --right;
        }

        //去除字符串中间的多余空白字符
        StringBuilder builder = new StringBuilder();
        while (left <= right){
            char c = s.charAt(left);
            if (c != ' '){
                builder.append(c);
            }else if (builder.charAt(builder.length() - 1) != ' '){
                builder.append(c);
            }
            ++left;
        }

        return builder;
    }

    private void reverse(StringBuilder s, int start, int end){

        while (start < end){
            char c = s.charAt(start);
            s.setCharAt(start, s.charAt(end));
            s.setCharAt(end, c);
            ++start;
            --end;
        }
    }

    private void reverseWord(StringBuilder s){

        int n = s.length();
        int start = 0;
        int end = 0;
        while (start < n){

            //循环到每个单词结尾
            while (end < n && s.charAt(end) != ' '){
                ++end;
            }
            reverse(s, start, end - 1);
            start = end + 1;
            ++end;
        }
    }


    /**
     * 双端队列:双端队列支持从队列头部插入元素，我们可以沿着字符串一个一个单词处理，将单词压入队列的头部，最后将队列转换成字符串即可
     * 复杂度分析
     *  时间复杂度:O(n),n表示字符串s的长度
     *  空间复杂度:O(n),双端队列存储单词所需要的空间
     * @param s
     * @return
     */
    public String solutionV2(String s){

        int left = 0;
        int right = s.length() - 1;

        //去除头部的空白字符
        while (left <= right && s.charAt(left) == ' '){
            ++left;
        }
        //去除尾部的字符串
        while (left <= right && s.charAt(right) == ' '){
            --right;
        }

        Deque<String> deque = new ArrayDeque<>();
        StringBuilder word = new StringBuilder();
        while (left <= right){
            char c = s.charAt(left);
            if (word.length() != 0 && c == ' '){
                //添加到队列的头部
                deque.addFirst(word.toString());
                word.setLength(0);
            }else if (c != ' '){
                word.append(c);
            }
            ++left;
        }
        //最后一个单词
        deque.addFirst(word.toString());

        return String.join(" ", deque);
    }


    public static void main(String[] args) {

        ReverseWords reverseWords = new ReverseWords();
        String s = "a good   example";

        String ans = reverseWords.solutionV1(s);
        System.out.println(ans);

        String ans2 = reverseWords.solutionV2(s);
        System.out.println(ans2);
    }
}
