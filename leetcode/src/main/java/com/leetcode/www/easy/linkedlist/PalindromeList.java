package com.leetcode.www.easy.linkedlist;

/**
 * leetcode234(回文链表): 给你一个单链表的头节点head，请你判断该链表是否为回文链表。如果是，返回true ；否则，返回 false 。
 * 方法一(快慢指针):将链表的后半部分反转，然后将前半部分和后半部分进行比较，比较完成后恢复原样，流程的步骤如下
 *                1. 找到前半部分链表的尾结点
 *                2. 翻转后部分链表
 *                3. 判断是否是回文
 *                4. 恢复链表
 *                5. 返回结果
 */
public class PalindromeList {

    public boolean solution(ListNode head){

        if (head == null){
            return true;
        }

        ListNode middle = findMiddle(head);
        ListNode secondHalfPart = reverseList(middle.next);


        //判断是否是回文
        ListNode first = head;
        ListNode second = secondHalfPart;
        boolean result = true;
        while (result && second != null){
            if (first.val != second.val){
                result = false;
                break;
            }
            first = first.next;
            second = second.next;
        }

        //还原链表
        middle.next = reverseList(secondHalfPart);
        return result;
    }

    private ListNode reverseList(ListNode head){

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

    private ListNode findMiddle(ListNode head){
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
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
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        boolean result = new PalindromeList().solution(head);
        System.out.println(result);
    }
}
