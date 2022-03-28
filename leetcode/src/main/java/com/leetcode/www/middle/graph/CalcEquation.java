package com.leetcode.www.middle.graph;

import java.util.*;

/**
 * leetcode399(除法求值):给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。
 * 每个 Ai 或 Bi 是一个表示单个变量的字符串。另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出
 * Cj / Dj = ? 的结果作为答案。返回所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，
 * 也需要用 -1.0 替代这个答案。注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
 *
 */
public class CalcEquation {


    /**
     * 将这个问题建模成一张图:给定图中的一些点(变量)，以及某些边的权值(两个变量的比值)，对任意两点(两个变量)求出其路径长(两个变量的比值)。因此首先遍历equations
     *                    数组，找出其中所有不同的字符串，利用哈希表将每个不同的字符串映射成整数。构建玩图后，对于任何一个查询，就可以从起点出发，通过广度优先
     *                    搜索的方式，不断更新起点和当前点之间的路径长度，直到搜索到终点为止
     * 复杂度分析
     *      时间复杂度:O(ML+Q(L+M)),M为边的数量，Q为询问的数量，L为字符串的平均长度，构建图时，需要处理M条边，每条边都涉及到O(L)的字符串比较；处理查询时，
     *               每次查询首先要进行O(L)的比较，至多遍历O(M)条边
     *      空间复杂度:O(NL+M)，其中N为点的数量，M为边的数量，L为字符串的平均长度。为了将每个字符串映射到整数，需要开辟空间为O(NL)的哈希表，同时需要花费O(M)
     *               的空间存储每条边的权重；处理查询时，还需要O(N)的空间维护队列，最终总的空间复杂度为O(NL+M+N) = O(NL+M)
     * @return
     */
    public double[] solution(List<List<String>> equations, double[] values, List<List<String>> queries){

        int nvars = 0;
        Map<String, Integer> variables = new HashMap<>();

        int n = equations.size();
        for (int i = 0; i < n; i++){
            if (!variables.containsKey(equations.get(i).get(0))){
                variables.put(equations.get(i).get(0), nvars++);
            }
            if (!variables.containsKey(equations.get(i).get(1))){
                variables.put(equations.get(i).get(1), nvars++);
            }
        }

        // 对于每个点，存储其直接连接到的所有点及其对应的权值
        List<Pair>[] edges = new List[nvars];
        for (int i = 0; i < nvars; i++){
            edges[i] = new ArrayList<>();
        }

        //遍历equations，获取到每个顶点，并且去variables中找到这个顶点的序号
        for (int i = 0; i < n; i++){
            int va = variables.get(equations.get(i).get(0));
            int vb = variables.get(equations.get(i).get(1));
            edges[va].add(new Pair(vb, values[i]));
            edges[vb].add(new Pair(va, 1.0 / values[i]));
        }

        int queryCounts = queries.size();
        double[] results = new double[queryCounts];
        for (int i = 0; i < queryCounts; i++){
            List<String> strings = queries.get(i);
            double result = -1.0;
            if (variables.containsKey(strings.get(0)) && variables.containsKey(strings.get(1))){

                int ia = variables.get(strings.get(0));
                int ib = variables.get(strings.get(1));
                if (ia == ib){
                    result = 1.0;
                }else {
                    Queue<Integer> points = new LinkedList<>();
                    points.offer(ia);
                    double[] ratios = new double[nvars];
                    Arrays.fill(ratios, -1);
                    ratios[ia] = 1.0;

                    while (!points.isEmpty() && ratios[ib] < 0){
                        int x = points.poll();
                        for (Pair pair : edges[x]){
                            int y = pair.index;
                            double val = pair.value;
                            if (ratios[y] < 0){
                                ratios[y] = ratios[x] * val;
                                points.offer(y);
                            }
                        }
                    }

                    result = ratios[ib];
                }
            }
            results[i] = result;
        }

        return results;
    }

    public static class Pair{

        int index;
        double value;
        Pair(int index, double value){
            this.index = index;
            this.value = value;
        }
    }


    public static void main(String[] args) {

        CalcEquation calcEquation = new CalcEquation();

        List<List<String>> equations = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list2.add("b");
        list2.add("c");
        equations.add(list1);
        equations.add(list2);


        double[] values = new double[]{2.0, 3.0};

        List<List<String>> queries = new ArrayList<>();
        List<String> qList1 = new ArrayList<>();
        List<String> qList2 = new ArrayList<>();
        List<String> qList3 = new ArrayList<>();
        List<String> qList4 = new ArrayList<>();
        List<String> qList5 = new ArrayList<>();
        qList1.add("a");
        qList1.add("c");
        qList2.add("b");
        qList2.add("a");
        qList3.add("a");
        qList3.add("e");
        qList4.add("a");
        qList4.add("a");
        qList5.add("x");
        qList5.add("x");
        queries.add(qList1);
        queries.add(qList2);
        queries.add(qList3);
        queries.add(qList4);
        queries.add(qList5);

        double[] ans = calcEquation.solution(equations, values, queries);
        System.out.println(Arrays.toString(ans));
    }
}
