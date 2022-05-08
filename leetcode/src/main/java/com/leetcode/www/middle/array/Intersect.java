package com.leetcode.www.middle.array;


import java.util.*;

/**
 * leetcode-350:两个数组的交集II
 * 给你两个整数数组nums1和nums2，请你以数组形式返回两数组的交集。返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑
 * 取较小值）。可以不考虑输出结果的顺序。
 */
public class Intersect {


    /**
     * 哈希表:使用哈希表存储数组nums1中每个数字出现的次数，然后枚举数组nums2中的每个元素，在哈希表中查找这个元素，存在的话添加到答案中，然后哈希表中这个数字出现
     *       的次数减去1，为了降低空间复杂度，先遍历较短的数组并在哈希表中维护每个数字出现的次数，再遍历较长的数组
     * 复杂度分析
     *  时间复杂度:O(m+n),m,n分别表示两个数组的长度，需要遍历两个数组并对哈希表进行操作
     *  空间复杂度:O(min(m,n)),m,n分别表示两个数组的长度，对较短的数组进行哈希表的操作,哈希表的大小不会超过较短数组的长度，为返回值创建的ans数组，其长度会等于
     *           较短数组的长度
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] solution(int[] nums1, int[] nums2){

        if (nums1.length > nums2.length){
            return solution(nums2, nums1);
        }

        Map<Integer, Integer> cntMap = new HashMap<>();
        for (int num : nums1){
            cntMap.put(num, cntMap.getOrDefault(num, 0) + 1);
        }

        int[] ans = new int[nums1.length];
        int index = 0;
        for (int num : nums2){
            if (cntMap.containsKey(num)){
                ans[index++] = num;
                Integer count = cntMap.get(num);
                --count;
                if (count > 0){
                    cntMap.put(num, count);
                }else {
                    cntMap.remove(num);
                }
            }
        }

        return Arrays.copyOfRange(ans, 0, index);
    }

    public static void main(String[] args) {

        Intersect intersect = new Intersect();
        int[] nums1 = {4,9,5};
        int[] nums2 = {9,4,9,8,4};
        int[] ans = intersect.solution(nums1, nums2);
        System.out.println(Arrays.toString(ans));
    }
}
