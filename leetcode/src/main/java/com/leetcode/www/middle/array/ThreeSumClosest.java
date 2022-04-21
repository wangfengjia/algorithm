package com.leetcode.www.middle.array;

import java.util.Arrays;

/**
 * leetcode-16:最接近的三数之和
 * 给你一个长度为 n 的整数数组nums和一个目标值target。请你从nums中选出三个整数，使它们的和与target最接近。返回这三个数的和。
 * 假定每组输入只存在恰好一个解。
 */
public class ThreeSumClosest {

    /**
     * 使用三数之和的方法:排序加双指针，只是多维护一个变量来保存当前离target最接近的和，每个三元组的和去和当前最接近的去比较，然后去更新这个最接近的和
     * 复杂度分析
     *  时间复杂度:O(n^2),n为数组的长度，第一重循环枚举a需要O(n),双指针枚举b,c需要O(n),所以总的就是O(n^2)
     *  空间复杂度:O(log^n),排序需要O(log^n)的空间复杂度
     * @param nums
     * @param target
     * @return
     */
    public int solution(int[] nums, int target){

        Arrays.sort(nums);
        int n = nums.length;
        int best = 10000;

        for (int i = 0; i < n; i++){
            //保证和上一次枚举的元素不同
            if (i > 0 && nums[i] == nums[i-1]){
                continue;
            }

            int left = i + 1;
            int right = n - 1;
            while (left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == target){
                    return target;
                }

                //根据差值的绝对值来更新答案
                if (Math.abs(sum - target) < Math.abs(best - target)){
                    best = sum;
                }
                //左指针往右移动
                if (sum < target){
                    int leftTmp = left + 1;
                    //移动到下一个不相等的元素
                    while (leftTmp < right && nums[leftTmp] == nums[left]){
                        ++leftTmp;
                    }
                    left = leftTmp;
                }else {
                    int rightTmp = right - 1;
                    //移动到下一个不相等的元素
                    while (rightTmp > left && nums[rightTmp] == nums[right]){
                        --rightTmp;
                    }
                    right = rightTmp;
                }
            }
        }

        return best;
    }

    public static void main(String[] args) {

        ThreeSumClosest closest = new ThreeSumClosest();
        int[] nums = {0,0,0};
        int target = 1;
        int ans = closest.solution(nums, target);
        System.out.println(ans);
    }
}
