package com.algorithm.www.dynamicprogramming;

/**
 * 莱文斯坦距离
 * 问题描述
 * 1.使用编辑距离来表示两个字符串之间的相似度，编辑距离就是一个字符串转换到另一个字符串的最少编辑操作次数
 * 2.莱文斯坦距离允许增加，删除，替换字符这三个操作
 *
 */
public class LwstBT {

    private char[] a;
    private char[] b;
    private int aLength;
    private int bLength;
    private int minDist = Integer.MAX_VALUE;

    public LwstBT(char[] a, char[] b){
        this.a = a;
        this.b = b;
        this.aLength = a.length;
        this.bLength = b.length;
    }

    /**
     * 使用回溯法来求莱文斯坦距离
     * 思路
     * 1.当a[i]与b[j]匹配，则递归考察a[i+1]和b[j+1]
     * 2.如果a[i]和b[j]不匹配，有如下的处理办法,下面每个处理办法处理过后，编辑操作次数都加1
     *  a.可以在a[i]前面增加一个和b[j]一样的字符，然后递归考察a[i]和b[j+1]
     *  b.可以在b[j]前面增加一个和a[i]相同的字符，然后递归考察a[i+1]和b[j]
     *  c.删除a[i],然后递归考察a[i+1]和b[j]
     *  d.删除b[j]，然后递归考察a[i]和b[j+1]
     *  e.可以将a[i]替换成b[j],或者将b[j]替换成a[i],然后递归考察a[i+1][j+1]
     * @param i 第一个字符串的下标
     * @param j 第二个字符串的下标
     * @param eDist 莱文斯坦距离，也就是最少编辑次数
     * @return
     */
    public void lwstDisByGreedyAlgo(int i, int j, int eDist){

        //有一个字符串已经遍历完了
        if (i == aLength || j == bLength){
            //判断哪个字符串还没有遍历完，然后加上删除剩余字符的操作次数
            if (i < aLength){
                eDist += aLength - i;
            }
            if (j < bLength){
                eDist += bLength - j;
            }

            if (eDist < minDist){
                minDist = eDist;
            }

            return;
        }

        //a[i]=b[j]
        if (a[i] == b[j]){
            lwstDisByGreedyAlgo(i + 1, j + 1, eDist);
        }else {
            //在a[i]前面增加一个和b[j]相同的字符，或者删除a[i]
            lwstDisByGreedyAlgo(i + 1, j, eDist + 1);
            //在b[j]前面添加一个和a[i]相同的字符，或者删除b[j]
            lwstDisByGreedyAlgo(i, j + 1, eDist + 1);
            //将a[i]替换成b[j],或者b[j]替换成a[i]
            lwstDisByGreedyAlgo(i + 1, j + 1, eDist + 1);
        }
    }

    /**
     * 使用动态规划求莱文斯坦距离
     * 状态转移方程(不是很理解怎么来的)
     *  a.如果a[i]=b[j], min_lwst(i,j) = min(min_lwst(i-1, j)+1,min_lwst(i,j-1)+1,min_lwst(i-1,j-1))
     *  b.如果a[i]!=b[j],min_lwst(i,j) = min(min_lwst(i-1,j)+1,min_lwst(i,j-1)+1,min_lswt(i-1,j-1)+1)
     * @param a
     * @param n
     * @param b
     * @param m
     * @return
     */
    public int lwstDisByDp(char[] a, int n, char[] b, int m){
        int[][] minDist = new int[n][m];
        //初始化第0行:a[0..0]与b[0..j]的编辑距离
        for (int j = 0; j < m; j++){
            if (a[0] == b[j]){
                minDist[0][j] = j; //碰到相等转化方式就是把前面的字符删除
            }else if (j != 0){
                minDist[0][j] = minDist[0][j - 1] + 1;
            }else {
                minDist[0][j] = 1;//当a[0]不等于j[0]时的初始值
            }
        }

        //初始化第0列:a[0..i]与b[0]的编辑距离
        for (int i = 0; i < n; i++){
            if (a[i] == b[0]){
                minDist[i][0] = i;
            }else if (i != 0){
                minDist[i][0] = minDist[i - 1][0] + 1;
            }else {
                minDist[i][0] = 1;
            }
        }

        //按行填表
        for (int i = 1; i < n; i++){
            for (int j = 1; j < m; j++){
                if (a[i] == b[j]){
                    minDist[i][j] = min(minDist[i - 1][j] + 1, minDist[i][j - 1] + 1, minDist[i - 1][j - 1]);
                }else {
                    minDist[i][j] = min(minDist[i - 1][j] + 1, minDist[i][j - 1] + 1, minDist[i - 1][j - 1] + 1);
                }
            }
        }

        return minDist[n - 1][m - 1];
    }

