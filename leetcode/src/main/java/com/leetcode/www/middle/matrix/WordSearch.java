package com.leetcode.www.middle.matrix;

/**
 * leetcode-79:单词搜索
 * 给定一个m x n 二维字符网格board 和一个字符串单词word 。如果word 存在于网格中，返回 true；否则，返回 false。单词必须按照字母顺序，通过相邻的单元格内的
 * 字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 */
public class WordSearch {

    /**
     * 回溯
     * 利用一个函数check(i,j,k)来判断从网格(i,j)位置出发，能否找到单词word[k...]，其中word[k..]表示字符串word从第k个字符开始的后缀子串。如果能搜索到就返回true,
     * 反之则返回false，这个函数的执行步骤如下
     *      1. 如果board[i][j] != word[k],当前字符不匹配，直接返回false
     *      2. 如果当前已经访问到字符串的末尾，且对应字符依然匹配，此时就直接返回true，就是找到了目标字符串
     *      3. 否则,遍历当前位置的所有相邻位置，如果从某个相邻位置出发，能够搜索到子串word[k+1..],则返回true，返回false
     * 我们对每一位置(i,j)都调用函数check，只要有一处返回true，就说明网格能找到对应的单词，否则就说明找不到。同时，为了防止重复遍历相同的位置，需要维护一个
     * visited数组,用于标识每个位置是否被访问过。遍历相邻位置时，跳过已经访问过的位置
     * 复杂度分析
     *  1. 时间复杂度：一个非常宽松的上界为O(MN⋅3L)，其中 M, NM,N 为网格的长度与宽度，LL 为字符串word的长度
     *  2. 空间复杂度:O(MN)，visited数组所消耗的空间
     *
     * @param board
     * @param word
     * @return
     */
    public boolean solution(char[][] board, String word){

        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visted = new boolean[rows][cols];
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                boolean flag = check(board, visted, i, j, word, 0);
                if (flag){
                    return true;
                }
            }
        }

        return false;
    }


    public boolean check(char[][] board, boolean[][] visited, int i, int j, String word, int k){
        if (board[i][j] != word.charAt(k)){
            return false;
        }else if (k == word.length() - 1){ // 找到了目标字符串
            return true;
        }


        // 选择borad[i][j]
        visited[i][j] = true;
        int[][] directions = {{0,1}, {0,-1}, {1,0},{-1,0}};
        boolean result = false;
        for (int[] dir : directions){
            int newi = i + dir[0];
            int newj = j + dir[1];
            if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length){
                boolean flag = check(board, visited, newi, newj, word, k + 1);
                if (flag){
                    result = true;
                    break;
                }
            }
        }
        //撤销选择
        visited[i][j] = false;
        return result;
    }


    public static void main(String[] args) {

        char[][] boards = {{'A', 'B', 'C', 'E'}, {'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCCB";
        WordSearch wordSearch = new WordSearch();
        boolean ans = wordSearch.solution(boards, word);
        System.out.println(ans);
    }
}
