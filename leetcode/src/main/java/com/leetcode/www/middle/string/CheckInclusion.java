package com.leetcode.www.middle.string;

import java.util.Arrays;

/**
 * leetcode-567:字符串的排列
 * 给你两个字符串s1和s2 ，写一个函数来判断s2是否包含s1的排列。如果是返回 true；否则返回 false 。
 * 换句话说，s1 的排列之一是 s2 的 子串 。
 */
public class CheckInclusion {


    /**
     * 滑动窗口:首先统计s1中每个字符出现的次数;然后使用长度为s1.length()的滑动窗口在s2中滑动，滑动过程中统计每个字符出现的次数,如果滑动窗口中的每个字符出现的次数
     *         会等于s1中每个字符出现的次数，则s2包含s1的排列。排列不会改变每个字符出现的次数，所以只要两个字符串中每个字符出现的次数相等时，一个字符串就是另外
     *         一个字符串的排列
     * 复杂度分析
     *  时间复杂度:O(n+m+∣Σ∣)。n是字符串s1的长度，m是字符串s2的长度，Σ是字符集
     *  空间复杂度:O(∣Σ∣)
     * @param s1
     * @param s2
     * @return
     */
    public boolean solution(String s1, String s2){

        int n = s1.length();
        int m = s2.length();
        if (n > m){
            return false;
        }

        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];

        for (int i = 0; i < n; i++){
            ++cnt1[s1.charAt(i) - 'a'];
            ++cnt2[s2.charAt(i) - 'a'];
        }

        if (Arrays.equals(cnt1, cnt2)){
            return true;
        }

        for (int i = n; i < m; i++){
            ++cnt2[s2.charAt(i) - 'a'];
            --cnt2[s2.charAt(i-n) - 'a'];
            if (Arrays.equals(cnt1, cnt2)){
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        CheckInclusion inclusion = new CheckInclusion();
        String s1 = "ab";
        String s2 = "eidboaoo";
        boolean ans = inclusion.solution(s1, s2);
        System.out.println(ans);
    }
}
