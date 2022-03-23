package com.algorithm.www.queue;

public class QueueBasedOnLinkedList {

    private Node head;

    private Node tail;

    /**
     * 入列
     * @param value
     * @return
     */
    public boolean enqueue(int value){

        Node newNode = new Node(value, null);
        if (tail == null){
            head = newNode;
            tail = newNode;
        }else {
            tail.setNext(newNode);
            tail = tail.getNext();
        }

        return true;
    }

    /**
     * 出列,-1表示队列为空
     * @return
     */
    public int dequeue(){
        if (head == null){
            return -1;
        }
        int tem = head.getData();
        head = head.getNext();
        if (head == null){
            tail = null;
        }
        return tem;
    }

    public static class Node{
        private int data;
        private Node next;

        public Node(int data, Node next){
            this.data = data;
            this.next = next;
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
}
