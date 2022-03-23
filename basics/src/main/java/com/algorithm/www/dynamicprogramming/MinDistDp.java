package com.algorithm.www.dynamicprogramming;

/**
 * 利用动态规划求解矩阵来个顶点之间的最短路径(经过的元素之和最小)，规定每个阶段只有向右和向下两种决策，
 *  一次只能走一步
 */
public class MinDistDp {

    //状态转移方程解法中使用的属性
    private int[][] items; //矩阵元素
    private int[][] states; //保存每个阶段的状态数组
    private int num; // 矩阵大小


    public MinDistDp(int[][] items, int num){
        this.items = items;
        this.states = new int[num][num];
        this.num = num;
    }

    /**
     * 利用状态转移表法求解最短路径
     * 思路
     * 1.通过上一个阶段得到下一个阶段的状态值，如要获取到(i, j)元素的最短路径，因为只有(i - 1, j)或者(i, j - 1)
     *  可以到达到(i, j)，所以到达(i, j)的最短路径等于min(min_dist(i - 1, j), min_dist(i, j - 1)) + items[i][j]
     *  将从起点到二维矩阵元素的最短路径保存到二维数组里面，数组的下标为item[i][j]
     * @param items 二维矩阵元素
     * @param n 二维矩阵的大小
     * @return
     */
    public int minDistDp(int[][] items, int n){

        int[][] states = new int[n][n];
        //初始化第一行，因为第一行的元素只可以通过前面一个元素向右移可以达到
        int firstRowSum = 0;
        for (int j = 0; j < n; j++){
            firstRowSum += items[0][j];
            states[0][j] = firstRowSum;
        }

        //初始化第一列，因为第一列的元素只可以通过上面一个元素向下移动可以到达
        int firstColumnSum = 0;
        for (int i = 0; i < n; i++){
            firstColumnSum += items[i][0];
            states[i][0] = firstColumnSum;
        }

        //记录其他可以通过向右或者向下移动到达的元素的状态
        for (int i = 1; i < n; i++){
            for (int j = 1; j < n; j++){
                states[i][j] = items[i][j] + Math.min(states[i][j - 1], states[i - 1][j]);
            }
        }

        return states[n - 1][n - 1];
    }


    /**
     * 利用状态转移方程求解
     * @param i
     * @param j
     * @return
     */
    public int minDist(int i, int j){

        if (i == 0 && j == 0){
            return items[0][0];
        }

        //说明这个阶段的状态值已经求得，直接返回
        if (states[i][j] > 0){
            return states[i][j];
        }

        //从左边一个元素向右移动到达目标元素，所以先得到从起点到左边元素的最短路径
        int minLeft = Integer.MAX_VALUE;
        if (j - 1 >= 0){
            minLeft = minDist(i, j - 1);
        }
        //从上面一个元素向下移动到目标元素，所以先得到从起点到上面元素的最短路径
        int minUp = Integer.MAX_VALUE;
        if (i - 1 >= 0){
            minUp = minDist(i - 1, j);
        }

        int currentDist = items[i][j] + Math.min(minLeft, minUp);
        states[i][j] = currentDist;
        return currentDist;
    }


    public static void main(String[] args){

        int size = 4;
        int[][] items = {
                {1, 3, 5, 9},
                {2, 1, 3, 4},
                {5, 2, 6, 7},
                {6, 8, 4, 3}
        };
        MinDistDp minDistDp = new MinDistDp(items, size);

        int distDp = minDistDp.minDistDp(items, size);
        System.out.println("状态转移表求得最短路径_" + distDp);

        int minDist = minDistDp.minDist(3, 3);
        System.out.println("状态转移方程求得最短路径_" + minDist);
    }
}
