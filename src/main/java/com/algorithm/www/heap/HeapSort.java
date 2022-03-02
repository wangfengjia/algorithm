package com.algorithm.www.heap;

/**
 * 堆排序
 * 思路
 * 1.先建堆
 * 2.堆顶和最后一个节点进行交换位置
 * 3,从上往下堆化，找到堆中值最大的元素
 * 4.重复步骤2，3
 */
public class HeapSort {


    /**
     * 堆排序
     * @param arr
     * @param n
     */
    public static void heapSort(int[] arr, int n){

        //先建堆
        buldHeap(arr, n);
        int k = n;
        while (k > 1){
            swap(arr, 1, k);
            --k; //堆的大小减一
            heapfy(arr, k, 1);
        }
    }

    /**
     * 建堆
     * @param arr
     * @param n 表示数据的个数，数组arr中的数据下标是从1 ~ n;n也是数组的最大下标
     */
    public static void buldHeap(int[] arr, int n){
        for (int i = n / 2; i >= 1; --i){
            heapfy(arr, n, i);
        }
    }

    /**
     * 堆化
     * @param arr
     * @param n
     * @param i
     */
    private static void heapfy(int[] arr, int n, int i){

        while (true){
            int maxPos = i;
            if (i * 2 <= n && arr[i * 2] > arr[maxPos]){
                maxPos = 2 * i;
            }
            if (i * 2 + 1 <= n && arr[i * 2 + 1] > arr[maxPos]){
                maxPos = i * 2 + 1;
            }
            if (maxPos == i){
                break;
            }

            swap(arr, maxPos, i);
            i = maxPos;
        }
    }

    private static void swap(int[] arr, int source, int des){

        int tem = arr[des];
        arr[des] = arr[source];
        arr[source] = tem;
    }


    public static void main(String[] args){

        int[] arr = {0, 22, 2121, 34, -3, 342, 34, 22, 3232323, 433};
        heapSort(arr, arr.length - 1);
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < arr.length; i++){
            builder.append(arr[i]);
            builder.append(",");
        }
        System.out.println(builder.toString());
    }
}
