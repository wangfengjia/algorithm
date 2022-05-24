package com.leetcode.www.hard.matrix;

import java.util.*;

/**
 * leetcode-675:为高尔夫比赛砍树
 * 你被请来给一个要举办高尔夫比赛的树林砍树。树林由一个m x n 的矩阵表示， 在这个矩阵中：
     * 0 表示障碍，无法触碰
     * 1表示地面，可以行走
     * 比 1 大的数表示有树的单元格，可以行走，数值表示树的高度
 * 每一步，你都可以向上、下、左、右四个方向之一移动一个单位，如果你站的地方有一棵树，那么你可以决定是否要砍倒它。
 * 你需要按照树的高度从低向高砍掉所有的树，每砍过一颗树，该单元格的值变为 1（即变为地面）。
 * 你将从 (0, 0) 点开始工作，返回你砍完所有树需要走的最小步数。 如果你无法砍完所有的树，返回 -1 。
 * 可以保证的是，没有两棵树的高度是相同的，并且你至少需要砍倒一棵树。
 */
public class CutOffTree {


    /**
     * BFS
     * 首先对矩阵中的树按照树的高度进行排序，依次求出相邻的树之间的最短距离。利用广度优先遍历，按照层次遍历，处理队列中的节点(网格位置)。使用visited记录已经添加到
     * 队列中的节点。对于下一个要处理的节点，查看他们四个方向相邻的节点。如果相邻的节点没有被访问过且不是障碍，则添加到队列中，直到找到终点为止。返回当前的步数即可，
     * 最后返回所有的步数之和
     *
     * 复杂度分析
     *  时间复杂度:O(m^2 * n^2),m和n分别是矩阵的行数和列数。矩阵中最多有m * n颗树,对树进行排序，时间复杂度为O(m * n * log(m * n))。利用广度优先搜索两棵树
     *           之间的距离需要的时间是O(m * n),因此总的时间复杂度为O(m * n * log(m * n) + m^2 * n^2)=O(m^2 * n^2)
     *  空间复杂度:O(m * n),m和n分别是矩阵的行数和列数，对树进行排序需要的栈空间为O(m * n)。广度优先搜索队列中的元素最多有O(m * n)个元素，标记已经遍历过的元素
     *           需要的空间为O(m * n)，因此总的空间复杂度为O(m * n)
     * @param forest
     * @return
     */
    public int solution(List<List<Integer>> forest){

        List<int[]> trees = new ArrayList<>();
        int rowNums = forest.size();
        int colNums = forest.get(0).size();
        for (int row = 0; row < rowNums; row++){
            for (int col = 0; col < colNums; col++){
                if (forest.get(row).get(col) > 1){
                    trees.add(new int[]{row, col});
                }
            }
        }

        Collections.sort(trees, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return forest.get(o1[0]).get(o1[1]) - forest.get(o2[0]).get(o2[1]);
            }
        });

        int sourceX = 0;
        int sourceY = 0;
        int ans = 0;
        for (int i = 0; i < trees.size(); i++){
            int step = bfs(forest, sourceX, sourceY, trees.get(i)[0], trees.get(i)[1]);
            if (step == -1){
                return -1;
            }
            ans += step;
            sourceX = trees.get(i)[0];
            sourceY = trees.get(i)[1];
        }

        return ans;
    }

    private int bfs(List<List<Integer>> forest, int sourceX, int sourceY, int targetX, int targetY){

        if (sourceX == targetX && sourceY == targetY){
            return 0;
        }

        int rowNums = forest.size();
        int colNums = forest.get(0).size();
        boolean[][] visited = new boolean[rowNums][colNums];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{sourceX, sourceY});
        visited[sourceX][sourceY] = true;
        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
        int step = 0;

        while (!queue.isEmpty()){
            ++step;
            int size = queue.size();
            for (int i = 0; i < size; i++){
                int[] cell = queue.poll();
                for (int j = 0; j < 4; j++){
                    int newX = cell[0] + dirs[j][0];
                    int newY = cell[1] + dirs[j][1];
                    if (newX >= 0 && newX < rowNums && newY >= 0 && newY < colNums){
                        if (!visited[newX][newY] && forest.get(newX).get(newY) > 1){
                            if (newX == targetX && newY == targetY){
                                return step;
                            }
                            queue.add(new int[]{newX, newY});
                            visited[newX][newY] = true;
                        }
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        CutOffTree cutOffTree = new CutOffTree();

        List<List<Integer>> forest = new ArrayList<>();

        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        List<Integer> list2 = new ArrayList<>();
        list2.add(0);
        list2.add(0);
        list2.add(0);
        List<Integer> list3 = new ArrayList<>();
        list3.add(7);
        list3.add(6);
        list3.add(5);

        forest.add(list1);
        forest.add(list2);
        forest.add(list3);

        int ans = cutOffTree.solution(forest);
        System.out.println(ans);
    }
}
