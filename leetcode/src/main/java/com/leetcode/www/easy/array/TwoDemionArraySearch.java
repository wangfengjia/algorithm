package com.leetcode.www.easy.array;

/**
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，
 * 输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * 思路
 * 1.左下角元素为所在行的最小值，所在列的最大值
 * 2.当目标元素小于二维数组左下角元素时，则目标元素不会在左下角元素所在的行，则可以消除掉左下角元素所在的那一行
 * 3.当目标元素大于二维数组左下角元素时，则目标元素不会再左下角元素所在的列，则可以消除掉左下角元素所在的那一列
 * */
public class TwoDemionArraySearch {


    public boolean twoDemionArraySearch(int[][] matrix, int target){

        int row = matrix.length - 1;
        int column = 0;

        while (row >= 0 && column <= matrix[row].length - 1){

            if (target < matrix[row][column]){
                row--;
            }else if (target > matrix[row][column]){
                column++;
            }else {
                return true;
            }
        }

        return false;
    }


    public static void main(String[] args) {

        TwoDemionArraySearch arraySearch = new TwoDemionArraySearch();
        int[][] matrix= {{1,3, 5, 7}, {2, 4, 6, 8}, {3, 6, 9, 11}};
        int target = 10;
        boolean search = arraySearch.twoDemionArraySearch(matrix, target);
        System.out.println(search);
    }
}
