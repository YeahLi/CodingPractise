package com.basics.linear.dequeue;

import java.util.ArrayDeque;
import java.util.Deque;

public class MyArrayDeque {
    public static void main(String[] args) {
        Deque<Integer> arrayDeque = new ArrayDeque<>();
        stackDemo(arrayDeque);
        queueDemo(arrayDeque);
        try {
            arrayDeque.add(null);
        } catch (NullPointerException e) {
            System.out.println("ArrayDequeue doesn't allow NULL element.");
        }
    }

    public static void queueDemo(Deque<Integer> queue) {
        queue.addFirst(3);
        queue.addLast(4);
        queue.offer(5);

        queue.poll();
        queue.peek();
    }

    public static void stackDemo(Deque<Integer> stack) {
        stack.push(3);
        stack.peek();
        stack.poll();
        stack.size();
    }
}
