package com.algorithm.www.heap;


/**
 * 堆
 * @author wangyongchun
 * @date 2019/07/06 20:41
 */
public class Heap {

    /**
     * 存储数据的数组
     */
    private int[] arr;

    /**
     * 堆可以存储的最大数据个数
     */
    private int capacity;

    /**
     * 堆中已经存储的数据个数
     */
    private int count;


    public Heap(int capacity){
        this.arr = new int[capacity + 1];
        this.capacity = capacity;
        count = 0;
    }

    /**
     * 堆的插入
     * @param value
     * @return
     */
    public boolean insert(int value){
        if (count >= capacity){
            return false;
        }
        ++count;
        arr[count] = value;
        //堆化(最大堆为例):假如插入的节点比父节点的值大，就将值进行交换，一直重复这个过程，直到父子节点之间满足对应的大小关系
        int i = count;
        while (i / 2 > 0 && arr[i] > arr[i / 2]){
            swap(arr, i, i / 2);
            i = i / 2;
        }

        return true;
    }

    /**
     * 删除最大堆的根节点
     * 思路
     * 1.将最后一个节点放到堆顶，然后从上往下堆化
     * @return
     */
    public boolean removeMax(){

        if (count == 0){
            return false;
        }

        arr[1] = arr[count];
        --count;
        heapify(arr, count, 1);
        return true;
    }

    private void heapify(int[] arr, int n, int i){
        while (true){
            int maxPos = i;
            if (i * 2 <= n && arr[i * 2] > arr[maxPos]){
                maxPos = i * 2;
            }
            if (i * 2 + 1 <= n && arr[i * 2 + 1] > arr[maxPos]){
                maxPos = 2 * i + 1;
            }
            if (maxPos == i){
                break;
            }

            swap(arr, maxPos, i);
            i = maxPos;
        }
    }

    private void swap(int[] arr, int source, int des){
        int tem = arr[des];
        arr[des] = arr[source];
        arr[source] = tem;
    }

    /**
     * 建堆
     * 思路
     * 1.从后往前处理数组，然后从上向下堆化
     * 2.数组的(n/2 + 1) ~ n 是叶子节点， 从1 ~ n/2是非叶子节点
     * @param a 数组
     * @param n 数组的最大下标，也就是数组的长度减去1
     */
    public static void buildHeap(int[] a, int n){

        for (int i = n / 2; i >= 1; --i){
            buildHeapify(a, n, i);
        }
    }

    private static void buildHeapify(int[] arr, int n, int i){

        while (true){
            int maxPos = i;
            if (i * 2 <= n && arr[i * 2] > arr[maxPos]){
                maxPos = i * 2;
            }
            if (i * 2 + 1 <= n && arr[i * 2 + 1] > arr[maxPos]){
                maxPos = i * 2 + 1;
            }
            if (maxPos == i){
                break;
            }

            buildHeapSwap(arr, maxPos, i);
            i = maxPos;
        }
    }

    private static void buildHeapSwap(int[] a, int source, int des){
        int temp = a[des];
        a[des] = a[source];
        a[source] = temp;
    }


    public int getMax(){
        if (count == 0){
            return 'N';
        }

        return arr[1];
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args){

        int[] arr = {0, 1,33,22, 123, 331, 23};
        buildHeap(arr, arr.length - 1);
        System.out.println(arr[1]);
    }
}
