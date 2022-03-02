package com.algorithm.www.dynamicprogramming;

/**
 * 背包问题升级版
 * 1.问题描述
 *  不同重量，不同价值的物品，求装到一个有最大重量限制的背包里面的物品的最大价值
 */
public class KnapsackUpdate {

    private int maxV = Integer.MIN_VALUE;
    private int[] weight = {1, 3, 4, 6, 5}; //每个物品的重量
    private int[] value = {22, 43, 88, 212, 12}; //每个物品的价值
    private int count = 5; //物品的个数
    private int maxW = 9; //背包的最大承重
    /**
     * 使用回溯法来解决
     * @param i 表示当前要决策第i物品是否放入背包
     * @param cw 表示当前背包里面物品的总重量
     * @param cv 表示当前背包里面物品的总价值
     */
    public void greedyAlgo(int i, int cw, int cv){

        if (cw == maxW || i == count){ // cw=w表示背包已经装满了，i=count表示物品已经考察完了
            if (cv > maxV){
                maxV = cv;
            }
        }

        //第i个物品不放入背包
        greedyAlgo(i + 1, cw, cv);
        //第i个物品放入背包
        if (cw + weight[i] <= maxW){
            greedyAlgo(i + 1, cw + weight[i], cv + value[i]);
        }
    }

    /**
     * 使用动态规划解决背包问题
     * @param dWeight 每个物品的重量
     * @param dValue 每个物品的价值
     * @param dn 物品的个数
     * @param dw 背包最大承重
     * @return
     */
    public int getMaxValueByDynamicProgramming(int[] dWeight, int[] dValue, int dn, int dw){

        //二维数组里面存储的当前状态对应的最大总价值
        int[][] states = new int[dn][dw + 1];
        //初始化数组
        for (int i = 0; i < dn; i++){
            for (int j = 0; j < dw + 1; j++){
                states[i][j] = -1;
            }
        }


        states[0][0] = 0;
        states[0][dWeight[0]] = dValue[0];
        //开始动态规划
        for (int i = 1; i < dn; i++){

            //不将第i个物品放入背包
            for (int j = 0; j <= dw; j++){
                if (states[i - 1][j] >= 0){
                    states[i][j] = states[i - 1][j];
                }
            }

            //将第i个物品放入背包
            for (int j = 0; j <= dw - dWeight[i]; j++){
                if (states[i - 1][j] >= 0){
                    //保存当前状态的价值最大值
                    int value = states[i - 1][j] + dValue[i];
                    if (value > states[i][j + dWeight[i]]){
                        states[i][j + dWeight[i]] = value;
                    }
                }
            }
        }

        //找出最大值，最大值在二维数组的最后一个数组
        int maxValue = 0;
        for (int i = 0; i <= dw; i++){
            if (states[dn - 1][i] > maxValue){
                maxValue = states[dn - 1][i];
            }
        }
        return maxValue;
    }

    /**
     * 双11活动中，有满200减50的活动，求得选择哪几个商品以后，这些商品的总价值是大于等于200的数中的最小值，并且求出选择了哪些商品
     * 思路
     * 1.由于这个问题是要求大于等于200的最小的值，那么类型0-1背包问题中的最大承重的值不应该是200，而太大的话就
     * 没有太大的优惠，所以可以将这个值设置为200的3倍
     * 2.利用动态规划，将这个问题分解为多个阶段，每个阶段对应一个决策(放入或者不放入购物车)，记录每一个阶段可达状态集合
     *  用一个二维数组记录这些可达状态集合
     * 3.从价格等于200开始遍历二维数组最后一个数组，第一个价格的值为true的就是大于等于200的最小值
     * 4.得到选择了哪些商品:后面的状态值(i,j)只能通过(i - 1, j)和(i - 1, j - item[i])得到，当(i - 1, j - item[i])下标
     *  对应的值为true的时候表示这个商品被选中,当(i - 1, j)为true的时候，表示第i件商品没有被选中
     * @param price 每件商品价格
     * @param n 商品数量
     * @param limit 满额的数值
     */
    public int doubleElevenAdvance(int[] price, int n, int limit){

        boolean[][] states = new boolean[n][3 * limit + 1];
        states[0][0] = true;
        states[0][price[0]] = true;

        for (int i = 1; i < n; i++){

            //不选择第i件物品
            for (int j = 0; j <= 3 * limit; j++){
                if (states[i - 1][j]){
                    states[i][j] = true;
                }
            }

            //选择第i件物品
            for (int j = 0; j <= 3 * limit - price[i]; j++){
                if (states[i - 1][j]){
                    states[i][j + price[i]] = true;
                }
            }
        }

        //得到大于等于200的最小值
        int j;
        for (j = limit; j <= 3 * limit; j++){
            if (states[n - 1][j]){
                break;
            }
        }

        int totalPrice = j;
        //没有可行的解
        if (j == -1){
            return 0;
        }

        for (int i = n - 1; i >= 1; i--){
            //判断是否购买第i件物品
            if (j - price[i] >= 0 && states[i - 1][j - price[i]]){
                System.out.print(price[i] + ","); //购买了这件商品
                j = j - price[i];
            }
        }

        if (j != 0){
            System.out.print(price[0]);
        }

        return totalPrice;
    }


    public static void main(String[] args){

        KnapsackUpdate knapsackUpdate = new KnapsackUpdate();
        int[] dWeight = {3, 4, 6, 7, 8, 1, 9};
        int[] dValue = {212, 22, 43, 21, 90, 55, 1000};
        int dn = 7;
        int dw = 20;
        int maxValue = knapsackUpdate.getMaxValueByDynamicProgramming(dWeight, dValue, dn, dw);
        System.out.println("背包能装的物品最大值为_" + maxValue);


        int[] price = {21, 21, 34, 65, 90, 55, 72, 35};
        int num = price.length;
        int priceLimit = 228;
        int totalPrice = knapsackUpdate.doubleElevenAdvance(price, num, priceLimit);
        System.out.println("大于等于200的价格为_" + totalPrice);
    }
}
