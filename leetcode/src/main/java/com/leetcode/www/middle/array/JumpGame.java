package com.leetcode.www.middle.array;

/**
 * leetcode-55:跳跃游戏
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。判断你是否能够到达最后一个下标。
 */
public class JumpGame {


    /**
     * 贪心法:数组中任意位置y是否可以到达的条件是:只要存在一个位置x，这个x本身是可以到达的，并且它跳跃的最大长度为x+nums[i]这个值大于等于y。也就是说对于每一个
     * 可以达到的位置x，它使得x+1,x+2..x+nums[i]这些连续的位置都可以到达。因此我们可以遍历数组中的每一个位置，并实时维护最远可以到达的位置。在遍历的过程中
     * 如果最远可以到达的位置大于等于数组的最后一个位置，那说明最后一个位置可以到达，我们就可以返回true，否则返回false
     * 复杂度分析
     *  时间复杂度:O(N),遍历一遍数组
     *  空间复杂度:O(1)
     * @param nums
     * @return
     */
    public boolean solution(int[] nums){

        int n = nums.length;
        int maxPos = 0;
        for (int i = 0; i < n; i++){
            if (i <= maxPos){
                maxPos = Math.max(maxPos, i + nums[i]);
                if (maxPos >= n - 1){
                    return true;
                }
            }
        }

        return false;
    }


    public static void main(String[] args) {

        JumpGame jumpGame = new JumpGame();
        int[] nums = new int[]{2,3,1,1,4};
        int[] nums2 = new int[]{3,2,1,0,4};
        boolean ans = jumpGame.solution(nums2);
        System.out.println(ans);
    }
}
