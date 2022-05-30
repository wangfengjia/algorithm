package com.leetcode.www.middle.array;


/**
 * leetcode-875:爱吃香蕉的珂珂
 * 珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有piles[i]根香蕉。警卫已经离开了，将在 h 小时后回来。
 * 珂珂可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 * 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。
 */
public class MinEatingSpeed {


    /**
     * 二分查找
     * 由于每个小时只能选择一堆香蕉吃，所以速度最大值是这几堆香蕉中数量最多的那一堆，而速度最小值可以是1。然后利用二叉查找，找到一个最小速度k，可以在h小时内
     * 吃完所有香蕉
     *
     * 复杂度分析
     *  时间复杂度:O(Nlog^max(piles)),N是数组piles的长度，我们在[1,max(piles)]范围内使用二叉查找定位最小速度，每次判别函数的时间复杂度为O(N)
     *  空间复杂度:O(1),只使用了常数个临时变量
     * @param piles
     * @param h
     * @return
     */
    public int solution(int[] piles, int h){

        int maxValue = 1;
        for (int pile : piles){
            maxValue = Math.max(pile, maxValue);
        }

        //速度最小值，耗时最长
        int left = 1;
        //速度最大值，耗时最短
        int right = maxValue;

        while (left < right){
            int mid = left + (right - left) / 2;
            if (calculateSum(piles, mid) > h){
                left = mid + 1;
            }else {
                right = mid;
            }
        }

        return left;
    }

    /**
     * 以速度speed去吃全部香蕉所需要的时间，根据题意，时间向上取整
     * @param piles
     * @param speed
     * @return
     */
    private int calculateSum(int[] piles, int speed){

        int sum = 0;
        for (int pile : piles){
            //向上取整
            sum += (pile + speed - 1) / speed;
        }

        return sum;
    }


    public static void main(String[] args) {

        MinEatingSpeed speed = new MinEatingSpeed();
        int[] piles = {30,11,23,4,20};
        int h = 5;
        int ans = speed.solution(piles, h);
        System.out.println(ans);
    }
}
