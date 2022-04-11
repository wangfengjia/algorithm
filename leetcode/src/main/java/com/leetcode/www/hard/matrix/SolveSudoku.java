package com.leetcode.www.hard.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode-37:解数独
 * 编写一个程序，通过填充空格来解决数独问题。
 * 数独的解法需 遵循如下规则：
     * 数字1-9在每一行只能出现一次。
     * 数字1-9在每一列只能出现一次。
     * 数字1-9在每一个以粗实线分隔的3x3宫内只能出现一次。（请参考示例图）
     * 数独部分空格内已填入了数字，空白格用'.'表示。
 *
 */
public class SolveSudoku {

    private boolean[][] rowArr = new boolean[9][9];
    private boolean[][] colArr = new boolean[9][9];
    private boolean[][][] block = new boolean[3][3][9];
    private boolean valid = false;
    private List<int[]> spaces = new ArrayList<>();

    /**
     * 回溯:创建2个二维数组,rowArr和colArr，分别用来记录1~9在每一行和没一列是否存在，创建一个3维数组block,记录1~9在每一个九宫格中是否存在。先对数独进行遍历，
     *     对于遍历到的第i行第j列的位置，如果这个位置是空白格，则将其加入到一个存储空白格位置的列表中，便于后续的回溯处理;如果是一个数字x,则将rows[i][x-1],
     *     colArr[j][x-1],block[i/3][j/3][x-1]为true。
     *     结束遍历以后，就开始进行递归枚举，当递归到第i行第j列的位置时,我们枚举填入的数字x，数字x需要在rowArr[i][x-1],colArr[j][x-1],block[i/3][j/3][x-1]
     *     均为false的情况下，数字x才是满足条件的
     * 复杂度分析
     *  时间复杂度:O(9^81)
     * @param boards
     */
    public void solution(char[][] boards){

        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (boards[i][j] == '.'){
                    spaces.add(new int[]{i,j});
                }else {
                    int digit = boards[i][j] - '0' - 1;
                    rowArr[i][digit] = true;
                    colArr[j][digit] = true;
                    block[i/3][j/3][digit] = true;
                }
            }
        }

        dfs(boards, 0);
    }

    private void dfs(char[][] boards, int pos){

        if (pos == spaces.size()){
            valid = true;
            return;
        }

        int[] location = spaces.get(pos);
        int i = location[0];
        int j = location[1];
        for (int digit = 0; digit < 9 && !valid; digit++){
            if (!rowArr[i][digit] && !colArr[j][digit] && !block[i/3][j/3][digit]){
                //作出选择
                rowArr[i][digit] = colArr[j][digit] = block[i/3][j/3][digit] = true;
                boards[i][j] = (char) (digit + '0' + 1);
                dfs(boards, pos+1);
                //撤销选择
                rowArr[i][digit] = colArr[j][digit] = block[i/3][j/3][digit] = false;
            }
        }
    }
}
