package com.leetcode.www.middle.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 * 解法: 排序 + 双指针
 * 复杂度分析
 *  时间复杂度：O(N^2)其中N是数组nums 的长度。
 *  空间复杂度：O(logN)。我们忽略存储答案的空间，额外的排序的空间复杂度为O(logN)。然而我们修改了输入的数组nums，在实际情况下不一定允许，
 *            因此也可以看成使用了一个额外的数组存储了nums 的副本并进行排序，空间复杂度为 O(N)。
 *
 */
public class ThreeNumberSum {


    public List<List<Integer>> solution(int[] nums){

        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();

        // 枚举a
        for (int first = 0; first < n; ++first){
            //需要和上次枚举的数不同
            if (first > 0 && nums[first] == nums[first - 1]){
                continue;
            }

            // c对应的指针指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            //枚举b
            for (int second = first + 1; second < n; ++second){
                if (second > (first + 1) && nums[second] == nums[second - 1]){
                    continue;
                }

                //需要保证b的指针在c的指针右侧
                while (third > second && (nums[second] + nums[third]) > target){
                    --third;
                }

                //如果指针重合，随着b的后续增加，就不会有满足a+b+c = 0 且b < c的c了，退出循环
                if (second == third){
                    break;
                }

                if ((nums[second] + nums[third]) == target){
                    ArrayList<Integer> collect = new ArrayList<>();
                    collect.add(nums[first]);
                    collect.add(nums[second]);
                    collect.add(nums[third]);
                    ans.add(collect);
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {

        int[] nums = new int[]{-1,0,1,2,-1,4};
        ThreeNumberSum threeNumberSum = new ThreeNumberSum();
        List<List<Integer>> solution = threeNumberSum.solution(nums);
        for (List<Integer> arr : solution){
            System.out.println(Arrays.toString(arr.toArray()));;
        }
    }
}
