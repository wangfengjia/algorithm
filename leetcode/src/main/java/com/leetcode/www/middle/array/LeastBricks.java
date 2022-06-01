package com.leetcode.www.middle.array;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * leetcode-554:砖墙
 * 你的面前有一堵矩形的、由 n 行砖块组成的砖墙。这些砖块高度相同（也就是一个单位高）但是宽度不同。每一行砖块的宽度之和相等。
 * 你现在要画一条 自顶向下 的、穿过 最少 砖块的垂线。如果你画的线只是从砖块的边缘经过，就不算穿过这块砖。你不能沿着墙的两个垂直边缘之一画线，这样显然是没有
 * 穿过一块砖的。给你一个二维数组 wall ，该数组包含这堵墙的相关信息。其中，wall[i] 是一个代表从左至右每块砖的宽度的数组。你需要找出怎样画才能使这条线 穿过
 * 的砖块数量最少 ，并且返回 穿过的砖块数量 。
 */
public class LeastBricks {


    /**
     * 哈希表
     * 题目要求穿过的墙数量最少，也就是通过的间隙最多。因此可以使用哈希表记录每个间隙出现的次数,最终统计所有的行中那些间隙出现得最多，使用总行数减去间隙出现的最多
     * 次数就是答案
     *
     * 复杂度分析
     *  时间复杂度:O(nm),n是砖墙的高度，m是每行砖墙的平均数量
     *  空间复杂度:O(nm),n是砖墙的高度，m是每行砖墙的平均数量
     * @param wall
     * @return
     */
    public int solution(List<List<Integer>> wall){

        Map<Integer, Integer> map = new HashMap<>();
        for (List<Integer> wal : wall){
            int sum = 0;
            for (Integer piece : wal){
                sum += piece;
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
            //不能从两边穿过，需要移除掉最后一个
            map.remove(sum);
        }

        int n = wall.size();
        int ans = n;
        for (Integer key : map.keySet()){
            Integer cnt = map.get(key);
            ans = Math.min(ans, n - cnt);
        }

        return ans;
    }


    public static void main(String[] args) {

        LeastBricks leastBricks = new LeastBricks();
        List<List<Integer>> wall = new ArrayList<>();

        List<Integer> w1 = new ArrayList<>();
        w1.add(1);
        w1.add(2);
        w1.add(2);
        w1.add(1);

        List<Integer> w2 = new ArrayList<>();
        w2.add(3);
        w2.add(1);
        w2.add(2);

        List<Integer> w3 = new ArrayList<>();
        w3.add(1);
        w3.add(3);
        w3.add(2);

        List<Integer> w4 = new ArrayList<>();
        w4.add(2);
        w4.add(4);

        List<Integer> w5 = new ArrayList<>();
        w5.add(3);
        w5.add(1);
        w5.add(2);

        List<Integer> w6 = new ArrayList<>();
        w6.add(1);
        w6.add(3);
        w6.add(1);
        w6.add(1);

        wall.add(w1);
        wall.add(w2);
        wall.add(w3);
        wall.add(w4);
        wall.add(w5);
        wall.add(w6);
        int ans = leastBricks.solution(wall);
        System.out.println(ans);
    }
}
