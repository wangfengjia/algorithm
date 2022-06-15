package com.leetcode.www.hard.array;


import java.util.Arrays;

/**
 * leetcode-719:找出第 K 小的数对距离
 * 数对 (a,b) 由整数 a 和 b 组成，其数对距离定义为 a 和 b 的绝对差值。
 * 给你一个整数数组 nums 和一个整数 k ，数对由 nums[i] 和 nums[j] 组成且满足 0 <= i < j < nums.length 。返回 所有数对距离中 第 k 小的数对距离。
 */
public class SmallestDistancePair {


    /**
     * 排序+二分查找+双指针
     * 先将nums排序，排序后，数组数对的距离的范围区间是[0, nums[n-1] - nums[0]],第k小的数对距离在这个区间中，因此可以在这个区间进行二分查找。
     * 给定距离mid,计算所有小于等于mid的数对数目cnt，这个可以利用双指针来计算:初始左端点i=0，从小到大枚举所有数对的右端点j，移动左端点直到
     * nums[j] - nums[i] <= mid,此时cnt = j - i,计算所有满足条件的cnt之和。再用cnt和k进行比较来决定往左还是往右去查找
     *
     * 复杂度分析
     *  时间复杂度:O(n * (log^n + log^D)),n是数组nums的长度，D=max(nums)-min(nums)。外层二分查找需要O(log^D)的时间复杂度，内层双指针需要O(n),
     *           排序的平均时间复杂度为O(nlog^n)
     *  空间复杂度:O(log^n),排序过程中栈的空间开销
     * @param nums
     * @param k
     * @return
     */
    public int solution(int[] nums, int k){

        Arrays.sort(nums);
        int n = nums.length;
        int left = 0;
        int right = nums[n - 1] - nums[0];

        while (left <= right){

            int cnt = 0;
            int mid = (left + right) / 2;
            for (int i = 0, j = 0; j < n; j++){
                while (nums[j] - nums[i] > mid){
                    ++i;
                }
                cnt += j - i;
            }

            if (cnt >= k){
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }

        return left;
    }


    public static void main(String[] args) {

        SmallestDistancePair distancePair = new SmallestDistancePair();
        int[] nums = {1,3,1};
        int k = 1;
        int ans = distancePair.solution(nums, k);
        System.out.println(ans);
    }
}
