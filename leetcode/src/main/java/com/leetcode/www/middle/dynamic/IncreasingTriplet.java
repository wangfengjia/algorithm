package com.leetcode.www.middle.dynamic;

/**
 * leetcode-334:递增的三元子序列
 * 给你一个整数数组nums ，判断这个数组中是否存在长度为 3 的递增子序列。
 * 如果存在这样的三元组下标 (i, j, k)且满足 i < j < k ，使得nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。
 */
public class IncreasingTriplet {


    /**
     * 双向遍历:nums[i]要是是三元递增子序列的中间元素，那么在nums[i]左边存在一个元素小于nums[i]，这个等价于nums[i]左边的最小元素小于nums[i],同理,在nums[i]
     *        右边存在一个元素大于nums[i],这个等价于nums[i]右边的最大元素大于nums[i]。因此可以通过遍历数组，得到每个元素左边的最小元素和右边的最大元素，
     *        假如有一个下标i满足leftMin[i] < nums[i] < rightMax[i]这个条件，则这个数组存在递增的三元子序列
     * 复杂度分析
     *  时间复杂度:O(n),n表示数组nums的长度
     *  空间复杂度:O(n),n表示数组的长度
     * @param nums
     * @return
     */
    public boolean solution(int[] nums){

        int n = nums.length;
        if (n < 3){
            return false;
        }

        int[] leftMin = new int[n];
        leftMin[0] = nums[0];
        for (int i = 1; i < n; i++){
            leftMin[i] = Math.min(leftMin[i-1], nums[i]);
        }

        int[] rightMax = new int[n];
        rightMax[n-1] = nums[n-1];
        for (int i = n - 2; i >= 0; i--){
            rightMax[i] = Math.max(rightMax[i+1], nums[i]);
        }

        for (int i = 1; i < n - 1; i++){
            if (leftMin[i] < nums[i] || rightMax[i] > nums[i]){
                return true;
            }
        }

        return false;
    }

    /**
     * 贪心:用两个变量first、second分别表示递增的三元子序列中的第一个数和第二个数，任何时候first < second。first的初始值为nums[0],second值为无穷大。然后
     *     遍历数组，每个被遍历到的元素nums[i]进行如下比较
     *     1. 如果nums[i]大于second，则找到了一个递增的三元子序列，则返回true
     *     2. 如果nums[i]大于first，则second更新为nums[i]
     *     3. 否则，将first更新为nums[i]
     *     上述做法的贪心思想是:为了找到递增的三元子序列，first和second尽量小，此时找到递增的三元子序列的可能性更大
     * 复杂度分析
     *  时间复杂度:O(n),n表示数组nums的长度
     *  空间复杂度:O(1)
     * @param nums
     * @return
     */
    public boolean solutionV2(int[] nums){

        int n = nums.length;
        if (n < 3){
            return false;
        }

        int first = nums[0];
        int second = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++){
            int num = nums[i];
            if (num > second){
                return true;
            }else if (num > first){
                second = num;
            }else {
                first = num;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        IncreasingTriplet increasingTriplet = new IncreasingTriplet();
        int[] nums = {2,1,5,0,4,6};
        boolean ans = increasingTriplet.solution(nums);
        System.out.println(ans);
    }
}
