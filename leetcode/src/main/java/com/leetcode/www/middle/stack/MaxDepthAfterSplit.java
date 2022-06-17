package com.leetcode.www.middle.stack;


import java.util.Arrays;

/**
 * leetcode-1111:有效括号的嵌套深度
 * 有效括号字符串 定义：对于每个左括号，都能找到与之对应的右括号，反之亦然。详情参见题末「有效括号字符串」部分。
 * 嵌套深度 depth 定义：即有效括号字符串嵌套的层数，depth(A) 表示有效括号字符串 A 的嵌套深度。详情参见题末「嵌套深度」部分。
 * 给你一个「有效括号字符串」 seq，请你将其分成两个不相交的有效括号字符串，A 和B，并使这两个字符串的深度最小。
     * 不相交：每个 seq[i] 只能分给 A 和 B 二者中的一个，不能既属于 A 也属于 B 。
     * A 或 B 中的元素在原字符串中可以不连续。
     * A.length + B.length = seq.length
     * 深度最小：max(depth(A), depth(B))的可能取值最小。
 * 划分方案用一个长度为 seq.length 的答案数组 answer 表示，编码规则如下：
     * answer[i] = 0，seq[i] 分给 A 。
     * answer[i] = 1，seq[i] 分给 B 。
 * 如果存在多个满足要求的答案，只需返回其中任意 一个 即可。
 */
public class MaxDepthAfterSplit {


    /**
     * 栈
     * 将有效括号字符串seq分成两个不想交的有效括号字符串后使得max(depth(A), depth(B))可能取值最小，这就类似二叉树，要使得二叉树深度最小，那么就需要二叉树
     * 平衡，一个可行的做法:将栈中连续出现的左括号'('根据奇偶性分到不同的组，右括号随与之匹配左括号的组号
     *
     * 复杂度分析
     *  时间复杂度:O(n),n是字符串seq的长度，只需要遍历字符串一次
     *  空间复杂度:O(1)
     * @param seq
     * @return
     */
    public int[] solution(String seq){

        int len = seq.length();
        int[] ans = new int[len];
        int depth = 0;
        for (int i = 0; i < len; i++){
            if (seq.charAt(i) == '('){
                ++depth;
                ans[i] = depth % 2;
            }else {
                //遍历到右括号,与当前栈顶左括号分到一组，因此需要先取模再减1
                ans[i] = depth % 2;
                --depth;
            }
        }

        return ans;
    }


    public static void main(String[] args) {

        MaxDepthAfterSplit split = new MaxDepthAfterSplit();
        String seq = "(()())";
        int[] ans = split.solution(seq);
        System.out.println(Arrays.toString(ans));
    }
}
