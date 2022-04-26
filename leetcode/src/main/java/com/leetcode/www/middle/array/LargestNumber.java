package com.leetcode.www.middle.array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * leetcode-179:最大数
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 */
public class LargestNumber {


    /**
     * 要想组成最大整数，可以通过把数值大的数放在高位。那么我们可以比较输入数组的每个元素的最高位,最高位相同的时候比较次高位，依次类推，完成排序，然后把它们拼接起来
     * 复杂度分析
     *  时间复杂度:O(nlog^nlog^m)
     *  空间复杂度:O(log^n),排序所需要的栈空间
     * @param nums
     * @return
     */
    public String solution(int[] nums){

        int n = nums.length;
        //将数组转换成包装类型，以便传入Comparator对象
        Integer[] numsArr = new Integer[n];
        for (int i = 0; i < n; i++){
            numsArr[i] = nums[i];
        }

        Arrays.sort(numsArr, new Comparator<Integer>() {
            @Override
            public int compare(Integer x, Integer y) {
                long sx = 10;
                while (sx <= x){
                    sx *= 10;
                }
                long sy = 10;
                while (sy <= y){
                    sy *= 10;
                }

                return (int) (-sy * x - y + sx * y + x);
            }
        });

        if (numsArr[0] == 0){
            return "0";
        }
        StringBuilder builder = new StringBuilder();
        for (int num : numsArr){
            builder.append(num);
        }

        return builder.toString();
    }

    public static void main(String[] args) {

        LargestNumber largestNumber = new LargestNumber();
        int[] nums = {3,30,34,5,9};
        String ans = largestNumber.solution(nums);
        System.out.println(ans);

    }
}
