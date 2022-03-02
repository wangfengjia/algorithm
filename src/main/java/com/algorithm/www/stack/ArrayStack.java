package com.algorithm.www.stack;

public class ArrayStack {

    /**
     * 存放元素的数组
     */
    private String[] items;
    /**
     * 栈中元素个数
     */
    private int count;

    /**
     * 栈的容量
     */
    private int capacity;

    public ArrayStack(int capacity){
        this.capacity = capacity;
        items = new String[capacity];
        count = 0;
    }

    /**
     * 入栈操作
     * @param value
     * @return
     */
    public boolean push(String value){
        if (count == capacity){
            return false;
        }

        items[count] = value;
        ++count;
        return true;
    }

    /**
     * 出栈操作
     * @return
     */
    public String pop(){
        if (count == 0){
            return null;
        }
        String tem = items[count - 1];
        --count;
        return tem;
    }

}
