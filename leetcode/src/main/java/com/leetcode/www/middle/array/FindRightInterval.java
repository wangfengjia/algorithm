package com.leetcode.www.middle.array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * leetcode-436:寻找右区间
 * 给你一个区间数组 intervals ，其中intervals[i] = [starti, endi] ，且每个starti 都 不同 。
 * 区间 i 的 右侧区间 可以记作区间 j ，并满足 startj>= endi ，且 startj 最小化 。
 * 返回一个由每个区间 i 的 右侧区间 的最小起始位置组成的数组。如果某个区间 i 不存在对应的 右侧区间 ，则下标 i 处的值设为 -1 。
 */
public class FindRightInterval {

    /**
     * 排序+二分:对每个区间的左端点排序，并且使用一个数组startIntervals保存每个左端点所在的区间的下标i。然后枚举每个区间的右端点，利用二分查找在左端点排序后的数组
     *         中找到大于等于右端点的最小值val即可,此时区间i对应的右侧区间的下标就是val对应的索引
     * 复杂度分析
     *  时间复杂度:O(nlog^n),n为区间数组的长度，排序的时间复杂度为O(nlog^n),每次二分查找的时间复杂度为O(log^n),一共进行n次查找，所以总的时间复杂度为O(nlog^n)
     *  空间复杂度:O(n),n为区间数组的长度，startIntervals里面一共存储了n个元素，所以空间复杂度为O(n)
     * @param intervals
     * @return
     */
    public int[] solution(int[][] intervals){

        int n = intervals.length;
        int[][] startIntervals = new int[n][2];
        for (int i = 0; i < n; i++){
            startIntervals[i][0] = intervals[i][0];
            startIntervals[i][1] = i;
        }
        Arrays.sort(startIntervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int[] ans = new int[n];
        for (int i = 0; i < n; i++){
            int left = 0;
            int right = n - 1;
            int target = -1;
            while (left <= right){
                int mid = (left + right) / 2;
                if (startIntervals[mid][0] >= intervals[i][1]){
                    target = startIntervals[mid][1];
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            }
            ans[i] = target;
        }

        return ans;
    }

    /**
     * 双指针:分别使用数组startIntervals和endIntervals来保存每个区间的左端点和右端点，再对这两个数组里面的元素从小到大排序。然后遍历endIntervals里面的每个
     *       区间，在startIntervals查找到第一个大于等于它的区间j。由于startIntervals和endIntervals都是从小到大排序，所以endIntervals的后续区间j后面进行
     *       查找，不再需要从startIntervals数组的第一个元素开始查找
     * @param intervals
     * @return
     */
    public int[] solutionV2(int[][] intervals){

        int n = intervals.length;
        int[][] startIntervals = new int[n][2];
        int[][] endIntervals = new int[n][2];
        for (int i = 0; i < n; i++){
            startIntervals[i][0] = intervals[i][0];
            startIntervals[i][1] = i;
            endIntervals[i][0] = intervals[i][1];
            endIntervals[i][1] = i;
        }

        Arrays.sort(startIntervals, (a,b) -> a[0] - b[0]);
        Arrays.sort(endIntervals, (a,b) -> a[0] - b[0]);
        int[] ans = new int[n];
        for (int i = 0, j = 0; i < n; i++){
            while (j < n && endIntervals[i][0] > startIntervals[j][0]){
                j++;
            }
            if (j < n){
                ans[endIntervals[i][1]] = startIntervals[j][1];
            }else {
                ans[endIntervals[i][1]] = -1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {

        FindRightInterval findRightInterval = new FindRightInterval();
        int[][] intervals = {
                {1,4},
                {2,3},
                {3,4}
        };
        int[] ans = findRightInterval.solution(intervals);
        System.out.println(Arrays.toString(ans));

        int[] ans2 = findRightInterval.solutionV2(intervals);
        System.out.println(Arrays.toString(ans2));
    }
}
