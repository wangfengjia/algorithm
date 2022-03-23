package com.leetcode.www.easy.array;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode169:给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于⌊n/2⌋的元素。就是出现次数最多的元素
 */
public class MajorityElement {

    /**
     * 使用哈希表，记录每个元素出现的次数，然后去遍历这个哈希表，得到出现次数最多的元素
     * 复杂度
     *      时间复杂度:O(n)。对数组做一次遍历
     *      空间复杂度:O(n)。由于题中保证一定会有一个众数，会占用最少n/2+1个数字，所以最多只有n-(n/2+1)个不同的其他数字，所以最多有n-n/2个不同的元素
     * @param nums
     * @return
     */
    public int solutionV1(int[] nums){

        Map<Integer, Integer> counts = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            if (!counts.containsKey(nums[i])){
                counts.put(nums[i], 1);
            }else {
                counts.put(nums[i], counts.get(nums[i]) + 1);
            }
        }

        Map.Entry<Integer, Integer> majorityEntry = null;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()){
            if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()){
                majorityEntry = entry;
            }
        }

        return majorityEntry.getKey();
    }

    public static void main(String[] args) {

        int[] nums = new int[]{2,2,1};
        MajorityElement majorityElement = new MajorityElement();
        int element = majorityElement.solutionV1(nums);
        System.out.println(element);
    }
}
