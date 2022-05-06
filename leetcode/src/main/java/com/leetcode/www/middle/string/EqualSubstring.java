package com.leetcode.www.middle.string;

/**
 * leetcode-1208:尽可能使字符串相等
 * 给你两个长度相同的字符串，s 和 t。
 * 将s中的第i个字符变到t中的第i个字符需要s[i] - t[i]的开销（开销可能为0），也就是两个字符的ASCII码值的差的绝对值。
 * 用于变更字符串的最大预算是maxCost。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。
 * 如果你可以将s的子字符串转化为它在t中对应的子字符串，则返回可以转化的最大长度。
 * 如果s中没有子字符串可以转化成t中对应的子字符串，则返回 0。
 */
public class EqualSubstring {


    /**
     * 假定字符串origin和target的长度均为n，对于长度0<=i<=n,将origin[i]转变为target[i]的开销为abs(origin[i]-target[i]),因此可以创建一个长度为n的
     * 数组diff,其中diff[i]=abs(origin[i]-target[i])。这个问题转化为了计算数组diff的元素和不超过maxCost的最长子数组的长度。由于diff的每个元素都是
     * 非负的,因此可以使用滑动窗口的方法得到元素和不超过maxCost的最长子数组的长度
     * 滑动窗口:使用left和right指针来表示子数组的开始和结束下标，同时使用sum来维护子数组的元素和，right指针不断向右移动，再去比较sum和maxCost的大小关系，
     *         当sum > maxCost时，移动左指针，直到sum<=cost。右指针每向右边移动一步，维护最大子数组的长度。遍历结束后，就可以得到符合要求的最长子数组的
     *         长度，即字符串可以转化的最大长度
     * 复杂度分析
     *  时间复杂度:O(n),n表示字符串的长度
     *  空间复杂度:O(n),n表示字符串的长度,diff数组所使用的空间
     * @param origin
     * @param target
     * @param maxCost
     * @return
     */
    public int solution(String origin, String target, int maxCost){

        int n = origin.length();
        int[] diff = new int[n];
        for (int i = 0; i < n; i++){
            diff[i] = Math.abs(origin.charAt(i) - target.charAt(i));
        }

        int ans = 0;
        int left = 0;
        int right = 0;
        int sum = 0;
        while (right < n){
            sum += diff[right];
            while (sum > maxCost){
                sum -= diff[left];
                ++left;
            }
            ans = Math.max(ans, right - left + 1);
            ++right;
        }

        return ans;
    }


    public static void main(String[] args) {

        EqualSubstring substring = new EqualSubstring();
        String origin = "abcd";
        String target = "cdef";
        int maxCost = 3;
        int ans = substring.solution(origin, target, maxCost);
        System.out.println(ans);
    }
}
