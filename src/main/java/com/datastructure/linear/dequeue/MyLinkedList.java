package com.datastructure.linear.dequeue;

import java.util.LinkedList;

public class MyLinkedList {
    public static void main(String[] args) {
        /**
         * Add -> boolean:
         *  addFirst = push = offerFirst, add = addLast = offer = offerLast,
         * Remove -> E
         *  removeFirst = poll = pollFirst, removeLast
         * Get -> E
         *  getFirst = peek = peekFirst, getLast = peekLast
         */
        linkedListDemo();
    }

    public static void linkedListDemo() {
        LinkedList<String> myLinkedList = new LinkedList<>();

        //Add
        myLinkedList.add("are");
        myLinkedList.addLast("you?");
        myLinkedList.addFirst("How");

        System.out.println(String.join(" ", myLinkedList));

        //Get
        String first = myLinkedList.getFirst();
        String last = myLinkedList.getLast();


        //Remove
        myLinkedList.removeFirst();
        System.out.println(String.join(" ", myLinkedList));

        //Stack: Push, Peek and Poll
        myLinkedList.push("How");
        System.out.println(String.join(" ", myLinkedList));

        String peek = myLinkedList.peek();
        String poll = myLinkedList.poll();
        System.out.println(peek == poll);
        System.out.println(String.join(" ", myLinkedList));

        //Queue: offerFirst, offer = offerLast, poll
        myLinkedList.offerFirst("How");
        myLinkedList.offer("Fine.");
        System.out.println(String.join(" ", myLinkedList));
    }
}
