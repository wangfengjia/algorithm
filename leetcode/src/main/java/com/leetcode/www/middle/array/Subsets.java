package com.leetcode.www.middle.array;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode-78:子集
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集)。解集不能包含重复的子集。你可以按任意顺序返回解集。
 * 方法一(回溯):由于解集不能包含重复的子集，所以任意一个元素只会出现在子集里面一次，同时数组中的元素有选中和不被选中两种状态，就可以去深度遍历(回溯)数组，对于数组中的
 *             每个元素，有选中和不被选中(通过这两种选择得到各种小于等于数组长度的子集)，选中的话就加到一个集合里面，由于是回溯处理，所以需要在回溯过程中移除到上次
 *             加到集合中的元素
 *             时间复杂度: O(n * 2^n).一共有2^n个状态，每种状态需要O(n)的时间构造子集
 *             空间复杂度: O(n),临时数组t的空间代价是O(n),递归栈空间代价也是O(n)
 */
public class Subsets {

    public List<Integer> t = new ArrayList<>();
    public List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> solution(int[] arr){
        dfs(0, arr, arr.length);
        return ans;
    }

    public void dfs(int cur, int[] arr, int len){

        if (cur == len){
            this.ans.add(new ArrayList<>(t));
            return;
        }

        //当前元素选中，添加到集合里面
        t.add(arr[cur]);
        dfs(cur + 1, arr, len);
        //回溯处理
        t.remove(t.size() - 1);
        //当前元素不选中
        dfs(cur + 1, arr, len);
    }

    public static void main(String[] args) {


        int[] nums = new int[]{1,2,3};
        Subsets subsets = new Subsets();
        List<List<Integer>> lists = subsets.solution(nums);
        System.out.println(lists);
    }
}
