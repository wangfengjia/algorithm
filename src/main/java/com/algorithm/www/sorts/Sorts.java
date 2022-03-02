package com.algorithm.www.sorts;

import java.util.Arrays;

/**
 * 基础排序
 *
 * @author wangyongchun
 * @date 2019/06/25 14:48
 */
public class Sorts {

    public static void bubbleSort(int[] arr, int n){

        for (int i = 0; i < n; i++){
            //提前退出冒泡排序的标志位，当没有数据交换的时候表示已经达到完全有序
            boolean flag = false;
            for (int j = 1; j <= n - i -1; j++){
                if (arr[j] < arr[j - 1]){
                    int tem = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tem;
                    flag = true;
                }
            }

            if (!flag){
                break;
            }
        }
    }

    /**
     * 冒泡排序改进版，改进思路
     *      1.记录每一轮比较最后一次交换数据的位置，作为下一轮比较的边界,对于边界外的元素下次不需要比较
     * @param arr
     * @param n
     */
    public static void bubbleSortV2(int[] arr, int n){
        if (n <= 1){
            return;
        }
        //最后一次交换的位置
        int lastChange = 0;
        //无序数据的边界，遍历到这里就结束
        int sortBorder = n - 1;

        for (int i = 0; i < n; i++){
            //提前退出标志位
            boolean flag = false;
            for (int j = 0; j < sortBorder; j++){
                if (arr[j] > arr[j + 1]){
                    int tem = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = tem;

                    //更新最后一次交换数据的位置
                    lastChange = j;
                    flag = true;
                }
            }

            if (!flag){
                break;
            }
            sortBorder = lastChange;
        }
    }

    /**
     * 插入排序
     * @param arr
     * @param n
     */
    public static void insertionSort(int[] arr, int n){

        if (n <= 1){
            return;
        }

        for (int i = 1; i < n; i++){
            int value = arr[i];
            int j = i - 1;
            for (; j >= 0; --j){
                if (arr[j] > value){
                    arr[j + 1] = arr[j];
                }else {
                    break;
                }
            }

            //在第二层循环判断的时候是j先减去1再判断的，也就可能出现-1
            arr[j + 1] = value;
        }
    }

    /**
     * 选择排序
     * @param arr
     * @param n
     */
    public static void selectionSort(int[] arr, int n){

        if (n <= 1){
            return;
        }

        for (int i = 0; i < n; i++){
            int minIndex = i;
            for (int j = i + 1; j < n; j++){
                if (arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }

            int tem = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = tem;
        }
    }

    public static void main(String[] args){

        int arr[] = {22, 3, 5,23, 6, 2111, 78, 111, 78};
//        bubbleSortV2(arr, arr.length);
//        insertionSort(arr, arr.length);
        selectionSort(arr, arr.length);
        System.out.println(Arrays.toString(arr));
    }
}
