package com.algorithm.www.linkedlist;

import java.util.HashMap;

/**
 * 基于哈希表和链表实现LRU
 *
 * @author wangyongchun
 * @date 2019/09/17 10:49
 */
public class LRUBaseLinkedListAndHashMap {

    private static final Integer DEFAULT_CAPACITY = 10;

    private HashMap<String, Node> cache;

    private Integer capacity;

    private Integer length;

    private Node head;

    private Node tail;

    public LRUBaseLinkedListAndHashMap(Integer capacity){
        this.capacity = capacity;
        this.length = 0;
        this.head = null;
        this.cache = new HashMap<String, Node>();
    }

    public boolean set(String key, String value){
        Node node = cache.get(key);
        if (node == null){
            Node newNode = new Node(key, value);
            if (length >= capacity){
                cache.remove(tail.key);
                tail.pre.next = null;
                tail = tail.pre;
                --length;
            }

            addToHead(newNode);
            cache.put(key, newNode);
        }else {
            node.value = value;
            moveToHead(node);
        }

        return true;
    }

    public String get(String key){

        Node node = cache.get(key);
        if (node == null){
            return null;
        }

        moveToHead(node);
        return node.value;
    }

    private void moveToHead(Node node){

        if (node == head){
            return;
        }

        if (node == tail){
            tail.pre.next = null;
            tail = tail.pre;
        }else {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }

        node.pre = null;
        node.next = head;
        head.pre = node;

        head = node;
    }

    private void addToHead(Node node){
        if (head == null){
            node.next = null;
            node.pre = null;
            head = node;
            tail = head;
        }else {
            node.next = head;
            head.pre = node;

            node.pre = null;
            head = node;
        }

        ++length;
    }

    private class Node{

        public String key;
        public String value;
        public Node pre;
        public Node next;

        public Node(String key, String value){
            this.key = key;
            this.value = value;
            this.pre = null;
            this.next = null;
        }
    }

    public static void main(String[] args){

        int capacity = 5;
        LRUBaseLinkedListAndHashMap lruBaseLinkedListAndHashMap = new LRUBaseLinkedListAndHashMap(capacity);
        lruBaseLinkedListAndHashMap.set("a", "b");
        lruBaseLinkedListAndHashMap.set("c", "d");
        lruBaseLinkedListAndHashMap.set("e", "f");
        lruBaseLinkedListAndHashMap.set("h", "i");
        lruBaseLinkedListAndHashMap.set("j", "k");
        lruBaseLinkedListAndHashMap.set("l", "m");

        String aMapValue = lruBaseLinkedListAndHashMap.get("a");
        System.out.println(lruBaseLinkedListAndHashMap.length);
    }
}
