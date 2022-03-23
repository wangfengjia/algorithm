package com.algorithm.www.linkedlist;

public class SinglyLinkedList {

    private Node head = null;


    /**
     * 无头结点的链表反转
     * @param p
     * @return
     */
    public Node inreverseLinkedList(Node p){
        Node pre = null;
        Node r = head;
        System.out.println("z------" + r.getData());

        Node next = null;
        while (r != p){
            next = r.getNext();
            r.setNext(pre);
            pre = r;
            r = next;
        }

        r.setNext(pre);
        return r;
    }

    /**
     * 无头结点的链表反转
     *
     */
    public void reverseLinkedLink(){
        Node pre = null;
        Node tem = head;
        Node next = null;

        while (tem != null){
            next = tem.getNext();
            tem.setNext(pre);
            pre = tem;
            tem = next;
        }

        head = pre;
    }

    /**
     * 两个链表节点进行比较
     * @param left
     * @param right
     * @return
     */
    public boolean TFResult(Node left, Node right){
        Node l = left;
        Node r = right;

        System.out.println("left_:" + l.getData());
        System.out.println("right_:" + r.getData());
        while (l != null && r != null){
            if (l.getData() == r.getData()){
                l = l.getNext();
                r = r.getNext();
            }else {
                return false;
            }
        }

        return true;
    }

    //判断是否是回文
    public boolean palindrome(){
        if (head == null){
            return false;
        }

        System.out.println("开始寻找中间节点");
        //寻找中间节点
        Node p = head; //慢指针，一次走一个节点
        Node q = head; //快指针，一次走两个节点

        if (p.getNext() == null){
            System.out.println("表示链表只有一个节点");
            return true;
        }

        while (q.getNext() != null && q.getNext().getNext() != null){
            p = p.getNext();
            q = q.getNext().getNext();
        }

        System.out.println("中间节点为_" + p.getData());
        System.out.println("开始回文判断");
        //回文判断就是中间节点的后半部分反转以后会等于中间节点的前半部分
        Node leftLink = null;
        Node rightLink = null;
        //表示奇数个节点
        if (q.getNext() == null){
            //p是中间节点
            rightLink = p.getNext();
            leftLink = inreverseLinkedList(p).getNext();
            System.out.println("左边链表的第一个节点是_" + leftLink.getData());
            System.out.println("右边链表的第一个节点是_" + rightLink.getData());
        }else {
            //p,q都是中间节点
            rightLink = p.getNext();
            leftLink = inreverseLinkedList(p);
        }

        return TFResult(leftLink, rightLink);
    }

    /**
     * 添加节点到链表尾部
     * @param value
     */
    public void insertTail(int value){
        Node newNode = new Node(value, null);

        if (head == null){
            head = newNode;
        }else {
            Node temp = head;
            while (temp.getNext() != null){
                temp = temp.getNext();
            }

            temp.setNext(newNode);
        }
    }


    /**
     * 打印链表元素
     */
    public void printAll(){
        Node temp = head;
        while (temp != null){
            System.out.println(temp.getData() + ",");
            temp = temp.getNext();
        }
    }

    public static class Node{
        private int data;
        private Node next;

        public Node(int data, Node node){
            this.data = data;
            this.next = node;
        }

        public int getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }

        public void setData(int data) {
            this.data = data;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    public static Node createNode(int data){
        return new Node(data, null);
    }


    public static void main(String[] args){

        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        System.out.println("hello");

        int data[] = {1, 2, 3,3,33,333,3333, 2, 1};
        for (int i = 0; i < data.length; i++){
            singlyLinkedList.insertTail(data[i]);
        }

//        System.out.println("打印原始:");
//        singlyLinkedList.printAll();
//        if (singlyLinkedList.palindrome()){
//            System.out.println("是回文");
//        }else {
//            System.out.println("不是回文");
//        }


        singlyLinkedList.reverseLinkedLink();;
        System.out.println("链表反转以后:");
        singlyLinkedList.printAll();
    }
}
