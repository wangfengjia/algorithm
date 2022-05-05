package com.leetcode.www.middle.array;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 给你一个 无重复元素 的整数数组candidates 和一个目标整数target，找出candidates中可以使数字和为目标数target 的 所有不同组合 ，并以列表形式返回。
 * 你可以按 任意顺序 返回这些组合。candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * 对于给定的输入，保证和为target 的不同组合数少于 150 个。
 * 方法一:搜索回溯，时间复杂度O(S),S是所有可行解的长度之和
 */
public class CombinationSum {

    public List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> solution(int[] arr, int target){
        List<Integer> combine = new ArrayList<>();
        dfs(arr, combine, target, 0);
        return ans;
    }

    public void dfs(int[] candidates, List<Integer> combine, int target, int idx){

        if (idx == candidates.length){
            return;
        }
        //由于一个元素可以被无限制重复选择，所以这个判断可以放在判断深度后面
        if (target == 0){
            this.ans.add(new ArrayList<>(combine));
            return;
        }

        //两种选择，一种是使用当前数，一种是跳过当前数
        //直接跳过
        dfs(candidates, combine, target, idx+1);
        //选择当前数
        if (target - candidates[idx] >= 0){
            combine.add(candidates[idx]);
            //由于同一个数字可以被无限制重复选择，所以这边还是idx
            dfs(candidates, combine, target - candidates[idx], idx);
            combine.remove(combine.size() - 1);
        }
    }


    /**
     * 回溯加剪枝
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> solutionV2(int[] nums, int target){

        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        if (n == 0){
            return ans;
        }

        Deque<Integer> path = new ArrayDeque<>();
        dfsV2(nums, n, 0, target, path, ans);
        return ans;
    }

    private void dfsV2(int[] nums, int len, int begin, int target, Deque<Integer> path, List<List<Integer>> ans){

        if (target < 0){
            return;
        }
        if (target == 0){
            ans.add(new ArrayList<>(path));
            return;
        }

        //在搜索过程中，每层的第二个节点开始,不能搜索同一层节点已经使用过nums里面的元素，这样才能够使得最后结果里面不出现重复列表
        for (int i = begin; i < len; i++){
            path.addLast(nums[i]);
            //由于每个元素可以重复选择，所以下一轮搜索的起点还是i
            dfsV2(nums, len, i, target - nums[i], path, ans);
            //撤销选择
            path.removeLast();
        }
    }

    public static void main(String[] args) {

        int[] arr = new int[]{2,3,6,7};
        int target = 7;

        CombinationSum combinationSum = new CombinationSum();
        List<List<Integer>> lists = combinationSum.solution(arr, target);
        for (List<Integer> list : lists){
            System.out.println(list);
        }

        System.out.println("=============================================");

        List<List<Integer>> ans = combinationSum.solutionV2(arr, target);
        for (List<Integer> list : ans){
            System.out.println(list);
        }
    }
}
