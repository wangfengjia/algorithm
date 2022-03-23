package com.algorithm.www.thinking;


/**
 * 分治思想
 * 统计一个数组的逆序度，也就是逆序对的数量
 *
 * 思路
 * 1.利用分治的思想，求解一个数组的逆序度分解为求数组A的逆序度+数组B的逆序度+数组A和数组B之间的有序度
 * 2.求解数组间的逆序度的办法，利用归并排序里面的合并操作
 */
public class MergeSortCounting {


    private int num = 0;

    public int count(int[] arr, int n){
        num = 0;
        mergeSortCounting(arr, 0, n - 1);
        return num;
    }

    /**
     * 求逆序度
     * @param arr
     * @param left
     * @param right
     */
    private void mergeSortCounting(int[] arr, int left, int right){

        if (left >= right){
            return;
        }

        int middle = (left + right) / 2;
        mergeSortCounting(arr, left, middle);
        mergeSortCounting(arr, middle + 1, right);
//        merge(arr, left, middle, right);
        orderMerge(arr, left, middle, right);
    }

    /**
     * 求两个数组间的逆序对数量
     * @param arr
     * @param left
     * @param middle
     * @param right
     */
    private void merge(int[] arr, int left, int middle, int right){

        int p = left;
        int q = middle + 1;
        int k = 0;
        int[] tem = new int[right - left + 1];

        while (p <= middle && q <= right){

            //前一个数组里面的指针移动到下一个元素时，计算上一个元素的逆序对数量
            if (arr[p] <= arr[q]){
                num += q - middle - 1;
                tem[k++] = arr[p++];
            }else {
                tem[k++] = arr[q++];
            }
        }

        while (p <= middle){
            tem[k++] = arr[p++];
            num += q - middle - 1;
        }

        while (q <= right){
            tem[k++] = arr[q++];
        }

        for (int i = 0; i < right - left; i++){
            arr[i + left] = tem[i];
        }
    }


    /**
     * 求数组A和数组B之间的有序对数量
     * @param arr
     * @param left
     * @param middle
     * @param right
     */
    private void orderMerge(int[] arr, int left, int middle, int right){

        int p = left;
        int q = middle + 1;
        int k = 0;
        int[] temp = new int[right - left + 1];

        while (p <= middle && q <= right){
            if (arr[p] <= arr[q]){
                num += right - q + 1;
                temp[k++] = arr[p++];
            }else {
                temp[k++] = arr[q++];
            }
        }

        while (p <= middle){
            temp[k++] = arr[p++];
        }

        while (q <= right){
            temp[k++] = arr[q++];
        }

        for (int j = 0; j < right - left; j++){
            arr[left + j] = temp[j];
        }
    }




    public static void main(String[] args){

        MergeSortCounting mergeSortCounting = new MergeSortCounting();
        int[] arr = {1, 2, 3};
        int count = mergeSortCounting.count(arr, arr.length);
//        System.out.println("逆序对的数量为_" + count);
        System.out.println("有序对的数量为_" + count);
    }
}
