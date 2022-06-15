package com.leetcode.www.middle.dynamic;


import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * leetcode-313:超级丑数
 * 超级丑数 是一个正整数，并满足其所有质因数都出现在质数数组 primes 中。
 * 给你一个整数 n 和一个整数数组 primes ，返回第 n 个 超级丑数 。
 * 题目数据保证第 n 个 超级丑数 在 32-bit 带符号整数范围内。
 */
public class NthSuperUglyNumber {


    /**
     * 多路归并
     * 新的丑数是使用已有丑数乘以给定质因数primes[i].因此，如果我们所有丑数的有序序列为a1,a2,a3...an的话，序列中的每一个数必然能够被以下3个数列中至少一个
     * 覆盖(假设primes=[2,3,5])
     *  1. 由丑数*2所得的有序序列:1*2,2*2,3*2,4*2,5*2,6*2,8*2
     *  2. 由丑数*3所得的有序序列:1*3,2*3,3*3,4*3,5*3,6*3,8*3
     *  3. 由丑数*5所得的有序序列:1*5,2*5,3*5,4*5,5*5,6*5,8*5
     * 令这些有序序列为arr，最终的丑数序列为ans
     * 如果primes数组的长度为m，我们可以使用m个指针来指向这m个有序序列arr的当前下标，我们每次只需要去m个指针中值最小的那一个，然后指针向后移动一位，不断重复
     * 这个过程，就可以找到第n个丑数
     * 在实现上，我们并不需要构造m个有序序列，可以构造一个存储三元组的小根堆，三元组的信息为(val,i,idx)
     *  1. val:当前列表指针指向的具体值
     *  2. i: 表示这是由primes[i]构造出来的有序序列
     *  3. idx: 代表丑数下标，val = ans[idx] * primes
     * 一开始，把所有的(primes[i],i,0)假如优先队列中,每次取出堆顶元素，下一个该放入的元素为(ans[idx+1]*primes[i],i, idx+1)。同时，由于每个arr指针移动和
     * ans的构造都是单调递增的，因此可以通过与最后一位构造的ans[x]进行比较来实现去重
     *
     * 复杂度分析
     *  时间复杂度:O(nlog^m),需要构造长度为n的答案，每次构造需要从堆中取出和放入元素，堆中有m个元素，所以复杂度为O(nlog^m)
     *  空间复杂度:O(n+m),n是待求的超级丑数的编号，m是堆中的元素，所以总的空间复杂度为O(n+m)
     * @param primes
     * @param n
     * @return
     */
    public int solution(int[] primes, int n){

        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        for (int i = 0; i < primes.length; i++){
            queue.offer(new int[]{primes[i], i, 0});
        }

        int[] ans = new int[n];
        ans[0] = 1;
        for (int j = 1; j < n;){
            int[] cell = queue.poll();
            int val = cell[0];
            int i = cell[1];
            int idx = cell[2];
            if (ans[j-1] != val){
                ans[j++] = val;
            }
            queue.offer(new int[]{ans[idx + 1] * primes[i], i, idx+1});
        }
        return ans[n-1];
    }


    /**
     * 动态规划
     *      状态定义:dp[i]表示第i个超级丑数
     *      状态转移方程:创建与数组primes长度相同的数组pointers，下一个丑数是当前指针指向的超级丑数乘以对应的质因数，初始时，数组pointers的元素值都是1
     *          dp[i] = min(dp[pointes[j]] * primes[j]), 0<=j<m。对于每一个j，分别比较dp[i]和dp[pointers[j]] * primes[j]是否相等,如果相等则pointers[j]++
     *
     * @param primes
     * @param n
     * @return
     */
    public int solutionV2(int[] primes, int n){

        int[] dp = new int[n+1];
        int m = primes.length;
        int[] pointers = new int[m];
        int[] nums = new int[m];
        Arrays.fill(nums, 1);

        for (int i = 1; i <= n; i++){
            int minNum = Arrays.stream(nums).min().getAsInt();
            dp[i] = minNum;
            for (int j = 0; j < m; j++){
                if (nums[j] == minNum){
                    pointers[j]++;
                    nums[j] = dp[pointers[j]] * primes[j];
                }
            }
        }

        return dp[n];
    }


    public static void main(String[] args) {

        NthSuperUglyNumber uglyNumber = new NthSuperUglyNumber();
        int[] primes = {2,7,13,19};
        int n = 12;
        int ans = uglyNumber.solutionV2(primes, n);
        System.out.println(ans);
    }
}
