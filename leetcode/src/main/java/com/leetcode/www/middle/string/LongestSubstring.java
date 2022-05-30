package com.leetcode.www.middle.string;


/**
 * leetcode-395:至少有 K 个重复字符的最长子串
 * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
 */
public class LongestSubstring {


    /**
     * 滑动窗口
     * 由于字符种类数目较小，本题中的字符集是26.可以枚举最长子串中的字符数目。通过保证滑动窗口内的字符种类数量小于等于给定的字符种类数量(当total>k时，将left指针
     * 向右移动)，同时使用数组cnt统计滑动窗口内每个字符出现的次数。当滑动窗口内的字符满足字符种类数量total小于等于给定的字符种类数目t，并且窗口内每个字符出现的次数大于等于k时，
     * 就计算滑动窗口的大小，重复这个过程，最后得到最长子串的长度
     * 判断滑动窗口内的字符的出现次数是否都大于等于k，可以通过遍历cnt数组来实现，但是这样会带来O(∣Σ∣)的额外开销。可以通过维护一个变量less，代表当前出现次数小于k
     * 的字符的数量。每次移动滑动窗口的边界时，只会让某个字符的出现次数加一或者减一，以移动右指针right为例
     *  1. 当某个字符的出现次数从0增加到1时，less加1
     *  2. 当某个字符的出现次数从k-1增加到k时，将less减1
     *
     * 复杂度分析
     *  时间复杂度:O(N⋅∣Σ∣+∣Σ∣^2),N是字符串的长度，Σ是字符集,本题中字符串只包含小写字母，因此∣Σ∣=26
     *  空间复杂度:O(∣Σ∣)
     * @param s
     * @param k
     * @return
     */
    public int solution(String s, int k){

        int ans = 0;
        int len = s.length();
        for (int t = 1; t <= 26; t++){
            int left = 0;
            int right = 0;
            int[] cnt = new int[26];
            int total = 0;
            int less = 0;
            while (right < len){
                cnt[s.charAt(right) - 'a']++;
                if (cnt[s.charAt(right) - 'a'] == 1){
                    ++less;
                    ++total;
                }
                if (cnt[s.charAt(right) - 'a'] == k){
                    --less;
                }

                while (total > k){
                    cnt[s.charAt(left) - 'a']--;
                    if (cnt[s.charAt(left) - 'a'] == k - 1){
                        ++less;
                    }
                    if (cnt[s.charAt(left) - 'a'] == 0){
                        --total;
                        --less;
                    }
                    left++;
                }

                if (less == 0){
                    ans = Math.max(ans, right - left + 1);
                }
                ++right;
            }
        }

        return ans;
    }

    /**
     * 分治
     * 如果字符串中的某个字符ch出现的次数大于0小于k，则任何包含ch的子串都不能满足要求。因此我们可以将字符串按照ch切成若干段，则满足要求的最长子串一定出现在
     * 某个被切分的段，而不能跨越一个或者多个段，所以可以使用分治来求解
     * 复杂度分析
     *  时间复杂度:O(N*∣Σ∣),N是字符串s的长度，Σ是字符集，本题中字符串中只包含小写字母。因此∣Σ∣
     *  空间复杂度:O(∣Σ∣^2),递归的深度为O(∣Σ∣),每层需要开辟O(∣Σ∣)的额外空间
     * @param s
     * @param k
     * @return
     */
    public int solutionV2(String s, int k){

        return dfs(s, 0, s.length() - 1, k);
    }

    private int dfs(String s, int left, int right, int k){

        int[] cnt = new int[26];
        for (int i = left; i <= right; i++){
            cnt[s.charAt(i) - 'a']++;
        }


        char split = 0;
        for (int i = 0; i < 26; i++){
            if (cnt[i] > 0 && cnt[i] < k){
                split = (char) (i + 'a');
                break;
            }
        }

        if (split == 0){
            return right - left + 1;
        }

        int i = left;
        int ret = 0;
        while (i <= right){
            while (i <= right && s.charAt(i) == split){
                i++;
            }
            if (i > right){
                break;
            }
            int start = i;
            while (i <= right && s.charAt(i) != split){
                i++;
            }
            int length = dfs(s, start, i - 1, k);
            ret = Math.max(ret, length);
        }

        return ret;
    }

    public static void main(String[] args) {

        LongestSubstring substring = new LongestSubstring();
        String s = "ababbc";
        int k = 2;
        int ans = substring.solutionV2(s, k);
        System.out.println(ans);
    }
}
