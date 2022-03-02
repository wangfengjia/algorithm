package com.algorithm.www.queue;

/**
 * 基于数组实现队列
 */
public class ArrayQueue {

    /**
     * 数组
     */
    private String[] items;

    /**
     * 队列的大小
     */
    private int capacity;

    /**
     * 队列的头部
     */
    private int head;

    /**
     * 队列的尾部
     */
    private int tail;

    public ArrayQueue(int capacity){
        items = new String[capacity];
        this.capacity = capacity;
    }

    /**
     * 入列
     * @param item
     */
    public boolean enqueue(String item){
        if (tail == capacity){
            return false;
        }
        items[tail] = item;
        ++tail;
        return true;
    }

    /**
     * 出列
     * @return
     */
    public String dequeue(){
        //头尾指针相等时表示队列为空
        if (head == tail){
            return null;
        }
        String temp = items[head];
        ++head;
        return temp;
    }
}
