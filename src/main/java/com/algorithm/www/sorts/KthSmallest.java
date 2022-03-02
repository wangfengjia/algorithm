package com.algorithm.www.sorts;

/**
 * 求数组中第k大的元素
 */
public class KthSmallest {

    public static int kthSmallest(int[] arr, int k){
        if (arr == null || arr.length < k){
            return -1;
        }

        int length = arr.length;
        int pos = partition(arr, 0, length - 1);
        while (pos + 1 != k){
            if (pos + 1 < k){
                pos = partition(arr, pos + 1, length - 1);
            }else {
                pos = partition(arr, 0, pos - 1);
            }
        }

        return arr[pos];

    }

    private static int partition(int[] arr, int p, int q){
         int i = p;
         int pivot = arr[q];

         for (int j = p; j < q; j++){
             //这里使用<=，要不然会出现死循环的情况，例如数组{1, 1, 2}
            if (arr[j] <= pivot){
                swap(arr, i, j);
                ++i;
            }
         }

         swap(arr, i, q);
         return i;
    }

    private static void swap(int[] arr, int i, int j){
        if (i == j){
            return;
        }

        int tem = arr[i];
        arr[i] = arr[j];
        arr[j] = tem;
    }


    public static void main(String[] args){

        int[] arr = {1, 1, 2};
        int i = 2;
        int smallest = kthSmallest(arr, i);
        System.out.println("第" + i + "大的元素为_" + smallest);
    }
}
