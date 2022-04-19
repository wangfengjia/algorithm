package com.leetcode.www.middle.array;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode-216:组合之和III
 * 找出所有相加之和为n的k个数的组合，且满足下列条件：
     * 只使用数字1到9
     * 每个数字最多使用一次
 * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
 *
 */
public class CombinationSum3 {

    private List<List<Integer>> ans = new ArrayList<>();


    public List<List<Integer>> solution(int k, int n){

        List<Integer> combine = new ArrayList<>();
        dfs(1, combine, n, k);
        return ans;
    }

    private void dfs(int begin, List<Integer> combine, int target, int k){

        if (target == 0 && combine.size() == k){
            this.ans.add(new ArrayList<>(combine));
        }

        //
        for (int i = begin; i <= 9; i++){
            if (target < i){
                return;
            }
            //选择当前值
            combine.add(i);
            //递归
            dfs(i + 1, combine, target - i, k);
            //撤销选择
            combine.remove(combine.size() - 1);
        }
    }

    public static void main(String[] args) {

        CombinationSum3 sum3 = new CombinationSum3();
        int k = 3;
        int n = 9;
        List<List<Integer>> ans = sum3.solution(k, n);
        System.out.println(ans);
    }
}
