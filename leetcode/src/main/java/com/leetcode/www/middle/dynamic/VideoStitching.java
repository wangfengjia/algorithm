package com.leetcode.www.middle.dynamic;


import java.util.Arrays;

/**
 * leetcode-1024:视频拼接
 * 你将会获得一系列视频片段，这些片段来自于一项持续时长为time秒的体育赛事。这些片段可能有所重叠，也可能长度不一。
 * 使用数组clips 描述所有的视频片段，其中 clips[i] = [starti, endi] 表示：某个视频片段开始于starti并于endi结束。
 * 甚至可以对这些片段自由地再剪辑：
    * 例如，片段[0, 7]可以剪切成[0, 1] +[1, 3] + [3, 7]三部分。
 * 我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, time]）。返回所需片段的最小数目，如果无法完成该任务，则返回-1 。
 */
public class VideoStitching {


    /**
     * 动态规划
     *      状态定义:dp[i]表示将区间[0,i)覆盖所需的最少子区间的数量
     *      状态转移方程:通过枚举每一个子区间来依次计算所有dp的值。当枚举i时，对于任意一个子区间[a,b),如果满足a<i<=b的条件，则这个子区间可以覆盖[0,i)的后半部分
     *                 而前半部分可以使用dp[a]对应的最优方法来覆盖，所以状态转移方程为
     *                 dp[i] = dp[a] + 1, 子区间[a,b)满足a<i<=b的条件
     *      边界条件和初始状态:由于我们希望子区间的数量尽量少，因此可以将dp[i]初始化为一个大整数，同时dp[0]的初始值为0
     * 复杂度分析
     *  时间复杂度:O(time * n),time是区间的长度，n是子区间的数量
     *  空间复杂度:O(time), time是区间的长度
     * @param clips
     * @param times
     * @return
     */
    public int solution(int[][] clips, int times){

        int[] dp = new int[times + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= times; i++){
            for (int[] clip : clips) {
                if (clip[0] < i && i <= clip[1]){
                    dp[i] = Math.min(dp[i], dp[clip[0]] + 1);
                }
            }
        }

        return dp[times] == Integer.MAX_VALUE ? -1 : dp[times];
    }

    public static void main(String[] args) {

        VideoStitching stitching = new VideoStitching();
        int[][] clips = {
                {0,2},
                {4,6},
                {8,10},
                {1,9},
                {1,5},
                {5,9}
        };
        int times = 10;
        int ans = stitching.solution(clips, times);
        System.out.println(ans);
    }
}
