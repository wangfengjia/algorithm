package com.leetcode.www.easy.linkedlist;

/**
 * 相交链表:求两个链表相交的起始节点。如果不存在,返回null
 * 方法一(双指针):有两个指针ptrA和ptrB，分表执行链表A和链表B,然后分以下几种情况去移动
 *              1. 当ptrA和ptrB不为空时，分别移动到下一个节点
 *              2.当两个链表的长度一样时，两个指针会同时到达两个链表相交的起始点
 *              3.当两个链表的长度不相等时:当ptrA或者ptrB为空时，将指针指向另外一个链表的头结点，然后移动到下一个节点，假如这两个链表相交，则这两个指针
 *                重合的时候指向的节点就是起始节点。证明:假如链表A不想交的部分有a个节点，链表B不想交的部分有b个节点，相交的部分有c个节点。经过这个步骤，
 *                当ptrA移动了a+c+b次，ptrB移动了b+c+a次后，两个指针会在相交起始点重合
*               4.时间复杂度:O(m+n),m,n分别是链表A,B的长度，两个指针同时遍历两个链表,每个指针遍历两个链表各一次
 *                空间复杂度:O(1),
 */
public class IntersectionNode {


    public ListNode solution(ListNode head1, ListNode head2){

        if (head1 == null || head2 == null){
            return null;
        }

        ListNode ptrA = head1;
        ListNode ptrB = head2;
        //即使两个链表没有交集，也可能出现ptrA等于ptrB的情况，那就是都等于null的时候
        while (ptrA != ptrB){
            ptrA = ptrA == null ? head2 : ptrA.next;
            ptrB = ptrB == null ? head1 : ptrB.next;
        }

        return ptrA;
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

        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        a1.next = a2;

        ListNode b1 = new ListNode(3);
        ListNode b2 = new ListNode(4);
        ListNode b3 = new ListNode(5);
        b1.next = b2;
        b2.next = b3;

        ListNode c1 = new ListNode(6);
        ListNode c2 = new ListNode(7);
        ListNode c3 = new ListNode(8);
        c1.next = c2;
        c2.next = c3;

        a2.next = c1;
        b3.next = c1;

        IntersectionNode intersectionNode = new IntersectionNode();
        ListNode node = intersectionNode.solution(a1, b1);
        System.out.println(node.val);
    }
}
