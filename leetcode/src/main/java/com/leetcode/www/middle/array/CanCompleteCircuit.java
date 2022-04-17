package com.leetcode.www.middle.array;

/**
 * leetcode-134:加油站
 * 在一条环路上有n个加油站，其中第i个加油站有汽油gas[i]升。你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1个加油站需要消耗汽油cost[i]升。你从其中的
 * 一个加油站出发，开始时油箱为空。给定两个整数数组 gas 和 cost ，如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则保证它是唯一的。
 */
public class CanCompleteCircuit {


    /**
     * 一次遍历:从头到尾遍历每个加油站，并检查以该加油站为起点，最终能否行驶一圈。并且有这么一个推论:假如从加油站x出发，每经过一个加油站就加一次油，最远可以
     * 到达的是y,那么x+1到y之间的节点都不可能绕圈一周。比如x+1的节点能绕圈一周，那么i+1一定可以到达y+1,又由于x能够到达x+1,所以x能够到达y+1。这就和x最远只可以
     * y相互矛盾，x到y之间的节点都可以通过相同的方法证明没法绕圈一周，所以遍历的下一个节点直接就可以是y+1
     * 复杂度分析
     *  时间复杂度:O(n),n为数组的长度，对数组进行单次遍历
     *  空间复杂度:O(1)
     * @param gas
     * @param cost
     * @return
     */
    public int solution(int[] gas, int[] cost){

        int n = gas.length;
        int left = 0;
        while (left < n){
            int gasSum = 0;
            int costSum = 0;
            int cnt = 0;
            while (cnt < n){
                int j = (left + cnt) % n;
                gasSum += gas[j];
                costSum += cost[j];
                if (costSum > gasSum){
                    break;
                }
                cnt++;
            }

            if (cnt == n){
                return left;
            } else {
                left = left + cnt + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        CanCompleteCircuit circuit = new CanCompleteCircuit();
        int[] gas = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2};
        int ans = circuit.solution(gas, cost);
        System.out.println(ans);
    }
}
