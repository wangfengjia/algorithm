package com.leetcode.www.middle.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode-99:恢复二叉搜索树
 * 给你二叉搜索树的根节点 root ，该树中的 恰好 两个节点的值被错误地交换。请在不改变其结构的情况下，恢复这棵树 。
 */
public class RecoverTree {


    /**
     * 中序遍历:二叉搜索树中序遍历得到的值序列是递增的，假如错误交换了两个节点，就会破坏中序遍历得到的值序列的递增性。递增的值序列交换两个值有两种情况:交换相邻值，
     *         这种条件下会有一个位置不满足nums[i] < nums[i+1];当交换的不是相邻值时，则会有两个位置不满足nums[i] < nums[i+1];根据这两种情况，得到解法
     *          1. 先进行中序遍历，得到二叉搜索树的值序列
     *          2. 找到值序列中不满足条件的位置,条件是nums[i] < nums[i+1]
     *          3. 如果有两个不满足条件的位置i,j，i<j && nums[i] > nums[i+1] && nums[j] > nums[j+1]，那么被错误交换的节点就是nums[i]和nums[j+1]
     *             对应的节点，分别记作x,y
     *          4. 如果有一个不满足条件的位置i, 那么被错误交换的节点就是nums[i]和nums[i+1]对应的节点,分别记作x,y
     *          5. 交换x和y两个节点
     * 复杂度分析
     *  时间复杂度:O(N),N为二叉搜索树的节点数，中序遍历需要O(N)的时间复杂度
     *  空间复杂度:O(N),需要使用nums数组来存储中序遍历得到的值列表
     * @param root
     */
    public void solution(TreeNode root){

        List<Integer> nums = new ArrayList<>();
        inorder(root, nums);
        int[] twoSwapped = findTwoSwapped(nums);
        recover(root, 2, twoSwapped[0], twoSwapped[1]);
    }

    public void inorder(TreeNode root, List<Integer> nums){

        if (root == null){
            return;
        }
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }

    private int[] findTwoSwapped(List<Integer> nums){

        int size = nums.size();
        int index1 = -1;
        int index2 = -1;
        for (int i = 0; i < size - 1; i++){
            if (nums.get(i) > nums.get(i+1)){
                index2 = i+1;
                if (index1 == -1){
                    index1 = i;
                }else {
                    break;
                }
            }
        }

        return new int[]{nums.get(index1), nums.get(index2)};
    }

    private void recover(TreeNode root, int count, int x, int y){

        if (root != null){
            if (root.val == x || root.val == y){
                root.val = root.val == x ? y : x;
                if (--count == 0){
                    return;
                }
            }
            recover(root.right, count, x, y);
            recover(root.left, count, x, y);
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

    public static void main(String[] args) {

        RecoverTree recoverTree = new RecoverTree();

        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(2);

        root.left = node1;
        root.right = node2;
        node2.left = node3;

        ArrayList<Integer> list = new ArrayList<>();
        recoverTree.inorder(root, list);
        System.out.println(list);

        recoverTree.solution(root);

        ArrayList<Integer> list1 = new ArrayList<>();
        recoverTree.inorder(root, list1);
        System.out.println(list1);
    }
}
