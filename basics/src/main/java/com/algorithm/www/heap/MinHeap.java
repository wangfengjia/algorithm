package com.algorithm.www.heap;


/**
 * 最小堆
 *
 * @author wangyongchun
 * @date 2019/07/07 20:29
 */
public class MinHeap {

    private int[] arr;

    private int capacity;

    private int count;

    public MinHeap(int capacity){
        this.arr = new int[capacity + 1];
        this.capacity = capacity;
        this.count = 0;
    }


    public boolean insert(int value){

        if (count >= capacity){
            return false;
        }

        ++count;
        arr[count] = value;
        int i = count;
        while (i / 2 > 0 && arr[i] < arr[i / 2]){
            swap(arr, i, i / 2);
            i = i / 2;
        }

        return true;
    }

    public boolean removeMin(){

        if (count == 0){
            return false;
        }

        arr[1] = arr[count];
        --count;
        heapify(arr, count, 1);
        return true;
    }

    private void heapify(int[] a, int n, int i){

        while (true){
            int minPos = i;
            if (2 * i <= n && a[2 * i] < a[minPos]){
                minPos = i * 2;
            }
            if (2 * i + 1 <= n && a[2 * i + 1] < a[minPos]){
                minPos = i * 2 + 1;
            }
            if (minPos == i){
                break;
            }
            swap(a, minPos, i);
            i = minPos;
        }
    }

    public int getMin(){

        if (count == 0){
            return -1;
        }

        return arr[1];
    }

    public int getCount() {
        return count;
    }

    private void swap(int[] a, int source, int des){

        int temp = a[des];
        a[des] = a[source];
        a[source] = temp;

    }

    public int[] getArr() {
        return arr;
    }
}
