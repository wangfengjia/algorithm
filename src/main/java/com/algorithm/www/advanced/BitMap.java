package com.algorithm.www.advanced;

/**
 * 位图
 *
 * @author wangyongchun
 * @date 2019/07/25
 */
public class BitMap {

    //java中char类型占16bit，也就是2个字节
    private char[] bytes;
    private int nbits;

    public BitMap(int nbits){
        this.nbits = nbits;
        this.bytes = new char[nbits / 16 + 1];
    }

    public void set(int k){
        if (k > nbits){
            return;
        }
        //求得bytes数组里面的索引下标
        int byteIndex = k / 16;
        //求得单个char字符里面的位索引下标
        int bitIndex = k % 16;
        bytes[byteIndex] |= (1 << bitIndex);
    }

    public boolean get(int k){
        if (k > nbits){
            return false;
        }

        int byteIndex = k / 16;
        int bitIndex = k % 16;
        return (bytes[byteIndex] & (1 << bitIndex)) != 0;
    }


    public static void main(String[] args){

        int nbits = 100000;
        BitMap bitMap = new BitMap(nbits);
        bitMap.set(13);
        boolean b = bitMap.get(14);
        System.out.println("13是否存在_" + b);
    }
}
