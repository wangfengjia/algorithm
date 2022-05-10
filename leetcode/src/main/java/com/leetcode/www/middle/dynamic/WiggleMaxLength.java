package com.leetcode.www.middle.dynamic;

/**
 * leetcode-376:摆动序列
 * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为 摆动序列 。第一个差（如果存在的话）可能是正数或负数。仅有一个元素或者含两个不等元素的序列也视作摆动序列。
 * 例如，[1, 7, 4, 9, 2, 5] 是一个 摆动序列 ，因为差值 (6, -3, 5, -7, 3)是正负交替出现的。
 * 相反，[1, 4, 7, 2, 5]和[1, 7, 4, 5, 5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
 * 子序列 可以通过从原始序列中删除一些（也可以不删除）元素来获得，剩下的元素保持其原始顺序。
 * 给你一个整数数组 nums ，返回 nums 中作为 摆动序列 的 最长子序列的长度 。
 *
 */
public class WiggleMaxLength {


    /**
     * 动态规划:一个元素作为摆动序列的一部分，这个元素要么是上升的，要么是下降的，这取决于前一个元素的大小
     *      1. 状态定义:
     *          1. up[i]表示以前i个元素中的某一个结尾的最长上升摆动序列长度
     *          2. down[i]表示以前i个元素中的某一个结尾的最长下降摆动序列
     *      2. 状态转移方程:
     *          1. up[i]的状态转移方程
     *              1. 当nums[i] <= nums[i-1]时，无法选出更长的上升摆动序列的长度。这个因为对于任何以nums[i]结尾的上升摆动序列，我们都可以将nums[i]替换为
     *                 nums[i-1],使其成为以nums[i-1]结尾的上升摆动序列
     *              2. 当nums[i] > nums[i-1]时，up[i]的状态可以从up[i-1]和down[i-1]得到，此时up[i] = max(up[i-1], down[i-1]+1)
     *          2. 根据up[i]的推导方式，同样可以得到down[i]的状态转移方程
     *              1. 当nums[i] >= nums[i-1],down[i] = down[i-1]
     *              2. nums[i] < nums[i-1],down[i] = max(down[i-1], up[i-1]+1)
     *      3. 边界条件:up[0]=down[0]=1
     * 复杂度分析
     *  时间复杂度:O(N),N表示数组的长度，只需要遍历数组一次
     *  空间复杂度:O(N),N表示数组的长度，状态数组所使用的空间
     * @param nums
     * @return
     */
    public int solution(int[] nums){

        int n = nums.length;
        if (n < 2){
            return n;
        }

        int[] up = new int[n];
        int[] down = new int[n];
        down[0] = up[0] = 1;
        for (int i = 1; i < n; i++){
            if (nums[i] > nums[i-1]){
                down[i] = down[i-1];
                up[i] = Math.max(up[i-1], down[i-1] + 1);
            }else if (nums[i] < nums[i-1]){
                up[i] = up[i-1];
                down[i] = Math.max(down[i-1], up[i-1]+1);
            }else {
                up[i] = up[i-1];
                down[i] = down[i-1];
            }
        }

        return Math.max(up[n-1], down[n-1]);
    }


    public static void main(String[] args) {

        WiggleMaxLength maxLength = new WiggleMaxLength();

        int[] nums1 = {1,7,4,9,2,5};
        int ans = maxLength.solution(nums1);
        System.out.println(ans);

        int[] nums2 = {1,17,5,10,13,15,10,5,16,8};
        int ans2 = maxLength.solution(nums2);
        System.out.println(ans2);
    }
}
