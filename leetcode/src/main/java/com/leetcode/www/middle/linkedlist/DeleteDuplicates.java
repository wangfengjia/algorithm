package com.leetcode.www.middle.linkedlist;

/**
 * leetcode-82:删除排序链表中的重复元素 II
 * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
 */
public class DeleteDuplicates {

    /**
     * 由于链表是有序的，所以重复元素在链表中是连续的，可以通过一次遍历来删除链表中重复的元素。另外，链表的头结点也可能被删除，所以要使用一个虚节点
     * 复杂度分析
     *  时间复杂度:O(n),n为链表节点的数目
     *  空间复杂度:O(1)
     * @param head
     * @return
     */
    public ListNode solution(ListNode head){

        if (head == null){
            return null;
        }

        ListNode dummpy = new ListNode(-1);
        dummpy.next = head;
        ListNode cur = dummpy;
        while (cur.next != null && cur.next.next != null){
            //找到了重复的元素
            if (cur.next.val == cur.next.next.val){
                int val = cur.next.val;
                //删除cur.next及其后面节点值等于val的所有节点
                while (cur.next != null && cur.next.val == val){
                    cur.next = cur.next.next;
                }
            }else {
                cur = cur.next;
            }
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

        DeleteDuplicates duplicates = new DeleteDuplicates();
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(4);
        ListNode node7 = new ListNode(5);

        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

        ListNode ans = duplicates.solution(head);
        while (ans != null){
            System.out.print(ans.val);
            System.out.print(",");
            ans = ans.next;
        }
    }
}
