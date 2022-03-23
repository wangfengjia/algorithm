package com.leetcode.www.middle.array;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * 思路(回溯):这个问题可以看作有 nn 个排列成一行的空格，我们需要从左往右依此填入题目给定的 nn 个数，每个数只能使用一次。那么很直接的可以想到一种穷举的算法，
 *           即从左往右每一个位置都依此尝试填入一个数，看能不能填完这 nn 个空格，在程序中我们可以用「回溯法」来模拟这个过程。
 *           我们定义递归函数 backtrack(first, output) 表示从左往右填到第first 个位置，当前排列为output。 那么整个递归函数分为两个情况：
 *           1. 如果first==n，说明我们已经填完了 nn 个位置（注意下标从 00 开始），找到了一个可行的解，我们将output 放入答案数组中，递归结束。
 *           2. first<n，我们要考虑这第first 个位置我们要填哪个数。根据题目要求我们肯定不能填已经填过的数，因此很容易想到的一个处理手段是我们定义
 *              一个标记数组vis[] 来标记已经填过的数，那么在填第first 个数的时候我们遍历题目给定的 nn 个数，如果这个数没有被标记过，我们就尝试填入，
 *              并将其标记，继续尝试填下一个位置，即调用函数 backtrack(first + 1, output)。回溯的时候要撤销这一个位置填的数以及标记，并继续尝试其
 *              他没被标记过的数。
 *
 */
public class permute {


    public List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> solution(int[] arr){

        List<Integer> output = new ArrayList<>();
        for (int i = 0; i < arr.length; i++){
            output.add(arr[i]);
        }

        backTrack(output, 0, arr.length);
        return ans;
    }

    public void backTrack(List<Integer> output, int first, int n){

        //所有数都填完了
        if (first == n){
            this.ans.add(new ArrayList<>(output));
        }

        for (int i = first; i < n; i++){
            //动态维护数组
            Collections.swap(output, first, i);
            backTrack(output, first + 1, n);
            //撤销操作
            Collections.swap(output, first, i);
        }
    }

    public List<List<Integer>> solutionV2(int[] arr){

        List<Integer> output = new ArrayList<>();
        int len = arr.length;
        boolean[] used = new boolean[len];
        backtrackV2(output, 0, len, arr, used);
        return ans;
    }

    public void backtrackV2(List<Integer> output, int index, int length, int[] arr, boolean[] used){

        if (index == length){
            this.ans.add(new ArrayList<>(output));
        }

        for (int i = 0; i < length; i++){
            //在结果里面一个元素不能重复出现
            if (!used[i]){
                output.add(arr[i]);
                used[i] = true;
                backtrackV2(output, index + 1, length, arr, used);
                //撤销上次的选择
                used[i] = false;
                output.remove(output.size() - 1);
            }
        }
    }

    public static void main(String[] args) {

        int[] arr = new int[]{1,2,3};
        permute permute = new permute();
        List<List<Integer>> solution = permute.solutionV2(arr);
        System.out.println(solution);
    }
}
