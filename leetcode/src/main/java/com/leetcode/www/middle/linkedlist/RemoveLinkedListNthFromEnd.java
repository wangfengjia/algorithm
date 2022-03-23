package com.leetcode.www.middle.linkedlist;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * leetcode19:给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 解法:
 * 1.双指针:前后两个指针，相差N个节点，然后前后指针(初始时前面的指针指向链表的头结点，后一个指针指向一个头结点的前一个节点(哑结点)，这样处理的话当前面的节点遍历到链
 *   表尾结点时，后面的指针指向的是需要被删除的节点的前一个节点，便于删除)往链表尾部移动，当前面的指针到达了链表尾节点，后面的指针就是倒数第n个节点
 */
public class RemoveLinkedListNthFromEnd {

    public ListNode solution(ListNode head, int n){

        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;

        for (int i = 0; i < n; i++){
            first = first.next;
        }

        while (first != null){
            first = first.next;
            second = second.next;
        }

        second.next = second.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

    private static class ListNode{

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
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        RemoveLinkedListNthFromEnd nthFromEnd = new RemoveLinkedListNthFromEnd();
        ListNode node = nthFromEnd.solution(head, 2);
        ArrayList<Integer> integers = new ArrayList<>();
        while (node != null){
            integers.add(node.val);
            node = node.next;
        }

        System.out.println(Arrays.toString(integers.toArray()));
    }
}
