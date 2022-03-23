package com.algorithm.www.dynamicprogramming;

/**
 * 使用动态规划解决背包问题
 * 思路
 * 1.将问题的求解分解为n(物品的个数)个阶段，每个阶段决策是否将物品放入背包，背包中的物品质量就会有很多种不同的情况，
 *  将同一层中重复的状态合并，这样每层的状态个数不会超过w(背包的承载重量)，这样就不会像回溯法解决的时候出现的每层
 *  状态的个数呈指数级增长
 * 2.用一个二维数组status[n][w+1]来记录每层可以达到的状态，n表示物品的个数，w表示最大承重
 * 3.经过上面的处理以后，只需要在status数组的最后一层找最接近w(最大承重)的值，这个值就是这个背包中能装下的最大值
 * @author wangyongchun
 * @date 2019/07/19 17:58
 */
public class Knapsack {

    /**
     * 动态规划解决背包问题
     * @param weight 每个物品的重量
     * @param n 物品个数
     * @param m 背包的最大承载
     * @return
     */
    public int knapsack(int[] weight, int n, int m){
        boolean[][] status = new boolean[n][m + 1];
        status[0][0] = true;
        status[0][weight[0]] = true;

        for (int i = 1; i < n; i++){

            //第i个物品不放入背包
            for (int j = 0; j <= m; j++){
                if (status[i - 1][j]){
                    status[i][j] = status[i - 1][j];
                }
            }

            //将第i个物品放入背包
            for (int j = 0; j <= m - weight[i]; j++){
                if (status[i - 1][j]){
                    status[i][j + weight[i]] = status[i - 1][j];
                }
            }
        }

        //输出结果
        for (int i = m; i >= 0; i--){
            if (status[n - 1][i]){
                return i;
            }
        }

        return 0;
    }


    /**
     * 用一个一维数组保存每个阶段的可达状态
     * @param weight 每个物品重量
     * @param n 背包能装下的最大物品个数
     * @param m 背包的最大承重
     */
    public int knapsackV2(int[] weight, int n, int m){
        boolean[] state = new boolean[m + 1];
        state[0] = true;
        state[weight[0]] = true;

        for (int i = 1; i < n; i++){
            for (int j = m - weight[i]; j >= 0; j--){
                if (state[j]){
                    state[j + weight[i]] = true;
                }
            }
        }

        for (int j = m; j >= 0; j--){
            if (state[j]){
                return j;
            }
        }

        return 0;
    }


    public static void main(String[] args){

        Knapsack knapsack = new Knapsack();
        int[] weight = {22, 12, 32, 7, 11, 55, 6, 8, 9, 2, 6};
        int n = weight.length;
        int m = 90;
        int w = knapsack.knapsack(weight, n, m);
        int ww = knapsack.knapsackV2(weight, n, m);
        System.out.println(w);
        System.out.println("====================");
        System.out.println(ww);
    }
}
