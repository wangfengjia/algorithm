package com.leetcode.www.middle.linkedlist;

public class Partition {


    /**
     * 自己实现的方案
     * @param head
     * @param x
     * @return
     */
    public ListNode solution(ListNode head, int x){

        if (head == null){
            return null;
        }

        ListNode dummpy = new ListNode(-1, head);
        ListNode cur = dummpy;
        ListNode ans = new ListNode(-1, head);
        ListNode target = ans;

        while (cur != null && cur.next != null){
            if (cur.next.val < x){
                if (cur.next == head){
                    target = head;
                }else {
                    ListNode node = cur.next;
                    cur.next = cur.next.next;
                    node.next = target.next;
                    target.next = node;
                    target = target.next;
                }

            }
            cur = cur.next;
        }

        return ans.next;
    }

    /**
     * 通过维护两个链表small和large，small链表按顺序存储所有小于x的节点，large链表按顺序存储所有大于等于x的节点，遍历完链表以后，只需要将small链表的尾结点
     * 指向large链表的头结点即可。在实现的过程中，通过两个虚节点来处理边界条件
     * 复杂度分析
     *  时间复杂度:O(n),n为原链表的长度
     *  空间复杂度:O(1)
     * @param head
     * @param x
     * @return
     */
    public ListNode solutionV2(ListNode head, int x){

        ListNode smallHead = new ListNode(-1);
        ListNode small = smallHead;
        ListNode largeHead = new ListNode(-1);
        ListNode large = largeHead;

        while (head != null){
            if (head.val < x){
                small.next = head;
                small = small.next;
            }else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }

        //当前节点复用的是原链表的节点，next指针在原链表可能指向一个小于x的节点，所以需要切断这个引用
        large.next = null;
        small.next = largeHead.next;
        return smallHead.next;
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

        Partition partition = new Partition();
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(2);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode ans = partition.solutionV2(head, 3);
        while (ans != null){
            System.out.print(ans.val);
            System.out.print(",");
            ans = ans.next;
        }
    }
}
