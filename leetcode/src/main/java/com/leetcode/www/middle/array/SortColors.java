package com.leetcode.www.middle.array;

import java.util.Arrays;

/**
 * leetcode-75:颜色分类
 * 给定一个包含红色、白色和蓝色、共n个元素的数组nums，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。我们使用整数 0、1 和 2 分别
 * 表示红色、白色和蓝色。必须在不使用库的sort函数的情况下解决这个问题。
 *
 */
public class SortColors {


    /**
     * 双指针:使用双指针p0,p1来来交换0和1
     *       1. 如果找到1，那么就将其与nums[p1]进行交换，并且将p1向后移动一个位置
     *       2. 由于可能出现先找到p1的情况，也就是p0<p1，假如这时找到0，直接和nums[p0]进行交换，就可能会把1交换出去，导致不正确的答案。当出现这种情况时，
     *          当前元素nums[i]和nums[p0]交换后，nums[i]要和nums[p1]做一次交换
     *
     * 复杂度分析
     *  1. 时间复杂度:O(N),N为数组的长度，对数组做一次遍历
     *  2. 空间复杂度:O(1)
     * @param nums
     */
    public void solution(int[] nums){

        int n = nums.length;
        int p0 = 0;
        int p1 = 0;

        for (int i = 0; i < n; i++){
            if (nums[i] == 1){
                int tmp = nums[p1];
                nums[p1] = nums[i];
                nums[i] = tmp;
                ++p1;
            }else if (nums[i] == 0){
                int tmp = nums[p0];
                nums[p0] = nums[i];
                nums[i] = tmp;
                // 当p0<p1时，p0指向的是1，交换0的时候把这个1换出去了，需要换到下一个1的位置p1
                if (p0 < p1){
                    int temp = nums[p1];
                    nums[p1] = nums[i];
                    nums[i] = temp;
                }

                ++p0;
                ++p1;
            }
        }
    }

    public static void main(String[] args) {

        SortColors sortColors = new SortColors();
        int[] nums = new int[]{2,0,2,1,1,0};
        sortColors.solution(nums);
        System.out.println(Arrays.toString(nums));
    }
}
