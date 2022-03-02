package com.basics.linear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MyList {
    public static void main(String[] args) {
        /**
         * 1. insert
         * 2. access and update by index - E set(index), E get(index)
         * 3. sort
         * 4. remove by index - boolean remove(index)
         * 5. sublist(from, to)
         * 6. int indexOf(E e)
         */
        listDemo();

        /**
         * shallow copy
         * equals has been overwritten
         */
        arrayListDemo();

        /**
         * 1. sort, reverse, shuffle
         * 2. switch(list, i, j)
         * 3. rotate(list, distance)
         * 4. binarySearch(list, key, comparator)
         * 5. replaceAll(list, old, new)
         * 6. fill(list, e)
         */
        collectionsForList();
    }

    public static void listDemo() {
        List<Integer> list = new LinkedList<>();

        //void: insert and insert all
        list.add(0, 1);
        list.add(1, 5);

        List<Integer> arrayList = new ArrayList<>();
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);

        list.addAll(1, arrayList);
        System.out.println(list);

        //E get(index)
        list.get(0);

        //update - E set(index, element)
        list.set(2, 3);

        //Sub - List<E> subList[from, to)
        List<Integer> sublist = list.subList(2,4);
        System.out.println(sublist);

        //boolean: remove(index)
        list.remove(2);
        list.remove((Integer) 2);

        //Sort - void sort(Comparator c)
        list.sort((x, y) -> y-x);
        System.out.println(list);

        //get index
        int index = list.indexOf(4);
        System.out.println(index);
        index = list.indexOf(100);
        assert(index == -1);
    }

    public static void arrayListDemo() {
        // shallow copy
        ArrayList<String> original = new ArrayList<>();
        original.add("abc");
        original.add("def");

        ArrayList<String> list = new ArrayList<>(original);
        System.out.println(list);

        //Equals is overwritten
        System.out.println(original.equals(list));
        System.out.println(list.get(0) == original.get(0));
    }

    public static void collectionsForList() {
        Integer[] nums = new Integer[]{1, 6, 3, 2, 5};
        List<Integer> myList = Arrays.asList(nums);

        // Sort
        Collections.sort(myList, (x, y) -> x-y);
        Collections.shuffle(myList);
        Collections.reverse(myList);

        // Switch
        Collections.swap(myList, 0, 1);

        // Rotate
        Collections.rotate(myList, 2);

        // Binary Search
        Collections.binarySearch(myList, 3);

        // Fill
        Collections.fill(myList, 0);
        System.out.println(myList);

        // Replace all
        Collections.replaceAll(myList, 0, 1);
        System.out.println(myList);
    }
}
