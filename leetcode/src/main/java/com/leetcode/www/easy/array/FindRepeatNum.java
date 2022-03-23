package com.leetcode.www.easy.array;

/**
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，
 * 也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。'
 *
 * 思路
 * 1.因为数组元素的大小范围是小于等于个数，所以将元素放到下标为元素值的位置，如果在放置过程中，
 *      a.发现要放置的位置的元素值与要放置的值相等，则这个就是重复重复元素，
 *      b.如果不相等，则将元素放置到这个这个元素值对应的下标
 */
public class FindRepeatNum {


    public int findRepeatNum(int[] nums){

        int i = 0;
        while (i < nums.length){
            //直到一个下标i的元素值就是i时，才不再需要执行交换操作，查看下一个元素
            if (nums[i] == i){
                ++i;
                continue;
            }

            if (nums[nums[i]] == nums[i]){
                return nums[i];
            }

            int tmp = nums[i];
            nums[i] = nums[nums[i]];
            nums[tmp] = tmp;
        }

        //不存在重复元素
        return -1;
    }


    public static void main(String[] args) {

        int[] nums = {2, 3, 1, 0, 2, 5, 3};
        FindRepeatNum findRepeatNum = new FindRepeatNum();
        int repeatNum = findRepeatNum.findRepeatNum(nums);
        System.out.println(repeatNum);
    }
}
