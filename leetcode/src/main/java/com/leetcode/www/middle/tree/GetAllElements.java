package com.leetcode.www.middle.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode-1305:两棵二叉搜索树中的所有元素
 * 给你 root1 和 root2 这两棵二叉搜索树。请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序
 */
public class GetAllElements {


    /**
     * 中序遍历+归并:二叉搜索树的中序遍历结果是一个有序数组，因此可以中序遍历这两棵二叉搜索树，得到两个有序数组，然后使用双指针合并这两个有序数组
     * 复杂度分析
     *  时间复杂度:O(n+m),其中n和m是两棵二叉搜索的节点个数
     *  空间复杂度:O(n+m),其中n和m是两棵二叉搜索的节点个数,存储结果数组和递归时的栈空间都是O(n+m)
     * @param root1
     * @param root2
     * @return
     */
    public List<Integer> solution(TreeNode root1, TreeNode root2){

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        inorder(root1, list1);
        inorder(root2, list2);

        List<Integer> ans = new ArrayList<>();
        int p1 = 0;
        int p2 = 0;
        while (true){
            if (p1 == list1.size()){
                ans.addAll(list2.subList(p2, list2.size()));
                break;
            }
            if (p2 == list2.size()){
                ans.addAll(list1.subList(p1, list1.size()));
                break;
            }
            if (list1.get(p1) < list2.get(p2)){
                ans.add(list1.get(p1++));
            }else {
                ans.add(list2.get(p2++));
            }
        }

        return ans;
    }

    private void inorder(TreeNode root, List<Integer> res){

        if (root == null){
            return;
        }

        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
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

        GetAllElements elements = new GetAllElements();

        TreeNode firstTreeRoot = new TreeNode(2);
        TreeNode firstTreeNode2 = new TreeNode(1);
        TreeNode firstTreeNode3 = new TreeNode(4);
        firstTreeRoot.left = firstTreeNode2;
        firstTreeRoot.right = firstTreeNode3;


        TreeNode secondTreeRoot = new TreeNode(1);
        TreeNode secondTreeNode2 = new TreeNode(0);
        TreeNode secondTreeNode3 = new TreeNode(3);
        secondTreeRoot.left = secondTreeNode2;
        secondTreeRoot.right = secondTreeNode3;

        List<Integer> ans = elements.solution(firstTreeRoot, secondTreeRoot);
        System.out.println(ans);
    }
}
