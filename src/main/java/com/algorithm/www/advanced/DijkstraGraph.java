package com.algorithm.www.advanced;

import java.util.LinkedList;

/**
 * 一个有向有权图，求两个顶点之间的最短路径，最短路径算法中最有名的是Dijkstra算法
 */
public class DijkstraGraph { //有向有权图表示

    public LinkedList<Edge> adj[]; //领接表
    public int v; //顶点个数

    public DijkstraGraph(int v){
        this.v = v;
        this.adj = new LinkedList[v];
        for (int i = 0; i < v; i++){
            this.adj[i] = new LinkedList<Edge>();
        }
    }

    public void addEdge(int s, int t, int w){
        this.adj[s].add(new Edge(s, t, w));
    }

    /**
     * 图中的边
     */
    private class Edge{
        public int sid; //边的起始顶点编号
        public int tid; //边的终止顶点编号
        public int weight; //权重

        public Edge(int sid, int tid, int weight){
            this.sid = sid;
            this.tid = tid;
            this.weight = weight;
        }
    }

    private class Vertex{
        public int id; //顶点编号id
        public int dist; //从起始顶点到这个顶点的距离
        public Vertex(int id, int dist){
            this.id = id;
            this.dist = dist;
        }
    }

    /**
     * 因为java的优先级队列没有曝露更新的接口，所以要重新实现一个
     */
    private class PriorityQueue{
        private Vertex[] nodes;
        private int capacity;
        private int count;

        public PriorityQueue(int capacity){
            this.nodes = new Vertex[capacity + 1]; //数组下标为0的位置不存储对象
            this.capacity = capacity;
            this.count = 0;
        }

        public boolean add(Vertex vertex){
            if (count >= capacity){
                return false;
            }
            ++count;
            nodes[count] = vertex;
            int i = count;
            while (i / 2 > 0 && nodes[i].dist < nodes[i / 2].dist){
                swap(nodes, i, i / 2);
                i = i / 2;
            }

            return true;
        }

        public Vertex poll(){
            if (count == 0){
                return null;
            }
            Vertex min = nodes[1];
            nodes[1] = nodes[count];
            --count;
            heapify(nodes, count, 1);
            return min;
        }

        /**
         * 从上往下堆化
         * @param nodes
         * @param n
         * @param m
         */
        private void heapify(Vertex[] nodes, int n, int m){

            while (true){
                int minPos = m;
                if (2 * m <= n && nodes[2 * m].dist < nodes[minPos].dist){
                    minPos = 2 * m;
                }
                if (2 * m + 1 <= n && nodes[2 * m + 1].dist < nodes[minPos].dist){
                    minPos = 2 * m + 1;
                }
                if (minPos == m){
                    break;
                }
                swap(nodes, minPos, m);
                m = minPos;
            }
        }

        private void swap(Vertex[] nodes, int source, int des){
            Vertex tem = nodes[des];
            nodes[des] = nodes[source];
            nodes[source] = tem;
        }

        public void update(Vertex vertex){
            for (int i = 1; i <= count; i++){
                if (vertex.id == nodes[i].id){
                    nodes[i].dist = vertex.dist;
                }
            }
        }

        public boolean isEmpty(){
            return count == 0;
        }
    }

    /**
     * s->t的最短路径
     * @param s
     * @param t
     */
    public void dijkstra(int s, int t){
        int[] predecessor = new int[this.v]; //用来还原最短路径
        Vertex[] vertices = new Vertex[this.v];
        //初始化每个顶点到起始顶点的最大值为无穷大
        for (int i = 0; i < this.v; i++){
            vertices[i] = new Vertex(i, Integer.MAX_VALUE);
        }
        PriorityQueue priorityQueue = new PriorityQueue(this.v); //小顶堆
        boolean[] inqueue = new boolean[this.v]; //记录是否入列过,避免一个顶点被多次添加到优先队列里面
        vertices[s].dist = 0;
        inqueue[s] = true;
        priorityQueue.add(vertices[s]);
        while (!priorityQueue.isEmpty()){
            Vertex minVertex = priorityQueue.poll(); //取出小顶堆的堆顶，并且删除
            if (minVertex.id == t){ //最短路径产生
                break;
            }
            for (int i = 0; i < adj[minVertex.id].size(); i++){
                Edge edge = adj[minVertex.id].get(i);
                Vertex nextVertex = vertices[edge.tid];
                if (minVertex.dist + edge.weight < nextVertex.dist){
                    nextVertex.dist = minVertex.dist + edge.weight;
                    predecessor[nextVertex.id] = minVertex.id;
                    if (inqueue[nextVertex.id]){
                        priorityQueue.update(nextVertex);
                    }else {
                        inqueue[nextVertex.id] = true;
                        priorityQueue.add(nextVertex);
                    }
                }
            }
        }

        System.out.println("输出最短路径");
        print(predecessor, s, t);
    }

    private void print(int[] predecessor, int s, int t){
        if (s == t){
            return;
        }
        print(predecessor, s, predecessor[t]);
        System.out.print("->" + t);
    }


    public static void main(String[] args){
        System.out.print("最短路径");

        int vertexNumber = 6;
        DijkstraGraph dijkstraGraph = new DijkstraGraph(vertexNumber);
        dijkstraGraph.addEdge(0, 1, 10);
        dijkstraGraph.addEdge(0, 4, 15);
        dijkstraGraph.addEdge(1, 2, 15);
        dijkstraGraph.addEdge(1, 3, 2);
        dijkstraGraph.addEdge(2, 5, 5);
        dijkstraGraph.addEdge(3, 2, 1);
        dijkstraGraph.addEdge(3, 5, 12);
        dijkstraGraph.addEdge(4, 5, 10);

        int s = 0;
        int t = 5;
        //0->1->3->2->5
        dijkstraGraph.dijkstra(s, t);

    }
}
