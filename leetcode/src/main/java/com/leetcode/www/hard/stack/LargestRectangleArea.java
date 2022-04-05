package com.leetcode.www.hard.stack;

import java.time.chrono.IsoChronology;
import java.util.Deque;
import java.util.LinkedList;

/**
 * leetcode-84:柱状图中的最大矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。求在该柱状图中，能够勾勒出来的矩形的最大面积。
 */
public class LargestRectangleArea {


    /**
     * 可以使用枚举柱高(高度固定，找到这个高度对应的最大宽度)的做法:首先枚举一根柱子i，它的高度为h，随后我们向两边扩展，在左右两侧找到最近高度小于h的柱子，这样这
     * 两根柱子之间的所有高度均不小于h，这也就是i能扩招到的最远范围。
     * 我们可以使用单调栈来做，单调栈里面存放的是数组下标，单调栈里面存放的下标从栈低到栈顶是单调递增的，并且对应的高度也是单调递增的。
     *      1. 当我们枚举到第i根柱子时，我们从栈顶不断移除hights[j] >= height[i]的j值，在移除完毕以后，栈顶的j值就一定满足height[j] < heights[i],此时
     *         j就是i左侧最近的小于其高度的柱子。这里面有个特殊的情况。如果我们移除了栈中所有的j值，那就说明i左侧的所有柱子的高度都大于heights[i]，这个时候我们
     *         可以认为i左侧且最近的小于其高度的柱子在位置j=-1,它是一根虚拟的，高度无限低的柱子，这根虚拟的柱子就是哨兵
     *      2. 再将i放入栈中
     * @param heights
     * @return
     */
    public int solution(int[] heights){

        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];

        Deque<Integer> stack = new LinkedList<>();
        //确定每根柱子左侧最近的小于其高度的柱子
        for (int i = 0; i < n; i++){
            if (!stack.isEmpty() && heights[stack.peek()] >= heights[i]){
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        stack.clear();
        //确定每根柱子右侧最近的小于其高度的柱子
        for (int i = n - 1; i >= 0; --i){
            if (!stack.isEmpty() && heights[stack.peek()] >= heights[i]){
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        // 求最大面积
        int ans = 0;
        for (int i = 0; i < n; i++){
            int height = heights[i];
            //左右哨兵分别是-1或者是n，所以需要减去1
            int width = right[i] - left[i] - 1;
            ans = Math.max(ans, (height * width));
        }

        return ans;
    }


    public static void main(String[] args) {

        LargestRectangleArea rectangleArea = new LargestRectangleArea();
        int[] heights = new int[]{2,4};
        int ans = rectangleArea.solution(heights);
        System.out.println(ans);
    }
}
