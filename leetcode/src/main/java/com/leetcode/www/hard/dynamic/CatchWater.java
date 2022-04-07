package com.leetcode.www.hard.dynamic;

import java.util.Deque;
import java.util.LinkedList;

/**
 * leetcode-42:接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 */
public class CatchWater {

    /**
     * 对于每根柱子能接的雨水可以通过枚举每一根柱子，然后向这根柱子左右两侧扩展，找到大于这个柱子的高度的最大高度，接水的量就等于min(leftMax,rightMax)-heights[i],
     * 直接暴力求解的方法时间复杂度比较高，需要O(N^2),就是n个元素，每个元素需要O(N)时间复杂度求解左右最大高度。可以使用动态规划的办法来做
     * 动态规划
     *      1. 状态定义
     *          1. leftMax[i]表示下标i左边的位置中，height的最大高度
     *          2. rightMax[i]表示下标i右边的位置中，height的最大高度
     *       2. 状态转移方程
     *          1. 当1<=i<=n-1时，leftMax[i] = max(leftMax[i-1], heights[i])
     *          2. 当0<=i<=n-2时，rightMax[i] = max(rightMax[i+1], heights[i])
     *       3. 边界条件
     *          leftMax[0] = heights[0], rightMax[n-1] = height[n-1]
     *       4. 复杂度分析
     *          1. 时间复杂度:O(n),在计算leftMax和rightMax的过程中，需要遍历数组heights一次
     *          2. 空间复杂度:O(n),数组leftMax和rightMax所占用的空间
     *
     * @param heights
     * @return
     */
    public int solution(int[] heights){

        int n = heights.length;
        if (n == 0){
            return 0;
        }

        int[] leftMax = new int[n];
        leftMax[0] = heights[0];
        for (int i = 1; i < n; i++){
            leftMax[i] = Math.max(leftMax[i-1], heights[i]);
        }

        int[] rightMax = new int[n];
        rightMax[n-1] = heights[n-1];
        for (int i = n - 2; i >= 0; i--){
            rightMax[i] = Math.max(rightMax[i+1], heights[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; i++){
            ans += Math.min(leftMax[i], rightMax[i]) - heights[i];
        }

        return ans;
    }

    /**
     * 可以使用单调栈，单调递减的栈，单调栈里面维护的是数组元素下标。假设栈顶元素的下标为top，栈顶的下一个元素是left，遍历数组过程中，当遍历到元素i时，且
     * heights[i] > heights[top],此时left和i就是top的左右边界，求出这个区域的面积，就是能接的水量，重复这个过程，就可以求出能接的雨水总量
     * 复杂度分析
     *  1. 时间复杂度:O(n),其中n为数组的长度，所有元素只会出栈和入栈一次
     *  2. 空间复杂度:O(n),n是数组的长度，栈所占用的空间不会超过n
     * @param heights
     * @return
     */
    public int solutionV1(int[] heights){

        int ans = 0;
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < heights.length; i++){
            while (!stack.isEmpty() && heights[i] > heights[stack.peek()]){
                int top = stack.pop();
                if (stack.isEmpty()){
                    break;
                }
                int left = stack.peek();
                int width = i - left - 1;
                int height = Math.min(heights[i], heights[left]) - heights[top];
                ans += width * height;
            }
            stack.push(i);
        }

        return ans;
    }

    public static void main(String[] args) {


        CatchWater catchWater = new CatchWater();
        int[] heights = {0,1,0,2,1,0,1,3,2,1,2,1};
        int ans = catchWater.solutionV1(heights);
        System.out.println(ans);
    }
}
