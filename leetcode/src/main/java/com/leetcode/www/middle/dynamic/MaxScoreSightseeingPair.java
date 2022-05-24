package com.leetcode.www.middle.dynamic;

/**
 * leetcode-1014:最佳观光组合
 * 给你一个正整数数组 values，其中 values[i]表示第 i 个观光景点的评分，并且两个景点i 和j之间的 距离 为j - i。
 * 一对景点（i < j）组成的观光组合的得分为 values[i] + values[j] + i - j ，也就是景点的评分之和 减去 它们两者之间的距离。
 * 返回一对观光景点能取得的最高分。
 */
public class MaxScoreSightseeingPair {


    /**
     * 由于res = values[i] + values[j]+i-j,对于每一个j，它的值values[j]和j是固定的，所以values[j]-j也是固定的。因此，要得到res的最大值，就是求
     * values[i]+i的最大值。可以使用一个变量preMax来存储每个j前面的i(i<j)的values[i]+i的最大值
     * 复杂度分析
     *  时间复杂度:O(n),n是数组values的长度
     *  空间复杂度:O(1)
     * @param values
     * @return
     */
    public int solution(int[] values){

        int ans = 0;
        int preMax = values[0];
        for (int j = 1; j < values.length; j++){
            ans = Math.max(ans, preMax + values[j] - j);
            preMax = Math.max(preMax, values[j] + j);
        }

        return ans;
    }


    public static void main(String[] args) {

        MaxScoreSightseeingPair pair = new MaxScoreSightseeingPair();
        int[] values = {8,1,5,2,6};
        int ans = pair.solution(values);
        System.out.println(ans);
    }
}
