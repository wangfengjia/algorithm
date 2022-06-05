package com.leetcode.www.middle.array;


/**
 * leetcode-275:H 指数 II
 * 给你一个整数数组 citations ，其中 citations[i] 表示研究者的第 i 篇论文被引用的次数，citations 已经按照升序排列。计算并返回该研究者的 h指数。
 * h 指数的定义：h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （n 篇论文中）总共有 h 篇论文分别被引用了至少 h 次。且其余的 n - h篇论文每篇被引用次数不超过 h 次。
 * 提示：如果 h 有多种可能的值，h 指数 是其中最大的那个。
 * 请你设计并实现对数时间复杂度的算法解决此问题。
 */
public class HIndex {


    /**
     * 二分查找
     * 根据h指数的定义和citations数组是有序的，可以利用二分查找来做。在区间[left,right]找一个位置mid,根据h指数的定义，使用nun[mid]的值和位置mid到数组最后
     * 一个元素的长度(len-mid)进行比较。如果nums[mid] < (len-mid),则应该往右边找，否则往左边找。
     * 也就是这个题目找一个分割线,分割线满足条件:分割线右边的最少引用次数 >= 分割线右边的论文篇数
     *
     * 复杂度分析
     *  时间复杂度:O(log^n),n是数组citations的长度，二分查找的时间复杂度为O(log^n)
     *  空间复杂度:O(1)
     */
    public int solution(int[] citations){

        int len = citations.length;
        if (citations[len - 1] == 0){
            return 0;
        }

        int left = 0;
        int right = len - 1;
        while (left < right){

            int mid = (left + right) / 2;
            if (citations[mid] < (len - mid)){
                left = mid + 1;
            }else {
                right = mid;
            }
        }

        return len - left;
    }

    public static void main(String[] args) {

        HIndex hIndex = new HIndex();
        int[] nums = {0,1,3,5,6};
        int sn = hIndex.solution(nums);
        System.out.println(sn);
    }
}
