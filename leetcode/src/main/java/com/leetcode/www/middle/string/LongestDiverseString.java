package com.leetcode.www.middle.string;


import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * leetcode-1405:最长快乐字符串
 * 如果字符串中不含有任何 'aaa'，'bbb' 或 'ccc' 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。
 * 给你三个整数 a，b ，c，请你返回 任意一个 满足下列全部条件的字符串 s：
     * s 是一个尽可能长的快乐字符串。
     * s 中 最多 有a 个字母 'a'、b个字母 'b'、c 个字母 'c' 。
     * s 中只含有 'a'、'b' 、'c' 三种字母。
 * 如果不存在这样的字符串 s ，请返回一个空字符串 ""。
 */
public class LongestDiverseString {


    /**
     * 贪心
     * 根据贪心思想，每次取当前剩余次数最多的字符来构造目标字符串。可以使用优先队列来实现，以(字符编号,字符剩余数量)的二元组形式进行存储,构造以字符剩余数量倒序
     * 的大根堆。每次取出堆顶元素(也就是剩余数量最多的字符)，以如下方式构造字符串
     *      1. 不违反连续三个字符相同，则将这个字符添加到当前答案的尾部，并且将这个字符的剩余次数减一，减完后剩余次数不为0，则将这个字符再次入堆
     *      2. 违反连续三个字符相同，说明当前这个字符不满足条件，无法添加到当前答案的尾部。则再次尝试从堆中取出剩余次数次大的字符(如果当前堆为空，说明没有任何合法
     *         字符能够追加，直接break)，若次大字符追加后还有字符剩余，则更新字符剩余数量重新入堆，并且将此前取出的最大字符也重新入堆
     * 贪心的正确性:当a=b=c!=0时能够确保所有字符轮流参与创建，得到长度最大的快乐字符串。而贪心策略(每次尽可能进行大数消除)可以确保能够尽可能凑成a=b=c的局面，
     * 并且凑成这个局面过程中不会从有解变为无解
     *
     * 复杂度分析
     *  时间复杂度:O((a+b+c)log^C),其中C等于3，表示堆中最多存储的元素数量，时间复杂度主要是元素出堆和入堆的时间复杂度
     *  空间复杂度:O(C),C会等于3，是堆中最多存储的元素数量
     * @param a
     * @param b
     * @param c
     * @return
     */
    public String solution(int a, int b, int c){

        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });
        if (a > 0){
            priorityQueue.add(new int[]{0, a});
        }
        if (b > 0){
            priorityQueue.add(new int[]{1, b});
        }
        if (c > 0){
            priorityQueue.add(new int[]{2, c});
        }

        StringBuilder builder = new StringBuilder();
        while (!priorityQueue.isEmpty()){
            int[] cur = priorityQueue.poll();
            int len = builder.length();
            if (len >= 2 && builder.charAt(len-1) - 'a' == cur[0] && builder.charAt(len - 2) - 'a' == cur[0]){
                if (priorityQueue.isEmpty()){
                    break;
                }
                int[] next = priorityQueue.poll();
                builder.append((char) (next[0] + 'a'));
                if (--next[1] != 0){
                    priorityQueue.add(next);
                }
                priorityQueue.add(cur);
            }else {
                builder.append((char) (cur[0] + 'a'));
                if (--cur[1] != 0){
                    priorityQueue.add(cur);
                }
            }
        }

        return builder.toString();
    }


    public static void main(String[] args) {

        LongestDiverseString diverseString = new LongestDiverseString();
        int a = 7;
        int b = 1;
        int c = 0;

        String ans = diverseString.solution(a, b, c);
        System.out.println(ans);
    }
}
