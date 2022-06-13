package com.leetcode.www.middle.string;

/**
 * leetcode-2038:如果相邻两个颜色均相同则删除当前颜色
 * 总共有 n个颜色片段排成一列，每个颜色片段要么是'A'要么是'B'。给你一个长度为n的字符串colors，其中colors[i]表示第i个颜色片段的颜色。
 * Alice 和 Bob 在玩一个游戏，他们 轮流从这个字符串中删除颜色。Alice 先手。
     * 如果一个颜色片段为 'A'且 相邻两个颜色都是颜色 'A'，那么 Alice 可以删除该颜色片段。Alice不可以删除任何颜色'B'片段。
     * 如果一个颜色片段为 'B'且 相邻两个颜色都是颜色 'B'，那么 Bob 可以删除该颜色片段。Bob 不可以删除任何颜色 'A'片段。
     * Alice 和 Bob 不能从字符串两端删除颜色片段。
     * 如果其中一人无法继续操作，则该玩家 输掉游戏且另一玩家 获胜。
 * 假设 Alice 和 Bob 都采用最优策略，如果 Alice 获胜，请返回true，否则 Bob 获胜，返回false。
 */
public class WinnerOfGame {


    /**
     * 贪心
     * 根据删除规则，删除任意一个A不会影响可被删除的B的数量，反之亦然。因此，可以统计 可删除的A的数量(Alice的操作数) 和 可删除的B的数量(Bob的操作数)，
     * 分别记为a和b，比较a和b的大小即可
     *
     * 复杂度分析
     *  时间复杂度:O(n),n是字符串colors的长度
     *  空间复杂度:O(1)
     * @param colors
     * @return
     */
    public boolean solution(String colors){

        int len = colors.length();
        int a = 0;
        int b = 0;
        for (int i = 1; i < len - 1; i++){
            if (colors.charAt(i-1) == 'A' && colors.charAt(i) == 'A' && colors.charAt(i+1) == 'A'){
                ++a;
            }
            if (colors.charAt(i-1) == 'B' && colors.charAt(i) == 'B' && colors.charAt(i+1) == 'B'){
                ++a;
            }
        }

        return a > b;
    }

    public static void main(String[] args) {


        WinnerOfGame game = new WinnerOfGame();
        String colors = "AAABABB";
        boolean ans = game.solution(colors);
        System.out.println(ans);
    }
}
