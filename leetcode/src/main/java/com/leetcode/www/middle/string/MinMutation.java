package com.leetcode.www.middle.string;


import java.util.*;

/**
 * leetcode-433:最小基因变化
 * 基因序列可以表示为一条由 8 个字符组成的字符串，其中每个字符都是 'A'、'C'、'G' 和 'T' 之一。
 * 假设我们需要调查从基因序列start 变为 end 所发生的基因变化。一次基因变化就意味着这个基因序列中的一个字符发生了变化。
    * 例如，"AACCGGTT" --> "AACCGGTA" 就是一次基因变化。
 * 另有一个基因库 bank 记录了所有有效的基因变化，只有基因库中的基因才是有效的基因序列。
 * 给你两个基因序列 start 和 end ，以及一个基因库 bank ，请你找出并返回能够使start 变化为 end 所需的最少变化次数。如果无法完成此基因变化，返回 -1 。
 * 注意：起始基因序列start 默认是有效的，但是它并不一定会出现在基因库中。
 */
public class MinMutation {


    /**
     * 使用广度优先搜索，类似于leetcode-127单词接龙题
     * 复杂度分析
     *  时间复杂度:O(C * m * n),n是基因序列的长度，m是数组bank的长度，C是数组key是的长度，对于队列中每个合法的基因序列需要计算C*n种变化，队列中最多m个
     *           元素,因此时间复杂度为O(C * m * n)
     *  空间复杂度:O(n * m),n是基因序列的长度，m是数组bank的长度，哈希表cnt中最多m个元素，队列最多m个元素，每个元素的空间是O(n),所以空间复杂度为O(n * m)
     * @param start
     * @param end
     * @param bank
     * @return
     */
    public int solution(String start, String end, String[] bank){

        Set<String> cnt = new HashSet<>();
        Set<String> visited = new HashSet<>();
        char[] keys = {'A', 'C', 'G', 'T'};
        for (String s : bank){
            cnt.add(s);
        }

        if (start.equals(end)){
            return 0;
        }
        if (!cnt.contains(end)){
            return -1;
        }

        Deque<String> deque = new ArrayDeque<>();
        deque.add(start);
        visited.add(start);
        int step = 1;
        while (!deque.isEmpty()){
            int size = deque.size();
            for (int i = 0; i < size; i++){
                String curr = deque.poll();
                for (int j = 0; j < 8; j++){
                    for (int k = 0; k < 4; k++){
                        if (curr.charAt(j) != keys[k]){
                            StringBuffer sb = new StringBuffer(curr);
                            sb.setCharAt(j, keys[k]);
                            String next = sb.toString();
                            if (cnt.contains(next) && !visited.contains(next)){
                                if (next.equals(end)){
                                    return step;
                                }
                                deque.add(next);
                                visited.add(next);
                            }
                        }
                    }
                }
            }
            ++step;
        }

        return -1;
    }


    /**
     * 可以对bank数组进行预处理，得到这个数组中每个字符串的合法变换，而不需要像方法一中每次需要去计算字符串的变化序列，并将这个合法变化关系存储在一个邻接表中，
     * 这样每次基因变化的搜索只需要在邻接表中进行即可
     * 复杂度分析
     *  时间复杂度:O(m * n^2),m为基因序列的长度,n为数组bank的长度，计算合法的字符串变化需要的时间为O(m * n^2)。广度优先搜索时,队列中最多n个元素，需要的时间
     *           为O(n),因此时间复杂度为O(m * n^2)
     *  空间复杂度:O(n^2),n是数组bank的长度，邻接表需要的空间是O(n^2),队列中最多有n个元素，因此空间复杂度为O(n^2)
     * @param start
     * @param end
     * @param bank
     * @return
     */
    public int solutionV2(String start, String end, String[] bank){


        int m = start.length();
        int n = bank.length;
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++){
            adj[i] = new ArrayList<>();
        }

        int endIndex = -1;
        for (int i = 0; i < n; i++){
            if (bank[i].equals(end)){
                endIndex = i;
            }
            for (int j = i + 1; j < n; j++){
                int mutations = 0;
                for (int k = 0; k < m; k++){
                    if (bank[i].charAt(k) != bank[j].charAt(k)){
                        ++mutations;
                    }
                    if (mutations > 1){
                        break;
                    }
                }
                if (mutations == 1){
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
        }

        if (endIndex == -1){
            return -1;
        }
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++){
            int mutations = 0;
            for (int j = 0; j < m; j++){
                if (start.charAt(j) != bank[i].charAt(j)){
                    ++mutations;
                }
                if (mutations > 1){
                    break;
                }
            }

            if (mutations == 1){
                queue.add(i);
                visited[i] = true;
            }
        }


        int step = 1;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                Integer cur = queue.poll();
                if (cur == endIndex){
                    return step;
                }
                for (int next : adj[cur]){
                    if (visited[next]){
                        continue;
                    }
                    visited[next] = true;
                    queue.add(next);
                }
            }
            ++step;
        }
        return -1;
    }

    public static void main(String[] args) {

        MinMutation minMutation = new MinMutation();
        String start = "AAAAACCC";
        String end = "AACCCCCC";
        String[] bank = {"AAAACCCC","AAACCCCC","AACCCCCC"};
        int ans = minMutation.solutionV2(start, end, bank);
        System.out.println(ans);
    }
}
