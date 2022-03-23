package com.leetcode.www.middle.dynamic;

/**
 * 乘积最大子数组:给你一个整数数组nums,请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * 方法一(动态规划):有一个类似的问题，就是和最大子数组，这个问题的状态转移方程为f(i) = max(f(i-1) + nums[i], nums[i]).按照类推，乘积最大子数组的状态转移方程
 *                为f(i) = max(f(i-1) * nums[i], nums[i]).但是这个状态转移方程是错误的，因为乘积是有负负得正的情况，比如nums={5,6,−3,4,−3}，按照这个状态
 *                转移方程，f(i)对应的序列是{5,30,-3,4,-3},但是正常答案应该是全部元素乘起来。
 *                我们可以根据正负性来讨论，假如当前元素为负数，我们就希望前面一个元素结尾的某个段的最大积也是一个负数，这样就负负得正了,并且希望这个积负得越多，这样当前
 *                元素的最大积也就会越大。同理，假如当前元素是正数，也就希望前面一个元素结尾的某个段的最大积是一个正数，并且尽可能大。于是，我们可以维护一个minF(i),
 *                它表示以第i个元素结尾的乘积最小子数组的乘积，所以状态转移方程会是这样
 *                MaxF(i) = max(MaxF(i-1) * nums[i], MinF(i-1)*nums[i], nums[i]);
 *                MinF(i) = min(MaxF(i-1) * nums[i], MinF(i-1)*nums[i], nums[i]);
 */
public class MaxProduct {


    public int solution(int[] nums){

        int length = nums.length;
        int[] maxF = new int[length];
        int[] minF = new int[length];

//        maxF[0] = nums[0];
//        minF[0] = nums[0];
        System.arraycopy(nums, 0, maxF, 0, length);
        System.arraycopy(nums, 0, minF, 0, length);
        for (int i = 1; i < length; i++){
            maxF[i] = Math.max(maxF[i-1] * nums[i], Math.max(minF[i-1] * nums[i], nums[i]));
            minF[i] = Math.min(maxF[i-1] * nums[i], Math.min(minF[i-1] * nums[i], nums[i]));
        }

        int ans = maxF[0];
        for (int i = 1; i < length; i++){
            ans = Math.max(maxF[i], ans);
        }

        return ans;
    }

    /**
     * 上面解法的优化版(优化空间):由于第i个元素的状态只会和第i-1个元素状态有关，利用滚动数组的思想，我们可以使用两个变量来维护第i-1的元素的状态,maxF和minF
     * @param nums
     * @return
     */
    public int solutionV2(int[] nums){

        int ans = nums[0];
        int maxF = nums[0];
        int minF = nums[0];
        int length = nums.length;

        for (int i = 1; i < length; i++){

            int max = maxF;
            int min = minF;
            maxF = Math.max(max * nums[i], Math.max(min * nums[i], nums[i]));
            minF = Math.min(max * nums[i], Math.min(min * nums[i], nums[i]));
            ans = Math.max(maxF, ans);
        }

        return ans;
    }

    public static void main(String[] args) {

        int[] nums = new int[]{5,6,-3,4,-3};
        MaxProduct maxProduct = new MaxProduct();
        int max = maxProduct.solutionV2(nums);
        System.out.println(max);
    }
}
