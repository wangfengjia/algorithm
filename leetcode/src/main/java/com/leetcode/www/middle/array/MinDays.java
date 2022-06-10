package com.leetcode.www.middle.array;

/**
 * leetcode-1482:制作 m 束花所需的最少天数
 * 给你一个整数数组 bloomDay，以及两个整数 m 和 k 。
 * 现需要制作 m 束花。制作花束时，需要使用花园中 相邻的 k 朵花 。
 * 花园中有 n 朵花，第 i 朵花会在 bloomDay[i] 时盛开，恰好 可以用于 一束 花中。
 * 请你返回从花园中摘 m 束花需要等待的最少的天数。如果不能摘到 m 束花则返回 -1 。
 */
public class MinDays {


    /**
     * 二分查找
     * 需要制作m束华，每束花需要k朵鲜花，需要的总的鲜花数量就是m*k。当bloomDay数组的长度小于m*k时，则不能够制作成功。当bloomDay数组的长度大于等于m*k时，则
     * 一定能够制作成功。给定天数很大时，总是可以制作成功，而给定天数很小时，总是无法制作成功。因此给定天数就会存在一个分割点，小于分割点的天数无法制作成功，大于
     * 等于分割点的天数可以制作成功，可以使用二分查找来找到分割点
     * 可以通过编写一个辅助函数来判断能否在给定天数days内能够制作出给定数量的花束。辅助函数的实现:遍历bloomDay数组，计算其中长度为k且最大元素不超过days的不
     * 重合的子数组的数量。如果符合要求的不重合的连续子数组的数目大于等于m就返回true，否则返回false
     *
     * 复杂度分析
     *  时间复杂度:O(nlog^(high-low)),n是数组bloomDay的长度。找到数组bloomDay中元素的最大值和最小值需要的时间复杂度为O(n)。二分查找的迭代次数为O(log^(high-low)),
     *           每次判断的时间复杂度为O(n),所以二分查找的总的时间复杂度为O(nlog^(high-low))。总的时间复杂度就为O(n)+O(nlog^(high-low)) = O(nlog^(high-low))
     *  空间复杂度:O(1)
     * @param bloomDay
     * @param m
     * @param k
     * @return
     */
    public int solution(int[] bloomDay, int m, int k){

        if (m > bloomDay.length){
            return -1;
        }

        int low = Integer.MAX_VALUE;
        int high = 0;
        int len = bloomDay.length;
        for (int i = 0; i < len; i++){
            low = Math.min(low, bloomDay[i]);
            high = Math.max(high, bloomDay[i]);
        }

        while (low < high){
            int days = (high - low) / 2 + low;
            if (check(bloomDay, days, m, k)){
                high = days;
            }else {
                low = days + 1;
            }
        }

        return low;
    }


    private boolean check(int[] bloomDay, int days, int m, int k){

        int flowers = 0;
        int bouquets = 0;
        int len = bloomDay.length;
        for (int i = 0; i < len; i++){
            if (bloomDay[i] <= days){
                flowers++;
                if (flowers == k){
                    bouquets++;
                    flowers = 0;
                }
            }else {
                flowers = 0;
            }
        }

        return bouquets >= m;
    }

    public static void main(String[] args) {

        MinDays minDays = new MinDays();
        int[] days = {7,7,7,7,12,7,7};
        int m = 2;
        int k = 3;
        int ans = minDays.solution(days, m, k);
        System.out.println(ans);
    }
}
