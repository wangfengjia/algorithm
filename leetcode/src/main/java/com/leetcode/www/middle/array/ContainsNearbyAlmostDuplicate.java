package com.leetcode.www.middle.array;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * leetcode-220:存在重复元素 III
 * 给你一个整数数组 nums 和两个整数k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
 * 如果存在则返回 true，不存在返回 false。
 */
public class ContainsNearbyAlmostDuplicate {


    /**
     * 利用桶排序的思想
     * 对于元素x，它影响的区间是[x-t,x+t]。因此我们可以设定桶的大小为t+1。如果两个元素同属于一个桶，那么这两个元素就是符合条件的。如果两个元素属于相邻桶，那么就
     * 校验这两个元素的差值是否不超过t。如果两个元素不属于相同桶，也不属于相邻桶，那么这两个元素必然不符合条件
     *
     * 复杂度分析
     *  时间复杂度:O(n),n是数组nums的长度，每个元素最多被插入哈希表和从哈希表中删除一次，每次的时间复杂度为O(1)
     *  空间复杂度:O(min(n,k)),哈希表中最多有min(n,k+1)个元素
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean solution(int[] nums, int k, int t){

        int n = nums.length;
        Map<Long, Long> map = new HashMap<>();
        long w = (long) t + 1;
        for (int i = 0; i < n; i++){
            long id = getId(nums[i], w);
            if (map.containsKey(id)){
                return true;
            }
            //相邻桶
            if (map.containsKey(id - 1) && Math.abs(nums[i] - map.get(id - 1)) < w){
                return true;
            }
            if (map.containsKey(id + 1) && Math.abs(nums[i] - map.get(id + 1)) < w){
                return true;
            }
            map.put(id, (long) nums[i]);
            if (i >= k){
                //移除下标范围不在[max(0, i-k),i]内的桶
                map.remove(getId(nums[i-k], w));
            }
        }

        return false;
    }


    /**
     * 滑动窗口+有序集合
     * 对于序列中的每个元素x左侧最多有k个元素，如果这k个元素中有一个元素在区间[x-t,x+t]中，此时就找到了一对符合条件的元素。由于两个相邻元素，它们各自左侧的k个元素
     * 有k-1是重合的，所以可以使用滑动窗口。维护一个大小为k的窗口，每次遍历到元素x，滑动窗口里面包含元素x前面最多的k个元素，检查窗口内是否存在元素处于区间[x-t,x+t]
     * 为了实现在区间内高效查找，使用有序集合来维护滑动窗口内的元素。在有序集合中查找大于等于x-t的最小元素y，如果y存在，并且y<=x+t,则我们找到了一对满足条件的元素。
     * 完成检查后,将x插入到有序集合中，如果有序集合中元素数量超过k，则删除最先插入的元素
     *
     * 复杂度分析
     *  时间复杂度:O(nlog^(min(n,k))),n是数组nums的长度，每个元素最多被插入有序集合有从有序集合删除一次，每次的时间复杂度为O(log^(min()n,k))
     *  空间复杂度:O(min(n,k)),n是给定数组的长度，有序集合中最多包含min(n,k+1)个元素
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean solutionV2(int[] nums, int k, int t){

        int n = nums.length;
        TreeSet<Long> treeSet = new TreeSet<>();
        for (int i = 0; i < n; i++){
            Long ceiling = treeSet.ceiling((long) nums[i] - (long) t);
            if (ceiling != null && ceiling <= ((long)nums[i] + (long) t)){
                return true;
            }
            treeSet.add((long) nums[i]);
            if (i >= k){
                treeSet.remove((long)nums[i - k]);
            }
        }

        return false;
    }

    private long getId(long x, long w){
        if (x >= 0){
            return x / w;
        }

        return (x + 1) / w - 1;
    }

    public static void main(String[] args) {

        ContainsNearbyAlmostDuplicate duplicate = new ContainsNearbyAlmostDuplicate();
        int[] nums = {1,5,9,1,5,9};
        int k = 2;
        int t = 3;
        boolean ans = duplicate.solution(nums, k, t);
        System.out.println(ans);

        boolean ans2 = duplicate.solutionV2(nums, k, t);
        System.out.println(ans2);
    }
}
