package com.leetcode.www.middle.array;

/**
 * leetcode-153:寻找旋转排序数组中的最小值
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
     * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
     * 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 * 你必须设计一个时间复杂度为O(log n) 的算法解决此问题。
 */
public class FindMinInRotationArray {

    /**
     * 二分查找:输入的经过旋转排序之后的数组有这样的特点:对于数组最后一个元素x，最小值右侧的元素都会小于x，最小值左侧的元素都会大于x，通过利用这个性质，可以通过
     * 二分查找找到最小值，二分查找的过程如下(左边界为low，右边界为high，区间的中点为pivot)
     *      1. 如果nums[pivot] < nums[high],则nums[pivot]是最小值右侧的元素，则可以忽略二分查找区间的右半部分
     *      2. 如果nums[pivot] > nums[high],则nums[pivot]是最小值左侧的元素，则可以忽略二分查找区间的左半部分
     *      3. 由于当前数组没有重复元素，并且只要当前二分查找的区间长度大于1，pivot就不会与high重合，而如果当前区间的长度为1，则可以结束二分查找，就是找到了
     *         最小值
     * 复杂度分析
     *  时间复杂度:O(log^n),二分查找的时间复杂度
     *  空间复杂度:O(1)
     * @param nums
     * @return
     */
    public int solution(int[] nums){

        int low = 0;
        int high = nums.length - 1;

        // 当low==high时，这时二分查找的区间长度为1，则可以结束二分查找了，找到了最小值
        while (low < high){
            int pivot = low + (high - low) / 2;
            if (nums[pivot] < nums[high]){
                high = pivot;
            }else {
                low = pivot + 1;
            }
        }

        return nums[low];
    }
}
