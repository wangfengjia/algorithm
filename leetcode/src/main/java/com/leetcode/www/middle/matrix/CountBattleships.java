package com.leetcode.www.middle.matrix;

/**
 * leetcode-419:甲板上的战舰
 * 给你一个大小为 m x n 的矩阵 board 表示甲板，其中，每个单元格可以是一艘战舰 'X' 或者是一个空位 '.' ，返回在甲板 board 上放置的 战舰 的数量。
 * 战舰 只能水平或者垂直放置在 board 上。换句话说，战舰只能按 1 x k（1 行，k 列）或 k x 1（k 行，1 列）的形状建造，其中 k 可以是任意大小。两艘战舰之间
 * 至少有一个水平或垂直的空位分隔 （即没有相邻的战舰）
 */
public class CountBattleships {


    /**
     * 枚举起点
     * 由于题目要求战舰之间至少有一个水平或者垂直的空位分隔，任意两个战舰是不相邻的，因此可以通过枚举每个战舰的左上顶点，只有这个战舰的左侧和上方为空位时，
     * 这个战舰就统计到战舰个数中，这样可以实现一次遍历和O(1)的空间复杂度
     *
     * 复杂度分析
     *  时间复杂度:O(m*n),m和n分别是矩阵的行数和列数，只需要遍历矩阵中的每一个位置一次
     *  空间复杂度:O(1)
     * @param board
     * @return
     */
    public int solution(char[][] board){

        int rowNums = board.length;
        int colNums = board[0].length;
        int ans = 0;

        for (int row = 0; row < rowNums; row++){
            for (int col = 0; col < colNums; col++){
                if (board[row][col] == 'X'){
                    if (row > 0 && board[row-1][col] != '.'){
                        continue;
                    }
                    if (col > 0 && board[row][col-1] != '.'){
                        continue;
                    }
                    ++ans;
                }
            }
        }

        return ans;
    }


    public static void main(String[] args) {

        CountBattleships battleships = new CountBattleships();
        char[][] board  ={
                {'X','.','.','X'},
                {'.','.','.','X'},
                {'.','.','.','X'}
        };
        int ans = battleships.solution(board);
        System.out.println(ans);
    }
}
