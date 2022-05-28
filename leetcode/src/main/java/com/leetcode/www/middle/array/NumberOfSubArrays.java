package com.leetcode.www.middle.array;

/**
 * leetcode-1248:统计「优美子数组」
 * 给你一个整数数组nums和一个整数 k。如果某个连续子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
 * 请返回这个数组中 「优美子数组」 的数目。
 */
public class NumberOfSubArrays {


    /**
     * 前缀和
     * 定义pre[i]表示[0..i]中奇数的个数，pre[i]可以由pre[i-1]推导而来。即pre[i] = pre[i-1]+(nums[i]&1)。那么[j..i]这个子数组中的奇数个数恰好为k,
     * 则可以转化为pre[i]-pre[j-1]=k,也就是pre[j-1] = pre[i]-k。同时使用一个数组来记录每一种前缀和的个数。
     * 因此我们可以遍历原数组，每遍历到一个元素，计算当前的前缀和sum，就在ans中累加上前缀和为sum-k的个数
     * 复杂度分析
     *  时间复杂度:O(n),n为数组nums的长度
     *  空间复杂度:O(n),n是数组nums的长度
     * @param nums
     * @param k
     * @return
     */
    public int solution(int[] nums, int k){

        int n = nums.length;
        //下标为前缀和，值为前缀和的个数
        int[] preCnt = new int[n+1];
        //遍历元素，计算当前元素的前缀和，统计到preCnt数组中
        //并且在ans中累加上与当前前缀和差值为k的前缀和的个数
        int sum = 0;
        int ans = 0;
        for (int num : nums){
            sum += num & 1;
            preCnt[sum]++;
            if (sum >= k){
                ans += preCnt[sum - k];
            }
        }

        return ans;
    }


    /**
     * 滑动窗口
     * right指针不断向右移动来扩大滑动窗口，使得窗口内有k个奇数，使用如下方法统计当前窗口的优美子数组的个数
        * 统计第一个奇数左边的偶数个数leftEvenCnt,这些偶数都可以作为优美子数组的起点，起点就有leftEvenCnt+1个选择(可以一个偶数都不取，所以需要加1)
        * 统计第k个奇数右边的偶数个数rightEvenCnt,这些偶数都可以作为优美子数组的终点，终点就有rightEvenCnt+1个选择(可以一个偶数都不取，所以需要加1)
        * 因此优美子数组左右起点组合数为(leftEvenCnt + 1) * (rightEvenCnt+1)
     *
     * 复杂度分析
     *  时间复杂度:O(n),n是数组nums的长度
     *  空间复杂度:O(1)
     * @param nums
     * @param k
     * @return
     */
    public int solutionV2(int[] nums, int k){

        int left = 0;
        int right = 0;
        int oddCnt = 0;
        int ans = 0;
        int n = nums.length;

        while (right < n){
            if ((nums[right++] & 1) == 1){
                ++oddCnt;
            }

            if (oddCnt == k){

                //right指针向右扩展，直到碰到一个奇数，就求出了第k个奇数右边的偶数个数
                int tmp = right;
                while (right < n && (nums[right] & 1) == 1){
                    ++right;
                }
                int rightEvenCnt = right - tmp;

                //统计第一个奇数左边的偶数
                int leftEvenCnt = 0;
                while ((nums[left] & 1) == 0){
                    ++left;
                    ++leftEvenCnt;
                }

                ans += (leftEvenCnt + 1) * (rightEvenCnt + 1);
                //此时left指针指向第一个奇数，而且这个区间已经统计完成，left指针右移一位，并且oddCnt减去1
                --oddCnt;
                ++left;
            }
        }

        return ans;
    }

    public static void main(String[] args) {

        NumberOfSubArrays subArrays = new NumberOfSubArrays();

        int[] nums = {1,1,2,1,1};
        int k = 3;

        int ans1 = subArrays.solution(nums, k);
        System.out.println(ans1);

        int ans2 = subArrays.solution(nums, k);
        System.out.println(ans2);
    }
}
