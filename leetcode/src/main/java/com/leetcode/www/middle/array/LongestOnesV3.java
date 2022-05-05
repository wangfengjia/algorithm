package com.leetcode.www.middle.array;

/**
 * leetcode-1004:最大连续1的个数 III
 * 给定一个二进制数组 nums 和一个整数 k，如果可以翻转最多 k 个 0 ，则返回 数组中连续 1 的最大个数 。
 */
public class LongestOnesV3 {


    /**
     * 题意可以转换为:找出一个最长的子数组，这个子数组内最多允许有k个0，就是求最大连续子区间，可以使用滑动窗口的方法来实现，滑动窗口的限制条件是:窗口内最多有
     * k个0，代码实现思路如下
     *  1. 使用left和right指针，分别指向滑动窗口的左右边界
     *  2. right主动右移:right指针每次移动一步，当nums[right]等于0时，说明滑动窗口0的数量加一
     *  3. left被动右移:判断此时窗口内0的个数，如果超过k，则left指针被动右移，直到窗口内0的数量小于等于k
     *  4. 滑动窗口长度的最大值就是答案
     * 复杂度分析
     *  时间复杂度:O(n),每个元素只遍历了一次
     *  空间复杂度:O(1)
     *
     * @param nums
     * @param k
     * @return
     */
    public int solution(int[] nums, int k){

        int n = nums.length;
        int ans = 0;
        int left = 0;
        int right = 0;
        int zeroNums = 0;

        while (right < n){
            if (nums[right] == 0){
                ++zeroNums;
            }
            while (zeroNums > k){
                if (nums[left++] == 0){
                    --zeroNums;
                }
            }
            ans = Math.max(ans, right - left + 1);
            right++;
        }

        return ans;
    }

    public static void main(String[] args) {

        LongestOnesV3 onesV3 = new LongestOnesV3();
        int[] nums = {1,1,1,0,0,0,1,1,1,1,0};
        int k = 2;
        int ans = onesV3.solution(nums, k);
        System.out.println(ans);
    }
}
