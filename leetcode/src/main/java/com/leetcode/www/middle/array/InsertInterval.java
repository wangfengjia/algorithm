package com.leetcode.www.middle.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode-57:插入区间
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 */
public class InsertInterval {


    /**
     * 对于区间s1 = [l1,r1]和s2=[l2,r2],如果他们之间没有交集，说明要么s1在s2的左侧，此时r1 < l2,或者s1在s2的右侧，此时r2 < l1。其他条件下，s1和s2就有交集，它们
     * 的交集为[max(l1,l2), min(r1,r2)],并集为[min(l1,l2), max(r1,r2)].对于在不重叠的区间集合中插入一个新的区间，可以按照如下步骤来实现
     *  1. 找到所有与新区间有交集的区间集合x
     *  2. 将区间集合x中的所有区间加上新的区间合并为一个大的区间，也就是取并集
     *  3. 最终答案就是不和区间集合x有重叠的区间和合并后的大区间
     * 复杂度分析
     *  时间复杂度:O(n),n表示数组intervals的长度
     *  空间复杂度:O(1),除了存储返回答案的空间之外，只需要额外的常数空间即可
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] solution(int[][] intervals, int[] newInterval){

        int left = newInterval[0];
        int right = newInterval[1];
        List<int[]> ansList = new ArrayList<>();
        int n = intervals.length;
        boolean placed = false;
        for (int i = 0; i < n; i++){
            //在插入区间的右侧，且没有交集
            if (intervals[i][0] > right){
                if (!placed){
                    ansList.add(new int[]{left, right});
                    placed = true;
                }
                ansList.add(intervals[i]);
            }else if (intervals[i][1] < left){
                //在插入区间的左侧，且没有交集
                ansList.add(intervals[i]);
            }else {
                //有交集的区间，求区间的并集
                left = Math.min(left, intervals[i][0]);
                right = Math.max(right, intervals[i][1]);
            }
        }

        if (!placed){
            ansList.add(new int[]{left, right});
        }

        int[][] ans = new int[ansList.size()][2];
        for (int i = 0; i < ansList.size(); i++){
            ans[i] = ansList.get(i);
        }

        return ans;
    }

    public static void main(String[] args) {

        InsertInterval insertInterval = new InsertInterval();
        int[][] intervals = {
                {1,3},
                {6,9}
        };
        int[] newInterval = {2,5};
        int[][] ans = insertInterval.solution(intervals, newInterval);
        for (int[] interval : ans){
            System.out.print(Arrays.toString(interval));
            System.out.print(",");
        }
    }
}
