package com.leetcode.www.hard.string;

import java.util.*;

/**
 * leetcode-127:单词接龙
 * 字典wordList 中从单词 beginWord和 endWord 的 转换序列 是一个按下述规格形成的序列beginWord -> s1-> s2-> ... -> sk：
     * 每一对相邻的单词只差一个字母。
     * 对于1 <= i <= k时，每个si都在wordList中。注意， beginWord不需要在wordList中。
     * sk == endWord
 * 给你两个单词 beginWord和 endWord 和一个字典 wordList ，返回 从beginWord 到endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0 。
 */
public class LadderLength {


    /**
     * 图+广度优先遍历:题目要求求出最短转换序列，就可以联想到图中两个顶点间的最短路径，可以通过广度优先搜索得到。因此可以利用题目给定的单词，构建出一张图，同时由于
     *              相邻单词只差一个字母，转换就是可以逆向的，因此这张图就是无向图。图构建完成了，再进行广度优先遍历，求得最短路径，就是要求的答案，步骤如下
     *              1. 先将所有单词放入一个哈希表中，然后在遍历的时候构建图,每次得到在单词列表中可以转换的单词，复杂度为O(26*wordLen),借助哈希表，找到邻居与
     *                 单词列表长度无关
     *              2. 进行BFS遍历，需要的辅助数据结构是
     *                 1. 队列
     *                 2. visited集合。记录遍历过的字符串
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int solution(String beginWord, String endWord, List<String> wordList){

        //先把wordList放入一个哈希表，便于判断是否包含某个单词
        Set<String> wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord)){
            return 0;
        }
        //beginWord不需要在wordList中
        wordSet.remove(beginWord);

        //图的广度优先遍历的辅助数据结构
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        //开始进行广度优先遍历，包含起点，因此初始化步数为1
        int step = 1;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                String currentWord = queue.poll();
                //如果currentWord能够修改一个字符后与endWord相同，则返回step+1
                if (changeWordEveryLetter(currentWord, endWord, queue, visited, wordSet)){
                    return step+1;
                }
            }
            ++step;
        }

        return 0;
    }


    /**
     * currentWord修改每一个字符，看能否和endWord匹配
     * @param currentWord
     * @param endWord
     * @param queue
     * @param visited
     * @param wordSet
     * @return
     */
    private boolean changeWordEveryLetter(String currentWord, String endWord, Queue<String> queue, Set<String> visited, Set<String> wordSet){


        char[] currentWordChars = currentWord.toCharArray();
        for (int i = 0; i < endWord.length(); i++){
            //先保存，然后恢复
            char origin = currentWordChars[i];
            for (char c = 'a'; c <= 'z'; c++){
                if (c == origin){
                    continue;
                }
                currentWordChars[i] = c;
                String nextWord = String.valueOf(currentWordChars);
                if (wordSet.contains(nextWord)){
                    if (nextWord.equals(endWord)){
                        return true;
                    }
                    if (!visited.contains(nextWord)){
                        queue.add(nextWord);
                        //添加到队列后马上被标记
                        visited.add(nextWord);
                    }
                }
            }
            currentWordChars[i] = origin;
        }

        return false;
    }


    /**
     * 双向广度优先遍历:已经起点和终点(目标顶点)，可以分别从起点和终点执行广度优先遍历，直到遍历的部分有交集，这样单词的搜索量会更小，同时每次才从单词数量小的
     *               集合开始扩散
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int solutionV2(String beginWord, String endWord, List<String> wordList){

        Set<String> wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord)){
            return 0;
        }
        wordSet.remove(beginWord);


        Set<String> visited = new HashSet<>();
        //分别用左边和右边扩散的哈希表代替单向BFS里的队列，在双向BFS中交替使用
        Set<String> beginVisited = new HashSet<>();
        beginVisited.add(beginWord);
        Set<String> endVisited = new HashSet<>();
        endVisited.add(endWord);

        //开始执行双向BFS,左右交替扩展的步数之和就是答案
        int step = 1;
        while (!beginVisited.isEmpty() && !endVisited.isEmpty()){
            if (beginVisited.size() > endVisited.size()){
                Set<String> tmp = beginVisited;
                beginVisited = endVisited;
                endVisited = tmp;
            }

            //扩散完成后，nextLevelVisited成为新的beginVisited
            Set<String> nextLevelVisited = new HashSet<>();
            for (String word : beginVisited){
                if (change(word, endVisited, visited, wordSet, nextLevelVisited)){
                    return step + 1;
                }
            }
            beginVisited = nextLevelVisited;
            ++step;
        }
        return 0;
    }


    /**
     * 尝试对word修改每一个字符得到的新的单词，判断是否在endVisited里面，扩展得到的新的word添加到nextLevelVisited里面
     * @param word
     * @param endVisited
     * @param visited
     * @param wordSet
     * @param nextLevelVisited
     * @return
     */
    private boolean change(String word, Set<String> endVisited, Set<String> visited, Set<String> wordSet, Set<String> nextLevelVisited){

        char[] charArr = word.toCharArray();
        for (int i = 0; i < word.length(); i++){
            char origin = charArr[i];
            for (char c = 'a'; c <= 'z'; c++){
                if (c == origin){
                    continue;
                }
                charArr[i] = c;
                String nextWord = String.valueOf(charArr);
                if (wordSet.contains(nextWord)){
                    if (endVisited.contains(nextWord)){
                        return true;
                    }
                    if (!visited.contains(nextWord)){
                        nextLevelVisited.add(nextWord);
                        visited.add(nextWord);
                    }
                }
            }
            charArr[i] = origin;
        }

        return false;
    }

    public static void main(String[] args) {

        LadderLength ladderLength = new LadderLength();
        String beginWord = "hit";
        String endWord = "cog";

        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");

        int ans = ladderLength.solution(beginWord, endWord, wordList);
        System.out.println(ans);


        int ans1 = ladderLength.solution(beginWord, endWord, wordList);
        System.out.println(ans1);
    }
}
