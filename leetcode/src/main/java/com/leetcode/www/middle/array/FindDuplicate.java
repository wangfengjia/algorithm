package com.leetcode.www.middle.array;

/**
 * 给定一个包含n + 1个整数的数组nums，其数字都在[1, n]范围内（包括 1 和 n），可知至少存在一个重复的整数。假设nums只有一个重复的整数 ，返回这个重复的数 。
 * 你设计的解决方案必须不修改数组nums且只用常量级 O(1) 的额外空间。
 */
public class FindDuplicate {


    /**
     * 解决办法:可以将数组构建成为图，每个位置i连一条i -> nums[i]的边，由于存在重复的数字target，因此target这个位置一定有起码两条指向它的边。这个问题就转换为环形链表找入口点的问题。
     *         这个问题可以通过快慢指针，快慢指针会在环里相遇，此时将慢指针指向链表的头结点，后续就快慢指针每次移动一步，快慢指针相遇的点就是环的入口点，就是重复的数
     * @return
     */
    public int solution(int[] nums){

        // 这个可以理解为链表头的虚节点
        int slow = 0;
        int fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }while (fast != slow);

        slow = 0;
        while (slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }

    public static void main(String[] args) {

        int[] nums = new int[]{1,3,4,2,2};
        int number = new FindDuplicate().solution(nums);
        System.out.println(number);
    }
}
