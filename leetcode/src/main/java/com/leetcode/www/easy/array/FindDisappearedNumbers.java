package com.leetcode.www.easy.array;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * leetcode448:找到所有数组中消失的数字
 * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
 */
public class FindDisappearedNumbers {

    /**
     * 哈希表
     * @param nums
     * @return
     */
    public List<Integer> solution(int[] nums){

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums){
            map.put(num, 1);
        }

        List<Integer> disappear = new ArrayList<>();
        for (int i = 1; i <= nums.length; i++){
            if (!map.containsKey(i)){
                disappear.add(i);
            }
        }

        return disappear;
    }

    public static void main(String[] args) {

        FindDisappearedNumbers disappearedNumbers = new FindDisappearedNumbers();
        int[] nums = new int[]{1,1};
        List<Integer> ans = disappearedNumbers.solution(nums);
        System.out.println(ans);
    }
}
