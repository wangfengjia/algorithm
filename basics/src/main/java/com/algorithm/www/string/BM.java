package com.algorithm.www.string;

/**
 * BM算法,时间复杂度O(n)
 * 思路
 * 坏字符规则(模式串移动的位数x)
 *  1.当模式串中某个字符与主串不匹配的时候，这个字符称为坏字符
 *  2.利用一个数组记录模式串中每个字符的位置，数组的下标为每个字符的ascii码，对应的值为字符在模式串中的下标
 *  2.坏字符对应模式串中字符的下标为si，模式串中查找坏字符，找到了的话坏字符的下标为xi,没找到则为-1
 *  3.运用坏字符规则时，模式串滑动的位数为x = si - xi
 * 好后缀规则 (模式串移动的位数y)
 *  1.模式串中的几个字符与主串匹配的时候，这几个字符串叫做好后缀
 *  2.在模式串中查找与好后缀匹配的另一个子串
 *  3.在好后缀的后缀子串中，查找最长，能跟模式串前缀子串匹配的后缀子串
 *  4.因为好后缀也是模式串的后缀子串，可以先利用两个数组suffix,prefix，
 *  a.suffix数组(下标为后缀子串的长度，值为另一个子串的起始下标)用来保存在模式串中与不同长度的好后缀匹配另一个子串的下标(subPos)，如果存在，则将模式串移动到
 *    子串{u*}和主串中{u}对齐的位置，模式串移动的位数为j(坏字符对应模式串字符的下标) - subPos + 1
 *  b.prefix数组(下标为后缀子串的长度，值为true/false)用来保存是否存在与不同长度的后缀子串匹配的前缀子串，这种情况就将
 *    模式串滑动到前缀子串与好后缀后缀子串匹配的位置，模式串移动的位数为k(与前缀子串匹配的好后缀后缀子串的起始小标值)
 * 模式串实际移动的位数
 *  利用坏字符规则得出来的移动的位数与好后缀规则得出来的移动的位数这两个数值更大的那一个
 *
 * @author wangyongchun
 * @date 2019/07/11 19:47
 */
public class BM {

    private static final int SIZE = 25600; //存储坏字符的下标数组大小

    /**
     * 存储坏字符的下标，数组的下标为坏字符的ASCII码，值为坏字符在模式串的位置
     * @param a
     * @param m
     * @param bc
     */
    private void generateBC(char[] a, int m, int[] bc){

        //初始化存储坏字符下标的数组
        for (int i = 0; i < SIZE; i++){
            bc[i] = -1;
        }

        for (int i = 0; i < m; i++){
            int ascii = (int) a[i];
            bc[ascii] = i;
        }
    }

    /**
     * bm算法实现
     * @param a
     * @param m
     * @param b
     * @param n
     * @return
     */
    public int bm(char[] a, int m, char[] b, int n){
        int[] bc = new int[SIZE];
        generateBC(b, n, bc); //坏字符的下标
        int[] suffix = new int[n];
        boolean[] prefix = new boolean[n];
        generateGS(b, n, suffix, prefix);
        int i = 0;
        while (i <= m - n){

            //寻找坏字符
            int j;
            for (j = n - 1; j >= 0; --j){
                if (a[i + j] != b[j]){
                    break;
                }
            }

            if (j < 0){
                return i; //匹配成功，返回主串与子串中第一个匹配的字符
            }

            //运用坏字符处理原则
            //坏字符对应模式串中字符的下标si, 坏字符在模式串中的小标为xi，不存在则为-1,则模式串往后滑动si - xi
            //相当于当前主串中的第一个与模式串对齐字符的下标往后移动si - xi位
            int x = j - bc[(int)a[i + j]];
            int y = 0;
            if (j < n - 1){ //说明有好后缀
                y = moveByGS(j, n, suffix, prefix);
            }

            i = i + Math.max(x, y);
        }

        return -1;
    }

    /**
     * 利用好后缀原则得到移动的位数
     * @param j 坏字符在模式串中的下标
     * @param n 模式串长度
     * @param suffix
     * @param prefix
     */
    private int moveByGS(int j, int n, int[] suffix, boolean[] prefix){
        int k = n - j - 1;
        //查找在模式串中是否与好后缀匹配的子串
        if (suffix[k] != -1){
            return j - suffix[k] + 1;
        }
        //查找与最长后缀子串与模式串前缀子串匹配
        for (int i = j + 2; i <= n - 1; i++){
            if (prefix[n - i]){
                return i;
            }
        }

        return n; //模式串的长度
    }

    /**
     *
     * @param a
     * @param m
     * @param suffix 模式串中与好后缀匹配的另外一个子串的起始下标值
     * @param prefix 模式串是否找到和好后缀最长后缀子串匹配的模式串的前缀子串
     */
    private void generateGS(char[] a, int m, int[] suffix, boolean[] prefix){
        for (int i = 0; i < m; ++i){
            suffix[i] = -1;
            prefix[i] = false;
        }

        //拿下标0到i(0 ~ m-2)的子串与整个模式串求公共后缀子串
        for (int i = 0; i < m - 1; i++){ //a[0,i]
            int j = i;
            int k = 0; //公共后缀子串的长度
            while (j >= 0 && a[j] == a[m - k -1]){ //与b[0, m-1]求公共后缀子串
                --j;
                ++k;
                suffix[k] = j + 1; //公共后缀子串在b[0,i]起始下标
            }
            if (j == -1){
                prefix[k] = true; //如果公共后缀子串也是模式串的前缀子串
            }
        }
    }


    public static void main(String[] args){
//        BM bm = new BM();
//        char[] a = {'a', 'b', 'f', 'h', 'k', 'm', 'i'};
//        int aLength = a.length;
//        char[] b = {'h', 'k', 'm'};
//        int bLength = b.length;
//
//        int i = bm.bm(a, aLength, b, bLength);
//        System.out.println(i);


        char[] a = {'a', 'b', 'c', 'd', 'e', 'f'};
        int m = a.length;
        char[] b = {'c', 'd', 'e'};
        int n = b.length;

        BM bm = new BM();
        int pos = bm.bm(a, m, b, n);
        System.out.println("主串中匹配模式串的起始下标为_" + pos);
    }
}
