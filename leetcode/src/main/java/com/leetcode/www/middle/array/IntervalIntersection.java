package com.leetcode.www.middle.array;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode-986:区间列表的交集
 * 给定两个由一些 闭区间 组成的列表，firstList 和 secondList ，其中 firstList[i] = [starti, endi] 而secondList[j] = [startj, endj] 。
 * 每个区间列表都是成对 不相交 的，并且 已经排序 。返回这 两个区间列表的交集 。
 * 形式上，闭区间[a, b]（其中a <= b）表示实数x的集合，而a <= x <= b 。
 * 两个闭区间的 交集 是一组实数，要么为空集，要么为闭区间。例如，[1, 3] 和 [2, 4] 的交集为 [2, 3] 。
 */
public class IntervalIntersection {


    /**
     * 双指针
     * 使用两个指针分别指针两个区间列表中的其中一个区间，求指针指向的两个区间的交集的办法是:求出这两个区间的左端点的更大的值lo,和这两个区间右端点的更小的值hi,
     * 当lo<=hi时,新的区间[lo,hi]就是这两个区间的交集。
     * 指针移动的时机:移动右端点更小的区间，由于这个区间不会再与另外一个列表的其他区间有交集
     *
     * 复杂度分析
     *  时间复杂度:O(m+n),m和n分别是数组a和b的长度
     *  时间复杂度:O(m+n),答案区间数量的上限
     * @param a
     * @param b
     * @return
     */
    public int[][] solution(int[][] a, int[][] b){

        List<int[]> list = new ArrayList<>();
        int i = 0;
        int j = 0;

        while (i < a.length && j < b.length){

            int lo = Math.max(a[i][0], b[j][0]);
            int hi = Math.min(a[i][1], b[j][1]);
            if (lo <= hi){
                list.add(new int[]{lo, hi});
            }

            //指向右端点更小的区间指针向右移动,这个区间和另外一个列表的区间不会再存在交集
            if (a[i][1] < b[j][1]){
                i++;
            }else {
                j++;
            }
        }

        return list.toArray(new int[list.size()][]);
    }


    public static void main(String[] args) {

        IntervalIntersection intersection = new IntervalIntersection();
        int[][] firstList = {
                {0,2},
                {5,10},
                {13,23},
                {24,25}
        };
        int[][] secondList = {
                {1,5},
                {8,12},
                {15,24},
                {25,26}
        };

        int[][] ans = intersection.solution(firstList, secondList);
        for (int[] an : ans) {
            System.out.println(Arrays.toString(an));
        }
    }
}
