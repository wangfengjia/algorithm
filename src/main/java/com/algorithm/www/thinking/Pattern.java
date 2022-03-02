package com.algorithm.www.thinking;

/**
 * 使用回溯实现正则表达式
 *
 * @author wangyongchun
 * @date 2019/07/19 11:47
 */
public class Pattern {

    private boolean matched = false;
    private char[] pattern;
    private int plen;

    public Pattern(char[] pattern, int plen){
        this.pattern = pattern;
        this.plen = plen;
    }

    public boolean match(char[] text, int tlen){
        matched = false;
        int ti = 0;
        int pi = 0;
        rmatch(ti, pi, text, tlen);
        return matched;
    }


    private void rmatch(int ti, int pi, char[] text, int tlen){
        if (matched){
            return;
        }
        if (pi == plen){ //正则表达式到了结尾
            if (ti == tlen){ //文本串也到了结尾
                matched = true;
            }
            return;
        }

        if (pattern[pi] == '*'){ //匹配任意字符
            for (int i = 0; i <= tlen - ti; i++){ //回溯查找
                rmatch(ti + i, pi + 1, text, tlen);
            }
        }else if (pattern[pi] == '?'){ //匹配0个或者一个字符
            rmatch(ti, pi + 1, text, tlen);
            rmatch(ti + 1, pi + 1, text, tlen);
        }else if (ti < tlen && pattern[pi] == text[ti]){
            rmatch(ti + 1, pi + 1, text, tlen);
        }
    }


    public static void main(String[] args){

        char[] pattern = {'o', 'a', 'c', 'i', 'e', 'k'};
        int plen = pattern.length;
        char[] text = {'a', 'c', 'h', 'e', 'j', 'e', 'h', 'h', 'a'};
        int tlen = text.length;


        Pattern pattern1 = new Pattern(pattern, plen);
        boolean match = pattern1.match(text, tlen);
        System.out.println("匹配结果为_" + match);
    }
}
