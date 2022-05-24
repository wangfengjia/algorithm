package com.leetcode.www.middle.array;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode-386:字典序排数
 * 给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。
 * 你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。
 */
public class LexicalOrder {

    /**
     * 深度优先搜索(DFS)
     * 复杂度分析
     *  时间复杂度:O(n)
     *  空间复杂度:O(1),忽略递归调用栈空间的开销
     * @param n
     * @return
     */
    public List<Integer> solution(int n){

        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= 9; i++){
            dfs(i, n, res);
        }

        return res;
    }

    private void dfs(int cur, int limit, List<Integer> res){

        if (cur > limit){
            return;
        }
        res.add(cur);
        for (int i = 0; i <= 9; i++){
            dfs(cur * 10 + i, limit, res);
        }
    }

    /**
     * 要实现严格的O(1)空间复杂度，使用迭代来实现DFS
     * @param n
     * @return
     */
    public List<Integer> solutionV2(int n){

        List<Integer> ans = new ArrayList<>();
        int j = 1;
        for (int i = 0; i < n; i++){
            ans.add(j);
            if (j * 10 <= n){
                j *= 10;
            }else {
                while (j % 10 == 9 || j + 1 > n){
                    j /= 10;
                }
                ++j;
            }
        }

        return ans;
    }

    public static void main(String[] args) {

        LexicalOrder lexicalOrder = new LexicalOrder();
        int n = 14;

        List<Integer> ans1 = lexicalOrder.solution(n);
        System.out.println(ans1);

        List<Integer> ans2 = lexicalOrder.solutionV2(n);
        System.out.println(ans2);
    }
}
