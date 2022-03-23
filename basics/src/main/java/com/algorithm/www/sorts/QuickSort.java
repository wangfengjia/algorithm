package com.algorithm.www.sorts;

import com.sun.istack.internal.NotNull;

import java.util.Arrays;

/**
 * 快速排序
 *
 * @author wangyongchun
 * @date 2019/06/26 14:19
 */
public class QuickSort {

    public static void quickSort(int[] arr, int n){
        quickSortInternally(arr, 0, n - 1);
    }

    private static void quickSortInternally(int[] arr, int p, int q){
        if (p >= q){
            return;
        }

        int partition = partitionV2(arr, p, q);
        quickSortInternally(arr, p, partition - 1);
        quickSortInternally(arr, partition + 1, q);
    }

    private static int partition(@NotNull int[] arr, int p, int q){

        int i = p;
        int j = p;
        int pivot = q;

        for (int k = 0; k < pivot - p; k++){
            if (arr[i] < arr[pivot]){
                int tem = arr[i];
                arr[i] = arr[j];
                arr[j] = tem;
                ++i;
                ++j;
            }else {
                ++j;
            }
        }

        int temp = arr[i];
        arr[i] = arr[q];
        arr[q] = temp;

        System.out.println("i=" + i);
        return i;
    }


    private static int partitionV2(@NotNull int[] arr, int p, int q){
        int pivot = arr[q];
        int i = p;

        for (int j = p; j < q; ++j){
            if (arr[j] < pivot){
                if (i == j){
                    ++i;
                }else {
                    int tem = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tem;
                    ++i;
                }
            }
        }

        int temp = arr[i];
        arr[i] = pivot;
        arr[q] = temp;

        System.out.println("i = " + i);
        return i;
    }


    public static void main(String[] args){
        int[] arr = {1,222,119, 210, 33,44,210,87,21,12121,3243};
        quickSort(arr, arr.length);
        System.out.println(Arrays.toString(arr));
    }
}
