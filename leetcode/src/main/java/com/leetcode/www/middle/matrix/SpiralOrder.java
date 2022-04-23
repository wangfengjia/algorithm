package com.leetcode.www.middle.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode-54:螺旋矩阵
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 */
public class SpiralOrder {


    /**
     * 按层模拟:将矩阵看成若干层，从外到里，依次输出每一层的元素。对于每一层，从左上方开始以顺时针遍历所有元素。假设当前层的左上角位于(top,left),右下角位于
     *         (bottom,right),步骤如下
     *         1. 从左到右遍历上侧元素，依次为(top,left)到(top,right)
     *         2. 从上到下遍历右侧元素，依次为(top+1,right)到(bottom,right)
     *         3. left<right和top<bottom，则分两种方式处理:从右向左遍历下侧元素，依次为(bottom,right-1)到(bottom,left+1);从下向上遍历左侧元素,依次
     *            (bottom,left)到(top+1,left)
     *         4.当遍历完当前层元素后，就将top和left加1，bottom和right减去一，进入一下层继续遍历
     *
     * 复杂度分析
     *  时间复杂度:O(mn),m,n分别是矩阵的行数和列数，矩阵中的每个元素被访问一次
     *  空间复杂度:O(1)
     * @param matrix
     * @return
     */
    public List<Integer> solution(int[][] matrix){

        List<Integer> ans = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return ans;
        }

        int rowTop = 0;
        int colLeft = 0;
        int rowBottom = matrix.length - 1;
        int colRight = matrix[0].length - 1;

        while (rowTop <= rowBottom && colLeft <= colRight){

            for (int col = colLeft; col <= colRight; col++){
                ans.add(matrix[rowTop][col]);
            }
            for (int row = rowTop + 1; row <= rowBottom; row++){
                ans.add(matrix[row][colRight]);
            }
            if (colLeft < colRight && rowTop < rowBottom){

                for (int col = colRight - 1; col >= colLeft + 1; --col){
                    ans.add(matrix[rowBottom][col]);
                }
                for (int row = rowBottom; row >= rowTop+1; --row){
                    ans.add(matrix[row][colLeft]);
                }
            }

            ++rowTop;
            ++colLeft;
            --rowBottom;
            --colRight;
        }

        return ans;
    }

    public static void main(String[] args) {

        SpiralOrder spiralOrder = new SpiralOrder();
        int[][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };

        List<Integer> ans = spiralOrder.solution(matrix);
        System.out.println(ans);
    }

}
