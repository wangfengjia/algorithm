package com.leetcode.www.middle.tree;

import java.util.*;

/**
 * leetcode-103:二叉树的锯齿形层序遍历
 * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 */
public class ZigzagLevelOrder {


    /**
     * 进行层序遍历过程中，使用一个链表来保存这一层的节点值，根据同一层的节点是要求从左向右还是从右向左遍历，将节点值添加到链表的头部还是尾部
     * 复杂度分析
     *  时间复杂度:O(n),n表示二叉树的节点数目，每个节点仅仅会被遍历一次
     *  空间复杂度:O(n),存储节点的队列和存储节点值的双端队列
     * @param root
     * @return
     */
    public List<List<Integer>> solution(TreeNode root){

        if (root == null){
            return null;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        boolean isOrderLeft = true;
        List<List<Integer>> ans = new ArrayList<>();

        while (!queue.isEmpty()){

            Deque<Integer> levelList = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                //从左向右遍历时，后出列的元素在先出列的元素后面，所以后出列的元素添加到链表的尾部
                //从右向左遍历时，后出列的元素在先出列的元素前面，所以后出列的元素添加到链表的头部
                if (isOrderLeft){
                    levelList.offerLast(node.val);
                }else {
                    levelList.offerFirst(node.val);
                }
                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right != null){
                    queue.add(node.right);
                }
            }
            ans.add(new ArrayList<>(levelList));
            isOrderLeft = !isOrderLeft;
        }

        return ans;
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

    public static void main(String[] args) {

        ZigzagLevelOrder order = new ZigzagLevelOrder();

        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);

        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node2.right = node4;

        List<List<Integer>> ans = order.solution(root);
        System.out.println(ans);
    }
}
