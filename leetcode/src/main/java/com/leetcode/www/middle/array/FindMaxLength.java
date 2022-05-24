package com.leetcode.www.middle.array;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode-525:连续数组
 * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 */
public class FindMaxLength {


    /**
     * 前缀和+哈希表
     * 将数组中值为0的元素变为-1，这个问题就能转化为 求最长一段区间和为0的子数组。就可以使用前缀和+哈希表。哈希表里面维护的是某个前缀和出现的最小下标
     * 复杂度分析
     *  时间复杂度:O(n)
     *  空间复杂度:O(n)
     * @param nums
     * @return
     */
    public int solution(int[] nums){

        int n = nums.length;
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; i++){
            preSum[i] = preSum[i-1] + (nums[i-1] == 0 ? -1 : 1);
        }

        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        //子数组长度至少为2，所以循环从2开始
        for (int i = 2; i <= n; i++){
            if (!map.containsKey(preSum[i-2])){
                map.put(preSum[i-2], i-2);
            }
            if (map.containsKey(preSum[i])){
                ans = Math.max(ans, i - map.get(preSum[i]));
            }
        }

        return ans;
    }

    public static void main(String[] args) {

        FindMaxLength maxLength = new FindMaxLength();
        int[] nums = {0,1,0};
        int ans = maxLength.solution(nums);
        System.out.println(ans);
    }
}
