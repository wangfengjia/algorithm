package com.leetcode.www.hard.array;

/**
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组nums1 和nums2。请你找出并返回这两个正序数组的 中位数 。
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * 思路
 * 一、二分查找
 * 如何把时间复杂度降低到 O(log(m+n)) 呢？如果对时间复杂度的要求有log，通常都需要用到二分查找，这道题也可以通过二分查找实现。
 * 根据中位数的定义，当 m+nm+n 是奇数时，中位数是两个有序数组中的第 (m+n)/2(m+n)/2 个元素，当 m+nm+n 是偶数时，中位数是两个有序数组
 * 中的第 (m+n)/2(m+n)/2 个元素和第 (m+n)/2+1(m+n)/2+1 个元素的平均值。因此，这道题可以转化成寻找两个有序数组中的第 k 小的数，其
 * 中 k 为 (m+n)/2(m+n)/2 或 (m+n)/2+1(m+n)/2+1。
 *
 */
public class FindMedianSortedArrays {


    public double solution(int[] num1, int[] num2){

        int length1 = num1.length;
        int length2 = num2.length;
        int totalLength = length1 + length2;
        if (totalLength % 2 == 1){
            int middleIndex = totalLength / 2;
            double median = getKthElement(num1, num2, middleIndex + 1);
            return median;
        }else {
            int midIndex1 = totalLength / 2 - 1;
            int midIndex2 = totalLength / 2;
            double median = (getKthElement(num1, num2, midIndex1) + getKthElement(num1, num2, midIndex2)) / 2.0;
            return median;
        }
    }


    /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
     * 这里的 "/" 表示整除
     * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
     * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
     * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
     * 这样 pivot 本身最大也只能是第 k-1 小的元素
     * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
     * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
     * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
     */
    public int getKthElement(int[] num1, int[] num2, int k){

        int length1 = num1.length;
        int length2 = num2.length;
        int index1 = 0;
        int index2 = 0;
        int kthElement = 0;

        while (true){

            /**
             * 边界情况
             * 1.如果一个数组已经为空，就说明这个数组中的所有元素都被排除，就可以返回另外一个数组第k小的元素
             * 2.如果k = 1,我们只要返回两个数组首元素的最小值就可以
             */
            if (index1 == length1){
                return num2[index2 + k - 1];
            }
            if (index2 == length2){
                return num1[index1 + k - 1];
            }

            if (k == 1){
                return Math.min(num1[index1], num2[index2]);
            }

             // 正常情况
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            int pivot1 = num1[newIndex1];
            int pivot2 = num2[newIndex2];
            if (pivot1 <= pivot2){
                k -= (newIndex1 - index1) + 1;
                index1 = newIndex1 + 1;
            }else {
                k -= (newIndex2 - index2) + 1;
                index2 = newIndex2 + 1;
            }
        }
    }

    /**
     * 在统计学上，中位数被用来将一个集合划分为两个长度相等的子集，其中一个子集中的元素总是大于另外一个集合中的元素，得出这个公式
     * i + j = m - i + n - j（当 m+nm+n 为偶数）或 i + j = m - i + n - j + 1（当 m+n 为奇数），进一步可以推导出i+j = (m+n+1) / 2
     * 然后因此我们可以对 ii 在 [0, m]的区间上进行二分搜索，找到最大的满足A[i−1]≤B[j] 的 ii 值，就得到了划分的方法。此时，划分前一部分元素中的最大值，
     * 以及划分后一部分元素中的最小值，才可能作为就是这两个数组的中位数。
     *
     * @return
     */
    public double solutionV2(int[] nums1, int[] nums2){

        if (nums1.length > nums2.length){
            return solutionV2(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;

        int left = 0;
        int right = m;
        //前一部分的最大值
        int median1 = 0;
        //后一部分的最小值
        int median2 = 0;

        while (left <= right){

            //前一部分包含nums1[0...i-1]和nums2[0....j-1]
            //后一部分包括nums1[i...m]和nums[j...n]
            int i = (left + right) / 2;
            int j = (m + n + 1) / 2 - i;

            // nums_im1, nums_i, nums_jm1, nums_j 分别表示 nums1[i-1], nums1[i], nums2[j-1], nums2[j]
            int nums_im1 = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
            int nums_i = i == m ? Integer.MAX_VALUE : nums1[i];
            int nums_jm1 = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
            int nums_j = j == n ? Integer.MAX_VALUE : nums2[j];

            if (nums_im1 <= nums_j){
                median1 = Math.max(nums_im1, nums_jm1);
                median2 = Math.min(nums_i, nums_j);
                left = i + 1;
            }else {
                right = i - 1;
            }
        }

        return (m + n) % 2 == 0 ? (median1 + median2) / 2.0 : median1;
    }


    public static void main(String[] args) {

        int[] nums1 = new int[]{1,3};
        int[] nums2 = new int[]{2};

        FindMedianSortedArrays sortedArrays = new FindMedianSortedArrays();
        double median = sortedArrays.solutionV2(nums1, nums2);
        System.out.println(median);
    }
}
