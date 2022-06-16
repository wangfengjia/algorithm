package com.leetcode.www.middle.string;


/**
 * leetcode-921:使括号有效的最少添加
 * 只有满足下面几点之一，括号字符串才是有效的：
     * 它是一个空字符串，或者
     * 它可以被写成AB（A与B连接）, 其中A 和B都是有效字符串，或者
     * 它可以被写作(A)，其中A是有效字符串。
 * 给定一个括号字符串 s ，移动N次，你就可以在字符串的任何位置插入一个括号。
    * 例如，如果 s = "()))" ，你可以插入一个开始括号为 "(()))" 或结束括号为 "())))" 。
 * 返回 为使结果字符串 s 有效而必须添加的最少括号数。
 */
public class MinAddToMakeValid {


    /**
     * 平衡法
     * 保证左右括号数量的平衡:当'('出现的次数等于')'出现的次数时就是平衡的。可以计算字符数组s的每个前缀子数组的平衡度。如果值是负数，需要在前面加上一个'(',如果
     * 值是正数，那就得在末尾加上')'
     *
     * 复杂度分析
     *  时间复杂度:O(n),n是字符串s的长度
     *  空间复杂度:O(1)
     * @param s
     * @return
     */
    public int solution(String s){

        int ans = 0;
        int bal = 0;
        for (int i = 0; i < s.length(); i++){
            bal += s.charAt(i) == '(' ? 1 : -1;
            if (bal == -1){
                ++bal;
                ++ans;
            }
        }

        return ans + bal;
    }

    public static void main(String[] args) {

        MinAddToMakeValid makeValid = new MinAddToMakeValid();
        String s = "(a)";
        int ans = makeValid.solution(s);
        System.out.println(ans);
    }
}
