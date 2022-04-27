package com.leetcode.www.middle.array;

/**
 * leetcode-540:有序数组中的单一元素
 * 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
 * 请你找出并返回只出现一次的那个数。
 * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 */
public class SingleNonDuplicate {


    /**
     * 偶数下标的二分查找:由于只有一个数出现一次，其他数都是出现两次，则对于出现一次的元素下标x的左边有偶数个元素，所以下标x一定是偶数。因此，可以在偶数下标范围内
     *                 进行二分查找。二分查找的目标是找到满足nums[i] != nums[i+1]的最小偶数下标x，则下标x处的元素只是出现一次的元素。中间位置mid会等于
     *                 (high-low) / 2 + low,由于是在偶数下标中进行二分查找，需要确保mid值是偶数。再比较nums[mid]和nums[mid+1],如果相等则mid<i,调整
     *                 左边界，否则mid>=i,调整右边界，继续进行二分查找
     * 复杂度分析
     *  时间复杂度:O(log^n)
     *  空间复杂度:O(1)
     * @param nums
     * @return
     */
    public int solution(int[] nums){

        int low = 0;
        int high = nums.length - 1;

        while (low < high){
            int mid = (high - low) / 2 + low;
            //需要确保mid是偶数，当mid是偶数时，mid&1 = 0,当mid是奇数时，mid&1 = 0;所以可以通过mid -= mid&1来确保mid是偶数
            mid -= mid & 1;
            if (nums[mid] == nums[mid + 1]){
                //左指针从下标0开始向右移动
                low = mid + 2;
            }else {
                high = mid;
            }
        }

        return nums[low];
    }

    public static void main(String[] args) {

        SingleNonDuplicate duplicate = new SingleNonDuplicate();
        int[] nums = {1,1,2,3,3,4,4,8,8};
        int ans = duplicate.solution(nums);
        System.out.println(ans);
    }
}
