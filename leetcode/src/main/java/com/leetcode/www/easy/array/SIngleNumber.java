package com.leetcode.www.easy.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 */
public class SIngleNumber {


    public int solution(int[] nums){


        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            if (map.containsKey(nums[i])){
                map.put(nums[i], map.get(nums[i]) + 1);
            }else {
                map.put(nums[i], 1);
            }
        }

        Integer i = null;
        for (Integer key : map.keySet()){
            if (map.get(key) == 1){
                i = map.get(key);
            }
        }

        return i;
    }

    public static void main(String[] args) {

        int[] nums = new int[]{1,2,2};
        SIngleNumber sIngleNumber = new SIngleNumber();
        System.out.println(sIngleNumber.solution(nums));
    }
}
