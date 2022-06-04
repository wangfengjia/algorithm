package com.leetcode.www.middle.queue;


import java.util.ArrayDeque;
import java.util.Queue;

/**
 * leetcode-1823:找出游戏的获胜者
 * 共有 n 名小伙伴一起做游戏。小伙伴们围成一圈，按 顺时针顺序 从 1 到 n 编号。确切地说，从第 i 名小伙伴顺时针移动一位会到达第 (i+1) 名小伙伴的位置，其中 1 <= i < n ，从第 n 名小伙伴顺时针移动一位会回到第 1 名小伙伴的位置。
 * 游戏遵循如下规则：
     * 从第 1 名小伙伴所在位置 开始 。
     * 沿着顺时针方向数 k 名小伙伴，计数时需要 包含 起始时的那位小伙伴。逐个绕圈进行计数，一些小伙伴可能会被数过不止一次。
     * 你数到的最后一名小伙伴需要离开圈子，并视作输掉游戏。
     * 如果圈子中仍然有不止一名小伙伴，从刚刚输掉的小伙伴的 顺时针下一位 小伙伴 开始，回到步骤 2 继续执行。
     * 否则，圈子中最后一名小伙伴赢得游戏。
     * 给你参与游戏的小伙伴总数 n ，和一个整数 k ，返回游戏的获胜者。
 */
public class FindTheWinner {


    /**
     * 队列+模拟
     * 使用队列模拟游戏的过程:将队首元素取出，并将元素在队尾处加入队列，重复此操作k-1次，此时队首元素就是这一轮中数到第k名的小伙伴的编号，将队首元素取出，
     * 就是将数到的第k名小伙伴离开圈子。每一轮游戏后，队列中减少一个元素,重复上述过程，直到队列中只有一个元素
     *
     * 复杂度分析
     *  时间复杂度:O(nk),每一轮需要将k个元素从队列中取出，将k-1个元素加入队列,一共有n-1轮，所以时间复杂度为O(nk)
     *  空间复杂度:O(n),队列所使用的空间
     * @param n
     * @param k
     * @return
     */
    public int solution(int n, int k){

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++){
            queue.offer(i);
        }

        while (queue.size() > 1){
            for (int i = 1; i < k; i++){
                queue.offer(queue.poll());
            }
            queue.poll();
        }

        return queue.peek();
    }

    public static void main(String[] args) {

        FindTheWinner winner = new FindTheWinner();
        int n = 5;
        int k = 2;
        int ans = winner.solution(n, k);
        System.out.println(ans);
    }
}
