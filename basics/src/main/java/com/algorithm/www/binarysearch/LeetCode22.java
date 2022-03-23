package com.algorithm.www.binarysearch;

/**
 * sqrt(2)约等于1.414，求sqrt(2)精确到小数点后10位
 */
public class LeetCode22 {


    private static final double EPSILON = 0.0000000001;

    public double sqrt2(){

        double low = 1.4;
        double high = 1.5;

        double middle = (low + high) / 2;
        while (high - low > EPSILON){
            if (middle * middle > 2){
                high = middle;
            }else {
                low = middle;
            }
            middle = (low + high) / 2;
        }

        return middle;
    }

    public static void main(String[] args){

        LeetCode22 leetCode22 = new LeetCode22();
        double v = leetCode22.sqrt2();
        System.out.println(v);
    }

}
