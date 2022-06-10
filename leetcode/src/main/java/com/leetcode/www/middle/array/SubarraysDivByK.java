package com.leetcode.www.middle.array;


import java.util.HashMap;
import java.util.Map;

/**
 * leetcode-974:和可被 K 整除的子数组
 * 给定一个整数数组 nums 和一个整数 k ，返回其中元素之和可被 k 整除的（连续、非空） 子数组 的数目。
 * 子数组 是数组的 连续 部分。
 */
public class SubarraysDivByK {


    /**
     * 前缀和+哈希表
     * 我们使用preSum[i]来表述元素nums[i]的前缀和，要使得区间(i,j)的所有元素之和被k整除，根据同余定理,pre[j] % k == pre[i] % k。同时使用一个哈希表来记录
     * 各种前缀和对k取模的结果值出现的次数
     *
     * 复杂度分析
     *  时间复杂度:O(n),n是数组nums的长度,数组中每个元素被访问一次
     *  空间复杂度:O(min(n,k))。即哈希表所需要的空间。当n<=k时，最多有n个前缀和，因此哈希表中最多有n+1个键值对。当n>k时，最多有k个不同余数，因此哈希表中最多有
     *           k个键值对，也就是哈希表需要的空间取决于n和k的较小值
     * @param nums
     * @param k
     * @return
     */
    public int solution(int[] nums, int k){

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int preSum = 0;
        int ans = 0;
        for (int num : nums){
            preSum += num;
            //由于前缀和可能出现负数，我们需要将其加上k，从而使得对前缀和对k取模的结果为正数
            int mod = (preSum % k + k) % k;
            int cur = map.getOrDefault(mod, 0);
            ans += cur;
            map.put(mod, cur + 1);
        }

        return ans;
    }


    public static void main(String[] args) {

        SubarraysDivByK subarraysDivByK = new SubarraysDivByK();
        int[] nums = {4,5,0,-2,-3,1};
        int k = 5;
        int ans = subarraysDivByK.solution(nums, k);
        System.out.println(ans);
    }
}
