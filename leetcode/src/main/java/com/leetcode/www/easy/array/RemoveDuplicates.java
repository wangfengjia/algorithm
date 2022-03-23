package com.leetcode.www.easy.array;

/**
 * 给你一个升序排列的数组nums，请你原地删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的相对顺序应该保持一致。不要使用额外的空间，
 * 你必须在原地修改输入数组 并在使用O(1)额外空间的条件下完成。
 * 方法一(双指针):使用快慢指针，由于这个数组是有序的，所以重复元素的下标是连续的，这样就可以移动fast指针，同时判断nums[fast] 是否等于nums[fast-1],当相等的时候
 *              就移动快指针，但是不移动慢指针。当不相等时就把fast指针指向的元素赋值给slow下标指向的位置。这样就当fast指针前面有重复元素时，就可以把重复元素移除掉
 */
public class RemoveDuplicates {


    public int solution(int[] arr){

        int len = arr.length;
        if (len == 0){
            return 0;
        }

        int fast = 1;
        int slow = 1;
        while (fast < len){
            if (arr[fast] != arr[fast - 1]){
                arr[slow++] = arr[fast++];
            }else {
                fast++;
            }
        }

        return slow;
    }

    public static void main(String[] args) {

        int[] nums = new int[]{1,1,2};
        RemoveDuplicates removeDuplicates = new RemoveDuplicates();
        int len = removeDuplicates.solution(nums);
        System.out.println(len);
    }
}
