package com.leetcode.www.middle.linkedlist;

/**
 * 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0开头。
 *
 * 思路与算法
 *
 * 由于输入的两个链表都是逆序存储数字的位数的，因此两个链表中同一位置的数字可以直接相加。
 * 我们同时遍历两个链表，逐位计算它们的和，并与当前位置的进位值相加。具体而言，如果当前两个链表处相应位置的数字为 n1,n2，进位值为carry，则它们的和为 n1+n2+carry；
 * 其中，答案链表处相应位置的数字为(n1+n2+carry)mod10，而新的进位值为 (n1+n2+carry) / 10
 * 如果两个链表的长度不同，则可以认为长度短的链表的后面有若干个0;此外，如果链表遍历结束后，有carry>0，还需要在答案链表的后面附加一个节点，节点的值为carry。
 *
 */
public class AddTwoNumbers {


    public ListNode solution(ListNode l1, ListNode l2){

        ListNode head = null;
        ListNode tail = null;
        int carry = 0;

        while (l1 != null || l2 != null){

            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null){
                head = tail = new ListNode(sum % 10);
            }else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }

            carry = sum / 10;
            if (l1 != null){
                l1 = l1.next;
            }
            if (l2 != null){
                l2 = l2.next;
            }
        }

        if (carry > 0){
            tail.next = new ListNode(carry);
        }

        return head;
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

        ListNode list1 = new ListNode(2);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(3);
        list1.next = node2;
        node2.next = node3;


        ListNode list2 = new ListNode(5);
        ListNode node4 = new ListNode(6);
        ListNode node5 = new ListNode(4);
        list2.next = node4;
        node4.next = node5;

        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        ListNode result = addTwoNumbers.solution(list1, list2);

        ListNode head = result;
        while (head != null){
            System.out.println(head.val);
            head = head.next;
        }
    }
}
