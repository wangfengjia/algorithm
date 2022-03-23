package com.leetcode.www.easy.array;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {


    public void twoSum(int[] arr, int summary){

        Map<Integer, Integer> sumMap = new HashMap<Integer, Integer>();
        int length = arr.length;


        for (int i = 0; i < length; i++){

            Integer element = sumMap.get(arr[i]);
            if (element != null){
                System.out.println(arr[i] + "+" + element);
                return;
            }else {
                sumMap.put(summary - arr[i], arr[i]);
            }
        }
    }


    public static void main(String[] args){

        int[] arr = {1, 3, 4, 5, 7};
        TwoSum twoSum = new TwoSum();
        twoSum.twoSum(arr, 11);
    }
}
