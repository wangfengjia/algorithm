package com.algorithm.www.trees;

/**
 * 红黑树
 *
 * @author wangyongchun
 * @date 2019/07/05  10:58
 */
public class RedBlackBST {

    private final int RED = 0;
    private final int BLACK = 1;

    //根节点
    private Node root;

    public void insert(Node node){
        Node tem = root;
        if (root == null){
            root = node;
            root.color = BLACK;
            root.parent = null;
        }else {
            node.color = RED;
            while (true){
                if (node.data < tem.data){
                    if (tem.left == null){
                        node.parent = tem;
                        tem.left = node;
                        break;
                    }else {
                        tem = tem.left;
                    }
                }else if (node.data >= tem.data){
                    if (tem.right == null){
                        node.parent = tem;
                        tem.left = node;
                        break;
                    }else {
                        tem = tem.right;
                    }
                }
            }
            insertFixTree(node);
        }
    }

    /**
     * 插入调整
     * @param node
     */
    private void fixTree(Node node){
        while (node.parent.color == RED){
            //先得到插入节点的叔叔节点
            Node uncle;
            //父节点是祖父节点的左子节点
            if (node.parent == node.parent.parent.left){ //树的左边进行调整
                uncle = node.parent.parent.right;

                //运用第一种情况
                //插入节点的叔叔节点是红色，调整办法是
                //1.将父节点和叔叔节点设置成黑色
                //2.将祖父节点设置为红色
                //3.将关注节点设置为祖父节点
                //4.跳到第二种或者第三种情况
                if (uncle != null && uncle.color == RED){
                    node.parent.color = BLACK;
                    node.parent.parent.right.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                }

                //运用第二种情况的调整策略
                //叔叔节点是黑色，并且插入节点是父节点的右子节点，调整办法是
                //1.关注节点设置为插入节点的父节点
                //2.围绕插入节点的父节点左旋
                //3.跳到第三种情况
                if (node == node.parent.right){
                    node = node.parent;
                    rotateLeft(node);
                }

                //第三种情况
                //插入节点的叔叔节点是黑色，并且插入节点是父节点的左子节点是，调整方法是
                //1.围绕关注节点的祖父节点右旋
                //2.将关注节点的父节点和兄弟节点的颜色互换
                //3.调整结束
                node.parent.color = BLACK;
                node.parent.parent.color = RED;
                rotateRight(node.parent.parent);
            }else { //树的右边进行调整
                uncle = node.parent.parent.left;

                if (uncle != null && uncle.color == RED){
                    node.parent.color = BLACK;
                    node.parent.parent.left.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                }

                if (node == node.parent.left){
                    node = node.parent;
                    rotateRight(node);
                }

                node.parent.color = BLACK;
                node.parent.parent.color = RED;
                rotateLeft(node.parent.parent);
            }
        }

        //根节点是黑色
        root.color = BLACK;
    }


    /**
     * 插入的时候的调整
     * 插入的节点为红色，其父节点也为红色时，就有如下三种情况
     * 1.插入节点的叔叔节点也为红色，调整方法是:将祖父节点颜色和叔叔，父亲节点互换，也就是父亲节点和叔叔节点设
     *  置为黑色，祖父节点为红色
     * 2.插入的节点，插入节点的父节点，插入节点的祖父节点在一条斜线上，调整的方法
     *  a.在树的左边的时候，插入节点的父亲节点和祖父节点互换颜色，然后围绕祖父节点右旋
     *  b.在树的右边的时候，插入节点的父节点和祖父节点互换颜色，绕后围绕祖父节点左旋
     * 3.插入的节点，插入节点的父节点，插入节点的祖父节点不在一条斜线上，调整方法
     *  a.在树的左边的时候(插入节点是父节点的右子节点)，围绕父节点左旋，这样就转换成了第二种情况，运用第二种情况的处理办法处理
     *  b.在树的右边的时候(插入的节点是父节点的左子节点)，围绕父节点右旋，这样转换成了第二种情况
     * @param node
     */
    private void insertFixTree(Node node){
        while (node.parent.color == RED){
            Node uncle; //用来确实需要调整的节点是在树的左边还是右边
            if (node.parent == node.parent.parent.left){
                uncle = node.parent.parent.right;

                if (uncle != null && uncle.color == RED){
                    node.parent.color = BLACK;
                    node.parent.parent.right.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                }

                if (node == node.parent.right){
                    node = node.parent;
                    rotateLeft(node);
                }

                node.parent.color = BLACK;
                node.parent.parent.color = RED;
                rotateRight(node.parent.parent);
            }else {
                uncle = node.parent.parent.left;

                if (uncle != null && uncle.color == RED){
                    node.parent.color = BLACK;
                    node.parent.parent.left.color = BLACK;
                    node.parent.parent.color = RED;
                    continue;
                }

                if (node == node.parent.left){
                    node = node.parent;
                    rotateRight(node);
                }

                node.parent.color = BLACK;
                node.parent.parent.color = RED;
                rotateLeft(node.parent);
            }
        }
        root.color = BLACK;
    }

    /**
     * 新增时左旋
     * @param node
     */
    private void rotateLeft(Node node){

        if (node.parent != null){
            if (node.parent.left == node){
                node.parent.left = node.right;
            }else if (node.parent.right == node){
                node.parent.right = node.right;
            }

            node.right.parent = node.parent;
            node.parent = node.right;
            if (node.right.left != null){
                node.right.left.parent = node;
            }
            node.right = node.right.left;
            node.parent.left = node;
        }else {
            Node right = root.right;
            root.right = right.left;
            right.left.parent = root;
            root.parent = right;
            root.parent.left = root;
            right.parent = null;
            root = right;
        }
    }


    /**
     * 新增时右旋
     * @param node
     */
    private void rotateRight(Node node){

        if (node.parent != null){
            if (node.parent.left == node){
                node.parent.left = node.left;
            }else if (node.parent.right == node){
                node.parent.right = node.left;
            }

            node.left.parent = node.parent;
            node.parent = node.left;
            if (node.left.right != null){
                node.left.right.parent = node;
            }
            node.left = node.left.right;
            node.parent.right = node;
        }else {
            Node left = root.left;
            root.left = left.right;
            left.right.parent = root;
            root.parent = left;
            left.right = root;
            left.parent = null;
            root = left;
        }
    }

    public class Node{

        private int data;
        private Node left;
        private Node right;
        private Node parent;
        private int color;

        public Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
            this.color = RED;
            this.parent = null;
        }
    }
}
