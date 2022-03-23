package com.leetcode.www.middle.dynamic;

/**
 * 最长回文子串
 * 动态规划解法
 *  对于一个子串而言，如果它是回文串，并且长度大于2，那么将它首尾的两个字母去除之后，它仍然是个回文串。例如对于字符串“ababa”，如果我们已经知道
 *  “bab” 是回文串，那么“ababa” 一定是回文串，这是因为它的首尾两个字母都是“a”。动态规划边界条件:子串的长度为1或者2，对于长度为1的子串，它就是回文串;
 *  对于长度为2的子串，只有它两个字母相同时它才是一个回文串
 *          P(i,j) = P(i+1, j-1)^(Si == Sj)
 *          1.只有s[i+1,j-1]是回文串,并且s的第i和j个字母相同时，s[i,j]才是回文串
 *          2.P(i,j)表示字符串s的第i到j字符是否是回文串
*    复杂度分析
 *
 * 时间复杂度：O(n^2),其中 nn 是字符串的长度。
 * 动态规划的状态总数为 O(n^2))，对于每个状态，我们需要转移的时间为 O(1)。
 * 空间复杂度：O(n^2)即存储动态规划状态需要的空间。
 *
 *
 */
public class LongestPalindrome {


    public String solution(String s){

        int length = s.length();
        if (length < 2){
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        //dp[i][j]表示s[i..j]是否是回文串
        boolean[][] dp = new boolean[length][length];
        //初始化所有长度为1的子串都是回文串
        for (int i = 0; i < length; i++){
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();
        //递推开始，先枚举子串长度
        for (int L = 2; L <= length; L++){
            //枚举的左边界，左边界的上限可以设置宽松一些
            for (int i = 0; i < length; i++){
                //确定右边界
                int j = i + L - 1;
                //如果右边界已越界，退出循环
                if (j >= length){
                    break;
                }

                if (charArray[i] != charArray[j]){
                    dp[i][j] = false;
                }else {
                    //子串的长度为为，且左右边界字符相同，所以是回文串
                    if (j - i < 3){
                        dp[i][j] = true;
                    }else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要dp[i][j] == true,就表示子串s[i..j是回文]，此时记录回文长度和起始位置
                if (dp[i][j] && (j - i + 1) > maxLen){
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }

        return s.substring(begin, begin + maxLen);
    }


    /**
     * 1. 观察动态规划解法的状态转移方程可以发现，所有的状态在转移的时候的可能性都是唯一的。也就是说，我们可以从每一种边界情况开始「扩展」，
     *    也可以得出所有的状态对应的答案。
     * 2. 边界情况即为子串长度为 1 或 2 的情况。我们枚举每一种边界情况，并从对应的子串开始不断地向两边扩展。如果两边的字母相同，我们就可以继续扩展，
     *    例如从 P(i+1,j-1)P(i+1,j−1) 扩展到 P(i,j)P(i,j)；如果两边的字母不同，我们就可以停止扩展，因为在这之后的子串都不能是回文串了。
     * 3. 聪明的读者此时应该可以发现，「边界情况」对应的子串实际上就是我们「扩展」出的回文串的「回文中心」。方法二的本质即为：我们枚举所有的「回文中心」并尝试「扩展」，
     *    直到无法扩展为止，此时的回文串长度即为此「回文中心」下的最长回文串长度。我们对所有的长度求出最大值，即可得到最终的答案。
     * 4. 复杂度分析
     *    时间复杂度：O(n^2)，其中 nn 是字符串的长度。长度为1和2的回文中心分别有 n 和 n-1 个，每个回文中心最多会向外扩展 O(n) 次。
     *    空间复杂度：O(1)
     *
     * @return
     */
    public String solutionV2(String s){

        if (s == null || s.length() < 1){
            return "";
        }

        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++){
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > (len2 - len1)){
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    public int expandAroundCenter(String s, int left, int right){
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            --left;
            ++right;
        }

        return right - left - 1;
    }

    public static void main(String[] args) {

        String s = "bacfd";
        LongestPalindrome longestPalindrome = new LongestPalindrome();
        String s1 = longestPalindrome.solutionV2(s);
        System.out.println(s1);
    }
}
