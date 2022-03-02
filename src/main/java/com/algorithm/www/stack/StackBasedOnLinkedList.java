package com.algorithm.www.stack;

/**
 * 基于链表实现的栈
 *
 * @author wangyongchun
 * @date 2019/06/24 15:03
 */
public class StackBasedOnLinkedList {

    private Node top;

    /**
     * 入栈
     * @param value
     */
    public void push(int value){
        Node newNode = new Node(value, null);
        if (top == null){
            top = newNode;
        }else {
            newNode.setNext(top);
            top = newNode;
        }
    }

    public void printAll(){
        Node tem = top;
        while (tem != null){
            System.out.println(tem.getData() + ",");
            tem = tem.getNext();
        }
    }

    /**
     * 出栈，-1表示栈为空
     * @return
     */
    public int pop(){
        if (top == null){
            return -1;
        }

        int value = top.getData();
        top = top.getNext();
        return value;
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

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    public static void main(String[] args){
        StackBasedOnLinkedList stackBasedOnLinkedList = new StackBasedOnLinkedList();
        int data[] = {1, 3, 5, 4, 33};

        for (int i = 0; i < data.length; i++){
            stackBasedOnLinkedList.push(data[i]);
        }
        stackBasedOnLinkedList.pop();
        stackBasedOnLinkedList.pop();
        stackBasedOnLinkedList.printAll();
    }
}
