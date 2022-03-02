package com.algorithm.www.linkedlist;

import java.util.Scanner;

/**
 * 基于单链表的LRU算法
 * 越早访问到的元素越靠近链表尾部
 * @author wangyongchun
 * @date 2019/06/23 15:33
 */
public class LRUBaseLinkedList<T> {

    /**
     * 单链表的容量
     */
    private static final Integer DEFAULT_CAPACITY = 10;

    /**
     * 单链表的头结点
     */
    private SNode<T> headNode;

    /**
     * 链表的长度
     */
    private Integer length;

    /**
     * 链表的容量
     */
    private Integer capacity;


    public LRUBaseLinkedList(){
        this.headNode = new SNode();
        this.capacity = DEFAULT_CAPACITY;
        this.length = 0;
    }

    public LRUBaseLinkedList(Integer capacity){
        this.headNode = new SNode();
        this.capacity = capacity;
        this.length = 0;
    }

    /**
     * 将元素添加到单链表
     * @param data
     */
    public void add(T data){
        SNode preNode = findPreNode(data);

        //假如要添加的数据已经在链表中，则将链表中原有的数据删除，将这个元素添加到链表的头部
        if (preNode != null){
            deleteElemOptim(preNode);
            insertElementAtBegin(data);
        }else {
            //假如链表长度大于链表容量，则将链表的尾节点删除
            if (length >= capacity){
                deleteElementAtEnd();
            }
            insertElementAtBegin(data);
        }
    }
    /**
     * 找到元素的前一个节点
     * @param data
     * @return
     */
    private SNode findPreNode(T data){
        SNode tem = headNode;
        while (tem.getNext() != null){
            if (data.equals(tem.getNext().getElememt())){
                return tem;
            }
            tem = tem.getNext();
        }


        return null;
    }

    /**
     * 删除preNode节点的下一个节点
     * @param preNode
     */
    private void deleteElemOptim(SNode preNode){
        SNode temp = preNode.getNext();
        preNode.setNext(temp.getNext());
        temp = null;
        length--;
    }

    /**
     * 在链表头部插入节点
     * @param data
     */
    private void insertElementAtBegin(T data){
        SNode tem = headNode.getNext();
        headNode.setNext(new SNode(data, tem));
        length++;
    }

    /**
     * 删除尾节点
     */
    private void deleteElementAtEnd(){
        SNode preNode = headNode;
        //空链表直接返回
        if (preNode.getNext() == null){
            return;
        }
        //找到倒数第二个节点
        while (preNode.getNext().getNext() != null){
            preNode = preNode.getNext();
        }
        //找到了最后一个节点
        SNode tem = preNode.getNext();
        preNode.setNext(null);
        tem = null;
        length--;
    }

    /**
     * 打印链表所有节点
     */
    private void printAll(){
        SNode tem = headNode.getNext();
        while (tem != null){
            System.out.println(tem.getElememt() + ",");
            tem = tem.getNext();
        }

        System.out.println("打印结束");
    }

    public static class SNode<T>{
        private T elememt;
        private SNode next;

        public SNode(T elememt){
            this.elememt = elememt;
        }

        public SNode(T elememt, SNode next){
            this.elememt = elememt;
            this.next = next;
        }

        public SNode(){
            this.next = null;
        }


        public T getElememt() {
            return elememt;
        }

        public void setElememt(T elememt) {
            this.elememt = elememt;
        }

        public SNode getNext() {
            return next;
        }

        public void setNext(SNode next) {
            this.next = next;
        }
    }


    public static void main(String[] args){
        LRUBaseLinkedList<Integer> objectLRUBaseLinkedList = new LRUBaseLinkedList<Integer>();
        Scanner scanner = new Scanner(System.in);
        while (true){
            objectLRUBaseLinkedList.add(scanner.nextInt());
            objectLRUBaseLinkedList.printAll();
        }
    }
}
