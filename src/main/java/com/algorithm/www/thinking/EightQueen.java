package com.algorithm.www.thinking;

/**
 * 回溯算法之八皇后问题
 */
public class EightQueen {

    private int[] result = new int[8];

    /**
     * 行数
     * @param row
     */
    public void callEightQueen(int row){
        if (row == 8){
            printQueen();
            return;
        }

        for (int column = 0; column < 8; column++){
            if (isOk(row, column)){
                result[row] = column;
                callEightQueen(row + 1); //放置下一行
            }
        }

    }

    private boolean isOk(int row, int column){

        int leftUp = column - 1;
        int rightUp = column + 1;

        for (int i = row - 1; i >= 0; i--){ //依次遍历要放的行的下面几行

            //判断下面几行的对应的列有没有元素
            if (result[i] == column){
                return false;
            }

            //判断左下对角线
            if (leftUp >= 0){
                if (result[i] == leftUp){
                    return false;
                }
            }

            //判断右下对角线
            if (rightUp < 8){
                if (result[i] == rightUp){
                    return false;
                }
            }

            --leftUp;
            ++rightUp;
        }

        return true;
    }

    private void printQueen(){

        for (int row = 0; row < 8; row++){

            for (int column = 0; column < 8; column++){
                if (result[row] == column){
                    System.out.print("Q ");
                }else {
                    System.out.print("* ");
                }
            }

            System.out.println();
        }

        System.out.println();
    }


    public static void main(String[] args){

        EightQueen eightQueen = new EightQueen();
        int row = 0;
        eightQueen.callEightQueen(row);
    }
}
