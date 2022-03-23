package com.algorithm.www.linkedlist;

public class LinkedListAlgo {

    private Node head;

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    /**
     * 检测环
     *
     * @param header
     * @return
     */
    public static boolean checkCircle(Node header) {
        if (header == null) {
            return false;
        }

        Node fast = header.getNext();
        Node slow = header;

        while (fast != null && fast.getNext() != null) {
            fast = fast.getNext().getNext();
            slow = slow.getNext();

            if (fast == slow) {
                return true;
            }
        }

        return false;
    }

    /**
     * 检测环第二版
     *
     * @param header
     * @return
     */
    public static boolean checkCircleV2(Node header) {
        if (header == null) {
            return false;
        }

        Node fast = header;
        Node slow = header;

        while (fast != null && fast.getNext() != null) {
            fast = fast.getNext().getNext();
            slow = slow.getNext();

            if (fast == slow) {
                return true;
            }
        }

        return false;
    }

    /**
     * 合并两个有序链表
     *
     * @param left
     * @param right
     * @return
     */
    public static Node mergeSortedLinkedList(Node left, Node right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        Node l = left;
        Node r = right;
        Node header;
        if (l.data < r.data) {
            header = l;
            l = l.getNext();
        } else {
            header = r;
            r = r.getNext();
        }

        Node tem = header;
        while (l != null && r != null) {
            if (l.getData() < r.getData()) {
                tem.setNext(l);
                l = l.getNext();
            } else {
                tem.setNext(r);
                r = r.getNext();
            }

            tem = tem.getNext();
        }

        if (l != null) {
            tem.setNext(l);
        } else {
            tem.setNext(r);
        }

        return header;
    }


    public void insertTail(int value) {

        Node newNode = new Node(value, null);

        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(newNode);
        }
    }


    public void createCircleLinkedList(int value) {
        Node newNode = new Node(value, null);

        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.getNext() != null && temp.getNext() != head) {
                temp = temp.getNext();
            }

            newNode.setNext(head);
            temp.setNext(newNode);
        }
    }

    /**
     * 寻找单链表的中间节点
     *
     * @param header
     * @return
     */
    public static Node findMiddleNode(Node header) {
        if (header == null) {
            return null;
        }

        Node fast = header;
        Node slow = header;

        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            fast = fast.getNext().getNext();
            slow = slow.getNext();
        }

        return slow;
    }

    public static void printAll(Node head) {

        Node tem = head;
        while (tem != null) {
            System.out.println(tem.getData() + ",");
            tem = tem.getNext();
        }
    }

    public static Node createNode(int value) {
        return new Node(value, null);
    }

    public static class Node {

        private int data;
        private Node next;

        public Node(int data, Node next) {
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


    public static void main(String[] args) {

        LinkedListAlgo linkedListAlgo1 = new LinkedListAlgo();
        LinkedListAlgo linkedListAlgo = new LinkedListAlgo();
        LinkedListAlgo linkedListAlgo2 = new LinkedListAlgo();

        System.out.println("hello world");

        int data[] = {1, 3, 5, 7, 9, 11};
        int data1[] = {2, 4, 6, 8, 10, 12};

        for (int i = 0; i < data.length; i++) {
            linkedListAlgo1.insertTail(data[i]);
            linkedListAlgo2.createCircleLinkedList(data[i]);
        }

        for (int i = 0; i < data1.length; i++) {
            linkedListAlgo.insertTail(data1[i]);
        }

//        Node mergeSortedLinkedList = mergeSortedLinkedList(linkedListAlgo1.getHead(), linkedListAlgo.getHead());
//        printAll(mergeSortedLinkedList);
        boolean checkCircle = checkCircleV2(linkedListAlgo.getHead());
        System.out.println("环检测的结果是_" + checkCircle);
//        printAll(linkedListAlgo2.getHead());
    }
}
