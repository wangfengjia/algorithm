package com.leetcode.www.middle.array;

import java.util.*;

/**
 * leetcode-491:递增子序列
 * 给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有两个元素 。你可以按 任意顺序 返回答案。
 * 数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。
 */
public class FindSubsequences {


    /**
     * 回溯+剪枝
     * @param nums
     * @return
     */
    public List<List<Integer>> solution(int[] nums){

        Deque<Integer> path = new ArrayDeque<>();
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, 0, path, res);

        return res;
    }


    private void dfs(int[] nums, int start, Deque<Integer> path, List<List<Integer>> res){

        if (path.size() > 1){
            res.add(new ArrayList<>(path));
            //这里不能return，是由于要取递归树上的所有节点
        }

        //在leetcode-90题求子集去重的办法是把数组排序+标记数组来实现同一层相同值的元素只被使用一次。而这个题目是要求递增子序列，所以不能对原数组进行排序
        //排序后的数组就是递增子序列了。因此，在这个题目中使用哈希表用来判断同一层的相同元素是否使用过
        Set<Integer> set = new HashSet<>();
        for (int i = start; i < nums.length; i++){
            if ((!path.isEmpty() &&  nums[i] < path.peekLast()) || (set.contains(nums[i]))){
                continue;
            }
            set.add(nums[i]);
            path.addLast(nums[i]);
            dfs(nums, i + 1, path, res);
            path.removeLast();
        }
    }

    public static void main(String[] args) {

        FindSubsequences findSubsequences = new FindSubsequences();
        int[] nums = {4,6,7,7};
        List<List<Integer>> ans = findSubsequences.solution(nums);
        for (List<Integer> list : ans){
            System.out.println(list);
        }
    }
}
