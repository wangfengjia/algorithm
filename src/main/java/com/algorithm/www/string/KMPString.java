package com.algorithm.www.string;


/**
 * kmp算法，时间复杂度O(m+n)
 *s
 * @author wangyongchun
 * @date 2019/07/14 14:43
 */
public class KMPString {

    public int kmp(char[] a, int n, char[] b, int m){
        int[] nexts = getNexts(b, m);
        int j = 0;

        for (int i = 0; i < n; i++){
            while (j > 0 && a[i] != b[j]){ //直到找到a[i] == a[j]
                j = nexts[j - 1] + 1;
            }

            //如果相等，则模式串的指针加1
            if (a[i] == b[j]){
                ++j;
            }

            //表示匹配到了
            if (j == m){
                return i - m + 1;
            }
        }

        return -1;

    }

    public int[] getNexts(char[] b, int m){
        int[] nexts = new int[m];
        nexts[0] = -1;
        int k = -1;

        //下标为1的时候才有后缀
        for (int i = 1; i < m; ++i){

            //递归找最长前缀子串的最长前缀子串
            while (k != -1 && b[k + 1] != b[i]){
                k = nexts[k];
            }
            if (b[k + 1] == b[i]){
                ++k;
            }

            nexts[i] = k;
        }

        return nexts;
    }



    public static void main(String[] args){

        KMPString kmpString = new KMPString();
        char[] a = {'a', 'b', 'a', 'b', 'a', 'e', 'a', 'b', 'a', 'c'};
        char[] b = {'a', 'e', 'a', 'b'};
        int kmp = kmpString.kmp(a, a.length, b, b.length);
        System.out.println(kmp);
    }
}
