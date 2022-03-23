package com.leetcode.www.middle.dynamic;

/**
 * 接雨水
 * @author wangyongchun
 */
public class CatchWater {

    public int trap(int[] height){

        if (height == null || height.length == 0){
            return 0;
        }

        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];

        //初始化，base case
        leftMax[0] = height[0];
        rightMax[height.length - 1] = height[height.length - 1];

        for (int i = 1; i < height.length; i++){
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
        }

        for (int i = height.length - 2; i >= 0; i--){
            rightMax[i] = Math.max(height[i], rightMax[i + 1]);
        }

        int ans = 0;
        for (int i = 1; i < height.length - 1; i++){
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return ans;
    }


    public int anotherTrap(int[] height){

        if (height == null || height.length == 0){
            return 0;
        }

        int ans = 0;
        int leftMax;
        int rightMax;

        //左右指针
        int left = 0;
        int right = height.length - 1;

        //初始化
        leftMax = height[0];
        rightMax = height[height.length - 1];

        while (left < right){

            //更新左右两边柱子最大值
            leftMax = Math.max(height[left], leftMax);
            rightMax = Math.max(height[right], rightMax);

            if (leftMax < rightMax){
                ans += leftMax - height[left];
                left++;
            }else {
                ans += rightMax - height[right];
                right--;
            }
        }

        return ans;
    }

    public static void main(String[] args) {


        CatchWater catchWater = new CatchWater();
        int[] height = {9,8,7,3,6};

        int trap = catchWater.anotherTrap(height);
        System.out.println(trap);
    }
}
