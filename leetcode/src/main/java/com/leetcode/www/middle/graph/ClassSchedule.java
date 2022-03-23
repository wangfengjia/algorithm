package com.leetcode.www.middle.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode207(课程表):你这个学期必须选修numCourses门课程，记为0到numCourses-1。 在选修某些课程之前需要一些先修课程。 先修课程按数组prerequisites给出，其中
 * prerequisites[i] = [ai, bi] ，表示如果要学习课程ai则必须先学习课程bi。例如，先修课程对[0, 1] 表示：想要学习课程0，你需要先完成课程 1。请你判断是否可能完成
 * 所有课程的学习？如果可以，返回 true;否则，返回 false。
 * 方法一(拓扑排序):这个问题可以建模成一个求拓扑排序的问题。假如存在拓扑排序，则存在一种符合要求的课程学习顺序
 *                1. 将每一门课看成是一个节点
 *                2.如果想要学习课程A之前必须完成课程B，那么B到A连接一条有向边，这样在拓扑排序中，B一点出现在A的前面
 */
public class ClassSchedule {

    public List<List<Integer>> edges;
    int[] visited;
    boolean valid = true;
    /**
     * 将深度优先搜索的流程和拓扑排序的求解过程联系起来，用一个栈来存储所有已经完成搜索的节点。流程是这样:假如我们当前搜索到的节点u，如果它的所有相邻节点都已经搜索完成，
     * 相邻节点的定义是通过一条有向边到达的节点，那么这些相邻节点就已经存在栈中，此时我们再把u入栈。此时u处于栈顶的位置，那么u出现在它的所有相邻节点的前面，对于u来说
     * 它是满足拓扑排序的
     * @param courseNumbers
     * @param prepare
     * @return
     */
    public boolean solution(int courseNumbers, int[][] prepare){

        edges = new ArrayList<>();
        for (int i = 0; i < courseNumbers; i++){
            edges.add(new ArrayList<>());
        }

        visited = new int[courseNumbers];
        for (int[] info : prepare){
            edges.get(info[1]).add(info[0]);
        }
        for (int i = 0; i < courseNumbers && valid; i++){
            if (visited[i] == 0){
                dfs(i);
            }
        }

        return valid;
    }

    /**
     * 图中任意节点在搜索过程中有3种状态
     *  1. 未搜索:还没有搜索到这个节点
     *  2. 搜索中:搜索过这个节点，但是还没有回溯这个节点,就是这个节点还没入栈，还有相邻节点没有搜索完成
     *  3. 已完成:搜索并回溯过这个节点，就是这个节点已经入栈
     * 对于当前被搜索节点我们标记为搜索中，然后遍历这个节点的每一个相邻节点v
     *  1. 如果v未搜索，我们就开始搜索v，然后回溯u
     *  2. 如果v为搜索中，那么我们在图中找到了一个环，因此不存在拓扑排序
     *  3. 如果为v已完成，那么v已经在栈中,而u还不在栈中，无论u什么时候入栈，都不会影响到(u,v)之间的拓扑关系，因此不用进行任何操作
     *  4. 当u的所有相邻节点都为已完成时，我们就把u入栈，标记为已完成
     * @param u
     */
    public void dfs(int u){

        //搜索中
        visited[u] = 1;
        for (Integer v : edges.get(u)){
            if (visited[v] == 0){
                dfs(v);
                if (!valid){
                    return;
                }
            }else if (visited[v] == 1){
                //当u指向的节点v也是在搜索中的状态，说明图中存在环，所以不存在拓扑排序，也就无法完成课程
                valid = false;
                return;
            }
        }
        //节点u搜索完成,入栈
        visited[u] = 2;
    }


    public static void main(String[] args) {

        int courseNums = 2;
        int[][] prepare = new int[][]{{1,0}};
        boolean valid = new ClassSchedule().solution(courseNums, prepare);
        System.out.println(valid);
    }
}
