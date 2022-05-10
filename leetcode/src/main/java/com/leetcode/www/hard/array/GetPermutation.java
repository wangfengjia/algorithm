package com.leetcode.www.hard.array;

import java.util.Arrays;

/**
 * leetcode-60:排列序列
 * 给出集合 [1,2,3,...,n]，其所有元素共有n! 种排列。
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
    * "123"
    * "132"
    * "213"
    * "231"
    * "312"
    * "321"
 * 给定n和k，返回第k个排列。
 */
public class GetPermutation {



    /**
     * 回溯+剪枝(直接来到叶子节点):按照最基本的回溯，依次得到全排列，输出第k个排列即可，但是我们不必求出所有的全排列。由于所求的排列一定在递归树的叶子节点得
     * 到，进入每一个分支，可以根据已经选定个数算出未选定的数的个数，然后计算阶乘,就知道这一分支的叶子节点数目，根据k和这个数目的大小关系有如下两种处理
     *      1. 如果k大于这一分支的叶子节点数目，直接跳过这个分支，这就是剪枝
     *      2. 如果k小于等于这一分支的将要产生的叶子节点数目，那么所求的全排列一点在这一分支产生的叶子节点中，需要递归求解
     * 复杂度分析
     *  时间复杂度:O(N^2)
     *  空间复杂度:O(N),递归调用栈所使用的空间
     * @param n
     * @param k
     * @return
     */
    public String solution(int n, int k){

        // 求每个下标对应的阶乘数
        int[] factorial = getFactorial(n);

        StringBuilder builder = new StringBuilder();
        boolean[] visited = new boolean[n+1];
        dfs(n, k, 0, visited, factorial, builder);

        return builder.toString();
    }


    private void dfs(int n, int k, int index, boolean[] visited, int[] factorial,StringBuilder builder){

        if (index == n){
            return;
        }

        //计算未选中数的阶乘数
        int cnt = factorial[n - index - 1];
        for (int i = 1; i <= n; i++){
            if (visited[i]){
                continue;
            }
            if (k > cnt){
                k -= cnt;
                continue;
            }
            builder.append(i);
            visited[i] = true;
            dfs(n, k, index + 1, visited, factorial, builder);
            //1.不可以回溯,算法设计是一下来到叶子节点,没有回头的过程
            //2.这边要return，后面的数没必要去尝试
            return;
        }

    }

    private int[] getFactorial(int n){

        int[] factorial = new int[n+1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++){
            factorial[i] = factorial[i-1] * i;
        }

        return factorial;
    }


    /**
     * 不用回溯的版本
     * @param n
     * @param k
     * @return
     */
    public String solutionV2(int n, int k){

        int[] factorial = getFactorial(n);
        boolean[] visited = new boolean[n+1];
        Arrays.fill(visited, false);
        StringBuilder builder = new StringBuilder();
        for (int i = n - 1; i >= 0; i--){
            int cnt = factorial[i];
            for (int j = 1; j <= n; j++){
                if (visited[j]){
                    continue;
                }
                if (k > cnt){
                    k -= cnt;
                    continue;
                }
                visited[j] = true;
                builder.append(j);
                break;
            }
        }

        return builder.toString();
    }

    public static void main(String[] args) {

        GetPermutation getPermutation = new GetPermutation();
        int n = 5;
        int k = 30;
        String ans = getPermutation.solutionV2(n, k);
        System.out.println(ans);

    }
}
