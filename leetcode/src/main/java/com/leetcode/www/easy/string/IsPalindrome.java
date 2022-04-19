package com.leetcode.www.easy.string;

/**
 * leetcode-125:验证回文串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。说明：本题中，我们将空字符串定义为有效的回文串。
 */
public class IsPalindrome {

    /**
     * 使用双指针:移动左右指针left，right，当left，right指针指向的是数字字符或者是字母字符时，判断他们是否相等
     * 复杂度分析
     *  时间复杂度:O(s),s是字符串的长度
     *  空间复杂度:O(1)
     * @param s
     * @return
     */
    public boolean solution(String s){

        int left = 0;
        int right = s.length() - 1;
        //终止条件是left，right指针重合
        while (left < right){

            while (left < right && !Character.isLetterOrDigit(s.charAt(left))){
                ++left;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))){
                --right;
            }

            if (left < right){
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))){
                    return false;
                }
                ++left;
                --right;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        IsPalindrome palindrome = new IsPalindrome();
        String s = "A man, a plan, a canal: Panama";
        String s2 = "race a car";
        boolean ans = palindrome.solution(s2);
        System.out.println(ans);
    }
}
