package com.algorithm.www.recursion;

public class RecursionExample {

    /**
     * n个台阶的走法，一次只能上一个或者两个台阶
     * 把这个问题分解为
     *      1.第一步上一个台阶的时候剩下的台阶的走法
     *      2.第一步上两个台阶的时候剩下的台阶的走法
     * @param step
     * @return
     */
    public static int walkStep(int step){
        if (step == 1){
            return 1;
        }
        if (step == 2){
            return 2;
        }

        return walkStep(step - 1) + walkStep(step - 2);
    }

    public static void main(String[] args){
        int walkStep = walkStep(10);
        System.out.println(walkStep);
    }
}
