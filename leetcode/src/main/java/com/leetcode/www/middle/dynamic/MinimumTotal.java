package com.leetcode.www.middle.dynamic;


import java.util.ArrayList;
import java.util.List;

/**
 * leetcode-120:三角形最小路径和
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 */
public class MinimumTotal {


    /**
     * 动态规划
     *      状态定义:dp[i][j]表示从点(i,j)到底边的最小路径和
     *      状态转移方程:由于每一步只能移动到下一行的相邻节点，也就是(i+1,j)或(i+1,j+1),所以状态转移方程为
     *                 dp[i][j] = min(dp[i+1][j], dp[i+1][j+1]) + triangle[i][j]。由于上层的点的状态依赖下层的点的状态，因此从底向上递推
     * 复杂度分析
     *  时间复杂度:O(n^2), n是三角形的行数
     *  时间复杂度:O(n^2), n是三角形的行数
     * @param triangle
     * @return
     */
    public int solution(List<List<Integer>> triangle){

        int n = triangle.size();
        int[][] dp = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 0; i--){
            for (int j = 0; j <= i; j++){
                dp[i][j] = Math.min(dp[i+1][j], dp[i+1][j+1]) + triangle.get(i).get(j);
            }
        }

        return dp[0][0];
    }


    public static void main(String[] args) {

        MinimumTotal total = new MinimumTotal();
        List<List<Integer>> triangle = new ArrayList<>();


        List<Integer> list1 = new ArrayList<>();
        list1.add(2);

        List<Integer> list2 = new ArrayList<>();
        list2.add(3);
        list2.add(4);

        List<Integer> list3 = new ArrayList<>();
        list3.add(6);
        list3.add(5);
        list3.add(7);

        List<Integer> list4 = new ArrayList<>();
        list4.add(4);
        list4.add(1);
        list4.add(8);
        list4.add(3);

        triangle.add(list1);
        triangle.add(list2);
        triangle.add(list3);
        triangle.add(list4);
        int ans = total.solution(triangle);
        System.out.println(ans);

    }
}
