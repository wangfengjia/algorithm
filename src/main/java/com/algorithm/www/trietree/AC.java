package com.algorithm.www.trietree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * AC自动机，匹配多个模式串
 * 1.构建Trie树的时间复杂度为O(m*len),m表示敏感词的数量，len表示每个敏感词的平均长度
 * 2.构建失败指针的时间复杂度O(k*len),在构建失败指针时，主要耗时的环节是while循环中的p=p->failPointer,
 *  每执行一次这条语句，q指向节点的深度都会减1，而树的最高高度不会超过len，所以每个节点构建失败指针的时间复杂度
 *  为O(len),k个节点构建失败指针的时间复杂度为O(k*len)
 * 3.AC自动机做匹配的时间复杂度为O(n*len),在for循环依次遍历主串的每个字符，for循环内部最耗时的是while循环的
 *  p=p->failPointer,每执行一次这个语句，则p指向的节点的深度减一，而树的最高高度为len，所以时间复杂度为O(len),
 *  匹配n个字符，所以时间复杂度为O(n*len),敏感词的长度一般都不长，所以近似O(n)
 * @author wangyongchun
 * @date 2019/07/16
 */
public class AC {

    public AcNode root = new AcNode('/');

    /**
     * 插入
     * @param text
     */
    public void insert(char[] text){

        AcNode temp = root;
        for (int i = 0; i < text.length; i++){

            int index = text[i] - 'a';
            if (temp.children[index] == null){
                AcNode newNode = new AcNode(text[i]);
                temp.children[index] = newNode;
            }

            temp = temp.children[index];
        }

        temp.isEndingChar = true;
        temp.length = text.length;
    }


    /**
     * 构建失败指针
     */
    private void buildFailPointer(){

        Queue<AcNode> queue = new LinkedList<AcNode>();
        root.failPointer = null;
        queue.add(root);
        while (!queue.isEmpty()){

            AcNode p = queue.remove();
            for (int i = 0; i < 26; i++){
                AcNode pc = p.children[i];
                if (pc == null){
                    continue;
                }
                if (p == root){
                    pc.failPointer = root;
                }else {

                    //得到父节点的失败指针qc，假如子节点的值会等于qc的子节点的值，则这个子节点的failPointer
                    //执行qc的子节点
                    AcNode q = p.failPointer;
                    while (q != null){
                        AcNode qc = q.children[pc.data - 'a'];
                        if (qc != null){
                            pc.failPointer = qc;
                            break;
                        }
                        q = q.failPointer;
                    }

                    if (q == null){
                        pc.failPointer = root;
                    }
                }

                queue.add(pc);
            }
        }
    }


    /**
     * 一个主串匹配多个模式串
     * @param text 主串
     */
    public void match(char[] text){

        buildFailPointer();
        int length = text.length;
        AcNode p = root;

        for (int i = 0; i < length; i++){

            int index = text[i] - 'a';
            while (p.children[index] == null && p != root){
                p = p.failPointer;
            }

            p = p.children[index];
            if (p == null){
                p = root; //如果没有匹配，则从root重新匹配
            }
            AcNode temp = p;
            while (temp != root){ //打印出可以匹配的模式串
                if (temp.isEndingChar){
                    int pos = i - temp.length + 1;
                    System.out.println("匹配起始下标_" + pos + "; 长度_" + temp.length);
                }
                temp = temp.failPointer;
            }
        }
    }


    public static void main(String[] args){

        AC ac = new AC();
        char[] a = {'a', 'b', 'c', 'e', 'f', 'h'};
        char[] b = {'a', 'b', 'c', 'e', 'f'};
        char[] c = {'a', 'b', 'c'};
        char[] d = {'a', 'b'};
        char[] e = {'a'};

        ac.insert(a);
        ac.insert(b);
        ac.insert(c);
        ac.insert(d);
        ac.insert(e);


        char[] text = {'b', 'c', 'e', 'f', 'h', 'a', 'b', 'c', 'e', 'f', 'a', 'b', 'c'};
        ac.match(text);
    }

}
