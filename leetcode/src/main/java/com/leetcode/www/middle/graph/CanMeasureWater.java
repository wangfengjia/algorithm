package com.leetcode.www.middle.graph;

import java.util.*;

/**
 * leetcode-365:水壶问题
 * 有两个水壶，容量分别为jug1Capacity和 jug2Capacity 升。水的供应是无限的。确定是否有可能使用这两个壶准确得到targetCapacity 升。
 * 如果可以得到targetCapacity升水，最后请用以上水壶中的一或两个来盛放取得的targetCapacity升水。
 * 你可以：
     * 装满任意一个水壶
     * 清空任意一个水壶
     * 从一个水壶向另外一个水壶倒水，直到装满或者倒空
 */
public class CanMeasureWater {

    /**
     * 广度优先搜索
     * 将题目允许的操作转换为如下定义
     * 装满一个水壶，定义为操作一，分为
     *  1. 装满A，包括A为空和A非空的时候把A倒满的情况下
     *  1. 装满B，包括B为空和B非空的时候把B倒满的情况下
     * 清空一个水壶，定义为操作二，分为
     *  1. 清空A
     *  2. 清空B
     * 从一个水壶向另外一个水壶倒水，直到倒满或者倒空，定义为操作三，可以分为
     *  1. 从A到B,使得B满，A还有剩余
     *  2. 从A到B,此时A的水太少了,A倒尽，B没有满
     *  3. 从B到A,使得A满，B还有剩余
        4. 从B到A,此时B的水太少了,B倒尽，A没有满
     * 因此，从当前状态最多可以进行8种操作，得到8个新状态，对这8个新状态,依然可以扩展,一直做下去，直到某一个状态满足题目要求。由于是只需要找一个符合题意的状态
     * 即可，可以使用广度优先搜索。这是由于广度优先搜索有一个性质，一层一层像水波纹一样扩散，路径最短
     * @param x
     * @param y
     * @param z
     * @return
     */
    public boolean solution(int x, int y, int z){

        if (z == 0){
            return true;
        }
        if (x + y < z){
            return false;
        }

        State initState = new State(0, 0);
        Queue<State> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>();

        queue.offer(initState);
        visited.add(initState);

        while (!queue.isEmpty()){

            //此题不需要求路径最短的长度，因此在出队列的时候不读取队列的长度
            State state = queue.poll();

            int curX = state.getX();
            int curY = state.getY();
            if (curX == z || curY == z || curX + curY == z){
                return true;
            }

            //从当前状态获取所有可能的下一步状态
            List<State> nextStates = getNextStates(curX, curY, x, y);
            for (State cur : nextStates){
                if (!visited.contains(cur)){
                    queue.offer(cur);
                    visited.add(cur);
                }
            }
        }

        return false;
    }

    private List<State> getNextStates(int curX, int curY, int x, int y){

        List<State> nextStates = new ArrayList<>();

        //对应操作一的两个状态
        //外部加水，使得A满
        State nextState1 = new State(x, curY);
        //外部加水，使得B满
        State nextState2 = new State(curX, y);

        //对应操作二的两个状态
        //把A清空
        State nextState3 = new State(0, curY);
        //把B清空
        State nextState4 = new State(curX, 0);

        //对应操作三的四个状态
        //从A到B,使得B满，A还有剩下
        State nextState5 = new State(curX - (y - curY), y);
        //从A到B,此时A的水太少,A倒尽，B没有满
        State nextState6 = new State(0, curX + curY);

        //从B到A,使得A满，B还有剩下
        State nextState7 = new State(x, curY - (x - curX));
        //从B到A,此时B的水太少,B倒尽，A没有满
        State nextState8 = new State(curX + curY, 0);

        //没有满时才需要加水
        if (curX < x){
            nextStates.add(nextState1);
        }
        if (curY < y){
            nextStates.add(nextState2);
        }

        //有水的时候才需要倒掉
        if (curX > 0){
            nextStates.add(nextState3);
        }
        if (curY > 0){
            nextStates.add(nextState4);
        }

        //有剩余才倒
        if (curX - (y - curY) > 0){
            nextStates.add(nextState5);
        }
        if (curY - (x - curX) > 0){
            nextStates.add(nextState7);
        }

        //倒过去倒不满才倒
        if (curX + curY < y){
            nextStates.add(nextState6);
        }
        if (curX + curY < x){
            nextStates.add(nextState8);
        }

        return nextStates;
    }

    private class State{

        int x;
        int y;

        public State(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public String toString(){
            return "State{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        @Override
        public boolean equals(Object o){

            if (this == o){
                return true;
            }
            if (o == null || getClass() != o.getClass()){
                return false;
            }

            State state = (State) o;
            return x == state.x && y == state.y;
        }

        @Override
        public int hashCode(){
            return Objects.hash(x, y);
        }
    }

    public static void main(String[] args) {

        CanMeasureWater measureWater = new CanMeasureWater();
        int x = 1;
        int y = 2;
        int z = 3;

        boolean ans = measureWater.solution(x, y, z);
        System.out.println(ans);
    }
}
