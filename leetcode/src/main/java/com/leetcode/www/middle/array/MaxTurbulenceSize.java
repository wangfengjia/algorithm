package com.leetcode.www.middle.array;

/**
 * leetcode-978:最长湍流子数组
 * 给定一个整数数组 arr，返回 arr的最大湍流子数组的长度。
 * 如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
 * 更正式地来说，当 arr的子数组A[i], A[i+1], ..., A[j]满足仅满足下列条件时，我们称其为湍流子数组：
     * 若i <= k < j：
         * 当 k为奇数时，A[k] > A[k+1]，且
         * 当 k 为偶数时，A[k] < A[k+1]；
     * 或 若i <= k < j：
         * 当 k 为偶数时，A[k] > A[k+1]，且
         * 当 k为奇数时，A[k] < A[k+1]。
 */
public class MaxTurbulenceSize {


    /**
     * 滑动窗口:假设窗口[left,right](0<=left<=right<=n-1)内构成一个湍流数组，然后考虑窗口的下一个位置。根据湍流数组的定义，当0<right<n-1时，有如下情况
     *         1. 如果nums[right-1] < nums[right]且nums[right] > nums[right+1]，则[left,right+1]也构成湍流子数组，此时right指针向右移动一位
     *         2. 如果nums[right-1] > nums[right]且nums[right] < nums[right+1]，则[left,right+1]也构成湍流子数组，此时right指针向右移动一位
     *         3. 否则[right-1, right+1]无法构成湍流子数组，当left<right时,[left,right+1]也无法构成湍流子数组，因此需要将left移动到right的位置
     * 复杂度分析
     *  时间复杂度:O(n),n是数组nums的长度,数组中的每个元素最多被访问一次
     *  空间复杂度:O(1)
     * @param nums
     * @return
     */
    public int solution(int[] nums){

        int n = nums.length;
        int left = 0;
        int right = 0;
        int ans = 1;

        //由于需要计算下标right+1,所以right需要小于n-1
        while (right < n - 1){
            if (left == right){
                if (nums[left] == nums[left + 1]){
                    ++left;
                }
                ++right;
            }else {
                if ((nums[right - 1] < nums[right]) && (nums[right] > nums[right + 1])){
                    ++right;
                }else if ((nums[right - 1] > nums[right]) && (nums[right] < nums[right + 1])){
                    ++right;
                }else {
                    left = right;
                }
            }

            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }

    /**
     * 动态规划:
     *      状态定义
     *          1. dp[i][0]表示以nums[i]结尾，且nums[i-1] > nums[i]的湍流子数组的长度
     *          2. dp[i][1]表示以nums[i]结尾，且nums[i-1] < nums[i]的湍流子数组的长度
     *      状态转移方程
     *          1. 当nums[i-1] > nums[i]时
         *          1. dp[i][0] = dp[i-1][1]+1。要使以nums[i]结尾，且nums[i-1]>nums[i]的子数组是湍流子数组，则需要以nums[i-1]结尾且
     *                 num[i-2]<num[i-1]的子数组是湍流子数组
     *              2. dp[i][1] = 1;
 *              2. 当nums[i-1] < nums[i]时
         *          1. dp[i][1] = dp[i-1][0]+1。要使以nums[i]结尾，且nums[i-1]<nums[i]的子数组是湍流子数组，则需要以nums[i-1]结尾且
     *                  num[i-2]>nums[i]的子数组是湍流子数组，则需要nums[i-1]结尾且num[i-2]>num[i-1]的子数组是湍流子数组
     *              2. dp[i][0] = 1;
     *      边界条件:以下标为0结尾的湍流子数组的最大长度为1，因此dp[0][0]=dp[0][1] = 1
     *  复杂度分析
     *      时间复杂度:O(n),n是数组nums的长度
     *      空间复杂度:O(n),n是数组nums的长度，状态数组所消耗的空间
     * @param nums
     * @return
     */
    public int solutionV2(int[] nums){

        int n = nums.length;
        int[][] dp = new int[n][2];
        dp[0][0] = dp[0][1] = 1;

        for (int i = 1; i < n; i++){
            dp[i][0] = dp[i][1] = 1;
            if (nums[i - 1] > nums[i]){
                dp[i][0] = dp[i-1][1] + 1;
            }else if (nums[i - 1] < nums[i]){
                dp[i][1] = dp[i-1][0] + 1;
            }
        }

        int ans = 1;
        for (int i = 0; i < n; i++){
            ans = Math.max(ans, dp[i][0]);
            ans = Math.max(ans, dp[i][1]);
        }

        return ans;
    }


    public static void main(String[] args) {

        MaxTurbulenceSize turbulenceSize = new MaxTurbulenceSize();
        int[] nums = {9,4,2,10,7,8,8,1,9};
        int ans = turbulenceSize.solution(nums);
        System.out.println(ans);

        int ans2 = turbulenceSize.solutionV2(nums);
        System.out.println(ans2);
    }
}
