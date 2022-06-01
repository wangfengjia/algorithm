package com.leetcode.www.middle.string;


import java.util.ArrayDeque;
import java.util.Deque;

/**
 * leetcode-838:推多米诺
 * n 张多米诺骨牌排成一行，将每张多米诺骨牌垂直竖立。在开始时，同时把一些多米诺骨牌向左或向右推。
 * 每过一秒，倒向左边的多米诺骨牌会推动其左侧相邻的多米诺骨牌。同样地，倒向右边的多米诺骨牌也会推动竖立在其右侧的相邻多米诺骨牌。
 * 如果一张垂直竖立的多米诺骨牌的两侧同时有多米诺骨牌倒下时，由于受力平衡， 该骨牌仍然保持不变。
 * 就这个问题而言，我们会认为一张正在倒下的多米诺骨牌不会对其它正在倒下或已经倒下的多米诺骨牌施加额外的力。
 * 给你一个字符串 dominoes 表示这一行多米诺骨牌的初始状态，其中：
 * dominoes[i] = 'L'，表示第 i 张多米诺骨牌被推向左侧，
 * dominoes[i] = 'R'，表示第 i 张多米诺骨牌被推向右侧，
 * dominoes[i] = '.'，表示没有推动第 i 张多米诺骨牌。
 * 返回表示最终状态的字符串。
 */
public class PushDominoes {


    /**
     * BFS
     * 可以使用BFS来模拟推导骨牌。起始将所有不为"."的骨牌以(loc,time,dire)三元组入队列，三元组所代表的含义为"位置为loc的骨牌在time时刻受到一个方向为dire的力"，
     * 然后进行常规的BFS。
     * 在入队时，尝试修改骨牌的状态，同时为了解决一个骨牌同时受左右推力，维持状态不变的问题，我们需要记录下骨牌状态修改时间,如果在同一个时间内，一个骨牌受到左右
     * 推力时,将这个骨牌恢复到竖立状态
     *
     * 复杂度分析
     *  时间复杂度:O(n),n是字符串dominoes的长度
     *  空间复杂度:O(n),n是字符串dominoes的长度
     * @param dominoes
     * @return
     */
    public String solution(String dominoes){

        char[] cs = dominoes.toCharArray();
        int n = cs.length;
        int[] g = new int[n];
        Deque<int[]> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++){
            if (cs[i] == '.'){
                continue;
            }
            int dire = cs[i] == 'L' ? -1 : 1;
            deque.add(new int[]{i, 1, dire});
            g[i] = 1;
        }

        while (!deque.isEmpty()){
            int[] cell = deque.pollFirst();
            int loc = cell[0];
            int time = cell[1];
            int dire = cell[2];
            int ne = loc + dire;

            if (cs[loc] == '.' || (ne < 0 || ne >= n)){
                continue;
            }
            //首次受力
            if (g[ne] == 0){
                deque.addLast(new int[]{ne, time + 1, dire});
                g[ne] = time + 1;
                cs[ne] = dire == -1 ? 'L' : 'R';
            }else if (g[ne] == time + 1){ // 多次受力
                cs[ne] = '.';
            }
        }

        return String.valueOf(cs);
    }

    public static void main(String[] args) {

        PushDominoes dominoes = new PushDominoes();
        String s = "RR.L";
        String sn = dominoes.solution(s);
        System.out.println(sn);
    }
}
