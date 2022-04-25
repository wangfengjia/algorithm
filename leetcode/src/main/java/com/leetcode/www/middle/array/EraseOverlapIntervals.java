package com.leetcode.www.middle.array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * leetcode-435:无重叠区间
 * 给定一个区间的集合intervals，其中 intervals[i] = [starti, endi]。返回 需要移除区间的最小数量，使剩余区间互不重叠。
 *
 */
public class EraseOverlapIntervals {


    /**
     * 这个题目等价于"选出最多数量的不重叠区间"，我们可以先按照每个区间的左端点从小到大进行排序，然后进行动态规划
     * 动态规划:
     *      状态定义:dp[i]表示以第i个区间为最后一个区间,可以选出的区间数量最大值
     *      状态转移方程:枚举第i个区间前面的第[0..i-1]个区间，同时比较当前区间的左端点是否大于等于被枚举到的区间的右端点，当大于等于的时候，状态转移方程为
     *          dp[i] = max(dp[j] + 1),j的范围为(0...i-1)
     *      边界条件:dp[0]=1,每个区间可以选出的区间数量最大值初始都是1
     * 复杂度分析
     *  时间复杂度:O(n^2),n为区间的数量，需要O(n^2)的时间复杂度进行动态规划
     *  空间复杂度:O(n),n为区间的长度，状态数组所需要的空间
     * @param intervals
     * @return
     */
    public int solution(int[][] intervals){

        int n = intervals.length;
        if (n == 0){
            return 0;
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[1];
            }
        });

        int ans = 1;
        int[] dp = new int[n];
        dp[0] = 1;

        for (int i = 1; i < n; i++){
            int curMax = 1;
            for (int j = 0; j < i; j++){
                if (intervals[i][0] >= intervals[j][1]){
                    curMax = Math.max(curMax, dp[j] + 1);
                }
            }
            dp[i] = curMax;
            ans = Math.max(curMax, ans);
        }

        return n - ans;
    }


    public static void main(String[] args) {

        EraseOverlapIntervals overlapIntervals = new EraseOverlapIntervals();

        int[][] intervals = {
                {1,2},
//                {1,2},
//                {1,2},
                {2,3},
                {3,4},
                {1,3}
        };

        int ans = overlapIntervals.solution(intervals);
        System.out.println(ans);
    }
}
