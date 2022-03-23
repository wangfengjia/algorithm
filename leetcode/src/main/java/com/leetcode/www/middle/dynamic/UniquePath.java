package com.leetcode.www.middle.dynamic;

/**
 * 在一个m * n的网格，从左上角走到右下角，总共有多少条不同的路径
 * 思路
 * 方法一(动态规划版):用f(i,j)表示从左上角到(i,j)的路径数量，由于每一步只能向下或者向右移动一步，所以要到(i,j),所以只能从(i-1,j)或者(i,j-1)走过来，所以状态
 *                  转移方程为f(i,j) = f(i-1,j) + f(i, j - 1)。为了实现的方便，我们把所有的f(0,j)和f(i,0)设置为边界条件，值都是1,时间复杂度O(mn)
 */
public class UniquePath {

    public int solution(int m, int n){

        int[][] paths = new int[m][n];
        for (int i = 0; i < m; i++){
            paths[i][0] = 1;
        }

        for (int j = 0; j < n; j++){
            paths[0][j] = 1;
        }

        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                paths[i][j] = paths[i - 1][j] + paths[i][j - 1];
            }
        }

        return paths[m - 1][n - 1];
    }

    public static void main(String[] args) {

        int m = 3;
        int n = 7;

        UniquePath uniquePath = new UniquePath();
        int solution = uniquePath.solution(m, n);
        System.out.println(solution);

    }
}
