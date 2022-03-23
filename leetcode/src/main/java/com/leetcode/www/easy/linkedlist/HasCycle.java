package com.leetcode.www.easy.linkedlist;

/**
 * 判断链表是否有环:快慢指针
 */
public class HasCycle {


    /**
     * 快慢指针
     * @param head
     * @return
     */
    public boolean solution(ListNode head){


        //当链表为空或者链表只有一个节点时，不存在环
        if (head == null || head.next == null){
            return false;
        }

        //假想存在一个虚拟节点，快慢指针的起点都是这个虚拟节点，慢指针一次走一步，快指针一次走两步
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != slow){

            if (fast == null || fast.next == null){
                return true;
            }

            fast = fast.next.next;
            slow = slow.next;
        }

        return false;
    }

    public static class ListNode{

        int val;
        ListNode next;

        ListNode(int val){
            this.val = val;
        }
    }
}
