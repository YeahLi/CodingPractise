package com.questions.amazon;

public class MaxReadPage {
    static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    public static int maxReadPagesBad(int n, Node head) {
        int max = Integer.MIN_VALUE;
        int leftVolumn = n;

        Node p = head;

        while (leftVolumn > 0) {
            Node q = p;
            int moveSteps = leftVolumn - 1;
            while (moveSteps > 0) {
                q = q.next;
                moveSteps--;
            }

            int pages = p.val + q.val;
            if (max < pages) {
                max = pages;
            }

            leftVolumn -= 2;
            p = p.next;
        }

        return max;
    }

    public static int maxReadPagesBetter(int n, Node head) {
        //Divide the link list into two
        int moveSteps = n / 2;
        Node head2 = head;
        Node prev = null;
        while (moveSteps > 0) {
            prev = head2;
            head2 = head2.next;
            moveSteps--;
        }
        prev.next = null;

        //reverse the second link list
        Node p = head2;
        Node q = p.next;
        p.next = null;
        while (q != null) {
            Node r = q.next;
            q.next = p;

            p = q;
            q = r;
        }
        head2 = p;

        Node p1 = head;
        Node p2 = head2;

        // Find the max read pages
        int max = Integer.MIN_VALUE;
        while (p1 != null && p2 != null) {
            int pages = p1.val + p2.val;
            if (max < pages) {
                max = pages;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 3, 2};
        Node head = getSligthlyLinkedList(arr);
        System.out.println(maxReadPagesBetter(arr.length, head));
    }

    private static Node getSligthlyLinkedList(int[] arr) {
        Node head = null;
        for (int i = arr.length - 1; i >= 0; i--) {
            int num = arr[i];
            Node node = new Node(num);
            if (head == null) {
                head = node;
            } else {
                node.next = head;
                head = node;
            }
        }
        return head;
    }
}
