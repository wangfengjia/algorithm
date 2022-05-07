package com.leetcode.www.middle.string;

/**
 * leetcode-316:去除重复字母
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 */
public class RemoveDuplicateLetters {


    /**
     * 背景:给定一个字符串s，去除掉一个字符ch，使得得到的字符串字典序最小。那么这个字符应该是最小的满足s[i] > s[i+1]的下标i对应的字符s[i],这种字符可以称为
     *     关键字符，这个题目就是在字符串s中找关键字符
     * 贪心+单调栈:从前往后扫描字符串，假设在扫描s[i]时，s[i-1]之前的所有关键字符都已经被去除完毕，新出现的关键字符只能出现在s[i]以及其后面的位置。
     *           同时使用单调栈来维护去除关键字符后得到的字符串,单调栈里面的字符是在字典序递增的。当扫描到s[i]时，s[i]与栈顶字符比较，如果栈顶字符大于s[i],
     *           说明栈顶字符是关键字符，应当被除去。去除后重复上述过程，直到栈为空或者栈顶字符不再大于s[i],最后将s[i]放入栈中
     *           由于题目要求原字符串s中的每个字符都需要出现在新字符串中,且只能出现一次，因此需要加上如下两个处理
     *              1. 如果字符s[i]已经在栈中，则不再将s[i]入栈，因此需要记录每个字符是否出现在栈中
     *              2. 在弹出栈顶字符时，如果字符串后面的位置上没有这个字符了，就不能弹出这个栈顶字符，为此，需要记录每个字符的剩余数量，当这个数量为0时，
     *                 就不能弹出栈顶字符
     * 复杂度分析
     *  时间复杂度:O(N),N表示字符串的长度，每个字符只会出栈、入栈各一次
     *  空间复杂度:O(∣Σ∣),Σ表示字符集合，本题中都是小写字符，所以Σ = 26。cnt和visited数组所使用的空间
     * @param s
     * @return
     */
    public String solution(String s){

        if (s == null){
            return null;
        }

        int n = s.length();
        int[] cnt = new int[26];
        boolean[] visited = new boolean[26];
        //统计字符串s中每个字符的数量
        for (int i = 0; i < n; i++){
            cnt[s.charAt(i) - 'a']++;
        }

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < n; i++){
            char ch = s.charAt(i);
            if (!visited[ch - 'a']){
                while (buffer.length() > 0 && buffer.charAt(buffer.length() - 1) > ch){
                    if (cnt[buffer.charAt(buffer.length() - 1) - 'a'] <= 0){
                        break;
                    }
                    visited[buffer.charAt(buffer.length() - 1) - 'a'] = false;
                    buffer.deleteCharAt(buffer.length() - 1);
                }
                visited[ch - 'a'] = true;
                buffer.append(ch);
            }
            cnt[ch - 'a'] -= 1;
        }

        return buffer.toString();
    }



    public static void main(String[] args) {

        RemoveDuplicateLetters removeDuplicateLetters = new RemoveDuplicateLetters();
        String s = "cbacdcbc";
        String ans = removeDuplicateLetters.solution(s);
        System.out.println(ans);
    }
}
