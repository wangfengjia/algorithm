package com.leetcode.www.middle.array;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode-137:只出现一次的数字 II
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 */
public class SingleNumberV2 {

    /**
     * 使用哈希表去统计每个数字出现的次数，然后遍历哈希表得到只出现一次的数字
     * 复杂度分析
     *  时间复杂度:O(n),n表示数组的长度
     *  空间复杂度:O(n),统计数字出现的次数的哈希表所使用的空间
     * @param nums
     * @return
     */
    public int solution(int[] nums){

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()){
            Integer key = entry.getKey();
            Integer val = entry.getValue();
            if (val == 1){
                ans = key;
            }
        }

        return ans;
    }

    public static void main(String[] args) {

        SingleNumberV2 singleNumberV2 = new SingleNumberV2();
        int[] nums = {2,2,3,2};
        int ans = singleNumberV2.solution(nums);
        System.out.println(ans);
    }
}
