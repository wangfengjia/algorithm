package com.algorithm.www.sorts;


/**
 * 找出两个升序数组(A,B)归并之后的中位数
 * 思路
 * 1.大数组被中位数分为两部分,每部分又可以根据来源划分为两部分，一部分是数组A的元素，一部分是数组B的元素
 * 2.数组A，B根据会出现在大数组的左部分和右部分分为两部分，出现在左边部分的标记为绿色，出现在右边部分的标记为橙色，
 * 而且绿色元素和橙色元素个数是相等的(偶数个数情况下)，而且最大的绿色元素小于最小的橙色元素
 * 3.假如数组A的长度为m,绿色和橙色的分界点是i，数组B的长度为n，绿色和橙色的分界点是j，为了让大数组左右两部分长度
 * 相等，则需要满足如下关系式: median=i+j=(m+n+1)/2,加1的目的是为应对大数组个数为奇数的情况
 * 4.利用二分查找在数组m找到中点i，则j等于median - i,则会出现如下三种情况
 *  a.a[i] >= b[j - 1] && a[i - 1] <= b[j],这一组i，j就是满足条件的
 *  b.a[i] < b[j - 1],则i右移
 *  c.a[i - 1] > b[j],则i左移
 * 5.取中位数
 *  a.假如大数组的长度为奇数时，则中位数等于max(a[i - 1], b[j-1]),就是大数组左半部分的最大值
 *  b.假如大数组的长度为偶数时，则中位数等于(Max(a[i-1], b[j - 1]) + Min(a[i], b[j])) / 2,就是大数组左半部分
 *      的最大值和大数组右半部分的最小值的平均值
 * 6.两种特殊情况的处理
 *  a.数组A的长度远远大于数组B，则数组A和数组B进行交换
 *  b.数组A所有元素小于数组B的所有元素，或者数组A所有元素大于数组B所有元素
 *
 * @author wangyongchun
 * @date 2019/07/29 10:16
 */
public class MedianSortedArrays {


    public double findMedianSortedArrays(int[] arrayA, int[] arrayB){
        int m = arrayA.length;
        int n = arrayB.length;

        //如果数组A的长度大于数组B的长度，则交换数组
        if (m > n){
            int[] temArr = arrayA;
            arrayA = arrayB;
            arrayB = temArr;
            int tem = m;
            m = n;
            n = tem;
        }

        int start = 0;
        int end = m;
        int median = (m + n + 1) / 2;
        while (start <= end){

            int i = (start + end) / 2;
            int j = median - i;
            if (i < end && arrayA[i] < arrayB[j - 1]){ //a[i] < b[j-1]，i向右侧移动
                start = i + 1;
            }else if (i > start && arrayA[i - 1] > arrayB[j]){ // a[i-1] > b[j]，i向左侧移动
                end = i - 1;
            }else {
                int maxLeft;
                if (i == 0){ //数组A都大于数组B的情况
                    maxLeft = arrayB[j - 1];
                }else if (j == 0){ //数组A都小于数组B的情况
                    maxLeft = arrayA[i - 1];
                }else {
                    maxLeft = Math.max(arrayA[i - 1], arrayB[j - 1]);
                }

                if ((m + n) % 2 == 1){ //如果大数组的长度为奇数，则中位数为左半部分的最大值
                    return maxLeft;
                }

                int minRight; //右半部分的最小值
                if (i == m){ //数组A都小于数组B的情况
                    minRight = arrayB[j];
                }else if (j == n){ //数组A都大于数组B的情况
                    minRight = arrayA[i];
                }else {
                    minRight = Math.min(arrayA[i], arrayB[j]);
                }
                //大数组的个数为偶数的时候，中位数等于左半部分的最大值和右半部分的最小值之和的平均值
                return (maxLeft + minRight) / 2;

            }

        }

        return 0.0;
    }


    public static void main(String[] args){
        int[] a = {3, 5, 6, 7, 8, 12, 20};
        int[] b = {1, 10, 17, 18};

        MedianSortedArrays sortedArrays = new MedianSortedArrays();
        double median = sortedArrays.findMedianSortedArrays(a, b);
        System.out.println("中位数为_" + median);
    }
}
