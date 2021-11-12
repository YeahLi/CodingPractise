package com.questions.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecentlyReviewedItems {
    static class Node {
        String value;
        Node next;
        Node prev;

        public Node(String value) {
            this.value = value;
        }
    }

    static class LRU {
        Map<String, Node> map = new HashMap<>();
        Node head;
        Node tail;

        public String get(String item) {
            if (!map.containsKey(item)) {
                return null;
            }
            Node node = map.get(item);
            moveNodeToHead(node);
            return node.value;
        }

        public void put(String item) {
            Node node = new Node(item);

            if (head == null) {
                head = node;
                tail = node;
            } else {
                node.next = head;
                head.prev = node;
                head = node;
            }

            map.put(item, node);
        }

        private void moveNodeToHead(Node node) {
            if (node == head) {
                return;
            }

            if (node == tail) {
                tail = node.prev;
                tail.next = null;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            node.prev = null;
            node.next = null;

            node.next = head;
            head.prev = node;
            head = node;
        }
    }

    public static List<String> recentItems(List<String> logs) {
        LRU lru = new LRU();
        for (String log : logs) {
            if (!lru.map.containsKey(log)) {
                lru.put(log);
            } else {
                lru.get(log);
            }
        }

        List<String> result = new ArrayList<>();
        Node n = lru.head;
        while (n != null) {
            result.add(n.value);
            n = n.next;
        }
        return result;
    }

    public static void main(String[] args) {
        String[] logs = {"Echo Show 8", "Kindle Oasis", "Five TV Stick", "Echo Show 8"};
        System.out.println(recentItems(Arrays.asList(logs)));
    }
}
