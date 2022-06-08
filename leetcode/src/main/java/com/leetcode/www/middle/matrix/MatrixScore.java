package com.leetcode.www.middle.matrix;


/**
 * leetcode-861:翻转矩阵后的得分
 * 有一个二维矩阵A 其中每个元素的值为0或1。
 * 移动是指选择任一行或列，并转换该行或列中的每一个值：将所有 0 都更改为 1，将所有 1 都更改为 0。
 * 在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的得分就是这些数字的总和。
 * 返回尽可能高的分数。
 */
public class MatrixScore {


    /**
     * 贪心
     * 根据贪心思想，为了得到最高的行数，每一行的最左边的数都必须是1，为了做到这一点，我们可以翻转那些最左边的数不为1的行,而其他行保持不变
     * 当每一行的最左边的数都翻转为1之后，就只能进行列的翻转，为了使总得分更多，应该使每一列中1的数目尽可能多。因此我们遍历除了最左边的列以外的其他列，
     * 如果这一列0的数量比1的数量多就翻转这一列
     *
     * 复杂度分析
     *  时间复杂度:O(m*n),m和n分别表示矩阵的行数和列数
     *  空间复杂度:O(1)
     * @param grid
     * @return
     */
    public int solution(int[][] grid){

        int rowNums = grid.length;
        int colNums = grid[0].length;

        int ans = rowNums * (1 << (colNums - 1));
        for (int col = 1; col < colNums; col++){
            int oneNums = 0;
            for (int row = 0; row < rowNums; row++){
                if (grid[row][0] == 1){
                    oneNums += grid[row][col];
                }else { // 不等于1就这一行一开始的时候进行了翻转，为了实现最左列都是1，因此这个元素的实际取值是1 - grid[row][col]
                    oneNums += 1 - grid[row][col];
                }
            }

            int k = Math.max(oneNums, rowNums - oneNums);
            ans += k * (1 << (colNums - col - 1));
        }

        return ans;
    }

    public static void main(String[] args) {

        MatrixScore score = new MatrixScore();
        int[][] grid = {
                {0,0,1,1},
                {1,0,1,0},
                {1,1,0,0}
        };

        int ans = score.solution(grid);
        System.out.println(ans);
    }
}
