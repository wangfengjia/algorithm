package com.algorithm.www.trietree;

/**
 * trie tree节点
 *
 * @author wangyongchun
 * @date 2019/07/15 15:33
 */
public class TrieNode {

    public char data;
    public TrieNode children[] = new TrieNode[26];
    public boolean isEndingChar = false;

    public TrieNode(char data){
        this.data = data;
    }
}
