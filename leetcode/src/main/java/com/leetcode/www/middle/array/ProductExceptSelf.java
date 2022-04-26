package com.leetcode.www.middle.array;

import java.util.Arrays;

/**
 * leetcode-238:除自身以外数组的乘积
 * 给你一个整数数组nums，返回 数组answer，其中answer[i]等于nums中除nums[i]之外其余各元素的乘积。
 * 题目数据 保证数组nums之中任意元素的全部前缀元素和后缀的乘积都在 32 位 整数范围内。
 * 请不要使用除法，且在O(n) 时间复杂度内完成此题。
 *
 */
public class ProductExceptSelf {


    /**
     * 左右两侧乘积列表:先通过遍历数组，求得任意一个元素的左右两侧乘积，分别用left和right来表示。那么任意一个元素除自身以外数组的乘积就会等于整个元素的左侧乘积乘以右侧乘积
     * 复杂度分析
     *  时间复杂度:O(n),n表示数组的长度
     *  空间复杂度:O(n),n表示数组的长度
     * @param nums
     * @return
     */
    public int[] solution(int[] nums){

        int n = nums.length;

        int[] left = new int[n];
        int[] right = new int[n];
        int[] ans = new int[n];


        //索引为0的元素左侧没有任何元素，所以left[0] = 1
        left[0] = 1;
        for (int i = 1; i < n; i++){
            //第i个元素左侧元素的乘积等于第i-1个元素左侧的乘积乘以第i-1个元素本身
            left[i] = nums[i-1] * left[i-1];
        }
        //索引为n-1的元素右侧没有任何元素，所以right[n-1]=1
        right[n-1] = 1;
        for (int i = n - 2; i >= 0; i--){
            right[i] = right[i+1]*nums[i+1];
        }

        for (int i = 0; i < n; i++){
            ans[i] = left[i] * right[i];
        }

        return ans;
    }


    /**
     * 空间复杂度为O(1)的写法
     * @param nums
     * @return
     */
    public int[] solutionV2(int[] nums){

        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, 1);
        int prefix = 1;
        int suffix = 1;

        //对于每一个元素i，这个元素的左侧乘积和数组中和这个元素对称元素的右侧乘积，将结果保存到ans数组里面，由于ans数组不算在空间复杂度，所以空间复杂度为O(1)
        for (int i = 0; i < n; i++){
            ans[i] *= prefix;
            ans[n-i-1] *= suffix;
            prefix *= nums[i];
            suffix *= nums[n-i-1];
        }

        return ans;
    }

    public static void main(String[] args) {

        ProductExceptSelf exceptSelf = new ProductExceptSelf();
        int[] nums = {1,2,3,4};
        int[] nums1 = {-1,1,0,-3,3};
        int[] ans = exceptSelf.solutionV2(nums);
        System.out.println(Arrays.toString(ans));
    }
}
