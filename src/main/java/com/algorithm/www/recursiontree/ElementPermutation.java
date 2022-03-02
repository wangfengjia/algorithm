package com.algorithm.www.recursiontree;


/**
 * 求一组数据的所有排列
 * 思路：
 * 1.先确定最后一位数据(最后一位数据有n种情况)，然后剩下的元素的排列方式就是一组数据的所有排列方式
 *
 * @author wangyongchun
 * @date 2019/07/06  14:28
 */
public class ElementPermutation {

    /**
     *
     * @param data 数组
     * @param n 排列的元素个数
     * @param k 数组的大小
     */
    public static void permutation(int[] data, int n, int k){

        if (k == 1){
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < n; i++){
        builder.append(data[i]);
        builder.append(",");
    }
            System.out.println(builder.toString());
            System.out.println("======================");
}


        for (int i = 0; i < k; i++){
        int tem = data[i];
        data[i] = data[k - 1];
        data[k - 1] = tem;

        permutation(data, n, k - 1);

        //递归以后需要将数据换回原来的位置，要不然会出现一个数据在最后一位出现两次
        //比如{1,2},第一次递归的时候是{2,1},不替换回来的话去取第二个数据放在最后一位的时候还是1
        tem = data[i];
        data[i] = data[k - 1];
        data[k - 1] = tem;
        }

    }


    public static void main(String[] args){

        int[] arr = {1, 2, 3};
        permutation(arr, arr.length, arr.length);
    }
}
