package com.algorithm.www.heap;

/**
 * 待合并的数组对象
 *
 * @author wangyongchun
 * @date 2019/07/07 19:55
 */
public class MergeArray {

    private int[] first;
    private int firstArrayMaxPos;

    private int[] second;
    private int secondArrayMaxPos;

    private int[] third;
    private int thirdArrayMaxPos;

    private int[] des;

    private int elementNums;

    public MergeArray(int[] first, int[] second, int[] third){
        this.first = first;
        this.firstArrayMaxPos = first.length - 1;
        this.second = second;
        this.secondArrayMaxPos = second.length - 1;
        this.third = third;
        this.thirdArrayMaxPos = third.length - 1;
        this.elementNums = first.length + second.length + third.length;
        des = new int[elementNums];
    }


    public int[] getFirst() {
        return first;
    }

    public void setFirst(int[] first) {
        this.first = first;
    }

    public int[] getSecond() {
        return second;
    }

    public void setSecond(int[] second) {
        this.second = second;
    }

    public int[] getThird() {
        return third;
    }

    public void setThird(int[] third) {
        this.third = third;
    }

    public int[] getDes() {
        return des;
    }

    public void setDes(int[] des) {
        this.des = des;
    }

    public int getElementNums() {
        return elementNums;
    }

    public void setElementNums(int elementNums) {
        this.elementNums = elementNums;
    }

    public int getFirstArrayMaxPos() {
        return firstArrayMaxPos;
    }

    public void setFirstArrayMaxPos(int firstArrayMaxPos) {
        this.firstArrayMaxPos = firstArrayMaxPos;
    }

    public int getSecondArrayMaxPos() {
        return secondArrayMaxPos;
    }

    public void setSecondArrayMaxPos(int secondArrayMaxPos) {
        this.secondArrayMaxPos = secondArrayMaxPos;
    }

    public int getThirdArrayMaxPos() {
        return thirdArrayMaxPos;
    }

    public void setThirdArrayMaxPos(int thirdArrayMaxPos) {
        this.thirdArrayMaxPos = thirdArrayMaxPos;
    }
}
