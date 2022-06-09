package com.leetcode.www.middle.matrix;


/**
 * leetcode-1219:黄金矿工
 * 你要开发一座金矿，地质勘测学家已经探明了这座金矿中的资源分布，并用大小为m * n 的网格 grid 进行了标注。每个单元格中的整数就表示这一单元格中的黄金数量；如果该单元格是空的，那么就是 0。
 * 为了使收益最大化，矿工需要按以下规则来开采黄金：
     * 每当矿工进入一个单元，就会收集该单元格中的所有黄金。
     * 矿工每次可以从当前位置向上下左右四个方向走。
     * 每个单元格只能被开采（进入）一次。
     * 不得开采（进入）黄金数目为 0 的单元格。
     * 矿工可以从网格中 任意一个 有黄金的单元格出发或者是停止。
 */
public class GetMaximumGold {

    private int ans = 0;
    /**
     * 回溯
     * 以每个格子内的数字大于0的格子作为起点进行深度优先遍历+回溯，枚举所有的可行路径，找到最大收益
     * @param grid
     * @return
     */
    public int solution(int[][] grid){

        int rowNums = grid.length;
        int colNums = grid[0].length;
        for (int row = 0; row < rowNums; row++){
            for (int col = 0; col < colNums; col++){
                if (grid[row][col] != 0){
                    dfs(grid, row, col, rowNums, colNums, 0);
                }
            }
        }

        return ans;
    }


    private void dfs(int[][] grid, int row, int col, int rowNums, int colNums, int gold){

        gold += grid[row][col];
        ans = Math.max(ans, gold);

        //由于题目规定每个单元格只能被开采一次，因此需要将被开采过的单元格进行标记，这边的处理方法是用一个变量保存当前单元格的值，然后将这个单元格的值设置为0,
        //在进行回溯之前，将这个单元格的值恢复
        int cur = grid[row][col];
        grid[row][col] = 0;

        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
        for (int i = 0; i < 4; i++){
            int newX = row + dirs[i][0];
            int newY = col + dirs[i][1];
            if (newX >= 0 && newX < rowNums && newY >= 0 && newY < colNums && grid[newX][newY] > 0){
                dfs(grid, newX, newY, rowNums, colNums, gold);
            }
        }

        //在回溯之前，将这个单元格的值恢复
        grid[row][col] = cur;
    }

    public static void main(String[] args) {

        GetMaximumGold gold = new GetMaximumGold();
        int[][] grid = {
                {1,0,7},
                {2,0,6},
                {3,4,5},
                {0,3,0},
                {9,0,20}
        };

        int ans = gold.solution(grid);
        System.out.println(ans);
    }
}
