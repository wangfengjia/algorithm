package com.leetcode.www.middle.array;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode-220:存在重复元素 III
 * 给你一个整数数组 nums 和两个整数k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
 * 如果存在则返回 true，不存在返回 false。
 */
public class ContainsNearbyAlmostDuplicate {


    /**
     * 利用桶排序的思想
     * 对于元素x，它影响的区间是[x-t,x+t]。因此我们可以设定桶的大小为t+1。如果两个元素同属于一个桶，那么这两个元素就是符合条件的。如果两个元素属于相邻桶，那么就
     * 校验这两个元素的差值是否不超过t。如果两个元素不属于相同桶，也不属于相邻桶，那么这两个元素必然不符合条件
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean solution(int[] nums, int k, int t){

        int n = nums.length;
        Map<Long, Long> map = new HashMap<>();
        long w = (long) t + 1;
        for (int i = 0; i < n; i++){
            long id = getId(nums[i], w);
            if (map.containsKey(id)){
                return true;
            }
            //相邻桶
            if (map.containsKey(id - 1) && Math.abs(nums[i] - map.get(id - 1)) < w){
                return true;
            }
            if (map.containsKey(id + 1) && Math.abs(nums[i] - map.get(id + 1)) < w){
                return true;
            }
            map.put(id, (long) nums[i]);
            if (i >= k){
                //移除下标范围不在[max(0, i-k),i]内的桶
                map.remove(getId(nums[i-k], w));
            }
        }

        return false;
    }

    private long getId(long x, long w){
        if (x >= 0){
            return x / w;
        }

        return (x + 1) / w - 1;
    }

    public static void main(String[] args) {

        ContainsNearbyAlmostDuplicate duplicate = new ContainsNearbyAlmostDuplicate();
        int[] nums = {1,5,9,1,5,9};
        int k = 2;
        int t = 3;
        boolean ans = duplicate.solution(nums, k, t);
        System.out.println(ans);
    }
}
