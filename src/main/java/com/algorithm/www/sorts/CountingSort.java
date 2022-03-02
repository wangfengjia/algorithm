package com.algorithm.www.sorts;

import java.util.Arrays;

/**
 * 计数排序,桶排序的一种特殊情况
 * 计数排序只能用在数据范围不大的情况，当要排序的数据范围比要排序的数据个数大很多时，就不适合使用计数排序，原因
 *      1.因为要先找到排序的数据里面最大值，利用这个最大值取创建一个数组，保存要排序的数据中每个数组值的个数,当数据
 *      范围比排序数据个数多很多时，会造成内存空间的浪费
 * 计数排序只能给非负整数排序
 * @author wangyongchun
 * @date 2019/06/27 10:38
 */
public class CountingSort {

    // n表示数组的大小
    public static void countingSort(int[] arr, int n){
        if (n <= 1){
            return;
        }

        //查找数组中的最大值
        int max = arr[0];
        for (int i = 1; i < n; i++){
            if (arr[i] > max){
                max = arr[i];
            }
        }

        int[] c = new int[max + 1];
        //统计出数组中每个值的个数
        for (int j = 0; j < n; j++){
            ++c[arr[j]];
        }

        //计算出小于等于c数组中每个下标的s元素的个数
            for (int i = 1; i <= max; i++){
            c[i] = c[i - 1] + c[i];
        }

        //申请一个临时数组
        int[] tem = new int[n];
        for (int i = 0; i < n; i++){
            int index = c[arr[i]] - 1;
            tem[index] = arr[i];
            --c[arr[i]];
        }

        //将临时数组拷贝回原来的数组
        for (int i = 0; i < n; ++i){
            arr[i] = tem[i];
        }
    }

    public static void main(String[] args){

        int[] arr = {1, 3, 7, 2, 4, 3, 2, 4, 5, 7, 6, 111, 23, 56, 45, 3213, 1000};
        countingSort(arr, arr.length);
        System.out.println(Arrays.toString(arr));

    }
}
