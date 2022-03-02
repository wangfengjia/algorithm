package com.algorithm.www.string;


import java.util.HashMap;
import java.util.Map;

/**
 * 暴力破解和RK算法
 *
 * @author wangyongchun
 * @date 2019/07/10 16:52
 */
public class BFAndRK {

    public boolean bf(String s, String p){
        boolean isFound = false;
        for (int i = 0; i < s.length() - p.length() + 1; i++){
            int pointer = i;
            for (int j = 0; j < p.length(); j++){
                if (s.charAt(pointer) == p.charAt(j)){
                    isFound = true;
                    ++pointer;
                }else {
                    isFound = false;
                    break;
                }
            }

            if (isFound){
                return true;
            }
        }

        return false;
    }


    public boolean rk(String s, String p){

        int key = 25;
        HashMap<String, Integer> letterMapInter = new HashMap<String, Integer>();
        letterMapInter.put("a", 0);
        letterMapInter.put("b", 1);
        letterMapInter.put("c", 2);
        letterMapInter.put("d", 3);
        letterMapInter.put("e", 4);
        letterMapInter.put("f", 5);
        letterMapInter.put("g", 6);
        letterMapInter.put("h", 7);
        letterMapInter.put("i", 8);
        letterMapInter.put("j", 9);
        letterMapInter.put("k", 10);

        Map<Integer, Double> data = new HashMap<Integer, Double>();
        for (int i = 1; i <= p.length(); i++){
            data.put(i, Math.pow(key, p.length() - i));
        }

        int pHash = 0;
        for (int i = 0; i < p.length(); i++){
            pHash += letterMapInter.get(String.valueOf(p.charAt(i))) * data.get(i + 1);
        }

        for (int i = 0; i < s.length() - p.length() + 1; i++){
            int pointer = i;
            int hash = 0;
            for (int j = 1; j <= p.length(); j++){
                hash += letterMapInter.get(String.valueOf(s.charAt(pointer))) * data.get(j);
                ++pointer;
            }
            if (hash == pHash){
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args){


        BFAndRK bfAndRK = new BFAndRK();
        String s = "abcdef";
        String p = "bc";
//        boolean bf = bfAndRK.bf(s, p);
        boolean rk = bfAndRK.rk(s, p);
        System.out.println(rk);
//        System.out.println(bf);

    }
}
