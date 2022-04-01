package com.leetcode.www.middle.array;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode-560:和为k的子数组
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 */
public class SubArraySum {

    /**
     * 前缀和+哈希表:用pre[i]表示[0...i]所有元素的和，而pre[i] = pre[i-1]+nums[i],如果[j..i]这个子数组和为k，那么pre[i]-pre[j-1] = k,推导得到
     * pre[j-1] = pre[i] - k。我们可以用哈希表来记录各种前缀和的值的次数，然后去这个哈希表查找pre[j]出现的次数。枚举数组，获取各种pre[j]出现的次数，统计起来
     * 就是子数组的个数
     * 复杂度分析
     *  1. 时间复杂度:O(n),其中n为数组的长度，遍历数组的时间复杂度为O(n)
     *  2. 空间复杂度:O(n),n为数组的长度。哈希表在最坏的情况下可能有n个不同的键值，因此需要O(n)的时间复杂度
     * @param nums
     * @param k
     * @return
     */
    public int solution(int[] nums, int k){

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int count = 0;
        int pre = 0;

        for (int num : nums){
            pre += num;
            if (map.containsKey(pre - k)){
                count += map.get((pre - k));
            }
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }

        return count;
    }


    public static void main(String[] args) {

        SubArraySum subArraySum = new SubArraySum();
        int[] nums = new int[]{1,2,3};
        int k = 3;
        int ans = subArraySum.solution(nums, k);
        System.out.println(ans);
    }
}
