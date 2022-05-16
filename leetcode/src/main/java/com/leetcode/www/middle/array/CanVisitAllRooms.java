package com.leetcode.www.middle.array;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode-841:钥匙和房间
 * 有 n 个房间，房间按从 0 到 n - 1 编号。最初，除 0 号房间外的其余所有房间都被锁住。你的目标是进入所有的房间。然而，你不能在没有获得钥匙的时候进入锁住的房间。
 * 当你进入一个房间，你可能会在里面找到一套不同的钥匙，每把钥匙上都有对应的房间号，即表示钥匙可以打开的房间。你可以拿上所有钥匙去解锁其他房间。
 * 给你一个数组 rooms 其中 rooms[i] 是你进入 i 号房间可以获得的钥匙集合。如果能进入 所有 房间返回 true，否则返回 false。
 */
public class CanVisitAllRooms {

    private boolean[] visited;
    private int num;
    /**
     * 深度优先搜索:x号房间中有y号房间的钥匙，就可以从x房间去到y号房间，这样就可以看作图中顶点x到顶点y有一条边。然后就可以利用深度优先搜索的方式遍历整张图
     *            并且统计到达的节点的个数。在遍历过程中用一个数组visited来记录一个节点是否访问过，防止重复访问
     * 复杂度分析
     *  时间复杂度:O(n+m),n是房间的数量，m是所有房间中的钥匙数量的总数
     *  空间复杂度:O(n),n是房间的数量，遍历图的栈空间的开销
     * @param rooms
     * @return
     */
    public boolean solution(List<List<Integer>> rooms){

        num = 0;
        int n = rooms.size();
        visited = new boolean[n];
        dfs(rooms, 0);

        return num == n;
    }


    private void dfs(List<List<Integer>> rooms, int index){

        visited[index] = true;
        ++num;
        for (Integer room : rooms.get(index)){
            if (!visited[room]){
                dfs(rooms, room);
            }
        }
    }

    public static void main(String[] args) {

        CanVisitAllRooms visitAllRooms = new CanVisitAllRooms();
        List<List<Integer>> rooms = new ArrayList<>();

        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(3);

        List<Integer> list2 = new ArrayList<>();
        list2.add(3);
        list2.add(0);
        list2.add(1);

        List<Integer> list3 = new ArrayList<>();
        list3.add(2);

        List<Integer> list4 = new ArrayList<>();
        list4.add(0);

        rooms.add(list1);
        rooms.add(list2);
        rooms.add(list3);
        rooms.add(list4);

        boolean ans = visitAllRooms.solution(rooms);
        System.out.println(ans);
    }
}
