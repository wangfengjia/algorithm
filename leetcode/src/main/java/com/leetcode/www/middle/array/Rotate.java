package com.leetcode.www.middle.array;

import java.util.Arrays;

/**
 * leetcode-189:轮转数组
 * 给你一个数组，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 */
public class Rotate {

    /**
     * 数组翻转:先将数组翻转，这样尾部k % n个元素就移动到了数组头部，然后再翻转[0,k % n - 1]和[k % n, n-1]这两个区间的元素就可以得到答案
     *        当nums = [1,2,3,4,5,6,7], k = 3,过程是如下[7,6,5,4,3,2,1] -> [5,6,7,4,3,2,1] -> [5,6,7,1,2,3,4]
     * 复杂度分析
     *  时间复杂度:O(n),n为数组的长度
     *  空间复杂度:O(1)
     * @param nums
     * @return
     */
    public void solution(int[] nums, int k){

        int n = nums.length;

        reverse(nums, 0, n - 1);
        reverse(nums, 0, k % n - 1);
        reverse(nums, k % n, n - 1);
    }


    private void reverse(int[] nums, int start, int end){

        while (start < end){
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            ++start;
            --end;
        }
    }

    public static void main(String[] args) {

        Rotate rotate = new Rotate();
        int[] nums = {1,2,3,4,5,6,7};
        int k = 3;
        rotate.solution(nums, k);
        System.out.println(Arrays.toString(nums));
    }
}
