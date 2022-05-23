package com.leetcode.www.middle.array;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode-397:整数替换
 * 给定一个正整数n ，你可以做如下操作：
     * 如果n是偶数，则用n / 2替换n 。
     * 如果n是奇数，则可以用n + 1或n - 1替换n 。
     * 返回 n变为 1 所需的 最小替换次数 。
 */
public class IntegerReplacement {

    private Map<Integer, Integer> map = new HashMap<>();

    /**
     * 深度优先搜索(BFS),为防止重复处理某些数，使用哈希表进行记忆化处理
     * 复杂度分析
     *  时间复杂度:O(log^n)
     *  空间复杂度:O(log^n),递归调用所需要的栈空间为O(log^n)
     * @param n
     * @return
     */
    public int solution(int n){

        return dfs(n);
    }

    private int dfs(int n){

        if (n == 1){
            return 0;
        }
        if (map.containsKey(n)){
            return map.get(n);
        }

        int ans;
        if (n % 2 == 0){
            ans = dfs(n / 2);
        }else {
            ans = Math.min(dfs(n-1), dfs(n+1));
        }
        map.put(n, ++ans);
        return ans;
    }

    public static void main(String[] args) {

        IntegerReplacement replacement = new IntegerReplacement();
        int n = 8;
        int ans = replacement.solution(n);
        System.out.println(ans);
    }
}
