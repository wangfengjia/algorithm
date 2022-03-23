package com.algorithm.www.trees;


/**
 * 数的前序，中序，后序遍历
 */
public class TreeTraversal {

    public static class Node{

        private int data;
        private Node left;
        private Node right;

        /**
         * 前序遍历
         * @param root
         */
        public void preOrder(Node root){
            if (root == null){
                return;
            }

            System.out.println(data + " ");
            preOrder(root.left);
            preOrder(root.right);
        }

        /**
         * 中序遍历
         * @param root
         */
        public void inOrder(Node root){

            if (root == null){
                return;
            }

            inOrder(root.left);
            System.out.println(data + "");
            inOrder(root.right);
        }

        /**
         * 后序遍历
         * @param root
         */
        public void postOrder(Node root){
            if (root == null){
                return;
            }

            postOrder(root.left);
            postOrder(root.right);
            System.out.println(data + " ");
        }
    }
}
