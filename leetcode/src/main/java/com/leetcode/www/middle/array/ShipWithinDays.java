package com.leetcode.www.middle.array;

/**
 * leetcode-1011:在 D 天内送达包裹的能力
 * 传送带上的包裹必须在 days 天内从一个港口运送到另一个港口。
 * 传送带上的第 i个包裹的重量为weights[i]。每一天，我们都会按给出重量（weights）的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
 * 返回能在 days 天内将传送带上的所有包裹送达的船的最低运载能力。
 */
public class ShipWithinDays {


    /**
     * 二分查找转换为判定问题:假如船的装载能力为x时，可以在days天内运送完包裹。大于x的装载能力肯定也是可以在days天内运送玩包裹。因此，存在一个运载能力的下限
     *                    ansX,使得当x>=ansX时，我们可以在days天内运送完所有包裹。当x<ansX时，我们无法再days天内运送完所有包裹。这样我们就可以通过
     *                    二分查找找出ansX的值。
     *                    二分查找的初始左右边界的确定
     *                    1. 左边界:由于包裹是不能拆分的，因此船的装载能力不能小于所有包裹中最重的那个重量，就是weights数组里面的元素最大值
     *                    2. 右边界:船的运载能力不会大于所有包裹的重量之和，也就是weights数组的所有元素之和
     * @param weights
     * @param days
     * @return
     */
    public int solution(int[] weights, int days){

        int left = 0;
        int right = 0;
        for (int weight : weights){
            right += weight;
            left = Math.max(left, weight);
        }

        while (left < right){

            int mid = (left + right) / 2;
            //需要运送的天数
            int need = 1;
            //当前这一天已经运送的包裹重量之和
            int cur = 0;
            for (int weight : weights){
                if (cur + weight > mid){
                    ++need;
                    cur = 0;
                }
                cur += weight;
            }

            if (need <= days){
                right = mid;
            }else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static void main(String[] args) {

        ShipWithinDays shipWithinDays = new ShipWithinDays();
        int[] weights = {1,2,3,4,5,6,7,8,9,10};
        int days = 5;
        int ans = shipWithinDays.solution(weights, days);
        System.out.println(ans);
    }
}
