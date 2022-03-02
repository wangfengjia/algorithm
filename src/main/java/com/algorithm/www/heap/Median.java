package com.algorithm.www.heap;

/**
 * 求一组数据的中位数
 * 静态数据思路：
 * 1.利用一个大顶堆和一个小顶堆，大顶堆存储前半部分数据(假如数组个数(n)是偶数，n/2个数据；
 *  假如数组个数是奇数,n / 2 + 1个数据)；小顶堆存储后半部分的数据(数组的个数是奇数和偶数都是n/2个数据)
 * 2.小顶堆中的数据都大于大顶堆中的数据，大顶堆的堆顶元素就是中位数
 * 动态数据思路
 * 1.新添加一个元素的时候，假如这个数据小于等于大顶堆的堆顶元素，就将它插入的到大顶堆；
 *  假如这个数据大于等于小顶堆，则将这个元素插入到小顶堆；
 * 2.调整大顶堆和小顶堆，使数据个数满足要求；
 *   a、个数为偶数，并且大顶堆的个数不等于小顶堆的个数，则将大顶堆的堆顶元素插入到小顶堆，然后删除大顶堆的堆顶
 *   a、个数为奇数，并且大顶堆的个数和小顶堆的个数之差不等于1，则将小顶堆的堆顶元素插入到大顶堆，然后删除小顶堆的堆顶
 */
public class Median {

    private Heap maxHeap;
    private MinHeap minHeap;
    private int[] arr;

    public Median(Heap maxHeap, MinHeap minHeap, int[] arr){
        this.maxHeap = maxHeap;
        this.minHeap = minHeap;
        this.arr = arr;
        buildHeap();
    }

    private void buildHeap(){
        int arrSize = arr.length;
        int middle = 0;
        if (arrSize % 2 == 0){
            middle = arrSize / 2;
        }else {
            middle = arrSize / 2 + 1;
        }

        for (int i = 0; i < middle; i++){
            maxHeap.insert(arr[i]);
        }

        for (int j = middle; j < arrSize; j++){
            minHeap.insert(arr[j]);
        }

    }

    public int getMedian(){
        return maxHeap.getMax();
    }

    public void dynamicInsert(int value){
        if (value <= maxHeap.getMax()){
            maxHeap.insert(value);
        }if (value >= minHeap.getMin()){
            minHeap.insert(value);
        }
        fixHeap();
    }

    private void fixHeap(){
        int maxHeapCount = maxHeap.getCount();
        int minHeapCount = minHeap.getCount();
        if ((maxHeapCount + minHeapCount) % 2 == 0 && maxHeapCount != minHeapCount){
            minHeap.insert(maxHeap.getMax());
            maxHeap.removeMax();
        }else if ((maxHeapCount + minHeapCount) % 2 != 0 && (maxHeapCount - minHeapCount) != 1){
            maxHeap.insert(minHeap.getMin());
            minHeap.removeMin();
        }
    }


    public static void main(String[] args){

        Heap maxHeap = new Heap(20);
        MinHeap minHeap = new MinHeap(20);
        int[] arr = {3, 6, 11, 33, 34, 43, 54};


        Median median = new Median(maxHeap, minHeap, arr);
        int median1 = median.getMedian();
//        System.out.println(median1);
        median.dynamicInsert(15);
        int median2 = median.getMedian();
        System.out.println(median2);
    }
}
