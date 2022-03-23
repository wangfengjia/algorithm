package com.leetcode.www.middle.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个整数数组nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。解集不能包含重复的子集。返回的解集中，子集可以按任意顺序排列。
 * 方法一(回溯): 由于可能包含重复的元素，所以可能出现重复子集，比如数组[1,2,2]的子集有两个[1,2],要解决出现重复子集的问题的办法是:先将数组排序，排完序后重复元素的下标
 *              是连续的，同时每个元素都有两个选择:选中或者不选中。由于数组里面有重复元素，导致有重复子集，所以需要搜索剪枝，剪枝的条件是:当前元素和前面一个元素
 *              相同的时候，前面一个没选择，后面一个也不能选择。比如这种子集情况:[][1](不选择第二个元素)[1,2](选择第二个元素),这个时候，假如第二个元素没选择，第三个
 *              元素(和第二个元素一样)选中后子集是[1,2],这样就和第一个元素和第二个元素都选中后的子集结果一样，出现了重复。最核心的思路就是重复元素在和其他元素组成
 *              集合时，重复元素只能选中一个
 */
public class SubsetsWithDup {


    public List<Integer> t = new ArrayList<>();
    public List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> solution(int[] arr){

        Arrays.sort(arr);
        dfs(arr, 0, arr.length, false);
        return ans;
    }

    public void dfs(int[] arr, int cur, int len, boolean choosePre){

        if (cur == len){
            this.ans.add(new ArrayList<>(t));
            return;
        }

        //不选中
        dfs(arr, cur + 1, len, false);
        //前一个元素没选中，并且这个元素和前一个元素相同，则这个元素不能选中
        if (!choosePre && cur > 0 && arr[cur] == arr[cur - 1]){
            return;
        }
        //选中
        t.add(arr[cur]);
        dfs(arr, cur + 1, len, true);
        t.remove(t.size() - 1);
    }

    public static void main(String[] args) {

        int[] nums = new int[]{1,2,2};
        SubsetsWithDup subsetsWithDup = new SubsetsWithDup();
        List<List<Integer>> lists = subsetsWithDup.solution(nums);
        System.out.println(lists);
    }
}
