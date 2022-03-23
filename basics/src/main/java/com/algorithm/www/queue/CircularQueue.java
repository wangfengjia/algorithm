package com.algorithm.www.queue;

/**
 * 基于数组实现的环形队列
 *
 * @author wangyongchun
 * @date 2019/06/24 16:17
 */
public class CircularQueue {

    /**
     * 队列数组
     */
    private String[] items;

    /**
     * 队列容量
     */
    private int capacity;

    /**
     * 队列头
     */
    private int head;

    /**
     * 队列尾部
     */
    private int tail;

    public CircularQueue(int capacity){
        items = new String[capacity];
        this.capacity = capacity;
    }

    private boolean isFull(){
        return (tail + 1) % capacity == head;
    }
    /**
     * 入列
     * @param value
     * @return
     */
    public boolean enqueue(String value){
        if (isFull()){
            return false;
        }
        items[tail] = value;
        tail = (tail + 1) % capacity;

        return true;
    }

    /**
     * 出列
     * @return
     */
    public String dequeue(){
        if (head == tail){
            return null;
        }
        String tem = items[head];
        head = (head + 1) % capacity;
        return tem;
    }
}
