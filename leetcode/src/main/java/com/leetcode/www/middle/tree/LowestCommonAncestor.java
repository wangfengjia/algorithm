package com.leetcode.www.middle.tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * leetcode236(二叉树的最近公共祖先):给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 */
public class LowestCommonAncestor {

    public Map<Integer, TreeNode> parent = new HashMap<>();
    public Set<Integer> visited = new HashSet<>();
    public TreeNode ans;
    /**
     * 解法:用哈希表去存储所有节点的父节点，然后利用父节点的信息从p节点开始不断往上访问，并记录已经访问过的节点。再从q节点开始不断往上访问。如果碰到了已经访问过的
     *     节点，那么这个节点就是最近公共祖先
     * 复杂度
     *     时间复杂度:O(N),N为二叉树节点的数量,二叉树的所有节点只会会访问一次
     *     空间复杂度:O(N),N为二叉树节点的数量。递归调用的深度取决于二叉树的高度，二叉树最坏情况下会退化成一个链表，此时高度为N。用来存储每个节点的父节点的哈希表
     *              也是O(N)时间复杂度，所以总的时间复杂度为O(N)
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode solutionV1(TreeNode root, TreeNode p, TreeNode q){

        dfs(root);
        while (p != null){
            visited.add(p.val);
            p = parent.get(p.val);
        }

        while (q != null){
            if (visited.contains(q.val)){
                return q;
            }
            q = parent.get(q.val);
        }

        return null;
    }

    public void dfs(TreeNode root){

        if (root.left != null){
            parent.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null){
            parent.put(root.right.val, root);
            dfs(root.right);
        }
    }

    /**
     * 递归版本:当以下两个条件之一满足的时候，就可以找出p,q的最近公共祖先
     *         1. 当一个节点x，它左右子树不为null，p,q分别在这个节点的左右子树，那么节点x就是p,q的最近公共祖先
     *         2. 当节点x恰好是p或者q时，这个时候另外一个节点在节点x的左子树或者右子树，则节点x就是最近公共祖先
     *         3. 最近公共祖先就是公共祖先中深度最大的那个节点。由于在递归处理过程中，是从底向上，从叶子节点开始处理的，所以在满足条件的公共祖先中深度最大的公共
     *            祖先会最先被访问到
     * 复杂度
     *     时间复杂度:O(N),N为二叉树节点的数量，二叉树的所有节点会被访问一次
     *     空间复杂度:O(N),N为二叉树节点的数量。递归调用的深度取决于二叉树的高度，二叉树最坏情况下会退化成一个链表，此时高度为N
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode solutionV2(TreeNode root, TreeNode p, TreeNode q){

        dfsV2(root, p, q);
        return ans;
    }

    public boolean dfsV2(TreeNode root, TreeNode p, TreeNode q){

        if (root == null){
            return false;
        }

        boolean left = dfsV2(root.left, p, q);
        boolean right = dfsV2(root.right, p, q);
        if ((left && right) || ((root.val == p.val || root.val == q.val) && (left || right))){
            ans = root;
        }

        return left || right || (root.val == p.val || root.val == q.val);
    }
    public static class TreeNode{

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(){}
        TreeNode(int val){
            this.val = val;
        }
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(0);
        TreeNode node6 = new TreeNode(8);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(4);

        root.left = node1;
        root.right = node2;

        node1.left = node3;
        node1.right = node4;

        node2.left = node5;
        node2.right = node6;

        node4.left = node7;
        node4.right = node8;

        LowestCommonAncestor ancestor = new LowestCommonAncestor();
        TreeNode treeNode = ancestor.solutionV2(root, node1, node8);
        System.out.println(treeNode.val);

    }
}
