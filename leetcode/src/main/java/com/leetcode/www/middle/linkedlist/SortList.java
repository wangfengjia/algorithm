package com.leetcode.www.middle.linkedlist;
/**
 * 链表排序，使用归并排序
 */
public class SortList {


    public ListNode solution(ListNode head){

        if (head.next == null){
            return head;
        }

        return sort(head, null);
    }

    public ListNode sort(ListNode head, ListNode tail){

        //递归终止条件是链表节点数量小于等于1
        if (head == null){
            return head;
        }
        if (head.next == tail){
            head.next = null;
            return head;
        }

        //寻找链表的中间节点,快指针一次走两步，慢指针一次走一步
        ListNode fast = head;
        ListNode slow = head;
        while (fast != tail){
            fast = fast.next;
            slow = slow.next;
            if (fast != tail){
                fast = fast.next;
            }
        }

        ListNode mid = slow;
        ListNode left = sort(head, mid);
        ListNode right = sort(mid, tail);

        return merge(left, right);
    }

    public ListNode merge(ListNode head1, ListNode head2){

        ListNode dumpyHead = new ListNode(-1);
        ListNode tmp = dumpyHead;
        ListNode tmp1 = head1;
        ListNode tmp2 = head2;
        while (tmp1 != null && tmp2 != null){
            if (tmp1.val <= tmp2.val){
                tmp.next = tmp1;
                tmp1 = tmp1.next;
            }else {
                tmp.next = tmp2;
                tmp2 = tmp2.next;
            }
            tmp = tmp.next;
        }
        if (tmp1 != null){
            tmp.next = tmp1;
        }else if (tmp2 != null){
            tmp.next = tmp2;
        }

        return dumpyHead.next;
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

        ListNode head = new ListNode(4);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(3);

        head.next = node2;
        node2.next = node3;
        node3.next = node4;

        SortList sortList = new SortList();
        ListNode node = sortList.solution(head);
        while (node != null){
            System.out.print(node.val);
            System.out.print(",");
            node = node.next;
        }
    }
}
