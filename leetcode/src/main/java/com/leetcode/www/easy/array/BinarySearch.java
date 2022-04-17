package com.leetcode.www.easy.array;

/**
 * leetcode-704:二分查找
 */
public class BinarySearch {


    public int solution(int[] nums, int target){

        int low = 0;
        int high = nums.length - 1;

        while (low <= high){
            int mid = low + (high - low) / 2;
            if (nums[mid] > target){
                high = mid - 1;
            }else if (nums[mid] < target){
                low = mid + 1;
            }else {
                return mid;
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        BinarySearch binarySearch = new BinarySearch();
        int[] nums = {1,2,3,4,6,8};
        int target = 6;
        int ans = binarySearch.solution(nums, target);
        System.out.println(ans);
    }
}
