package com.datastructure.graph.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyTrie {
    class TrieNode {
        boolean isEnd = false;
        Map<Character, TrieNode> children = new HashMap<>();
    }

    TrieNode root = new TrieNode();

    public static void main(String[] args) {
        MyTrie trie = new MyTrie();
        //1. add
        trie.add("apple");
        trie.add("app");
        trie.add("book");
        trie.add("booklet");
        //2. search
        System.out.println("app: " + trie.search("app"));
        System.out.println("ap: " + trie.search("ap"));
        //3. print all
        System.out.println("All strings: " + trie.getAllWords());
        //4. delete
        trie.deleteWord("apple");
        System.out.println("After deleting apple: " + trie.getAllWords());
        trie.deleteWord("book");
        System.out.println("After deleting book: " + trie.getAllWords());
    }

    public void add(String word) {
        if (word == null) {
            return;
        }

        TrieNode n = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (n.children.containsKey(c)) {
                n = n.children.get(c);
            } else {
                TrieNode newNode = new TrieNode();
                n.children.put(c, newNode);
                n = newNode;
            }
            if (i == word.length() - 1) {
                n.isEnd = true;
            }
        }
    }

    public List<String> search(String prefix) {
        List<String> result = new LinkedList<>();

        if (prefix == null) {
            return result;
        }

        TrieNode n = root;
        // find related node
        for (char c : prefix.toCharArray()) {
            if (n.children.containsKey(c)) {
                n = n.children.get(c);
            } else {
                return result;
            }
        }

        //dfs backtrace search
        StringBuffer sb = new StringBuffer(prefix);
        findAllWords(n, sb, result);
        return result;
    }

    private void findAllWords(TrieNode n, StringBuffer sb, List<String> result) {
        if (n.isEnd) {
            result.add(sb.toString());
        }

        if (n.children.isEmpty()) {
            return;
        }

        for (char k : n.children.keySet()) {
            sb.append(k);
            findAllWords(n.children.get(k), sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public List<String> getAllWords() {
        List<String> result = new LinkedList<>();
        findAllWords(root, new StringBuffer(), result);
        return result;
    }

    /**
     * 使用递归
     * 先通过递归确定删除字符串在trie中，返回true, 并将自己的isEnding 设为false
     * 然后父节点判断其对应子节点进行删除， 必须满足如下条件：
     * a. 子节点的isEnding equals false
     * b. 子节点的children is empty
     * 如此循环往上pop， 删除所有满足以上条件的子节点
     *
     * @param word
     */
    public void deleteWord(String word) {
        if (word == null || word.isEmpty()) {
            return;
        }
        //1. Does word exist?
        //2. We need to know if each character is used by other words (n.isEnd == true, or n.children.size()>=1)
        deleteWord(word, 0, root, null);
    }

    private void deleteWord(String word, int index, TrieNode n, TrieNode parent) {
        if (index == word.length()) { //找到最后一个字符
            if (!n.isEnd) {
                return; //没有这个 word
            }

            if (!n.children.isEmpty()) { //还有其他字符使用
                n.isEnd = false;
                return;
            }

            //父节点删除该字符
            parent.children.remove(word.charAt(index - 1));
            return;
        }

        char c = word.charAt(index);
        if (!n.children.containsKey(c)) { //不存在
            return;
        }

        deleteWord(word, index + 1, n.children.get(c), n);

        if (n.children.isEmpty() && !n.isEnd) { //没被其他字符使用
            if (parent != null) { //不删根节点
                parent.children.remove(c);
            }
        }
    }
}
