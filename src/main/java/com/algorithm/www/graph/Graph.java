package com.algorithm.www.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 无向图
 *
 * @author wangyongchun
 * @date 2019/07/09 14:35
 */
public class Graph {

    private int v; //顶点个数

    private LinkedList<Integer> adj[]; //邻接表

    private boolean found;

    public Graph(int v){
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++){
            adj[i] = new LinkedList<Integer>();
        }
        this.found = false;
    }

    //无向图一次增加两条边
    public void addEdge(int s, int t){
        adj[s].add(t);
        adj[t].add(s);
    }

    /**
     * 广度优先搜索
     * @param s
     * @param t
     */
    public void breathFirstSearch(int s, int t){

        if (s == t){
            return;
        }

        boolean[] visited = new boolean[v];
        visited[s] = true;
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        int[] pre = new int[v];
        for (int i = 0; i < v; i++){
            pre[i] = -1;
        }

        while (queue.size() != 0){
            int w = queue.poll();
            for (int i = 0; i < adj[w].size(); i++){
                int p = adj[w].get(i);
                if (!visited[p]){
                    pre[p] = w;
                    if (p == t){
                        print(pre, s, t);
                        return;
                    }
                    queue.add(p);
                    visited[p] = true;
                }
            }
        }

    }

    /**
     * s->t的路径
     * @param pre
     * @param s
     * @param t
     */
    private void print(int[] pre, int s, int t){
        if (pre[t] != -1 && s != t){
            print(pre, s, pre[t]);
        }

        System.out.println(t + " ");
    }

    /**
     * 深度优先搜索
     * 思路
     * 1.选取一个顶点，递归遍历它指向的一个顶点，在递归的时候去判断指向的顶点是否等于给定值，不相等的话
     *  接着遍历已经遍历过的顶点指向的顶点(并且这个顶点没有被访问过)，没找到的话就往上回溯，去递归遍历
     *  选取的节点指向的另外一个节点，直到找到对应的节点
     * 2.有一个全局变量或者类成员变量来保存给定值是否找到
     * @param s
     * @param t
     */
    public void depthFirstSearch(int s, int t){
        if (s == t){
            return;
        }

        boolean[] visited = new boolean[v];
        int[] pre = new int[v];
        for (int i = 0; i < v; i++){
            pre[i] = -1;
        }

        recurDfs(s, t, visited, pre);
        print(pre, s, t);
    }

    private void recurDfs(int s, int t, boolean[] visited, int[] pre){
        if (found){
            return;
        }
        visited[s] = true;
        if (s == t){
            found = true;
            return;
        }

        for (int i = 0; i < adj[s].size(); i++){
            int q = adj[s].get(i);
            if (!visited[q]){
                pre[q] = s;
                recurDfs(q, t, visited, pre);
            }
        }
    }
    public static void main(String[] args){

        Graph graph = new Graph(8);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(3, 4);
        graph.addEdge(2, 5);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 7);


        Graph graph1 = new Graph(9);
        graph1.addEdge(1, 2);
        graph1.addEdge(1, 4);
        graph1.addEdge(5, 4);
        graph1.addEdge(2, 5);
        graph1.addEdge(2, 3);
        graph1.addEdge(5, 6);
        graph1.addEdge(5, 7);
        graph1.addEdge(6, 3);
        graph1.addEdge(6, 8);
        graph1.addEdge(8, 7);

//        graph.breathFirstSearch(0, 7);
        graph1.depthFirstSearch(1, 7);

    }
}
