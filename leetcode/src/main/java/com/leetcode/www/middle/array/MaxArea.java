package com.leetcode.www.middle.array;


/**
 * 给定一个长度为 n 的整数数组height。有n条垂线，第 i 条线的两个端点是(i, 0)和(i, height[i])。找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * 说明：你不能倾斜容器。
 *
 */
public class MaxArea {

    public int solution(int[] height){

        int left = 0;
        int right = height.length - 1;
        int ans = 0;

        while (left < right){
            int area = Math.min(height[left], height[right]) * (right - left);
            ans = Math.max(area, ans);
            if (height[left] <= height[right]){
                ++left;
            }else {
                --right;
            }
        }

        return ans;
    }

    public static void main(String[] args) {

        int[] height = new int[]{1,8,6,2,5,4,8,3,7};
        MaxArea maxArea = new MaxArea();
        int area = maxArea.solution(height);
        System.out.println(area);
    }
}
