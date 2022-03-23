package com.algorithm.www.advanced;

import java.util.LinkedList;

/**
 * A*算法
 * @author wangyongchun
 * @date 2019/07/30
 */
public class ASearchGraph {

    public LinkedList<Edge> adj[]; //邻接表
    public int v;//顶点个数
    public Vertex[] vertices;

    public ASearchGraph(int v){
        this.v = v;
        for (int i = 0; i < v; i++){
            adj[i] = new LinkedList<Edge>();
        }
        vertices = new Vertex[v];
    }

    public void addEdge(int i, int j, int weight){
        adj[i].add(new Edge(i, j, weight));
    }

    /**
     * 添加顶点的坐标
     * @param id
     * @param x
     * @param y
     */
    public void addVertex(int id, int x, int y){
        vertices[id] = new Vertex(id, x, y);
    }

    private class Edge{

        public int sid; //边的起始顶点
        public int tid; //边的终止顶点
        public int weight; //边的权重

        public Edge(int sid, int tid, int weight){
            this.sid = sid;
            this.tid = tid;
            this.weight = weight;
        }
    }

    private class Vertex{

        public int id; //顶点id
        public int dist; //顶点到起点的距离，也就是g(i)
        public int f; //f(i) = g(i) + h(i)(曼哈顿距离:两点之间横纵坐标的距离之和)
        public int x;
        public int y;

        public Vertex(int id, int x, int y){
            this.id = id;
            this.dist = Integer.MAX_VALUE;
            this.f = Integer.MAX_VALUE;
            this.x = x;
            this.y = y;
        }
    }

    private class PriorityQueue{ //构建小顶堆

        public Vertex[] nodes;
        public int capacity;
        public int count;

        public PriorityQueue(int capacity){
            nodes = new Vertex[capacity + 1];
            this.count = 0;
            this.capacity = capacity;
        }

        public boolean insert(Vertex vertex){
            if (count >= capacity){
                return false;
            }
            ++count;
            nodes[count] = vertex;
            int i = count;
            while (i / 2 > 0 && nodes[i].f < nodes[i / 2].f){
                swap(nodes, i, i / 2);
                i = i / 2;
            }

            return true;
        }

        public Vertex poll(){
            if (count == 0){
                return null;
            }
            Vertex temp = nodes[1];
            nodes[1] = nodes[count];
            --count;
            heapify(nodes, count, 1);
            return temp;
        }

        /**
         * 从上往下堆化
         * @param nodes
         * @param count
         * @param i
         */
        private void heapify(Vertex[] nodes, int count, int i){

            while (true){
                int minPos = i;
                if (i * 2 <= count && nodes[i * 2].f < nodes[i].f){
                    minPos = i * 2;
                }
                if (i * 2 + 1 <= count && nodes[i * 2 + 1].f < nodes[minPos].f){
                    minPos = i * 2 + 1;
                }
                if (minPos == i){
                    break;
                }

                swap(nodes, minPos, i);
                i = minPos;
            }
        }

        private void swap(Vertex[] nodes, int source, int des){
            Vertex temp = nodes[des];
            nodes[des] = nodes[source];
            nodes[source] = temp;
        }

        public void update(Vertex vertex){
            for (int i = 1; i <= count; i++){
                if (vertex.id == nodes[i].id){
                    nodes[i].dist = vertex.dist;
                    nodes[i].f = vertex.f;
                }
            }
        }

        public boolean isEmpty(){
            return count == 0;
        }

        public void clear(){
            count = 0;
        }

    }

    /**
     * 曼哈顿距离:两点之间横纵坐标的距离之和
     * @param v1
     * @param v2
     * @return
     */
    public int hManhattan(Vertex v1, Vertex v2){
        return Math.abs(v1.x - v2.x) + Math.abs(v1.y - v2.y);
    }

    /**
     * 从顶点s到顶点t
     * @param s
     * @param t
     */
    public void aStar(int s, int t){
        int[] predecessor = new int[this.v]; //用来还原路径，也就是记录路径上一个节点的前继节点
        PriorityQueue queue = new PriorityQueue(this.v); //小顶堆，利用vertex的f值来构建小顶堆

        boolean[] inqueue = new boolean[this.v]; //用来记录已经进入优先级队列的节点
        vertices[s].dist = 0;
        vertices[s].f = 0;
        inqueue[s] = true;
        queue.insert(vertices[s]);
        while (!queue.isEmpty()){
            Vertex minVertex = queue.poll();
            for (int i = 0; i < adj[minVertex.id].size(); i++){
                Edge edge = adj[minVertex.id].get(i);
                Vertex nextVertex = vertices[edge.tid];
                if (minVertex.dist + edge.weight < nextVertex.dist){
                    nextVertex.dist = minVertex.dist + edge.weight;
                    nextVertex.f = hManhattan(nextVertex, vertices[t]);
                    predecessor[nextVertex.id] = minVertex.id;
                    if (inqueue[nextVertex.id]){
                        queue.update(nextVertex);
                    }else {
                        queue.insert(nextVertex);
                    }
                }

                //只需要遍历到了顶点t就结束，不需要像dijkstra算法，需要t从优先级队列出列的时候才结束
                if (nextVertex.id == t){
                    queue.clear(); //清空queue才能退出循环
                    break;
                }
            }
        }

        System.out.print("输出路径");
        print(predecessor, s, t);
    }

    private void print(int[] predecessor, int s, int t){
        if (s == t){
            return;
        }
        print(predecessor, s, predecessor[t]);
        System.out.print("->" + t);
    }
}
