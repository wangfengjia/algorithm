package com.leetcode.www.middle.queue;

import java.util.Arrays;
import java.util.Comparator;

/**
 * leetcode406:根据身高重建队列
 * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。每个 people[i] = [hi, ki]表示第i个人的身高为 hi ，前面正好有ki个
 * 身高大于或等于 hi 的人。请你重新构造并返回输入数组people 所表示的队列。返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的
 * 属性（queue[0] 是排在队列前面的人）。
 *
 */
public class ReConstructQueue {


    /**
     * 身高从低到高考虑:按身高从小到大排序，排序的目的是:当我们按照排完序之后的顺序去把每个人放入队列中，当我们需要放置第i个人时，由于第0~i-1个人已经在队列中安排了
     * 位置，并且由于他们比第i个人身高小，所以它们无论站哪里，对i个人都不会有什么影响;而第i+1 ~ n-1个人还没有被放入队列，并且他们的身高比第i个人高，只要它们站在
     * 第i个人前面，就会对第一个人产生影响
     * 可以通过创建一个长度为n的空队列，将排好序的数据放入这个新队列中，当我们需要放入第i个人时，我们需要给它安排一个空位置，并且这个位置前面恰好有ki+1个空位置，
     * 用来安排后面身高更高的人，也就是说第i个人的位置就是从新队列中从左往右数第ki+1个位置
     * 由于条件[hi,ki]表示hi前面正好有ki个身高大于等于ki的人，所以对于hi相同，但是ki不同的人要有一定的处理。处理办法可以是可以通过排序的过程中，可以按照hi为第一
     * 关键字升序，ki为第二关键字降序进行排序
     * 复杂度分析
     *  1.时间复杂度:O(n^2),其中n为数组people的长度，两层遍历的时间复杂度
     *  2.空间复杂度:O(log^n),排序需要的栈空间
     * @param peoples
     * @return
     */
    public int[][] solution(int[][] peoples){

        Arrays.sort(peoples, new Comparator<int[]>() {
            @Override
            public int compare(int[] people1, int[] people2) {
                if (people1[0] != people2[0]){
                    return people1[0] - people2[0];
                }else {
                    return people2[1] - people1[1];
                }
            }
        });

        int n = peoples.length;
        int[][] queue = new int[n][];
        for (int[] people: peoples){
            int spaces = people[1] + 1;
            for (int i = 0; i < n; i++){
                if (queue[i] == null){
                    --spaces;
                    if (spaces == 0){
                        queue[i] = people;
                        break;
                    }
                }
            }
        }

        return queue;
    }

    public static void main(String[] args) {

        ReConstructQueue reConstructQueue = new ReConstructQueue();
        int[][] peoples = new int[][]{{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
        int[][] ans = reConstructQueue.solution(peoples);
        for (int[] people : ans){

            System.out.print(Arrays.toString(people));
            System.out.print(",");
        }
    }
}
