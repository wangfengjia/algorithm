package com.leetcode.www.middle.linkedlist;

import java.util.HashSet;
import java.util.List;

/**
 * 判断链表是否有环，无环就返回null，有环怎返回如环点
 */
public class DetectCycle {


    /**
     * 设链表中环外部分的长度为a。slow 指针进入环后，又走了b的距离与fast相遇。此时，fast 指针已经走完了环的n圈，因此它走过的总距离为 a+n(b+c)+b=a+(n+1)b+nc。
     * 又由于任意时刻，fast指针走过的距离都是slow指针的2倍，所以a+(n+1)b+nc = 2(a+b) => a = c + (n-1)(b+c)。根据这个关系可以得知从相遇点到入环点的距离
     * 加上n-1圈的环长恰好等于从链表头结点和入环点的距离。因此，我们可以在发现fast和slow相遇的时候，使用一个额外指针ptr,一开始的时候，ptr指针指向链表的头部，随后，
     * 它和slow指针每次向后移动一个位置，然后它们会在一个节点相遇，这个节点就是入环点
     *
     * 上述方法的前提条件是:慢指针会在环的第一圈没走完的情况下和快指针相遇:
     *
     * @param head
     * @return
     */
    public ListNode solution(ListNode head){

        if (head == null){
            return null;
        }

        ListNode fast = head;
        ListNode slow = head;

        while (fast != null){
            slow = slow.next;
            if (fast.next != null){
                fast = fast.next.next;
            }else {
                return null;
            }

            if (slow == fast){
                ListNode ptr = head;
                while (ptr != slow){
                    ptr = ptr.next;
                    slow = slow.next;
                }

                return ptr;
            }
        }

        return null;
    }

    /**
     * 遍历单向链表，记录已经遍历过的节点，对于每个节点去哈希表判断是否存在，第一个存在的就是入环点
     * @param head
     * @return
     */
    public ListNode solutionV2(ListNode head){

        ListNode pos = head;
        HashSet<ListNode> visited = new HashSet<>();
        while (pos != null){
            if (visited.contains(pos)){
                return pos;
            }else {
                visited.add(pos);
            }

            pos = pos.next;
        }

        return null;
    }

    public static class ListNode{

        int val;
        ListNode next;

        ListNode(){}
        ListNode(int val){
            this.val = val;
        }
    }
}
