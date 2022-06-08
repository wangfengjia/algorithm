package com.leetcode.www.middle.array;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * leetcode-373:查找和最小的 K 对数字
 * 给定两个以 升序排列 的整数数组 nums1 和 nums2,以及一个整数 k。
 * 定义一对值(u,v)，其中第一个元素来自nums1，第二个元素来自 nums2。
 * 请找到和最小的 k个数对(u1,v1), (u2,v2) ... (uk,vk)。
 */
public class KSmallestPairs {


    /**
     * 优先队列(小根堆)
     * 将索引数对添加到优先队列(小顶堆)中,每次从优先队列中取出堆顶元素，这是当前和最小的索引数对。假设这个索引数对为(a,b),由于两个数组都是升序的，那么新的候选索引
     * 数对就是(a+1,b)或者(a,b+1),把这两个候选索引数对添加到堆中，由于这样添加可能会出现重复数对，一般使用设置标记位解决去重问题。但是这里我们可以将nums1的
     * 前k个索引数对(0,0),(1,0),(2,0)...(k-1,0)添加到优先队列中，每次从队列中取出元素(x,y)时，只需要将nums2的索引增加即可。
     *
     * 复杂度分析
     *  时间复杂度:O(klog^k),k是选择数对的数目，优先队列最多保存k个元素，每次新元素入队列的时间复杂度为O(log^k),所以总的时间复杂度为O(klog^k)
     *  空间复杂度:O(k),优先队列中最多保存k个元素
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> solution(int[] nums1, int[] nums2, int k){

        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]];
            }
        });

        List<List<Integer>> ans = new ArrayList<>();
        int m = nums1.length;
        int n = nums2.length;
        for (int i = 0; i < Math.min(m, n); i++){
            queue.offer(new int[]{i, 0});
        }

        while (k-- > 0 && !queue.isEmpty()){
            int[] cell = queue.poll();
            List<Integer> curList = new ArrayList<>();
            curList.add(nums1[cell[0]]);
            curList.add(nums2[cell[1]]);
            ans.add(curList);
            if (cell[1] + 1 < n){
                queue.offer(new int[]{cell[0], cell[1] + 1});
            }
        }

        return ans;
    }


    public static void main(String[] args) {


        KSmallestPairs pairs = new KSmallestPairs();
        int[] nums1 = {1,7,11};
        int[] nums2 = {2,4,6};
        int k = 3;
        List<List<Integer>> ans = pairs.solution(nums1, nums2, k);
        System.out.println(ans);
    }
}
