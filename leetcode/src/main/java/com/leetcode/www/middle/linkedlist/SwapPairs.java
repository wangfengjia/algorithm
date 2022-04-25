package com.leetcode.www.middle.linkedlist;


/**
 * leetcode-24:两两交换链表中的节点
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 */
public class SwapPairs {


    /**
     * 将链表每2个节点为一组，在每一组里面进行交换，然后连接到上一组的尾结点后面
     * 复杂度分析
     *  时间复杂度:O(n),n为链表节点数组
     *  空间复杂度:O(1)
     * @param head
     * @return
     */
    public ListNode solution(ListNode head){

        ListNode dummpy = new ListNode(-1, head);
        ListNode prev = dummpy;

        while (head != null && head.next != null){

            ListNode newNext = head.next.next;
            ListNode currentHead = head.next;
            ListNode currentTail = head;
            currentHead.next = currentTail;
            currentTail.next = newNext;
            prev.next = currentHead;
            prev = currentTail;
            head = newNext;
        }
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

        SwapPairs swapPairs = new SwapPairs();

        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;

        ListNode ans = swapPairs.solution(head);
        while (ans != null){
            System.out.print(ans.val);
            System.out.print(",");
            ans = ans.next;
        }
    }
}
