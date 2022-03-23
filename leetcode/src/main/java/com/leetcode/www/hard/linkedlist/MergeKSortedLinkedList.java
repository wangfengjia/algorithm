package com.leetcode.www.hard.linkedlist;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LeetCode23，合并多个有序链表
 * 思路:将k个链表的头结点放到小顶堆，堆顶元素就是最小的，重复这个步骤，最后合并了k个有序链表
 */
public class MergeKSortedLinkedList {

    public ListNode mergekSortedLinkedList(ListNode[] lists){
        int length = lists.length;
        if (length == 0){
            return null;
        }

        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<ListNode>(length, Comparator.comparingInt(a -> a.value));
        ListNode dumpyNode = new ListNode(-1);
        ListNode currentNode = dumpyNode;
        for (int i = 0; i < lists.length; i++){
            if (lists[i] != null){
                priorityQueue.add(lists[i]);
            }
        }

        while (!priorityQueue.isEmpty()){

            ListNode node = priorityQueue.poll();

            //合并后的链表的当前链表尾节点的下一个节点为从优先队列中取出的元素
            currentNode.next = node;
            //重置合并后的链表的尾节点指针
            currentNode = currentNode.next;
            //添加最小元素的下一个节点到优先队列
            if (node.next != null){
                priorityQueue.add(node.next);
            }
        }

        return dumpyNode.next;

    }


    /**
     * 利用分治的思想，将合并k个有序链表的问题分解为两个子问题(子问题可以递归分解):合并第i个到第middle个连接和合并第middle+1到第j个链表,然后利用两个子问题
     * 的解得到原问题的接
     * @param list
     * @return
     */
    public ListNode solutionV2(ListNode[] list){
        return merge(list, 0, list.length - 1);
    }

    public ListNode merge(ListNode[] list, int l, int r){

        if (l == r){
            return list[l];
        }

        if (l > r){
            return null;
        }

        int mid = (l + r) >> 1;
        ListNode left = merge(list, l, mid - 1);
        ListNode right = merge(list, mid + 1, r);
        return mergeTwoLists(left, right);
    }

    public ListNode mergeTwoLists(ListNode left, ListNode right){

        if (left == null || right == null){
            return left == null ? right : left;
        }

        ListNode head = new ListNode(0);
        ListNode tail = head;
        ListNode aPtr = left;
        ListNode bPtr = right;

        while (aPtr != null && bPtr != null){

            if (aPtr.value < bPtr.value){
                tail.next = aPtr;
                aPtr = aPtr.next;
            }else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }

        tail.next = aPtr != null ? aPtr : bPtr;
        return head.next;
    }

    private static class ListNode{

        public ListNode next;
        public int value;

        ListNode(){}
        public ListNode(int value){
            next = null;
            this.value = value;
        }
        ListNode(int val, ListNode next){
            this.next = next;
            this.value = val;
        }
    }

    public static void main(String[] args){

        ListNode first = new ListNode(1);
        ListNode first1 = new ListNode(3);
        ListNode first2 = new ListNode(5);
        ListNode first3 = new ListNode(7);
        ListNode first4 = new ListNode(9);
        first.next = first1;
        first1.next = first2;
        first2.next = first3;
        first3.next = first4;

        ListNode second = new ListNode(2);
        ListNode second1 = new ListNode(4);
        ListNode second2 = new ListNode(6);
        ListNode second3 = new ListNode(8);
        second.next = second1;
        second1.next = second2;
        second2.next = second3;

        ListNode third = new ListNode(-1);
        ListNode third1 = new ListNode(4);
        ListNode third2 = new ListNode(7);
        ListNode third3 = new ListNode(11);
        third.next = third1;
        third1.next = third2;
        third2.next = third3;

        ListNode[] nodes = {first, second, third};

        MergeKSortedLinkedList kSortedLinkedList = new MergeKSortedLinkedList();
        ListNode node = kSortedLinkedList.mergekSortedLinkedList(nodes);

        ListNode temp = node;
        while (temp != null){
            System.out.print("->" + temp.value);
            temp = temp.next;
        }

    }


}
