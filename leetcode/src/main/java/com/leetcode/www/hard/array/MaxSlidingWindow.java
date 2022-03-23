package com.leetcode.www.hard.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * leetcode239(滑动窗口最大值):给你一个整数数组 nums，有一个大小为k的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的k个数字。滑动窗口
 * 每次只向右移动一位。返回 滑动窗口中的最大值 。
 */
public class MaxSlidingWindow {


    /**
     * 使用优先队列(大顶堆):首先将数组前k个元素放入堆中，每当我们向右移动窗口时，就可以把一个新的元素放入优先级队列中，这个时候堆顶元素就是堆中所有元素的最大值。
     *                   但是，由于这个最大值可能不在窗口范围内，在这种情况下，这个最大值在数组中的位置是在滑动窗口左边界的左侧。因此，我们后续移动窗口时，
     *                   这个值就永远不会出现在滑动窗口中了，所以可以将这个最大值从优先队列中移除
     * 复杂度
     *      时间复杂度:O(nlog^n),n为数组的长度，将一个元素放入优先级队列的时间复杂度为O(log^n),所以总的时间复杂度为O(nlog^n)
     *      空间复杂度:O(n),优先级队列所使用的空间
     * @return
     */
    public int[] solutionV1(int[] nums, int k){

        int length = nums.length;
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] pair1, int[] pair2) {
                return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
            }
        });

        for (int i = 0; i < k; i++){
            priorityQueue.offer(new int[]{nums[i], i});
        }
        int[] ans = new int[length - k + 1];
        ans[0] = priorityQueue.peek()[0];
        for (int i = k; i < length; i++){
            priorityQueue.offer(new int[]{nums[i], i});
            //将不在滑动窗口的堆顶元素移除
            while (priorityQueue.peek()[1] <= (i - k)){
                priorityQueue.poll();
            }
            ans[i - k + 1] = priorityQueue.peek()[0];
        }

        return ans;
    }

    public static void main(String[] args) {

        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
        int k = 3;
        int[] ints = new MaxSlidingWindow().solutionV1(nums, k);
        System.out.println(Arrays.toString(ints));
    }
}
