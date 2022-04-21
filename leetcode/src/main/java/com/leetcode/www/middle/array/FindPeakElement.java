package com.leetcode.www.middle.array;

/**
 * leetcode-162:寻找峰值
 * 峰值元素是指其值严格大于左右相邻值的元素。给你一个整数数组nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 * 你可以假设nums[-1] = nums[n] = -∞ 。你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
 */
public class FindPeakElement {


    /**
     * 迭代爬坡:我们不断往高处走，最终一定可以到达一个峰值的位置，所以我们可以首先在数组中随机选取一个初始位置i,然后根据nums[i-1],nums[i],nums[i+1]三者的关系来
     *         决定往哪个方向走
     *         1. 如果nums[i-1]<nums[i]>nums[i+1],那么此时i就是峰值的位置，直接返回i作为答案就可以
     *         2. 如果nums[i-1]<nums[i]<nums[i+1],此时在上坡，需要往右边走，i=i+1
     *         3. 如果nums[i-1]>nums[i]>nums[i+1],此时在下坡，需要往左走，i = i-1
     *         4. 如果nums[i-1]>nums[i]<nums[i+1],此时在山谷，两侧都是上坡，我们规定往右边走，i=i+1
     *         根据2，3，4可以得到，当位置i不是峰值位置时，总结为以下两种走法
     *         1. nums[i]<nums[i+1],就往右边走i=i+1
     *         2. nums[i]>nums[i+1],就往左边走i=i-1
     * 复杂度分析
     *  时间复杂度:O(n),n是数组的长度
     *  空间复杂度:O(1)
     * @param nums
     * @return
     */
    public int solution(int[] nums){

        int n = nums.length;
        int idx = (int) (Math.random() * n);

        while (!(compare(nums, idx - 1, idx) < 0 && compare(nums, idx, idx + 1) > 0)){
            if (compare(nums, idx, idx + 1) < 0){
                ++idx;
            }else {
                --idx;
            }
        }

        return idx;
    }

    /**
     * 二分查找:由上一个方法中得到:当num[i]<nums[i+1]往右走，所以在后续的迭代过程中i左边的所有位置不可能在后续迭代到，这样就过滤掉了一半的查找数据(二分思想)，因此
     *         可以如下处理
     *         1. 在可行下标范围[l,r]随机选取一个下标i
     *         2. 如果nums[i] < nums[i+1],我们可以抛弃掉[l,i]的范围，然后在[i+1,r]中继续随机选取下标i
     *         3. 如果nums[i] > nums[i+1],我们可以抛弃掉[i,r],然后在[l,i-1]中继续随机选取下标i
     *         4. 每次选择的下标是[l,r]的中点，那么每次可行的下标范围会减少一半
     *
     * 复杂度分析
     *  时间复杂度:O(log^n),n是数组的长度
     *  空间复杂度:O(1)
     * @param nums
     * @return
     */
    public int solutionV2(int[] nums){

        int n = nums.length;
        int left = 0;
        int right = n - 1;
        int ans = -1;

        while (left <= right){
            int mid = (left + right) / 2;
            if (compare(nums, mid - 1, mid) < 0 && compare(nums, mid, mid + 1) > 0){
                ans = mid;
                break;
            }
            if (compare(nums, mid, mid + 1) < 0){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }

        return ans;
    }

    /**
     * 辅助函数，输入下标idx，返回一个二元组(0/1, nums[idx]),方便处理nums[-1]以及nums[n]的边界情况
     * @param nums
     * @param idx
     * @return
     */
    private int[] get(int[] nums, int idx){

        if (idx == -1 || idx == nums.length){
            return new int[]{0,0};
        }

        return new int[]{1, nums[idx]};
    }

    private int compare(int[] nums, int idx1, int idx2){

        int[] nums1 = get(nums, idx1);
        int[] nums2 = get(nums, idx2);
        if (nums1[0] != nums2[0]){
            return nums1[0] > nums2[0] ? 1 : -1;
        }
        if (nums1[1] == nums2[1]){
            return 0;
        }

        return nums1[1] > nums2[1] ? 1 : -1;
    }


    public static void main(String[] args) {

        FindPeakElement peakElement = new FindPeakElement();
        int[] nums = {1,2,3,1,4};
        int ans1 = peakElement.solution(nums);
        int ans2 = peakElement.solutionV2(nums);
        System.out.println(ans1);
        System.out.println(ans2);
    }
}
