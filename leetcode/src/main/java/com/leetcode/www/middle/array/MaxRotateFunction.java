package com.leetcode.www.middle.array;

import java.util.Arrays;

/**
 * leetcode-396:旋转函数
 * 给定一个长度为 n 的整数数组nums。
 * 假设arrk是数组nums顺时针旋转 k 个位置后的数组，我们定义nums的 旋转函数F为：
    * F(k) = 0 * arrk[0] + 1 * arrk[1] + ... + (n - 1) * arrk[n - 1]
 * 返回F(0), F(1), ..., F(n-1)中的最大值。
 * 生成的测试用例让答案符合32 位 整数。
 */
public class MaxRotateFunction {


    /**
     * 滑动窗口+前缀和
     * 根据旋转数组的定义，可以将nums数组进行复制拼接，得到长度2*n的数组，在新数组上任意一个长度为n的数组对应一个旋转数组。当滑动窗口向右移动一位时，那就是滑动窗口
     * 的右端点来到了i+n的位置，左端点来到了i+1的位置。也就是需要增加新右端点的值(nums[i+n] * (n-1)),减去旧左端点的值，即减少0，和更新新旧窗口的公共部分
     * [i+1,i+n-1],根据观察发现，随着窗口右移，每一位公共部分的权值系数都会减去1，所以公共部分的差值为sum(nums[idx]),idx的范围为[i+1,i+n-1],可以使用
     * 前缀和进行优化
     * 实际上，并不需要对数组nums进行复制拼接，只需要在计算前缀和数组preSum时对下标进行简单处理
     *
     * 复杂度分析
     *  时间复杂度:O(n),n是数组nums的长度
     *  空间复杂度:O(n),n是数组nums的长度
     * @param nums
     * @return
     */
    public int solution(int[] nums){

        int n = nums.length;
        int[] preSum = new int[n * 2 + 10];
        for (int i = 1; i <= 2 * n; i++){
            preSum[i] = preSum[i-1] + nums[(i-1) % n];
        }

        int ans = 0;
        for (int i = 1; i <= n; i++){
            ans += nums[i-1] * (i-1);
        }
        for (int i = n + 1, cur = ans; i < 2 * n; i++){
            cur += nums[(i - 1) % n] * (n - 1);
            cur -= preSum[i - 1] - preSum[i - n];
            if (cur > ans){
                ans = cur;
            }
        }

        return ans;
    }

    /**
     * 迭代
     * f(0) = 0*nums[0] + 1*nums[1] + .. + (n-1)*nums[n-1]
     * f(1) = 1*nums[0] + 2*nums[2] + .. + 0 * nums[n-1]
     * 由此可推:对于1<=k<n,f(k) = f(k-1) + numSum - n*nums[n-k]
     * 复杂度分析
     *  时间复杂度:O(n),n是数组nums的长度
     *  空间复杂度:O(1)
     * @param nums
     * @return
     */
    public int solutionV2(int[] nums){

        int n = nums.length;
        int numSum = Arrays.stream(nums).sum();
        int cur = 0;
        for (int i = 0; i < n; i++){
            cur += i * nums[i];
        }

        int ans = cur;
        for (int i = n - 1; i > 0; i--){
            cur += numSum - n * nums[i];
            ans = Math.max(ans, cur);
        }

        return ans;
    }

    public static void main(String[] args) {

        MaxRotateFunction rotateFunction = new MaxRotateFunction();
        int[] nums = {4,3,2,6};
        int ans = rotateFunction.solutionV2(nums);
        System.out.println(ans);
    }
}
