package com.leetcode.www.middle.matrix;

import java.util.Arrays;

/**
 * leetcode-130:被围绕的区域
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 */
public class EmbranceArea {

    /**
     * 由于任何边界上的'o'都不会被填充为'x'，因此所有不被包围的'o'都直接或者间接与边界上的'o'相连，我们可以利用这个性质来判断'o'是否在边界，具体步骤如下
     *      1. 对于每一个边界上的'o'，我们以它为起点，标记所有与它直接或者间接相连的字母o，使用字母'A'来标记
     *      2. 遍历这个矩阵，对于每一个字母
     *          1. 如果这个字母被标记过，则这个字母为没有被字母'x'包围的字母'o',将其还原为'o'
     *          2. 如果这个字母没有被标记过,则这个字母为被字母'x'包围的字母'o',将其修改为'x'
     * 复杂度分析
     *  时间复杂度:O(m*n),m和n分别表示矩阵的行数和列数。在深度优先搜索过程中，每个点至多被标记过一次
     *  空间复杂度:O(m*n),m和n分别表示矩阵的行数和列数，主要是深度优先搜索的栈的开销
     * @param board
     */
    public void solution(char[][] board){

        int rowNums = board.length;
        if (rowNums == 0){
            return;
        }

        int colNums = board[0].length;
        //以边界上的'o'为起点进行标记
        //第一列和最后一列
        for (int row = 0; row < rowNums; row++){
            //第一列和最后一列
            dfs(board, row, 0, rowNums, colNums);
            dfs(board, row, colNums - 1, rowNums, colNums);
        }
        //第一行和最后一行
        for (int col = 0; col < colNums; col++){
            dfs(board, 0, col, rowNums, colNums);
            dfs(board, rowNums - 1, col, rowNums, colNums);
        }

        for (int row = 0; row < rowNums; row++){
            for (int col = 0; col < colNums; col++){
                //被标记，所以是没有被字母'x'包围的'o',还原为'0'
                if (board[row][col] == 'A'){
                    board[row][col] = 'o';
                }else if (board[row][col] == 'o'){ //没有被标记的'o',会被字母'x'包围,置为'x'
                    board[row][col] = 'x';
                }
            }
        }

    }


    private void dfs(char[][] board, int row, int col, int rolNums, int colNums){

        if (row < 0 || row >= rolNums || col < 0 || col >= colNums || board[row][col] != 'o'){
            return;
        }

        board[row][col] = 'A';
        dfs(board, row - 1, col, rolNums, colNums);
        dfs(board, row + 1, col, rolNums, colNums);
        dfs(board, row, col - 1, rolNums, colNums);
        dfs(board, row, col + 1, rolNums, colNums);
    }

    public static void main(String[] args) {

        EmbranceArea embranceArea = new EmbranceArea();
        char[][] board = {
                {'x', 'x', 'x', 'x'},
                {'x', 'o', 'o', 'x'},
                {'x', 'x', 'o', 'x'},
                {'x', 'o', 'x', 'x'}
        };

        embranceArea.solution(board);
        for (char[] items : board){
            System.out.println(Arrays.toString(items));
        }
    }
}
