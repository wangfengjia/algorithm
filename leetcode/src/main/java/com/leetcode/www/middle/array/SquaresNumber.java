package com.leetcode.www.middle.array;

import java.util.Map;

/**
 * 给你一个整数n，返回和为n的完全平方数的最少数量 。完全平方数是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完
 * 全平方数，而 3 和 11 不是。
 */
public class SquaresNumber {


    /**
     * 动态规划:
     *      1. 状态表示:f(i)表示至少需要多少个数的平方加起来等于i
     *      2. 状态转移方程: 这些数的范围是(1,对i开根号)，我们可以去遍历枚举这些数。假如我们遍历到j，则我们还需要寻找最少多个书的平方加起来等于i-j^2，
     *                     这个子问题和原问题类似，所以可以得到状态转移方程为: f(i) = 1 + min(f(i - j^2))(j的范围是1 ~ 对i开根号)
     *      3. 边界条件:f(0) = 0;
     * @param
     * @return
     */
    public int solution(int n){

        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++){
            int min = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++){
                min = Math.min(min, dp[i - j*j]);
            }
            dp[i] = min + 1;
        }

        return dp[n];
    }


    public static void main(String[] args) {

        int n = 13;
        SquaresNumber squaresNumber = new SquaresNumber();
        int nums = squaresNumber.solution(n);
        System.out.println(nums);
    }
}
