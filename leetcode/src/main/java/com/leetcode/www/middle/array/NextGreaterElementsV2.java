package com.leetcode.www.middle.array;


import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * leetcode-503:下一个更大元素 II
 * 给定一个循环数组nums（nums[nums.length - 1]的下一个元素是nums[0]），返回nums中每个元素的 下一个更大元素 。数字x的 下一个更大的元素 是按数组遍历顺
 * 序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 *
 */
public class NextGreaterElementsV2 {


    /**
     * 循环数组加单调栈:利用一个单调栈存数组下标，数组下标在单调栈里面是单调递增的。对数组进行遍历，遍历到位置i，我们就将当前单调栈里面所有下标对应值小于nums[i]
     *                的下标出栈，这些下标对应的元素的下一个更大的元素就是nums[i](证明:如果有更靠前的更大元素，这些位置会被提前出栈)，再将i放入栈中。由于是循环
     *                数组，所以遍历一次数组是不够的(最后一个元素的下一个更大的元素就没找到)。一个简单的思路是:可以将循环数组拉直。即复制该序列的前n-1个元素拼接
     *                在原序列的后面。在这个题目中，不需要对将循环数组显式拉直，只需要对数组下标取模即可
     * 复杂度分析
     *  时间复杂度:O(n),n为序列的长度
     *  空间复杂度:O(n),n为序列的长度，单调栈所消耗的空间
     * @param nums
     * @return
     */
    public int[] solution(int[] nums){

        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < 2 * n - 1; i++){
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]){
                ans[stack.pop()] = nums[i % n];
            }
            stack.push(i % n);
        }

        return ans;
    }

    public static void main(String[] args) {


        NextGreaterElementsV2 elementsV2 = new NextGreaterElementsV2();
        int[] nums = {1,2,1};
        int[] ans = elementsV2.solution(nums);
        System.out.println(Arrays.toString(ans));

        int[] nums2 = {1,2,3,4,3};
        int[] ans1 = elementsV2.solution(nums2);
        System.out.println(Arrays.toString(ans1));
    }
}
