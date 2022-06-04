package com.leetcode.www.middle.array;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * leetcode-846:一手顺子
 * Alice 手中有一把牌，她想要重新排列这些牌，分成若干组，使每一组的牌数都是 groupSize ，并且由 groupSize 张连续的牌组成。
 * 给你一个整数数组 hand 其中 hand[i] 是写在第 i 张牌，和一个整数 groupSize 。如果她可能重新排列这些牌，返回 true ；否则，返回 false 。
 */
public class IsNStraightHand {


    /**
     * 哈希表+优先队列(小根堆)
     * 先使用哈希表统计数组hand中每个元素出现的次数和将hand中的元素添加到优先队列中，然后每次从优先队列中取出堆顶元素t来尝试作为顺子的发起点，用t作为发起点
     * 构造顺子，即对[t,t+1..,t+m-1]元素出现的次数减一。如果构造过程中没有出现剩余元素出现次数不足的情况，说明整个构造过程没有冲突，返回true,否则返回false
     * @param hand
     * @param groupSize
     * @return
     */
    public boolean solution(int[] hand, int groupSize){

        Map<Integer, Integer> cntMap = new HashMap<>();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        for (int e : hand){
            cntMap.put(e, cntMap.getOrDefault(e, 0) + 1);
            priorityQueue.add(e);
        }

        while (!priorityQueue.isEmpty()){
            Integer element = priorityQueue.poll();
            if (cntMap.get(element) == 0){
                continue;
            }
            for (int i = 0; i < groupSize; i++){
                Integer cnt = cntMap.getOrDefault(element + i, 0);
                if (cnt == 0){
                    return false;
                }
                cntMap.put(element + i, cnt - 1);
            }
        }

        return true;
    }

    public static void main(String[] args) {

        IsNStraightHand isNStraightHand = new IsNStraightHand();
        int[] hand = {1,2,3,4,5};
        int groupSize = 3;
        boolean ans = isNStraightHand.solution(hand, groupSize);
        System.out.println(ans);
    }
}
