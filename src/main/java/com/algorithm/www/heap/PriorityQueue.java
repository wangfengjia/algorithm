package com.algorithm.www.heap;

import java.util.Arrays;

/**
 * 基于堆实现优先队列
 *
 * @author wangyongchun
 * @date 2019/07/07 19:48
 */
public class PriorityQueue {

    /**
     * 合并多个有序数组，保证合并后的数组同样是有序的
     * 思路
     * 1.创建一个堆，堆里面的元素个数是数组的数量
     * 2.取堆顶元素放到合并后的数组
     * 3.删除堆顶元素
     * 4.取堆顶元素所在数组的下一个数据，将这个数据插入到堆里面
     * 5.重复步骤2，3，4
     */
    public static void mergeSortedArray(MergeArray mergeArray){

        int[] first = mergeArray.getFirst();
        int[] second = mergeArray.getSecond();
        int[] third = mergeArray.getThird();
        int elementNum = mergeArray.getElementNums();
        int[] des = mergeArray.getDes();


        int firstArrayPos = 0;
        int secondArrayPos = 0;
        int thirdArrayPos = 0;


        MinHeap minHeap = new MinHeap(3);
        minHeap.insert(first[firstArrayPos]);
        minHeap.insert(second[secondArrayPos]);
        minHeap.insert(third[thirdArrayPos]);


        int insertElement = 0;
        int heapMin = 0;

        for (int i = 0; i < elementNum; i++){
            heapMin = minHeap.getMin();
            des[i] = heapMin;
            minHeap.removeMin();
            if (heapMin == first[firstArrayPos] && (firstArrayPos + 1) <= mergeArray.getFirstArrayMaxPos()){
                minHeap.insert(first[++firstArrayPos]);
            }else if (heapMin == second[secondArrayPos] && (secondArrayPos + 1) <= mergeArray.getSecondArrayMaxPos()){
                minHeap.insert(second[++secondArrayPos]);
            }else if (heapMin == third[thirdArrayPos] && (thirdArrayPos + 1) <= mergeArray.getThirdArrayMaxPos()){
                minHeap.insert(third[++thirdArrayPos]);
            }else {
                if ((firstArrayPos + 1) <= mergeArray.getFirstArrayMaxPos()){
                    minHeap.insert(first[++firstArrayPos]);
                }else if ((secondArrayPos + 1) <= mergeArray.getSecondArrayMaxPos()){
                    minHeap.insert(second[++secondArrayPos]);
                }else if ((thirdArrayPos + 1) <= mergeArray.getThirdArrayMaxPos()){
                    minHeap.insert(third[++thirdArrayPos]);
                }
            }
        }

        System.out.println(Arrays.toString(des));
    }


    public static void topK(int[] arr, int k){

        MinHeap minHeap = new MinHeap(k);
        for (int i = 0; i < k; i++){
            minHeap.insert(arr[i]);
        }

        for (int i = k; i < arr.length; i++){
            if (arr[i] > minHeap.getMin()){
                minHeap.removeMin();
                minHeap.insert(arr[i]);
            }
        }

        System.out.println(Arrays.toString(minHeap.getArr()));
    }

    public static void main(String[] args){
        int[] first = {1, 7, 22, 33, 65, 75, 88};
        int[] second = {4, 5, 10, 15, 43, 65, 76};
        int[] third = {3,6, 11, 33, 43, 54};
//        MergeArray mergeArray = new MergeArray(first, second, third);
//        mergeSortedArray(mergeArray);


        int[] arr = {22, 33, 21, 2, 34, 123};
        topK(second, 3);
    }
}
