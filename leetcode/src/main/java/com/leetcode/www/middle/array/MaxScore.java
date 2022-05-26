package com.leetcode.www.middle.array;

import java.util.Arrays;

/**
 * leetcode-1423:可获得的最大点数
 * 几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。
 * 每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。
 * 你的点数就是你拿到手中的所有卡牌的点数之和。
 * 给你一个整数数组 cardPoints 和整数 k，请你返回可以获得的最大点数。
 */
public class MaxScore {

    /**
     * 滑动窗口
     * 由于只能从数组scores头尾取k个元素，最后剩下的就是n-k个连续的元素。可以通过求出剩余元素之和的最小值，这样就可以得到拿走卡牌的最大值
     * 使用一个固定长度为n-k的滑动窗口对数组scores进行遍历，求出滑动窗口内元素之和的最小值。然后用数组scores所有元素之和减去滑动窗口内所有元素之和的最小值，
     * 得到的结果就是拿走卡牌的最大值
     *
     * 复杂度分析
     *  时间复杂度:O(n),n是数组scores的长度
     *  空间复杂度:O(1)
     * @param scores
     * @param k
     * @return
     */
    public int solution(int[] scores, int k){

        int n = scores.length;
        int windowSize = n - k;
        int sum = 0;
        for (int i = 0; i < windowSize; i++){
            sum += scores[i];
        }

        int minSum = sum;
        for (int i = windowSize; i < n; i++){
            //滑动窗口向右移动一格，加上右侧进入窗口的元素值，减去从左侧窗口离开的元素的值
            sum += scores[i] - scores[i - windowSize];
            minSum = Math.min(minSum, sum);
        }

        return Arrays.stream(scores).sum() - minSum;
    }


    public static void main(String[] args) {

        MaxScore score = new MaxScore();
        int[] scores = {1,79,80,1,1,1,200,1};
        int k = 3;
        int ans = score.solution(scores, k);
        System.out.println(ans);
    }
}
