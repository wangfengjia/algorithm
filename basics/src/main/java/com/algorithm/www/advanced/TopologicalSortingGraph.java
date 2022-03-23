package com.algorithm.www.advanced;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 拓扑排序,一般用来解决通过局部顺序来推导全局顺序
 * 问题描述
 *  有两两相互依赖的东西(例如编译的时候，a.cpp依赖b.cpp,b.cpp依赖c.cpp,d.cpp依赖b.cpp)，利用拓扑排序生成一个序列
 *  这个序列满足局部依赖关系
 */
public class TopologicalSortingGraph {
    
    private int v; //表示顶点的个数

    private LinkedList<Integer> adj[]; //邻接表

    public TopologicalSortingGraph(int v){
        this.v = v;
        this.adj = new LinkedList[v];
        for (int i = 0; i < v; i++){
        adj[i] = new LinkedList<Integer>();
    }
}


    public void addEdge(int s, int j){ // s->j
        adj[s].add(j);
    }

    /**
     * 利用kahn算法实现拓扑排序
     * 解决思路
     *  可以用图的每个顶点来表示源文件，边表示依赖关系，先将文件添加到图里面。然后找到入度为0的顶点(入度为0表示没有其他顶点指向这个它，表示它
     *  不依赖其他文件)，然后将这个顶点删除，同时将这个顶点指向的顶点的入度为1，然后循环执行这个过程
     * 扩展
     * 检测图是否有环:假如通过kahn算法输出的顶点的数量少于图中顶点的数量，图中还有入度不等于0的顶点，说明图中有环
     */
    public void topologicalSortingByKahn(){
        //统计每个顶点的入度
        int[] inDegree = new int[v];
        for (int i = 0; i < v; i++){
            for (int j = 0; j < adj[i].size(); j++){
                Integer w = adj[i].get(j);
                ++inDegree[w];
            }
        }

        //找到入度为0的顶点
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < v; i++){
            if (inDegree[i] == 0){
                queue.add(i);
            }
        }

        while (!queue.isEmpty()){
            int w = queue.remove();
            System.out.print("->" + w);
            //将w顶点指向的顶点的入度减1
            for (int j = 0; j < adj[w].size(); j++){
                int k = adj[w].get(j);
                --inDegree[k];
                //它指向的顶点入度减1之后，判断这个顶点入度是否等于0
                if (inDegree[k] == 0){
                    queue.add(k);
                }
            }
        }
    }

    /**
     * 利用深度优化搜索的方法来拓扑排序
     */
    public void topoSortByDFS(){

        //构建逆邻接表
        LinkedList<Integer> inverseAdj[] = new LinkedList[v];
        for (int i = 0; i < v; i++){
            inverseAdj[i] = new LinkedList<Integer>();
        }

        for (int i = 0; i < v; i++){
            for (int j = 0; j < adj[i].size(); j++){
                int k = adj[i].get(j);
                inverseAdj[k].add(i);
            }
        }

        boolean[] visited = new boolean[v];
        for (int i = 0; i < v; i++){ //深度优先遍历图
            if (!visited[i]){
                visited[i] = true;
                dfs(i, inverseAdj, visited);
            }
        }
    }


    private void dfs(int vertex, LinkedList<Integer> inverseAdj[], boolean[] visited){
        for (int i = 0; i < inverseAdj[vertex].size(); i++){
            int k = inverseAdj[vertex].get(i);
            if (visited[k]){
                continue;
            }
            visited[k] = true;
            dfs(k, inverseAdj, visited);
        }
        //先把vertex可达的所有顶点打压出来(可达顶点就是它依赖的顶点),最后打印它自己
        System.out.print("->" + vertex);
    }

    public static void main(String[] args){

        int v = 4;
        TopologicalSortingGraph sortingGraph = new TopologicalSortingGraph(v);
        sortingGraph.addEdge(1, 0);
        sortingGraph.addEdge(2, 1);
        sortingGraph.addEdge(1, 3);

        sortingGraph.topologicalSortingByKahn();

        System.out.println("");
        sortingGraph.topoSortByDFS();
    }
}
