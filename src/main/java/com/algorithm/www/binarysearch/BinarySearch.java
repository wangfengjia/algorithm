package com.algorithm.www.binarysearch;


/**
 * 二分查找
 * @author wangyongchun
 * @date 2019/06/27 19:35
 */
public class BinarySearch {


    /**
     * 二分查找非递归版
     * @param arr
     * @param n
     * @param destination
     * @return
     */
    public static int binarySearch(int[] arr, int n, int destination){

        int left = 0;
        int right = n - 1;

        int pos = -1;
        while (left <= right){
            int middle = left + (right - left) / 2;
            if (arr[middle] == destination){
                pos = middle;
                break;
            }else if (destination < arr[middle]){
                right = middle - 1;
            }else {
                left = middle + 1;
            }
        }

        return pos;
    }

    /**
     * 二分查找递归版
     * @param arr
     * @param left
     * @param right
     * @param num
     * @return
     */
    public static int binarySearchByRecursion(int[] arr, int left, int right, int num){
        if (left > right){
            return -1;
        }

        int middle = left + (right - left) / 2;

        if (arr[middle] == num){
            return middle;
        }else if (arr[middle] > num){
            return binarySearchByRecursion(arr, left, middle - 1, num);
        }else {
            return binarySearchByRecursion(arr, middle + 1, right, num);
        }

    }

    /**
     * 查找第一个等于给定值的元素
     * @param arr
     * @param n
     * @return
     */
    public static int binarySearchFirstElement(int[] arr, int n, int value){

        int left = 0;
        int right = n - 1;

        int middle = -1;
        while (left <= right){
            int pos = left + (right - left) / 2;
            if (arr[pos] > value){
                right = pos - 1;
            }else if (arr[pos] < value){
                left = pos + 1;
            }else {
                if (pos == 0 || arr[pos - 1] != value){
                    middle = pos;
                    break;
                }else {
                    right = pos - 1;
                }
            }
        }

        return middle;
    }

    /**
     * 找到最后一个等于给定值的元素
     * @param arr
     * @param n
     * @param value
     * @return
     */
    public static int binarySearchLastElement(int[] arr, int n, int value){

        int left = 0;
        int right = n - 1;

        int middle = -1;
        while (left <= right){
            int pos = left + (right - left) / 2;
            if (arr[pos] > value){
                right = pos - 1;
            }else if (arr[pos] < value){
                left = pos + 1;
            }else {
                if (pos == n - 1 || arr[pos + 1] != value){
                    middle = pos;
                    break;
                }else {
                    left = pos + 1;
                }
            }
        }

        return middle;
    }

    /**
     * 找到第一个大于等于给定值的元素
     * @param arr
     * @param n
     * @param num
     * @return
     */
    public static int binarySearchEqualAndGreaterFirstElement(int[] arr, int n, int num){

        int left = 0;
        int right = n - 1;

        while (left <= right){
            int middle = left + (right - left) / 2;
            if (arr[middle] >= num){
                if (middle == 0 || arr[middle - 1] < num){
                    return middle;
                }
                right = middle - 1;
            }else {
                left = middle + 1;
            }
        }

        return -1;
    }

    /**
     * 找到最后一个小于等于给定值的元素
     * @param arr
     * @param n
     * @param value
     * @return
     */
    public static int binarySearchLessAndEqualLastElement(int[] arr, int n, int value){

        int left = 0;
        int right = n - 1;

        while (left <= right){

            int pos = left + (right - left) / 2;
            if (arr[pos] <= value){
                if (pos == n - 1 || arr[pos + 1] > value){
                    return pos;
                }
                left = pos + 1;
            }else {
                right = pos - 1;
            }
        }

        return -1;
    }



    public static void main(String[] args){

        int[] arr = {1, 3, 3,3,4, 6, 7,8, 11};
//        int i = binarySearchByRecursion(arr, 0, arr.length - 1, 4);
        int first = binarySearchFirstElement(arr, arr.length, 3);
        int last = binarySearchLastElement(arr, arr.length, 3);

        int greatAndEqualFirst = binarySearchEqualAndGreaterFirstElement(arr, arr.length, 5);
        int lessAndEqualLast = binarySearchLessAndEqualLastElement(arr, arr.length, 9);

        System.out.println("first = " + first);
        System.out.println("last = " + last);
        System.out.println("greatAndEqualFirst = " + greatAndEqualFirst);
        System.out.println("lessAndEqualLast = " + lessAndEqualLast);
    }
}
