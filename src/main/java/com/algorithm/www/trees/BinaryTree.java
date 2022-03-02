package com.algorithm.www.trees;

/**
 * 二叉树
 *
 * @author wangyongchun
 * @date 2019/07/03  20:01
 */
public class BinaryTree {

    /**
     * 二叉树的根节点
     */
    private Node root;

    public BinaryTree(){
        this.root = null;
    }


    /**
     * 查找值等于给定值的节点
     * @param value
     * @return
     */
    public Node find(int value){
        Node temp = root;
        while (temp != null){
            if (value > temp.data){
                temp = temp.right;
            }else if (value < temp.data){
                temp = temp.left;
            }else {
                return temp;
            }
        }

        return null;
    }

    /**
     * 将一个节点添加到二叉树
     * @param value
     */
    public void insert(int value){
        if (root == null){
            Node node = new Node(value);
            root = node;
            return;
        }

        Node tem = root;
        Node newNode = new Node(value);
        while (tem != null){
            if (value > tem.data){
                if (tem.right == null){
                    tem.right = newNode;
                    return;
                }
                tem = tem.right;
            }else { // value < tem.data
                if (tem.left == null){
                    tem.left = newNode;
                    return;
                }
                tem = tem.left;
            }
        }
    }

    /**
     * 二叉搜索树的删除
     * @param value
     */
    public void delete(int value){

        Node p = root;
        Node parent = root; //要删除的节点的父节点

        //找到要删除的节点的自身和它的父节点
        while (p != null && p.data != value){
            parent = p;
            if (value > p.data){
                p = p.right;
            }else {
                p = p.left;
            }
        }

        if (p == null){
            return;
        }

        // 要删除的节点有左右两个子节点,要找到此节点右子树中最小的节点
        if (p.left != null && p.right != null){
            Node minP = p.right;
            Node minPP = p;

            while (minP.left != null){
                minPP = minP;
                minP = minP.left;
            }

            p.data = minP.data; //将右子树最小节点的值替换要删除的值
            p = minP; //删除右子树中最小节点
            parent = minPP;
        }

        Node child;
        //要删除的节点只有一个子节点或者删除叶子节点的时候，将需要被删除的父节点指向此节点的指针指向
        //需要被删除的节点的子节点
        if (p.left != null){ //待删除的节点只有一个左子节点
            child = p.left;
        }else if (p.right != null){ //待删除的节点只有一个右子节点
            child = p.right;
        }else {  //待删除的节点没有子节点
            child = null;
        }

        //更新树,将删除节点的子节点加到被删除节点的父节点上
        if (parent == null){ //删除的是根节点
            root = child;
        }else if (parent.left == p){
            parent.left = child;
        }else if (parent.right == p){
            parent.right = child;
        }
    }

    /**
     * 找到二叉树中的最大值节点
     * @return
     */
    public Node findMax(){
        if (root == null){
            return null;
        }
        Node p = root;
        while (p.right != null){
            p = p.right;
        }
        return p;
    }

    /**
     * 找到二叉树中的最小值节点
     * @return
     */
    public Node findMin(){
        if (root == null){
            return null;
        }

        Node p = root;
        while (p.left != null){
            p = p.left;
        }

        return p;
    }

    /**
     * 前序遍历
     * @param node
     */
    public void preOrder(Node node){
        if (node == null){
            return;
        }

        System.out.println(node.data + ",");
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 中序遍历
     * @param node
     */
    public void inOrder(Node node){
        if (node == null){
            return;
        }

        inOrder(node.left);
        System.out.println(node.data + ",");
        inOrder(node.right);
    }

    /**
     * 后序遍历
     * @param node
     */
    public void postOrder(Node node){
        if (node == null){
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.data + ",");
    }
    public static class Node{

        private int data;

        private Node left;

        private Node right;

        private Node parent;

        public Node(int value){
            this.data = value;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }


    public static void main(String[] args){

        BinaryTree binaryTree = new BinaryTree();
        binaryTree.insert(3);
        binaryTree.insert(2);
        binaryTree.insert(323);
        binaryTree.insert(23);
        binaryTree.insert(45);
        binaryTree.insert(909);

        binaryTree.delete(23);

//        binaryTree.inOrder(binaryTree.root);

        System.out.println("==========前序遍历=================");
        binaryTree.preOrder(binaryTree.root);
        System.out.println("===========中序遍历================");
        binaryTree.inOrder(binaryTree.root);
//        System.out.println("============后序遍历===============");
//        binaryTree.postOrder(binaryTree.root);

        System.out.println("===========二叉树的最小值================");
        Node min = binaryTree.findMin();
        System.out.println(min.data);

        System.out.println("============二叉树的最大值=================");
        Node max = binaryTree.findMax();
        System.out.println(max.data);
    }
}
