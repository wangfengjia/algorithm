package com.leetcode.www.middle.array;

/**
 * 求数组第k小的元素，基于快速排序，找到分界点i，分界点对应的元素就是这个数组第i+1大的元素
 */
public class KthSmallest {


    public int solution(int[] arr, int k){

        if (arr == null || arr.length == 0){
            return -1;
        }

        int left = 0;
        int right = arr.length - 1;
        int pos = partition(arr, left, right);
        while (pos + 1 != k){
            if (pos + 1 < k){
                pos = partition(arr, pos + 1, right);
            }else {
                pos = partition(arr, left, pos - 1);
            }
        }

        return arr[pos];
    }


    public int partition(int[] arr, int left, int right){

        //将数组arr[left...right]里面小于arr[right]放到数组的前面，等这个过程结束以后，i指向的元素就是数组arr的第i+1大的元素
        int pivot = arr[right];
        int i = left;
        for (int j = left; j < right; j++){
            //第k小的时候是<，第k大的时候是大于
            if (arr[j] < pivot){
                if (i == j){
                    ++i;
                }else {
                    int tmp = arr[i];
                    arr[i++] = arr[j];
                    arr[j] = tmp;
                }
            }
        }

        int tmp = arr[i];
        arr[i] = pivot;
        arr[right] = tmp;
        return i;
    }


    public static void main(String[] args) {

        int[] arr = new int[]{3,2,1,5,6,4};
        int k = 2;
        int kth = new KthSmallest().solution(arr, 2);
        System.out.println(kth);
    }
}
