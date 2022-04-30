package com.leetcode.www.hard.dynamic;


/**
 * leetcode-135:分发糖果
 * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 * 你需要按照以下要求，给这些孩子分发糖果：
 * 每个孩子至少分配到 1 个糖果。
 * 相邻两个孩子评分更高的孩子会获得更多的糖果。
 * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 */
public class DistributeCandy {


    /**
     * 可以将"相邻的孩子中,评分高的孩子必须获得更多的糖果"这个规则拆分为如下两个规则
     *      1. 左规则:如果nums[i] > nums[i-1],则第i号孩子的糖果数量将比第i-1号孩子的糖果数量多
     *      2. 右规则:如果num[i] > nums[i+1],则第i号孩子的糖果数量将比第i+1号孩子的糖果数量多
     * 然后我们遍历两遍数组，处理每一个学生分别满足左规则或者右规则，最少需要被分得的糖果数量，每个人最终分得的糖果数量就是这两个数量的最大值
     * 复杂度分析
     *  时间复杂度:O(n),n为数组的长度
     *  空间复杂度:O(n),n为数组的长度
     * @param nums
     * @return
     */
    public int solution(int[] nums){

        int n = nums.length;
        int[] left = new int[n];
        int ans = 0;

        for (int i = 0; i < n; i++){
            if (i > 0 && nums[i] > nums[i - 1]){
                left[i] = left[i - 1] + 1;
            }else {
                left[i] = 1;
            }
        }

        int right = 0;
        for (int i = n - 1; i >= 0; i--){
            if (i < n - 1 && nums[i] > nums[i + 1]){
                right += 1;
            }else {
                right = 1;
            }
            ans += Math.max(left[i], right);
        }


        return ans;
    }


    /**
     * 空间复杂度O(1)的解法:从左向右枚举每一个同学，记前一个同学分得的糖果数量为pre，通过如下处理
     *      1. 如果当前同学比上一个同学评分高，说明我们在递增序列中，直接分配给该同学pre+1个糖果
     *      2. 否则就在一个递减队列中，直接分给给当前同学一个糖果，并把该同学所在的递减序列中所有的同学再多分配一个糖果，以保证糖果数量满足条件
     *          1. 无需显式地额外分配糖果，只需记录当前递减序列的长度，即可知道需要额外分配的糖果
     *          2. 如果当前的递减序列的长度和上一个递增序列等长时，需要把最近的递增序列的最后一个同学也并进递减序列中
     * @param nums
     * @return
     */
    public int solutionV2(int[] nums){

        int n = nums.length;
        int ans = 1;
        int inc = 1;
        int dec = 0;
        int pre = 1;

        for (int i = 1; i < n; i++){
            if (nums[i] >= nums[i-1]){
                dec = 0;
                pre = nums[i] == nums[i-1] ? 1 : pre + 1;
                ans += pre;
                inc = pre;
            }else {
                dec++;
                //如果当前的递减序列的长度和上一个递增序列等长时，需要把最近的递增序列的最后一个同学也并进递减序列中，如[1,2,4,3,2,1],假如没有这个处理，
                //每个同学分到的糖果数量为[1,2,3,3,2,1],这样nums[2] > nums[3],但是糖果数相等，所以不满足条件
                if (dec == inc){
                    ++dec;
                }
                ans += dec;
                pre = 1;
            }
        }

        return ans;
    }


    public static void main(String[] args) {

        DistributeCandy distributeCandy = new DistributeCandy();
        int[] nums = {1,2,4,3,2,1};
        int ans1 = distributeCandy.solution(nums);
        int ans2 = distributeCandy.solutionV2(nums);
        System.out.println(ans1);
        System.out.println(ans2);
    }
}
