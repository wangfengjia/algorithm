package com.leetcode.www.middle.string;


import java.util.HashMap;
import java.util.HashSet;

/**
 * leetcode-3:无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * 解法:使用滑动窗口在字符串移动，有左右两个指针和一个哈希集合，右指针不断向字符串右边界移动，右指针每移动一次，就利用哈希集合来判断这个字符在哈希集合中是否存在
 * 复杂度分析
 *      时间复杂度：O(N)，其中 NN 是字符串的长度。左指针和右指针分别会遍历整个字符串一次。
 *      空间复杂度：O(∣Σ∣)，其中Σ 表示字符集（即字符串中可以出现的字符），∣Σ∣ 表示字符集的大小。在本题中没有明确说明字符集，
 *                因此可以默认为所有 ASCII 码在 [0, 128)[0,128) 内的字符，即∣Σ∣=128。我们需要用到哈希集合来存储出现过的字符，
 *                而字符最多有∣Σ∣ 个，因此空间复杂度为O(∣Σ∣)。
 *
 */
public class LengthOfLongestSubstring {

    public int solution(String s){
        //哈希集合，记录每个字符是否出现过
        HashSet<Character> characters = new HashSet<>();
        int len = s.length();
        //右指针初始值为-1，相当于在我们字符串的左边界的左侧,还没有开始移动
        int right = -1;
        int ans = 0;
        for (int i = 0; i < len; ++i){
            if (i != 0){
                //左指针向右移动一位，从哈希集合中移除一个字符
                characters.remove(s.charAt(i - 1));
            }
            while ((right + 1) < len && !characters.contains(s.charAt(right + 1))){
                //将不重复元素加到哈希集合，然后不断向右移动指针
                characters.add(s.charAt(right + 1));
                ++right;
            }

            //第i个字符到第right个字符是一个极长的无重复字符子串
            ans = Math.max(ans, right - i + 1);
        }

        return ans;
    }

    /**
     * 当右指针移到 第二个a 时，左指针移到 第一个 b，依次类推，这种情况，其实就相当于一个个移动。 我的意思是滑动窗口的 左指针不需要一个个移动，遇到重复的字符时，
     * 可以跳跃移动，比如 "abcdefdhjk" 这种，当右指针移动到第二个d的时候，左指针可以直接到第一个d的下一个位置，即 e 开始,由于d重复了，即使把左指针移动到b到第一个
     * d之间的元素所构成的最长无重复子串也会比从a开始构造的最长无重复子串的长度短
     * @param s
     * @return
     */
    public int solutionV2(String s){

        HashMap<Character, Integer> occ = new HashMap<>();
        int max = 0;
        int left = 0;
        int len = s.length();
        for (int right = 0; right < len; right++){
            char c = s.charAt(right);
            if (occ.containsKey(c)){
                left = Math.max(occ.get(c) + 1, left);
//                left = occ.get(c) + 1;
            }

            max = Math.max(max, right - left + 1);
            occ.put(c, right);
        }

        return max;
    }

    public static void main(String[] args) {


        String s = "abcabcbb";
        LengthOfLongestSubstring substring = new LengthOfLongestSubstring();
        int len = substring.solutionV2(s);
        System.out.println(len);
    }
}
