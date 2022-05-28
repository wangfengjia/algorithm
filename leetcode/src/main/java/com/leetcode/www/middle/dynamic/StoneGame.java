package com.leetcode.www.middle.dynamic;

/**
 * leetcode-877:石子游戏
 * Alice 和 Bob 用几堆石子在做游戏。一共有偶数堆石子，排成一行；每堆都有 正 整数颗石子，数目为 piles[i]。
 * 游戏以谁手中的石子最多来决出胜负。石子的 总数 是 奇数 ，所以没有平局。
 * Alice 和 Bob 轮流进行，Alice 先开始 。 每回合，玩家从行的 开始 或 结束 处取走整堆石头。 这种情况一直持续到没有更多的石子堆为止，此时手中 石子最多 的玩家 获胜 。
 * 假设 Alice 和 Bob 都发挥出最佳水平，当 Alice 赢得比赛时返回true，当 Bob 赢得比赛时返回false。
 */
public class StoneGame {


    /**
     * 动态规划
        * 状态定义:dp[i][j]表示在在下标范围[i..j]中，当前玩家与另一个玩家的石子数量之差的最大值
        * 状态转移方程:当i<j时，当前玩家可以取走piles[i]或piles[j]，然后轮到另外一个玩家在剩下的石子堆中取走石子。当前玩家取两种方案中最优的那一个，方程为
     *               dp[i][j] = max(piles[i] - dp[i+1][j], piles[j] - dp[i][j-1])
        * 边界条件:当i>j时，dp[i][j]=0;当i==j时，只剩下一堆石子，当前玩家只能取走这一堆石子，对于所有的0 <= i <= nums.length,都有dp[i][i] = piles[i]
     * 复杂度分析
     *  时间复杂度:O(n^2),n是数组piles的长度。需要计算每个子数组对应的dp值，有(n*(n+1)) / 2个子数组
     *  空间复杂度:O(n^2),n是数组piles的长度，dp数组所消耗的空间
     * @param piles
     * @return
     */
    public boolean solution(int[] piles){

        int n = piles.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++){
            dp[i][i] = piles[i];
        }

        for (int i = n - 2; i >= 0; i--){
            for (int j = i + 1; j < n; j++){
                dp[i][j] = Math.max(piles[i] - dp[i+1][j], piles[j] - dp[i][j-1]);
            }
        }

        return dp[0][n-1] > 0;
    }

    public static void main(String[] args) {

        StoneGame stoneGame = new StoneGame();
        int[] piles = {5,3,4,5};
        boolean asn = stoneGame.solution(piles);
        System.out.println(asn);
    }
}
