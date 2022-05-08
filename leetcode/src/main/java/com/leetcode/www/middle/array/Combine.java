package com.leetcode.www.middle.array;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * leetcode-77:组合
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。你可以按 任何顺序 返回答案。
 */
public class Combine {


    /**
     * 回溯方法解决
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> solution(int n, int k){

        List<List<Integer>> ans = new ArrayList<>();
        if (k <= 0 || n < k){
            return ans;
        }

        Deque<Integer> path = new ArrayDeque<>();
        dfs(1, 0, k, n, path, ans);
        return ans;
    }

    private void dfs(int begin, int depth, int k, int n, Deque<Integer> path, List<List<Integer>> ans){

        if (depth == k){
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i <= n; i++){
            path.addLast(i);
            //由于组合数不允许出现重复的元素，所以begin会等于i+1
            dfs(i + 1, depth + 1, k, n, path, ans);
            path.removeLast();
        }
    }



    public static void main(String[] args) {

        Combine combine = new Combine();
        int n = 4;
        int k = 2;
        List<List<Integer>> ans = combine.solution(n, k);
        for (List<Integer> items : ans){
            System.out.println(items);
        }
    }
}
