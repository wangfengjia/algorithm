package com.algorithm.www.trietree;

/**
 * trie tree
 * 时间复杂度
 * 1.插入:O(n)，n表示所有字符串的长度之和，因为插入过程中需要扫描所有的字符串
 * 2.查找:O(k),k表示需要查找的字符串的长度，如果要查找的字符串的长度为k，则查找的过程中只需要扫描k个节点
 * @author wangyongchun
 * @date 2019/07/15 14:42
 */
public class TrieTree {


    public TrieNode root = new TrieNode('/');

    /**
     * 插入trie树
     * @param text
     */
    public void insert(char[] text){

        TrieNode temp = root;
        for (int i = 0; i < text.length; i++){

            //用字母的ascii码减去字母a的ascii码，得到的就是字符在字节数数组的下标
            int index = text[i] - 'a';
            if (temp.children[index] == null){
                TrieNode newNode = new TrieNode(text[i]);
                temp.children[index] = newNode;
            }

            temp = temp.children[index];
        }

        temp.isEndingChar = true;
    }

    /**
     * 查找
     * @param text
     * @return
     */
    public boolean find(char[] text){

        TrieNode p = root;
        for (int i = 0; i < text.length; i++){
            int index = text[i] - 'a';
            if (p.children[index] == null){
                return false;
            }

            p = p.children[index];
        }

        return p.isEndingChar;
    }

    public static void main(String[] args){

        TrieTree trieTree = new TrieTree();
        char[] text = {'h', 'e', 'l', 'l', 'o'};
        trieTree.insert(text);
        char[] pattern = {'h', 'e', 'l', 'l'};
        boolean b = trieTree.find(pattern);
        System.out.println(b);
    }
}
