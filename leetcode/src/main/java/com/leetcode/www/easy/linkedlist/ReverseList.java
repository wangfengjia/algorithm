package com.leetcode.www.easy.linkedlist;

/**
 * 反转单向链表
 */
public class ReverseList {

    /**
     * 将当前节点的next指针指向前一个节点，由于节点没有存储前一个节点，所以利用一个变量来存储前一个节点
     * @param head
     * @return
     */
    public ListNode solution(ListNode head){

        ListNode prev = null;
        ListNode cur = head;
        while (cur != null){
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
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

        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);

        head.next = node2;
        node2.next = node3;

        ReverseList reverseList = new ReverseList();
        ListNode newHead = reverseList.solution(head);
        while (newHead != null){
            System.out.print(newHead.val);
            System.out.print(",");
            newHead = newHead.next;
        }
    }
}
