package com.leetcode.www.middle.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * leetcode-1104:二叉树寻路
 * 在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 逐行 依次按“之” 字形进行标记。
 * 如下图所示，在奇数行（即，第一行、第三行、第五行……）中，按从左到右的顺序进行标记；
 * 而偶数行（即，第二行、第四行、第六行……）中，按从右到左的顺序进行标记。
 * 给你树上某一个节点的标号 label，请你返回从根节点到该标号为 label 节点的路径，该路径是由途经的节点标号所组成的。
 */
public class PathInZigZagTree {

    /**
     * 模拟:由于从根节点到任意一层都是满二叉树，我们可以先确定label所在的层级，就可以计算出当前层起始节点值和结束节点值。再利用每层节点数量翻倍和隔层奇偶
     *     性翻转，就可以寻找出上一层的节点的下标(每层下标从左向右计算，并从1开始)，直到构造出答案(到根节点)
     * 复杂度分析
     *  时间复杂度:O(N),N是节点数目,最坏情况下每个节点会被遍历一次
     *  空间复杂度:O(1)
     * @param n
     * @return
     */
    public List<Integer> solution(int n){

        //计算n所在的层级
        int level = 1;
        while (getEnd(level) < n){
            ++level;
        }

        int[] ans = new int[level];
        int index = level - 1;
        int cur = n;
        while (index >= 0){
            ans[index--] = cur;
            int tot = (int) Math.pow(2, level - 1);
            int start = getStart(level);
            int end = getEnd(level);
            if (level % 2 == 0){
                //当前层为偶数层，则数值从右往左递增，那么计算上一层的下标也应该从右往左
                int j = tot / 2;
                for (int i = start; i <= end; i += 2, j--){
                    if ((i == cur) || (i + 1) == cur){
                        break;
                    }
                }
                int prevStart = getStart(level - 1);
                while (j-- > 1){
                    prevStart++;
                }
                cur = prevStart;
            }else {
                //当前层为奇数层，数值从左右递增，则计算上一层的下标也应该从左往右
                int j = 1;
                for (int i = start; i <= end; i += 2, j++){
                    if ((i == cur) || (i+1) == cur){
                        break;
                    }
                }
                int preEnd = getEnd(level - 1);
                while (j-- > 1){
                    preEnd--;
                }
                cur = preEnd;
            }
            --level;
        }

        List<Integer> ansList = new ArrayList<>();
        for (int num : ans){
            ansList.add(num);
        }
        return ansList;
    }

    //第level层的起始节点
    private int getStart(int level){
        return (int) Math.pow(2, level - 1);
    }
    //第level层的结束节点
    private int getEnd(int level){
        int start = getStart(level);
        return start + start - 1;
    }

    public static void main(String[] args) {

        PathInZigZagTree zagTree = new PathInZigZagTree();
        int n = 14;
        List<Integer> ans = zagTree.solution(n);
        System.out.println(ans);
    }

}
