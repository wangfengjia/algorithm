package com.leetcode.www.middle.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，
 * 该数组需恰好覆盖输入中的所有区间。
 * 方法一(排序):按照区间的左端点排序，那么在排完序的列表中，可以合并的区间一定就是连续的。我们用一个数组merged来存储最终的答案，先列表中的区间左端点排序，然后将第一个区间
 *            加到merged数组中，并按顺序依次考虑之后的每个区间
 *            1. 如果当前区间的左端点大于数组merged中最后一个区间的右端点，那么它们就不会重合，就将这个区间加到merged数组末尾
 *            2. 否则，它们就重合，用当前区间的右端点去更新merged数组中最后一个区间的右端点,将其置为二者的较大值
 *            3.时间复杂度:O(nlogn),其中n为区间的数量。除去排序的开销，我们只需要一次线性扫描，所以主要的时间开销是排序的O(nlogn)
 *            3.空间复杂度: O(logn),其中n为区间的数量。这个是存储答案之外使用的额外空间，O(logn)就是排序所需的空间复杂度
 */
public class IntervalMerge {

    public int[][] solution(int[][] intervals){

        if (intervals.length == 0){
            return new int[0][2];
        }

        //所有区间左端点排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        List<int[]> merged = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++){
            int left = intervals[i][0];
            int right = intervals[i][1];

            if (merged.size() == 0 || (merged.get(merged.size() - 1)[1] < left)){
                merged.add(new int[]{left, right});
            }else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], right);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {

        int[][] intervals = new int[][]{new int[]{1,3}, new int[]{2,6}, new int[]{8,10}, new int[]{15,18}};
        IntervalMerge merge = new IntervalMerge();
        int[][] solution = merge.solution(intervals);
        for (int i = 0; i < solution.length; i++){
            System.out.print(solution[i][0]);
            System.out.print(",");
            System.out.print(solution[i][1]);
            System.out.println();
        }
    }
}
