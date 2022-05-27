package com.leetcode.www.middle.tree;

/**
 * leetcode-331:验证二叉树的前序序列化
 * 序列化二叉树的一种方法是使用 前序遍历 。当我们遇到一个非空节点时，我们可以记录下这个节点的值。如果它是一个空节点，我们可以使用一个标记值记录，例如 #。
 * 二叉树可以被序列化为字符串 "9,3,4,#,#,1,#,#,2,#,6,#,#"，其中 # 代表一个空节点。
 * 给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个在不重构树的条件下的可行算法。
 * 保证 每个以逗号分隔的字符或为一个整数或为一个表示 null 指针的 '#' 。
 * 你可以认为输入格式总是有效的
 * 例如它永远不会包含两个连续的逗号，比如"1,,3" 。
 * 注意：不允许重建树。
 */
public class IsValidSerialization {

    /**
     * 二叉树可以看成是有向图，一条有向边带来一个出度和一个入度，二叉树的总出度等于总入度。
     * 遍历前序序列化结果，对于每个节点按照如下处理
        * 根节点提供两个出度，0个入度
        * 根节点以外的非空节点提供两个出度，一个入度
        * 空节点提供一个入度
     * 复杂度分析
     *  时间复杂度:O(n),n是字符串s的长度
     *  空间复杂度:O(1)
     * @param s
     * @return
     */
    public boolean solution(String s){

        int inDegree = 0;
        int outDegree = 0;
        String[] split = s.split(",");
        int len = split.length;

        if (len == 1 && split[0].equals("#")){
            return true;
        }
        if (len > 1 && split[0].equals("#")){
            return false;
        }

        for (int i = 0; i < len; i++){
            if (i == 0){
                outDegree += 2;
            }else if (split[i].equals("#")){
                inDegree += 1;
            }else {
                inDegree += 1;
                outDegree += 2;
            }

            //一直保持inDegree < outDegree,直到最后才inDegree == outDegree，不满足条件就返回false
            if (i != len - 1 && inDegree >= outDegree){
                return false;
            }
        }

        return inDegree == outDegree;
    }


    public static void main(String[] args) {

        IsValidSerialization validSerialization = new IsValidSerialization();

        String s1 = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        String s2 = "1,#";
        String s3 = "9,#,#,1";

        boolean ans1 = validSerialization.solution(s1);
        System.out.println(ans1);

        boolean ans2 = validSerialization.solution(s2);
        System.out.println(ans2);

        boolean ans3 = validSerialization.solution(s3);
        System.out.println(ans3);
    }
}
