package com.leetcode.www.middle.linkedlist;


import java.util.List;

/**
 * leetcode-61:旋转链表
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 */
public class RotateRight {

    /**
     * 先得到链表的节点数目，需要向右移动k % n个节点，然后使用快慢指针，快指针移动到链表尾结点时结束，同时使用一个虚节点，虚节点初始时是指向头结点。最后将虚节点的
     * next指针指向slow的下一个节点即可
     * 复杂度分析
     *  时间复杂度:O(n),n表示链表节点的数目
     *  空间复杂度:O(1)
     * @param head
     * @param k
     * @return
     */
    public ListNode solution(ListNode head, int k){

        if (head == null){
            return null;
        }


        int count = 0;
        ListNode ptr = head;
        while (ptr != null){
            ++count;
            ptr = ptr.next;
        }

        //取模的元素是可能k的值大于链表节点总数，当向右移动链表节点数目n的次数时，链表变为原状，所以其实只需要向右移动 k % n个节点
        k %= count;
        ListNode dummpy = new ListNode(-1, head);
        ListNode slow = dummpy;
        ListNode fast = head;

        for (int i = 0; i < k-1; i++){
            fast = fast.next;
        }

        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next;
        }

        fast.next = dummpy.next;
        dummpy.next = slow.next;
        slow.next = null;

        return dummpy.next;
    }

    public static class ListNode{

        int val;
        ListNode next;
        ListNode(){}
        ListNode(int val){
            this.val = val;
        }
        ListNode(int val, ListNode next){
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {

        RotateRight rotateRight = new RotateRight();

        ListNode head = new ListNode(0);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(4);
//        ListNode node4 = new ListNode(5);

        head.next = node1;
        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;

        int k = 4;
        ListNode ans = rotateRight.solution(head, k);
        while (ans != null){
            System.out.print(ans.val);
            System.out.print(",");
            ans = ans.next;
        }
    }
}
