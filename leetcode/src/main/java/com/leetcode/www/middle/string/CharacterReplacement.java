package com.leetcode.www.middle.string;


/**
 * leetcode-424:替换后的最长重复字符
 * 给你一个字符串 s 和一个整数 k 。你可以选择字符串中的任一字符，并将其更改为任何其他大写英文字符。该操作最多可执行 k 次。
 * 在执行上述操作后，返回包含相同字母的最长子字符串的长度。
 */
public class CharacterReplacement {

    /**
     * 滑动窗口
     * 使用left指针和right指针构成一个滑动窗口,right指针向右移动，同时使用哈希表维护窗口内每个字符出现的次数。合格区间的条件是
     * (区间长度-这个区间出现次数最多的字符的次数) <= k,当区间不满足条件时，left指针向左移动
     * 复杂度分析
     *  时间复杂度:O(n),n是字符串s的长度
     *  空间复杂度:O(|Σ|),|Σ|是字符集的大小
     * @param s
     * @param k
     * @return
     */
    public int solution(String s, int k){

        int n = s.length();
        int[] cnt = new int[26];
        int left = 0;
        int right = 0;
        int maxCount = 0;

        while (right < n){
            cnt[s.charAt(right) - 'A']++;
            maxCount = Math.max(maxCount, cnt[s.charAt(right) - 'A']);
            if (right - left + 1 - maxCount > k){
                cnt[s.charAt(left) - 'A']--;
                //保证
                ++left;
            }
            ++right;
        }

        return right - left;
    }

    /**
     * 复杂度分析
     *  时间复杂度:O(n),n是字符串s的长度
     *  空间复杂度:O(1),使用了固定长度的数组进行统计
     * @param s
     * @param k
     * @return
     */
    public int solutionV2(String s, int k){

        int[] cnt = new int[26];
        int n = s.length();
        int left = 0;
        int ans = 0;
        for (int right = 0; right < n; right++){
            cnt[s.charAt(right) - 'A']++;
            while (!check(cnt, k)){
                cnt[s.charAt(left) - 'A']--;
                ++left;
            }

            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }

    private boolean check(int[] cnt, int k){

        int sum = 0;
        int max = 0;
        for (int i = 0; i < 26; i++){
            max = Math.max(max, cnt[i]);
            sum += cnt[i];
        }

        return (sum - max) <= k;
    }
    public static void main(String[] args) {

        CharacterReplacement replacement = new CharacterReplacement();
        String s = "AABABBA";
        int k = 1;
        int ans = replacement.solutionV2(s, k);
        System.out.println(ans);
    }
}
