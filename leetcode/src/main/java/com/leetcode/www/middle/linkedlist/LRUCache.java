package com.leetcode.www.middle.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode-146: LRU 缓存
 * 请你设计并实现一个满足LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
     * LRUCache(int capacity) 以 正整数 作为容量capacity 初始化 LRU 缓存
     * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
     * void put(int key, int value)如果关键字key 已经存在，则变更其数据值value ；如果不存在，则向缓存中插入该组key-value 。如果插入操作导致关键字数量超过capacity ，则应该 逐出 最久未使用的关键字。
     * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 *
 * 复杂度分析
 *  时间复杂度:O(1),哈希表的操作时间复杂度是O(1)
 *  空间复杂度:O(capacity),哈希表和双向链表最多存储capacity + 1个元素
 *
 */
public class LRUCache {

    private Map<Integer, DLinkedNode> cache = new HashMap<>();
    private int size;
    private int capacity;
    //使用虚头尾节点
    private DLinkedNode head, tail;


    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key){
        DLinkedNode node = cache.get(key);
        if (node == null){
            return -1;
        }

        // 将这个节点移动到链表头部
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value){

        DLinkedNode node = cache.get(key);
        if (node == null){
            //key不存在，就创建一个新的节点
            DLinkedNode newNode = new DLinkedNode(key, value);
            cache.put(key, newNode);
            //添加到双向链表头部
            addHead(newNode);
            ++size;
            if (size > capacity){
                //超出容量则删除双向链表尾结点
                DLinkedNode dLinkedNode = removeTail();
                // 将这个节点从哈希表中删除
                cache.remove(key);
                --size;
            }
        }else {
            // key存在时，修改值，并移动到双向链表头部
            node.value = value;
            moveToHead(node);
        }
    }

    private void addHead(DLinkedNode node){
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(DLinkedNode node){
        removeNode(node);
        addHead(node);
    }

    private DLinkedNode removeTail(){
        DLinkedNode node = tail.prev;
        removeNode(node);
        return node;
    }



    public static class DLinkedNode{

        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        DLinkedNode(){}
        DLinkedNode(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
}
