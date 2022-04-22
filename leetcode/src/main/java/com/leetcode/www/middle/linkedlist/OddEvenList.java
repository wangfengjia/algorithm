package com.leetcode.www.middle.linkedlist;

/**
 * leetcode-328:奇偶链表
 * 给定单链表的头节点head，将所有索引为奇数的节点和索引为偶数的节点分别组合在一起，然后返回重新排序的列表。第一个节点的索引被认为是 奇数，第二个节点的索引
 * 为偶数，以此类推。请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。
 *
 * 你必须在O(1)的额外空间复杂度和O(n)的时间复杂度下解决这个问题。
 */
public class OddEvenList {


    /**
     * 分离节点合并:将奇数节点和偶数节点分离成奇数链表和偶数链表，然后将偶数链表连接到奇数链表后，合并后的链表就是结果链表
     * 复杂度分析
     *  时间复杂度:O(n),n为链表节点数目，需要遍历链表中的每个节点，更新指针
     *  空间复杂度:O(1)
     * @param head
     * @return
     */
    public ListNode solution(ListNode head){

        if (head == null){
            return null;
        }

        ListNode evenHead = head.next;
        ListNode even = evenHead;
        ListNode odd = head;

        while (even != null && even.next != null){
            odd.next = even.next;
            odd = odd.next;
//            even.next = odd.next;
            even.next = even.next.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
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

        OddEvenList oddEvenList = new OddEvenList();
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        ListNode ans = oddEvenList.solution(head);
        while (ans != null){
            System.out.print(ans.val);
            System.out.print(",");
            ans = ans.next;
        }
    }
}
