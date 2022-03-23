package com.algorithm.www.advanced;

/**
 * B+树
 * 组织B+树的时候，让一个节点的大小会等于一个页的大小，如果要读取的数据超过一个页的大小，则会触发多次磁盘IO
 */
public class BPlusTree {


    /**
     * 假如keywords是{1,3,5,7}
     * 4个键值将数据分为5个区间[-INF, 1),[1,3),[3,5),[5,7),[7,INF]
     * 5个区间分别对应children[0]...children[4]
     */
    private class BPlusTreeNode{

        public int m = 5; //5叉树
        public int[] keywords = new int[m - 1]; //键值，用来划分数据区间
        public BPlusTree[] children = new BPlusTree[m];
    }


    /**
     * B+树的叶子节点和内部节点不一样
     * 叶子节点存储的是数据值，而不是区间
     * 这个定义里面，每个叶子节点存储三个数据行的键值和地址信息
     */
    private class BPlusTreeLeafNode{

        public int k = 3;
        public int[] keywords = new int[k];//数据的键值
        public long[] address = new long[k]; //数据的地址

        public BPlusTreeLeafNode pre; //这个节点在链表中的前驱节点
        public BPlusTreeLeafNode next; //这个节点在链表中的后置节点
    }
}
