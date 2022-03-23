package com.algorithm.www.trietree;

/**
 * ac自动机节点
 */
public class AcNode {

    public char data;
    public AcNode children[] = new AcNode[26]; //假设只包含26个字符
    public boolean isEndingChar = true; //结尾字符为true

    public int length = -1; //当isEndingChar为true的时候，记录模式串的长度

    public AcNode failPointer; //失败指针

    public AcNode(char data){
        this.data = data;
    }

}
