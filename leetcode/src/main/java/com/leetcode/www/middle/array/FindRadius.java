package com.leetcode.www.middle.array;

import java.util.Arrays;

/**
 * leetcode-475:供暖期
 * 冬季已经来临。你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
 * 在加热器的加热半径范围内的每个房屋都可以获得供暖。
 * 现在，给出位于一条水平线上的房屋houses 和供暖器heaters 的位置，请你找出并返回可以覆盖所有房屋的最小加热半径。
 * 说明：所有供暖器都遵循你的半径标准，加热的半径也一样。
 */
public class FindRadius {


    /**
     * 排序+双指针
     * 先对数组houses和数组heaters进行排序，然后同时遍历这两个数组。目标是:对于数组houses里面的下标i，需要找到数组heaters里面的下标j，使得abs(houses[i] - heaters[j])
     * 最小。此时heaters[j]就是距离houses[i]最近的供暖期，初始时i=j=0
     * 从左向右遍历遍历数组houses，对于每个下标i，需要维护离houses[i]最近的供暖期的距离，将这个距离初始化为abs(houses[i]-heaters[j])。如果这个距离大于等于
     * abs(houses[i]-heaters[j])，则将j+1。持续这个过程，直到j=n-1或者是abs(houses[i]-heaters[j])小于abs(houses[i]-heaters[j]),此时，heater[j]是
     * 距离houses[i]最近的供暖器，距离就是abs(houses[i]-heaters[j])。最后，所有房屋得到距离最近的供暖器的距离，求最大值就是答案
     * 复杂度分析
     *  时间复杂度:O(mlog^m+nlog^n),m是数组houses的长度，n是数组heaters的长度。对两个数组排序所需的时间复杂度是O(mlog^m)和O(nlog^n),双指针需要O(m+n)
     *           的时间。由于在渐进意义下O(m+n)小于O(mlog^m+nlog^n),所以总的时间复杂度为O(mlog^m+nlog^n)
     *  空间复杂度:O(log^m+log^n),m是数组houses的长度，n是数组heaters的长度。两个数组排序所需要的空间
     * @param houses
     * @param heaters
     * @return
     */
    public int solution(int[] houses, int[] heaters){

        Arrays.sort(houses);
        Arrays.sort(heaters);

        int ans = 0;
        int j = 0;
        for (int i = 0; i < houses.length; i++){

            int curDistance = Math.abs(houses[i] - heaters[j]);
            while (j < heaters.length - 1 && curDistance >= Math.abs(houses[i] - heaters[j+1])){
               curDistance = Math.abs(houses[i] - heaters[j+1]);
               j++;
            }
            ans = Math.max(ans, curDistance);
        }

        return ans;
    }


    /**
     * 排序+二分查找
     * 解决这个问题的思路是:找到与每个房屋最接近的供暖器的距离curMin，这些距离的最大值就是答案。先对数组heaters进行排序，对于每个房屋，分为三种情况处理
     *  1. 房屋在所有供暖器左边，则curMin = heaters[0]
     *  2. 房屋在所有供暖器右边，则curMin = heater[len-1]
     *  3. 房屋在供暖器之间，首先在房屋左边找到最近的供暖器，这个距离为leftDistance，房屋距离右侧供暖器的距离为rightDistance,curMin = min(leftDistance,rightDistance)
     * 复杂度分析
     *  时间复杂度:O((M+N)log^M),N是数组houses的长度，M是数组heaters的长度。对数组heaters排序的时间复杂度为O(Mlog^M),对数组houses里面的每个元素进行
     *           二分查找的时间复杂度为O(Nlog^M),所以总的时间复杂度为O((M+N)log^M)
     *  空间复杂度:O(log^M),M是数组heaters的长度，这里排序使用的是归并或者快速排序，递归调用栈的空间大小为O(log^M)
     * @param houses
     * @param heaters
     * @return
     */
    public int solutionV2(int[] houses, int[] heaters){

        Arrays.sort(heaters);
        int ans = 0;
        int heatersLen = heaters.length;;
        for (int house : houses){

            int curMin;
            if (house < heaters[0]){
                //房屋在所有供暖器左边
                curMin = heaters[0] - house;
            }else if (house > heaters[heatersLen - 1]){
                //房屋在所有供暖器右边
                curMin = house - heaters[heatersLen - 1];
            }else {
                //房屋在供暖器之间，找到最后一个小于等于当前house的供暖器的位置
                int left = 0;
                int right = heatersLen - 1;
                while (left < right){
                    int mid = (left + right + 1) / 2;
                    if (heaters[mid] > house){
                        right = mid - 1;
                    }else {
                        left = mid;
                    }
                }
                if (left == heatersLen - 1){
                    curMin = heaters[left] - house;
                }else {
                    curMin = Math.min(house - heaters[left], heaters[left + 1] - house);
                }
            }

            ans = Math.max(ans, curMin);
        }

        return ans;
    }

    public static void main(String[] args) {

        FindRadius findRadius = new FindRadius();
        int[] houses = {1,5};
        int[] heaters = {2};
        int ans = findRadius.solution(houses, heaters);
        System.out.println(ans);

        int ans1 = findRadius.solutionV2(houses, heaters);
        System.out.println(ans1);
    }
}
