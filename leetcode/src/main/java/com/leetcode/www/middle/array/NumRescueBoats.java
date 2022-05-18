package com.leetcode.www.middle.array;


import java.util.Arrays;

/**
 * leetcode-881:救生艇
 * 给定数组people。people[i]表示第 i个人的体重，船的数量不限，每艘船可以承载的最大重量为limit。
 * 每艘船最多可同时载两人，但条件是这些人的重量之和最多为limit。
 * 返回 承载所有人所需的最小船数。
 */
public class NumRescueBoats {


    /**
     * 贪心:为了实现需要的船的数量尽量小，那么乘坐两人的船尽量多。考虑剩余的人中最重和最轻的能否乘坐同一艘船
     *     1. 最轻的人和最重的人无法乘坐同一艘船，那么最重的人也无法和其他的人乘坐同一艘船。此时就单独分配一艘船给最重的人
     *     2. 最轻的人能和最重的人乘坐同一艘船，那么最轻的人能和任何人乘坐同一艘船，为了尽可能利用船的承载能力，选择最重和最轻的人乘坐同一艘船是最优的
     * 复杂度分析
     *  时间复杂度:O(nlog^n),n是数组people的长度，排序所需的时间复杂度是O(nlog^n)
     *  空间复杂度:O(log^n),n是数组people的长度，排序所需要的额外空间是O(log^n)
     * @param people
     * @param limit
     * @return
     */
    public int solution(int[] people, int limit){

        Arrays.sort(people);
        int ans = 0;
        int light = 0;
        int heavy = people.length - 1;

        while (light <= heavy){
            if (people[light] + people[heavy] <= light){
                ++light;
            }
            --heavy;
            ++ans;
        }

        return ans;
    }

    public static void main(String[] args) {

        NumRescueBoats boats = new NumRescueBoats();
        int[] nums = {3,2,2,1};
        int limit = 3;

        int ans = boats.solution(nums, limit);
        System.out.println(ans);
    }
}
