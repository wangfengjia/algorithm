package com.leetcode.www.middle.array;


import java.util.*;

/**
 * leetcode-47:全排列II
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 */
public class PermuteUnique {

    /**
     * 由于有相同的元素，按照原来的回溯方法，得到的结果集会有重复的列表，所以需要在回溯过程中，在会产生重复结果集的地方进行剪枝。剪枝的方法是:可以在搜索前就对候选数组
     * 进行排序，一旦发现某个分支搜索下去可能搜索到重复元素就停止搜索，这样结果集中不会包含重复列表
     * 复杂度分析
     *  时间复杂度:O(N*N!),N为数组的长度
     *  空间复杂度:O(N*N!)
     * @param nums
     * @return
     */
    public List<List<Integer>> solution(int[] nums){

        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        if (len == 0){
            return ans;
        }

        //排序是剪枝的前提
        Arrays.sort(nums);
        boolean[] visited = new boolean[len];
        Deque<Integer> path = new ArrayDeque<>();
        dfs(nums, len, 0, visited, path, ans);
        return ans;
    }


    private void dfs(int[] nums, int len, int index, boolean[] visited, Deque<Integer> path, List<List<Integer>> ans){

        if (index == nums.length){
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < len; i++){
            // 判断当前元素是否已经选择过,没选择过才能被选择
            if (!visited[i]){

                //剪枝，使结果集中不会出现重复的列表
                if (i > 0 && nums[i] == nums[i - 1] && !visited[i-1]){
                    continue;
                }
                //选择当前元素
                path.addLast(nums[i]);
                visited[i] = true;
                dfs(nums, len, index + 1, visited, path, ans);
                //撤销选择
                visited[i] = false;
                path.removeLast();
            }
        }
    }


    public static void main(String[] args) {

        PermuteUnique permuteUnique = new PermuteUnique();
        int[] nums = {1,2,3};
        List<List<Integer>> ans = permuteUnique.solution(nums);
        for (List<Integer> collect : ans){
            System.out.println(collect);
        }
    }
}
