package com.leetcode.www.middle.array;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode-523:连续的子数组和
 * 给你一个整数数组 nums 和一个整数k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：
     * 子数组大小 至少为 2 ，且
     * 子数组元素总和为 k 的倍数。
     * 如果存在，返回 true ；否则，返回 false 。
 * 如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。0 始终视为 k 的一个倍数。
 */
public class CheckSubarraySum {


    /**
     * 前缀和和哈希表:当pre[i]-pre[j]为k的倍数时，pre[i]和pre[j]除以k的余数是相同的,因此只需要计算每个前缀和除以k的余数就可以，然后使用哈希表来记录每个余数
     *              第一次出现的下标。并且规则空的前缀元素的和为0，空的前缀的结束下标为-1，初始时存入哈希表中
     * 复杂度分析
     *  时间复杂度:O(n),n是数组nums的长度
     *  空间复杂度:O(min(n,k)),n是数组的长度，空间复杂度取决于哈希表，哈希表中存储每个余数第一次出现的下标，最多有min(n,k)个余数
     * @param nums
     * @param k
     * @return
     */
    public boolean solution(int[] nums, int k){

        int n = nums.length;
        if (n < 2){
            return false;
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int remainder = 0;
        for (int i = 0; i < n; i++){
            remainder = (remainder + nums[i]) % k;
            if (map.containsKey(remainder)){
                Integer preIndex = map.get(remainder);
                if (i - preIndex >= 2){
                    return true;
                }
            }else {
                map.put(remainder, i);
            }
        }

        return false;
    }

    public boolean solutionV2(int[] nums, int k){

        int n = nums.length;
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; i++){
            preSum[i] = preSum[i-1] + nums[i-1];
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++){
            int mod = preSum[i] % k;
            if (map.containsKey(mod)){
                Integer preIndex = map.get(mod);
                if (i - preIndex >= 2){
                    return true;
                }
            }else {
                map.put(mod, i);
            }
        }

        return false;
    }

    public static void main(String[] args) {


        CheckSubarraySum checkSubarraySum = new CheckSubarraySum();
        int[] nums = {23,2,4,6,7};
        int k = 6;
        boolean ans = checkSubarraySum.solution(nums, k);
        System.out.println(ans);
    }
}
