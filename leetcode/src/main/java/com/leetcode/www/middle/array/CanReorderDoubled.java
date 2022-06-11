package com.leetcode.www.middle.array;


import java.util.*;

/**
 * leetcode-954:二倍数对数组
 * 给定一个长度为偶数的整数数组 arr，只有对 arr 进行重组后可以满足 “对于每个 0 <=i < len(arr) / 2，都有 arr[2 * i + 1] = 2 * arr[2 * i]”时，
 * 返回 true；否则，返回 false。
 */
public class CanReorderDoubled {


    /**
     * 哈希表+排序
     * 题目的本质是问数组arr能否分成n/2对元素，每对元素中的一个元素是另一个元素的两倍。可以使用哈希表cnt统计每个元素值出现的次数。然后将arr数组按照绝对值从小
     * 到大排序。再去遍历处理排序后的数组，假设x是arr中绝对值最小的元素，由于没有绝对值比x更小的数，因此x只能与2x匹配(这也就是按照绝对值排序的目的，使得
     * 处理的元素x是当前绝对值最小的元素，这样就只能去查找2x是否存在，而不能查找x/2是否存在)。如果cnt[2x] < cnt[x]时,那么会有一部分x找不到它的另一半，否则就
     * cnt[2x] -= xnt[x]。继续判断剩余元素是否满足题目要求，不断重复这个操作。
     *
     * 复杂度分析
     *  时间复杂度:O(nlog^n),n是数组arr的长度,最坏情况下哈希表中有n个元素，对其排序需要O(nlog^n)
     *  空间复杂度:O(n),最坏情况下哈希表中有n个元素,因此空间复杂度为O(n)
     * @param arr
     * @return
     */
    public boolean solution(int[] arr){

        Map<Integer, Integer> cnt = new HashMap<>();
        for (int e : arr){
            cnt.put(e, cnt.getOrDefault(e, 0) + 1);
        }

        //假如arr中存在元素值为0，它只能和0匹配，cnt[0]是奇数的话直接返回false
        if (cnt.getOrDefault(0, 0) % 2 != 0){
            return false;
        }

        List<Integer> list = new ArrayList<>();
        for (int key : cnt.keySet()){
            list.add(key);
        }
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return Math.abs(a) - Math.abs(b);
            }
        });

        for (int element : list){
            if (cnt.getOrDefault(2 * element, 0) < cnt.getOrDefault(element, 0)){
                return false;
            }
            cnt.put(2 * element, cnt.getOrDefault(2 * element, 0) - cnt.get(element));
        }

        return true;
    }

    public static void main(String[] args) {

        CanReorderDoubled doubled = new CanReorderDoubled();

        int[] arr1 = {4,-2,2,-4};
        boolean ans1 = doubled.solution(arr1);
        System.out.println(ans1);

        int[] arr2 = {3,1,3,6};
        boolean ans2 = doubled.solution(arr2);
        System.out.println(ans2);
    }
}
