package com.leetcode.www.middle.tree;

/**
 * 将有序链表转换为高度平衡的二叉搜索树(每个节点的左右子树的高度差不超过1)
 * 思路(分治加中序遍历): 要实现高度平衡，每颗子树的根节点都需要是这棵树所有节点的中间节点。因此，可以先求出链表的总长度，然后定位到中间节点，这样的话中间节点左边
 * 的节点就是左子树的节点(得到左子树节点数量)，中间节点右边的就是右子树节点(得到右子树节点数量)。最后使用相同的方法递归构造左右子树。
 * 由于随机访问一个链表节点的时间复杂度为O(n),这样做的时间复杂度比较高，因此就不直接去获取中位数节点的值，而是使用一个占位符，等到中序遍历到这个节点时，再填充它的值
 */
public class SortedListToBST {

    public ListNode globalHead;

    public TreeNode solution(ListNode head){

        globalHead = head;
        int length = 0;
        while (head != null){
            ++length;
            head = head.next;
        }

        return buildTree(0, length - 1);
    }

    public TreeNode buildTree(int left, int right){

        if (left > right){
            return null;
        }

        int mid = (right + left + 1) / 2;
        TreeNode root = new TreeNode(-1);
        root.left = buildTree(left, mid - 1);
        //中位数节点的值在创建的时候先用占位符，等到中序遍历到它的时候赋值
        root.val = globalHead.val;
        globalHead = globalHead.next;
        root.right = buildTree(mid + 1, right);
        return root;
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


    public static class TreeNode{

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(){}
        TreeNode(int val){
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
