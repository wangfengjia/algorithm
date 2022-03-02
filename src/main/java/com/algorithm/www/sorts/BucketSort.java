package com.algorithm.www.sorts;

import java.util.Arrays;

/**
 * 桶排序 (O(n))
 * 思路
 *      1.获取到要排序的数据的最大值，最小值
 *      2.根据最大值创建一定数量的桶，这些桶是有序的
 *      3.根据数据的大小划分到对应的桶
 *      4.每个桶里面进行快排
 *      5.申请一个临时数组，按照桶的顺序到每个桶里面取数据放到零食数组里面
 *      6.将临时数组的元素拷贝回原数组
 * 适用场景的条件
 *  1.要排序的数据比较容易划分为m个桶
 *  2.每个桶之间有序
 *  3.每个桶之间的数据比较均匀,在极端情况，所有数据都在一个桶里面，这样时间复杂度就会等于快排的数据复杂度(O(nlog^n))
 * 典型场景
 *  1.外部排序，就是数据存储在磁盘中，内存有限，无法将全部数据加载到内存
 */
public class BucketSort {

    /**
     * 桶排序
     * @param arr
     * @param bucketSize 桶的大小
     */
    public static void bucketSort(int[] arr, int bucketSize){

        if (arr.length == 1){
            return;
        }

        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++){
            if (arr[i] > max){
                max = arr[i];
            }
            if (arr[i] < min){
                min = arr[i];
            }
        }


        //得到桶的数量
        int bucketNum = (max - min) / bucketSize + 1;
        int[][] buckets = new int[bucketNum][bucketSize];
        //记录每个桶里面插入元素的时候的下标
        int[] bucketIndex = new int[bucketNum];
        for (int i = 0; i < arr.length; i++){
            //求放到哪个桶
            int index = (arr[i] - min) / bucketSize;
            //如果要插入桶中元素的下标等于桶的大小，则需要扩容桶
            if (bucketIndex[index] == buckets[index].length){
                ensureCapacity(buckets, index);
            }
            buckets[index][bucketIndex[index]++] = arr[i];
        }


        int k = 0;
        for (int i = 0; i < buckets.length; i++){

            if (bucketIndex[i] == 0){
                continue;
            }
            //每个桶里面进行快排u
            quickSort(buckets[i], 0, bucketIndex[i] - 1);
            for (int j = 0; j < bucketIndex[i]; j++){
                arr[k++] = buckets[i][j];
            }
        }
    }

    private static void quickSort(int[] arr, int p, int q){

        if (p >= q){
            return;
        }

        int pos = partition(arr, p, q);
        quickSort(arr, p, pos - 1);
        quickSort(arr, pos + 1, q);
    }

    private static int partition(int[] arr, int p, int q){

        int i = p;
        int pivot = arr[q];
        for (int j = p; j < q; j++){
            if (arr[j] < pivot){
                if (i == j){
                    ++i;
                }else {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    ++i;
                }
            }
        }

        int tem = arr[i];
        arr[i] = arr[q];
        arr[q] = tem;

        System.out.println("i=" + i);
        return i;
    }

    private static void ensureCapacity(int[][] buckets, int bucketIndex){
        int[] temArr = buckets[bucketIndex];
        //扩大为原来的两倍
        int[] tem = new int[temArr.length * 2];
        for (int i = 0; i < temArr.length; i++){
            tem[i] = temArr[i];
        }
        buckets[bucketIndex] = tem;
    }


    public static void main(String[] args){

        int[] arr = {1, 22, 3, 2153, 66, 666, 444, 212, 2133, 43320, 431222, 555, 3252, 32, 322, 212, -2, -21};
        int bucketSize = 3;
        bucketSort(arr, bucketSize);
        System.out.println(Arrays.toString(arr));
    }
}
