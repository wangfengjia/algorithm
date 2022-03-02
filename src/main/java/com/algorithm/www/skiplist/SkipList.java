package com.algorithm.www.skiplist;

import java.util.Random;

/**
 * 跳表，给单向链表构建多级索引，查找，插入，删除的时间复杂度都是(O(log^n))
 *
 * @author wangyongchun
 * @date 2019/06/29 11:11
 */
public class SkipList {

    private static final int MAX_LEVEL = 16;

    private int levelCount = 1;

    private Node head = new Node(); //带头链表

    private Random random = new Random();


    /**
     * 插入跳表中
     * @param value
     */
    public void insert(int value){
        int level = randomLevel();
        Node newNode = new Node();
        newNode.data = value;
        newNode.maxLevel = level;
        Node update[] = new Node[level];
        for (int i = 0; i < level; ++i){
            update[i] = head;
        }

        Node p = head;
        for (int i = level - 1; i >= 0; --i){
            while (p.forward[i] != null && p.forward[i].data < value){
                p = p.forward[i];
            }

            update[i] = p; //每一层插入的节点的前一个节点
        }

        //将节点插入到链表中
        for (int i = 0; i < level; ++i){
            newNode.forward[i] = update[i].forward[i];
            update[i].forward[i] = newNode;
        }

        //更新跳跃表的高度
        if (levelCount < level){
            levelCount = level;
        }
    }

    /**
     * 查找等于给定值的节点
     * @param value
     * @return
     */
    public Node find(int value){

        Node p = head;
        for (int i = levelCount - 1; i >= 0; --i){
            while (p.forward[i] != null && p.forward[i].data < value){
                p = p.forward[i];
            }
        }

        if (p.forward[0] != null && p.forward[0].data == value){
            return p.forward[0];
        }else {
            return null;
        }
    }
    //随机level次，来确定将插入值加到哪些索引层，如果是奇数层则加1，防止伪随机
    private int randomLevel(){

        int level = 1;
        for (int i = 1; i < MAX_LEVEL; ++i){
            if (random.nextInt() % 2 != 0){
                ++level;
            }
        }

        return level;
    }

    public void printAll(){
        Node p = head.forward[0];
        while (p != null){
            System.out.println(p.data);
            p = p.forward[0];
        }
        System.out.println();
    }
    /**
     * 删除给定值的节点
     * @param value
     */
    public void delete(int value){

        Node delete[] = new Node[levelCount];
        Node p = head;
        for (int i = levelCount - 1; levelCount >= 0; --i){
            while (p.forward[i] != null && p.forward[i].data < value){
                p = p.forward[i];
            }

            delete[i] = p;
        }

        //判断是否等于给定值
        if (p.forward[0] != null && p.forward[0].data == value){
            for (int i = levelCount - 1; i >= 0; --i){
                if (delete[i].forward[i] != null && delete[i].forward[i].data == value){
                    delete[i].forward[i] = delete[i].forward[i].forward[i];
                }
            }
        }

    }


    public class Node{

        private int data = -1;
        private Node forward[] = new Node[MAX_LEVEL];
        private int maxLevel = 0;

        @Override
        public String toString() {

            StringBuilder builder = new StringBuilder();
            builder.append("{ data: }");
            builder.append(data);
            builder.append("; levels: ");
            builder.append(maxLevel);
            builder.append(" }");

            return builder.toString();
        }
    }

    public static void main(String[] args){

        SkipList skipList = new SkipList();
        skipList.insert(2);
        skipList.insert(12);
        skipList.insert(8);
        skipList.insert(121);
        skipList.insert(23);
        skipList.insert(83);
//        skipList.delete(83);

        skipList.printAll();
//        Node node = skipList.find(23);
//        System.out.println(node.forward[0].forward[0].data);
    }
}
