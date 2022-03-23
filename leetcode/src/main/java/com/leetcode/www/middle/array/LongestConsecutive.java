package com.leetcode.www.middle.array;

import java.util.Currency;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 最长连续序列:给定一个未排序的整数数组nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。设计一个时间复杂度为O(n)的算法来解决这个问题(就是不能排序)
 * 方法一(哈希表):由于需要找到连续的最长子序列，这样就可以枚举数组中的每个数x,考虑以其为起点，去数组里面匹配x+1,x+2...是否存在，这个匹配可以通过哈希表来做。
 *              但是这样做之后时间复杂度还是O(n^2)(外层需要枚举O(n)个数，内层需要暴力匹配O(n)次)。经过分析可以发现，执行了很多不必要的枚举。比如当存在
 *              x-1,x,x+1,x+2...x+y这样一个序列时，x,x+1,x+2...x+y这个序列的长度肯定是比前面的短，所以可以在枚举的时候去作这样一个判断:当枚举的数x的x-1
 *              存在时，就跳过这个数
 *              时间复杂度:O(n)。外层循环需要 O(n)的时间复杂度，只有当一个数是连续序列的第一个数的情况下才会进入内层循环，然后在内层循环中匹配连续序列中的数，
 *                       因此数组中的每个数只会进入内层循环一次。根据上述分析可知，总时间复杂度为 O(n)，符合题目要求。
 */
public class LongestConsecutive {

    public int solution(int[] nums){

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++){
            set.add(nums[i]);
        }

        int longBreak = 0;
        for (Integer num : nums){
            if (!set.contains(num - 1)){
                int currentNum = num;
                int currentBreak = 1;

                while (set.contains(currentNum + 1)){
                    ++currentNum;
                    ++currentBreak;
                }
                longBreak = Math.max(longBreak, currentBreak);
            }

        }

        return longBreak;
    }


    /**
     * 动态规划版本:状态转移方程:f(i) = f(i - 1) + f(i + 1) + 1,f(i)表示元素i所在的连续子序列的长度
     * @param nums
     * @return
     */
    public int solutionV2(int[] nums){

        Map<Integer, Integer> map = new HashMap<>();
        int longBreak = 0;

        for (int i = 0; i < nums.length; i++){

            int currentNum = nums[i];
            if (!map.containsKey(currentNum)){

                int left = map.getOrDefault(currentNum - 1, 0);
                int right = map.getOrDefault(currentNum + 1, 0);

                map.put(currentNum, 1);

                int length = left + right + 1;
                longBreak = Math.max(longBreak, length);

                //left值表示在元素currentNum前面有left个连续元素，这中间无法再插入元素，所以只需要更新左端点(currentNum - left)所在最长连续子序列的长度，
                // 更新为length
                map.put((currentNum - left), length);
                //right值表示在元素currentNum后面有right个连续元素，这中间无法再插入其他元素，所以只需要更新右端点(current + right)所在最长连续子序列长度
                //更新为length
                map.put((currentNum + right), length);
            }
        }

        return longBreak;
    }

    public static void main(String[] args) {

        int[] nums = new int[]{100,4,200,1,3,2};
        LongestConsecutive consecutive = new LongestConsecutive();
        int longBreak = consecutive.solutionV2(nums);
        System.out.println(longBreak);
    }
}