    private int min(int i, int j, int k){
        int minV = Integer.MAX_VALUE;
        if (i < minV){
            minV = i;
        }
        if (j < minV){
            minV = j;
        }
        if (k < minV){
            minV = k;
        }

        return minV;
    }

    /**
     * 最长公共子串长度，只可以增加和删除字符串中的字符
     * 思路
     * 1.当a[i]和b[j]相等的话，最长公共子串长度加1，然后继续考察a[i+1]和b[j+1]
     * 2.当a[i]和b[j]不相等的话，最长公共子串长度不变，这个时候有两个决策路线
     *  a.删除a[i],或者在b[j]前面加上和a[i]相同的字符，然后继续考察a[i+1]和b[j]
     *  b.删除b[j],或者在a[i]前面加上和b[j]相同的字符，然后继续考察a[i]和b[j+1]
     * 3.根据1，2知道，a[0..i]和b[0...j]最长公共子串长度max_lcs(i,j),我们只有可能从以下三个状态转移过来
     *  a.(i-1, j-1,max_lcs),其中max_lcs表示a[0...i-1]和b[0...j-1]最长公共子串长度
     *  b.(i-1, j, max_lcs),其中max_lcs表示a[0...i-1]和b[0...j]最长公共子串长度
     *  c.(i, j - 1, max_lcs),其中max_lcs表示a[0...i]和b[0...j-1]最长公共子串长度
     * 4.状态转移方程
     *  a.如果a[i]=b[j],max_lcs(i,j) = max(max_lcs(i-1, j-1)+1, max_lcs(i, j-1), max(i-1,j))
     *  b.如果a[i]!=b[j],max_lcs(i,j) = max(max_lcs(i-1, j-1), max_lcs(i, j-1), max(i-1,j))
     * @param a
     * @param n
     * @param b
     * @param m
     * @return
     */
    public int lcsDp(char[] a, int n, char[] b, int m){

        int[][] lcs = new int[n][m];
        //初始化第一行
        for (int j = 0; j < m; j++){
            if (a[0] == b[j]){
                lcs[0][j] = 1;
            }else if (j != 0){
                lcs[0][j] = lcs[0][j - 1];
            }else {
                lcs[0][j] = 0;
            }
        }

        //初始化第一列
        for (int i = 0; i < n; i++){
            if (a[i] == b[0]){
                lcs[i][0] = 1;
            }else if (i != 0){
                lcs[i][0] = lcs[i - 1][0];
            }else {
                lcs[i][0] = 0;
            }
        }

        //按行填表
        for (int i = 1;i < n; i++){
            for (int j = 1; j < m; j++){
                if (a[i]== b[j]){
                    lcs[i][j] = max(lcs[i - 1][j - 1] + 1, lcs[i - 1][j], lcs[i][j - 1]);
                }else {
                    lcs[i][j] = max(lcs[i - 1][j - 1], lcs[i - 1][j], lcs[i][j - 1]);
                }
            }
        }

        return lcs[n - 1][m - 1];
    }

    private int max(int i, int j, int k){
        int max = Integer.MIN_VALUE;
        if (i > max){
            max = i;
        }
        if (j > max){
            max = j;
        }
        if (k > max){
            max = k;
        }

        return max;
    }


    public static void main(String[] args){

        char[] a = {'m', 'i', 't', 'c', 'm', 'u'};
        int aLength = a.length;
        char[] b = {'m', 't', 'a', 'c', 'n', 'u'};
        int bLength = b.length;
        LwstBT lwstBT = new LwstBT(a, b);
        int dis = lwstBT.lwstDisByDp(a, aLength, b, bLength);
        System.out.println("莱文斯坦距离为_" + dis);

        int lcs = lwstBT.lcsDp(a, aLength, b, bLength);
        System.out.println("最长公共子串长度为_" + lcs);

    }
}
