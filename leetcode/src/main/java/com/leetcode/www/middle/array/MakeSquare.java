package com.leetcode.www.middle.array;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode-473:火柴拼正方形
 * 你将得到一个整数数组 matchsticks ，其中 matchsticks[i] 是第 i个火柴棒的长度。你要用 所有的火柴棍拼成一个正方形。你 不能折断 任何一根火柴棒，
 * 但你可以把它们连在一起，而且每根火柴棒必须 使用一次 。如果你能使这个正方形，则返回 true ，否则返回 false 。
 */
public class MakeSquare {

    /**
     * 回溯
     * 首先计算所有火柴的总长度sum，假如总长度不是4的倍数，则无法拼成正方形，否则正方形的边长为len=sum/4。用一个edges数组来记录4条边已经放入的火柴总长度，
     * 对于第index根火柴，尝试把它放入一条边且放入后该边的长度不大于len，然后继续枚举第index+1根火柴的情况,如果所有火柴都已经放置，那么说明可以拼接成正方形
     * 为了减少搜索量，需要对火柴的长度从大到小排序
     *
     * 复杂度分析
     *  时间复杂度:O(4^n), n是数组sticks的长度，每根火柴都可以选择放在4条边上，因此时间复杂度为O(4^n)
     *  空间复杂度:O(n),递归调用栈需要O(n)空间
     * @param sticks
     * @return
     */
    public boolean solution(int[] sticks){

        int sum = Arrays.stream(sticks).sum();
        if (sum % 4 != 0){
            return false;
        }

        //从大到小排序
        Arrays.sort(sticks);
        for (int i = 0, j = sticks.length - 1; i < j; i++, j--){
            int tmp = sticks[i];
            sticks[i] = sticks[j];
            sticks[j] = tmp;
        }

        int len = sum / 4;
        int[] edges = new int[4];
        return dfs(sticks, 0, edges, len);
    }

    private boolean dfs(int[] sticks, int index, int[] edges, int len){

        //所有火柴都已经放置，则表示可以拼接成正方形
        if (index == sticks.length){
            return true;
        }
        for (int i = 0; i < 4; i++){
            edges[i] += sticks[index];
            //放置第index根火柴，假如放置后边长不大于len，则放置第index+1根火柴
            if (edges[i] <= len && dfs(sticks, index+1, edges, len)){
                return true;
            }
            //回溯
            edges[i] -= sticks[index];
        }
        return false;
    }

    public static void main(String[] args) {

        MakeSquare makeSquare = new MakeSquare();

        int[] sticks1 = {1,1,2,2,2};
        boolean ans1 = makeSquare.solution(sticks1);
        System.out.println(ans1);

        int[] sticks2 = {3,3,3,3,4};
        boolean ans2 = makeSquare.solution(sticks2);
        System.out.println(ans2);
    }
}
