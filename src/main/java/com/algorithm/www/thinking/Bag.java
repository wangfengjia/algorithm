package com.algorithm.www.thinking;

/**
 * 回溯算法解背包问题
 *
 * @author wangyongchun
 * @date 2019/07/18 20:05
 */
public class Bag {

    private int maxW = Integer.MIN_VALUE;

    /**
     * 回溯法解背包问题
     * @param i 表示考察到的物品
     * @param cw 表示背包的当前重量
     * @param item 表示每个物品的重量
     * @param n 表示物品个数
     * @param w 表示背包能装的最大重量
     */
    public void bag(int i, int cw, int[] item, int n, int w){

        if (cw == w || i == n){
            if (cw > maxW){
                maxW = cw;
            }

            return;
        }

        bag(i + 1, cw, item, n, w);
        if (cw + item[i] <= w){
            bag(i + 1, cw + item[i], item, n, w);
        }
    }


    public static void main(String[] args){

        Bag bag = new Bag();
        int[] items = {22, 12, 3, 32, 3, 5, 8, 1, 43};
        int i = 0;
        int cw = 0;
        int n = 6;
        int w = 100;
        bag.bag(i, cw, items, n, w);
        System.out.println(bag.maxW);

    }
}
