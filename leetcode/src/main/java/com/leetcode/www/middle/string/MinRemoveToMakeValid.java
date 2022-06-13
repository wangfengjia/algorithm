package com.leetcode.www.middle.string;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * leetcode-1249:移除无效的括号
 * 给你一个由 '('、')' 和小写字母组成的字符串 s。
 * 你需要从字符串中删除最少数目的 '(' 或者 ')'（可以删除任意位置的括号)，使得剩下的「括号字符串」有效。
 * 请返回任意一个合法字符串。
 * 有效「括号字符串」应当符合以下任意一条要求：
     * 空字符串或只包含小写字母的字符串
     * 可以被写作AB（A连接B）的字符串，其中A和B都是有效「括号字符串」
     * 可以被写作(A)的字符串，其中A是一个有效的「括号字符串」
 */
public class MinRemoveToMakeValid {


    /**
     * 栈+哈希表
     * 遍历字符串s，枚举到字符'('就将其入栈，枚举到字符')'时就有两种情况:一是假如栈不为空，就将栈顶元素出栈;而是栈为空，则表示当前')'字符是多余的，将这个字符的
     * 下标添加到哈希表中。这样处理完成以后，判断栈是否为空，假如栈不为空，则表示有多余的'('字符，则将这些字符的下标添加到哈希表中。最后，哈希表中记录了全部
     * 无效括号的下标，根据这个去重新构造字符串
     *
     * 复杂度分析
     *  时间复杂度:O(n),n是字符串s的长度
     *  空间复杂度:O(n),n是字符串的长度,stack,set,StringBuilder里面最多存储n个元素，因此总的空间复杂度为O(n)
     * @param s
     * @return
     */
    public String solution(String s){

        Set<Integer> set = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '('){
                stack.push(i);
            }
            if (s.charAt(i) == ')'){
                if (!stack.isEmpty()){
                    stack.pop();
                }else {
                    set.add(i);
                }
            }
        }

        while (!stack.isEmpty()){
            set.add(stack.pop());
        }

        //重构构造字符串
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++){
            if (!set.contains(i)){
                builder.append(s.charAt(i));
            }
        }

        return builder.toString();
    }

    public static void main(String[] args) {

        MinRemoveToMakeValid valid = new MinRemoveToMakeValid();
        String s = "lee(t(c)o)de)";
        String ans = valid.solution(s);
        System.out.println(ans);
    }
}
