package com.algorithm.www.sorts;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author wangyongchun
 * @date 2019/06/25 19:40
 */
public class MergeSort {

    public static void mergeSortInternally(int[] a, int n){
        mergeSort(a, 0, n-1);
    }
    public static void mergeSort(int[] arr, int left, int right){

        //递归的终止条件
        if (left >= right){
            return;
        }

        //为了防止left+right大于int值的范围
        int middle = left + (right - left) / 2;
        mergeSort(arr, left, middle);
        mergeSort(arr, middle + 1, right);

        mergeBySentry(arr, left, middle, right);
    }

    private static void merge(int[] arr, int p, int q, int r){
        int left = p;
        int right = q + 1;

        int k = 0;

        int[] temp = new int[r - p + 1];
        while (left <= q && right <= r){
            if (arr[left] <= arr[right]){
                temp[k++] = arr[left++];
            }else {
                temp[k++] = arr[right++];
            }
        }

        //判断哪个数组中还有剩余数据
        int start = left;
        int end = q;
        if (right <= r){
            start = right;
            end = r;
        }

        while (start <= end){
            temp[k++] = arr[start++];
        }

        //将临时数组中的数据拷贝回原数组
        for (int i = 0; i <= r - p; ++i){
            arr[p + i] = temp[i];
        }
    }


    private static void mergeBySentry(int[] arr, int p, int q, int r){

        //+2的原因是中间位置的元素和要放一个哨兵
        int[] leftArr = new int[q - p + 2];
        //+1的原因是要放哨兵
        int[] rightArr = new int[r - q + 1];

        for (int i = 0; i <= q - p; i++){
            leftArr[i] = arr[p + i];
        }

        //左边数组哨兵
        leftArr[q - p + 1] = Integer.MAX_VALUE;

        for (int j = 0; j < r - q; j++){
            rightArr[j] = arr[q + j + 1];
        }

        //右边数组哨兵
        rightArr[r - q] = Integer.MAX_VALUE;

        int left = 0;
        int right = 0;
        int k = p;

        while (k <= r){
            //当遍历到左边数组中的哨兵时，表示左边数组遍历完毕，i不再增加，直到右边数组读取完剩下的元素；同理右边的数组也是一样
            if (leftArr[left] <= rightArr[right]){
                arr[k++] = leftArr[left++];
            }else {
                arr[k++] = rightArr[right++];
            }
        }
    }


    public static void main(String[] args){

        int arr[] = {2, 3, 55, 23, 123, 43, 88, 75, 112, 213232,3232,321323212,323113};
//        mergeSort(arr, 0, arr.length - 1);
        mergeSortInternally(arr, arr.length);
        System.out.println(Arrays.toString(arr));
    }
}
