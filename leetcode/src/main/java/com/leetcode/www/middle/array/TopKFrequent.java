package com.leetcode.www.middle.array;

import java.util.*;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 */
public class TopKFrequent {


    /**
     * 遍历这个数组，利用哈希表记录每个数字出现的次数。然后创建一个小顶堆，然后去遍历哈希表。有以下几种处理方式
     *      1. 当堆里面元素个数小于k时就直接插入堆中
     *      2. 当堆中元素个数等于k时，遍历到的元素出现的次数大于堆顶元素出现的次数时，将堆顶元素弹出，将这个遍历到的元素插入到堆中；小于等于的时候就不作处理
     * 复杂度分析
     *      时间复杂度:O(Nlog^k),遍历数组获得每个元素出现的次数的时间复杂度为O(N),小顶堆的大小最多为k,因此每次堆操作的时间复杂度为(log^k)，所以总的时间复杂度
     *               为O(Nlog^k)
     *      空间复杂度:O(N),哈希表的大小。
     * @param nums
     * @return
     */
    public int[] solution(int[] nums, int k){

        Map<Integer, Integer> occurences = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            occurences.put(nums[i], occurences.getOrDefault(nums[i], 0) + 1);
        }

        // int[]数组里面第一个元素表示元素的值，第二个元素表示元素出现的次数
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] nums1, int[] nums2) {
                return nums1[1] - nums2[1];
            }
        });

        for (Map.Entry<Integer, Integer> entry : occurences.entrySet()){
            int num = entry.getKey();
            int count = entry.getValue();
            if (priorityQueue.size() == k){
                if (priorityQueue.peek()[1] < count){
                    priorityQueue.poll();
                    priorityQueue.offer(new int[]{num, count});
                }
            }else {
                priorityQueue.offer(new int[]{num, count});
            }
        }

        int[] high = new int[k];
        for (int i = 0; i < k; i++){
            high[i] = priorityQueue.poll()[0];
        }

        return high;
    }

    public static void main(String[] args) {

        TopKFrequent topKFrequent = new TopKFrequent();
        int[] nums = new int[]{1,1,1,2,2,3};
        int k = 2;
        int[] ints = topKFrequent.solution(nums, k);
        System.out.println(Arrays.toString(ints));
    }
}
