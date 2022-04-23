package com.leetcode.www.hard.linkedlist;

/**
 * leetcode-25:K 个一组翻转链表
 * 给你链表的头节点head ，每k个节点一组进行翻转，请你返回修改后的链表。k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是k的整数倍，那么请将最后剩
 * 余的节点保持原有顺序。你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 */
public class ReverseKGroup {


    /**
     * 把链表按照k个节点分组，每个组进行翻转链表，同时记录前一个组的尾结点和后一个组的头结点，翻转完成后，将后一个组的头结点连接前一个组的尾结点
     * 复杂度分析
     *  时间复杂度:O(n),n为链表的长度
     *  空间复杂度:O(1)
     * @param head
     * @param k
     * @return
     */
    public ListNode solution(ListNode head, int k){

        ListNode prevGroupTail = new ListNode(-1);
        ListNode dumpy = prevGroupTail;
        ListNode currentTail = head;

        while (currentTail != null){

            ListNode current = currentTail;
            ListNode currentPrev = null;
            int i;
            for (i = 1; i <= k-1; i++){
                currentTail = currentTail.next;
                if (currentTail == null){
                    break;
                }
            }

            ListNode nextStart;
            ListNode currentGroupHead;
            ListNode headTemp = null;
            if (i < k){
                nextStart = currentTail;
                currentGroupHead = current;
            }else {
                nextStart = currentTail.next;
                currentGroupHead = currentTail;
                headTemp = current;
                //要处理的节点数量
                for (int j = 1; j <= k; j++){
                    ListNode next = current.next;
                    current.next = currentPrev;
                    currentPrev = current;
                    current = next;
                }
            }

            prevGroupTail.next = currentGroupHead;
            prevGroupTail = headTemp;
            currentTail = nextStart;
        }

        return dumpy.next;
    }


    public ListNode solutionV2(ListNode head, int k){

        ListNode hair = new ListNode(-1);
        hair.next = head;
        ListNode preHead = hair;

        while (head != null){
            ListNode tail = preHead;
            for (int i = 0; i < k; i++){
                tail = tail.next;
                if (tail == null){
                    return hair.next;
                }
            }

            ListNode next = tail.next;
            ListNode[] listNodes = reverse(head, tail);
            head = listNodes[0];
            tail = listNodes[1];
            //将子链表拼接回原链表
            preHead.next = head;
            tail.next = next;
            preHead = tail;
            head = tail.next;
        }

        return hair.next;
    }

    private ListNode[] reverse(ListNode head, ListNode tail){

        ListNode prev = tail.next;
        ListNode current = head;
        while (prev != tail){
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return new ListNode[]{tail, head};
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

        ReverseKGroup reverseKGroup = new ReverseKGroup();


        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        int k = 3;
        ListNode ans = reverseKGroup.solutionV2(head, k);
        while (ans != null){
            System.out.print(ans.val);
            System.out.print(",");
            ans = ans.next;
        }
    }
}
