package com.leetcode.www.middle.dynamic;

import java.util.HashSet;
import java.util.Set;

/**
 * leetcode-983:最低票价
 * 在一个火车旅行很受欢迎的国度，你提前一年计划了一些火车旅行。在接下来的一年里，你要旅行的日子将以一个名为days的数组给出。每一项是一个从1到365的整数。
 * 火车票有 三种不同的销售方式 ：
     * 一张 为期一天 的通行证售价为costs[0] 美元；
     * 一张 为期七天 的通行证售价为costs[1] 美元；
     * 一张 为期三十天 的通行证售价为costs[2] 美元。
 * 通行证允许数天无限制的旅行。 例如，如果我们在第 2 天获得一张 为期 7 天 的通行证，那么我们可以连着旅行 7 天：第 2 天、第 3 天、第 4 天、第 5 天、第 6 天、第 7 天和第 8 天。
 * 返回 你想要完成在给定的列表days中列出的每一天的旅行所需要的最低消费。
 */
public class MinCostTickets {


    /**
     * 动态规划
     *      状态定义:dp[i]表示从第i天开始,所需最小费用累计
     *      状态转移方程:今天买多少，得看后面几天是是怎么安排(后面几天是否需要出行)，即前面依赖后面，因此从后往前进行动态规划
     *          1. 这一天不是必须出行的日期，可以选择不买通行证，因此dp[i] = dp[i+1]
     *          2. 这一天是必须出行的日期，我们可以选择购买1,7,30这三种通行证之一。如果我们购买了j天的通行证，那么接下来的j-1天不需要购买通行证，只需要考虑
     *             第i+j天即可，因此dp[i] = min(costs[j] + dp[i+j]),j∈{1,7,30}
     * @param days
     * @param costs
     * @return
     */
    public int solution(int[] days, int[] costs){

        int len = days.length;
        int maxDay = days[len - 1];
        int minDay = days[0];
        int[] dp = new int[maxDay + 31]; //多扩展几天，就不再需要365限制的判断

        Set<Integer> set = new HashSet<>();
        for (int e : days){
            set.add(e);
        }

        //只需要看maxDay -> minDay,此区间外都不需要花钱，不会增加费用
        for (int i = maxDay; i >= minDay; i--){
            if (set.contains(i)){
                dp[i] = Math.min(dp[i + 1] + costs[0], dp[i + 7] + costs[1]);
                dp[i] = Math.min(dp[i], dp[i+30] + costs[2]);
            }else {
                dp[i] = dp[i+1];
            }
        }

        //从后往前动态规划，返回最前的minDay
        return dp[minDay];
    }


    public static void main(String[] args) {


        MinCostTickets tickets = new MinCostTickets();
        int[] days = {1,2,3,4,5,6,7,8,9,10,30,31};
        int[] costs = {2,7,15};
        int ans = tickets.solution(days, costs);
        System.out.println(ans);
    }
}
