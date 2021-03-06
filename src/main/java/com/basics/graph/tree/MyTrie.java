package com.basics.graph.tree;

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
     * ????????????
     * ???????????????????????????????????????trie????????????true, ???????????????isEnding ??????false
     * ?????????????????????????????????????????????????????? ???????????????????????????
     * a. ????????????isEnding equals false
     * b. ????????????children is empty
     * ??????????????????pop??? ??????????????????????????????????????????
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
        if (index == word.length()) { //????????????????????????
            if (!n.isEnd) {
                return; //???????????? word
            }

            if (!n.children.isEmpty()) { //????????????????????????
                n.isEnd = false;
                return;
            }

            //????????????????????????
            parent.children.remove(word.charAt(index - 1));
            return;
        }

        char c = word.charAt(index);
        if (!n.children.containsKey(c)) { //?????????
            return;
        }

        deleteWord(word, index + 1, n.children.get(c), n);

        if (n.children.isEmpty() && !n.isEnd) { //????????????????????????
            if (parent != null) { //???????????????
                parent.children.remove(c);
            }
        }
    }
}
