package com.leetcode.www.middle.string;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * leetcode-451:根据字符出现频率排序
 * 给定一个字符串 s ，根据字符出现的 频率 对其进行 降序排序 。一个字符出现的 频率 是它出现在字符串中的次数。
 * 返回 已排序的字符串。如果有多个答案，返回其中任何一个。
 *
 */
public class FrequencySort {

    /**
     * 优先级队列:处理步骤如下
     *          1. 先用哈希表对词频进行统计。
     *          2. 然后遍历哈希表，将每个键值对以[字符,字频]的形式存储到优先队列中,并且优先级队列的排序逻辑如下
     *              1. 如果词频不同，就按照词频排序
     *              2. 如果词频相同，则根据字符字典序排序
     *          3. 从优先队列中弹出元素，构造方案
     * 复杂度分析
     *  时间复杂度:O(max(n, Clog^C)),使用哈希表统计词频的复杂度为O(n),最坏情况下字符集中的所有字符都有出现，最多有C个节点要添加到优先队列中，复杂度为O(Clog^C),
     *           构造答案时需要从优先队列中取出元素并拼接，复杂度为O(n),所以整体的复杂度为O(max(n,Clog^C))
     *  空间复杂度:O(n)
     * @param s
     * @return
     */
    public String solution(String s){

        char[] chars = s.toCharArray();
        Map<Character, Integer> frequentMap = new HashMap<>();
        for (char c : chars){
            frequentMap.put(c, frequentMap.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Node> queue = new PriorityQueue<>((a,b) -> {
            if (b.frequent != a.frequent){
                return b.frequent - a.frequent;
            }
            return a.c - b.c;
        });

        for (char c : frequentMap.keySet()){
            queue.add(new Node(c, frequentMap.get(c)));
        }

        StringBuilder builder = new StringBuilder();
        while (!queue.isEmpty()){
            Node node = queue.poll();
            int frequent = node.frequent;
            //字符重复的次数
            while (frequent-- > 0){
                builder.append(node.c);
            }
        }

        return builder.toString();
    }

    public static class Node{

        char c;
        int frequent;

        public Node(char c, int frequent){
            this.c = c;
            this.frequent = frequent;
        }
    }

    public static void main(String[] args) {


        FrequencySort frequencySort = new FrequencySort();
        String s = "tree";
        String ans = frequencySort.solution(s);
        System.out.println(ans);
    }
}
