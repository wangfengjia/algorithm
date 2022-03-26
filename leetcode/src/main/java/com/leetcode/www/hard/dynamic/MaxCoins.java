package com.leetcode.www.hard.dynamic;

/**
 * 有n个气球，编号为0到n - 1，每个气球上都标有一个数字，这些数字存在数组nums中。现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。
 * 这里的 i - 1 和 i + 1 代表和i相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。求所能获得硬币的最大数量。
 *
 */
public class MaxCoins {


    /**
     * 动态规划版本:戳气球的操作会导致两个气球从不相邻变成相邻，使得后续操作难以处理。那么可以倒过来看这些操作，将全过程看作是添加一个气球.
     *      1. 状态定义:dp[i][j]表示将开区间(i,j)内的位置全部填满气球能得到的硬币数量
     *      2. 状态转移方程:dp[i][j] = nums[i]*nums[k]*nums[j] + dp[i][k] + dp[k][j],k的范围是[i+1 .. j-1]。往开区间(i,j)放第一个元素nums[k]时，
     *                    得到的金币数量是nums[i] * nums[k]*nums[j]，同时会将开区间(i,j)分隔成为两个开区间(i,k),(k,j)，通过已求出的状态得到dp[i][k]
     *                    和dp[k][j]的值，这样就得到了dp[i][j]的值
     *      3. 边界条件:求值过程中，数组越界时，就把它当初数字为1
     *      4. 复杂度分析
     *          时间复杂度:O(n^3)。n为气球的数量。状态数为n^2,状态转移复杂度为O(n),最终复杂度为O(n^3)
     *          空间复杂度:O(n^2),n为气球的数量，就是状态转移数组所消耗的空间
     * @param nums
     * @return
     */
    public int solution(int[] nums){

        int length = nums.length;
        int[][] dp = new int[length + 2][length + 2];
        int[] val = new int[length + 2];
        val[0] = val[length+1] = 1;
        for (int i = 1; i <= length; i++){
            val[i] = nums[i-1];
        }

        // 从数组最后一个元素往前枚举，作为开区间的左端
        for (int i = length - 1; i >= 0; i--){
            for (int j = i + 2; j <= length + 1; j++){
                //区间(i,j)选择不同的k，得到不同的区间和选择k得到的硬币数
                for (int k = i + 1; k < j; k++){
                    int sum = val[i] * val[j] * val[k] + dp[i][k] + dp[k][j];
                    dp[i][j] = Math.max(dp[i][j], sum);
                }
            }
        }

        return dp[0][length + 1];
    }


    public static void main(String[] args) {

        int[] nums = new int[]{3,1,5,8};
        MaxCoins maxCoins = new MaxCoins();
        int max = maxCoins.solution(nums);
        System.out.println(max);
    }
}
