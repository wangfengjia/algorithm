package com.leetcode.www.easy.array;

import java.util.Arrays;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 */
public class MoveZeroes {


    /**
     * 双指针
     * @param nums
     */
    public void solution(int[] nums){

        int left = 0;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] != 0){
                int tmp = nums[left];
                nums[left++] = nums[i];
                nums[i] = tmp;
            }
        }
    }

    public static void main(String[] args) {

        int[] arr = new int[]{0,1,0,3,12};
        MoveZeroes moveZeroes = new MoveZeroes();
        moveZeroes.solution(arr);
        System.out.println(Arrays.toString(arr));
    }
}
