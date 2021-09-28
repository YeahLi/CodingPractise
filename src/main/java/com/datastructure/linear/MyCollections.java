package com.datastructure.linear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

public class MyCollections {
    public static void main(String[] args) {
        /**
         * 1. boolean: add(T), addAll(Collection), remove(T), removeAll(Collection)
         * 2. int size()
         * 2. boolean contains(obj)
         * 3. stream()
         * 4. Object[] toArray()
         * 5. clear()
         */
        CollectionInterfaceDemo();

        /**
         * 1. min, max
         * 2. frequency(coll, key)
         * 3. synchronizedXX()
         */
        collectionsDemo();
    }

    public static void CollectionInterfaceDemo() {
        Collection<String> myCollection = new ArrayList<>();
        //isEmpty()
        myCollection.isEmpty();

        // Append
        myCollection.add("abc");
        myCollection.add("cde");

        // Append all
        Set<String> set = new HashSet<>();
        set.add("fgh");
        myCollection.addAll(set);

        // Size
        myCollection.size();

        // Contains
        myCollection.contains("abc");

        // Print
        System.out.println(myCollection);

        // To array
        Object[] strings = myCollection.toArray();

        // stream
        myCollection.stream().forEach(System.out::println);

        // Remove
        myCollection.remove("abc");

        // Remove all
        myCollection.removeAll(set);

        // Clear
        myCollection.clear();
    }

    public static void collectionsDemo() {
        int[] nums = new int[]{1, 6, 3, 2, 5};
        Set<Integer> mySet = Arrays.stream(nums).boxed().collect(Collectors.toSet());

        // 1. maximum and minimum
        Collections.max(mySet);
        int min = Collections.min(mySet);

        int reverseMax = Collections.max(mySet, (x, y) -> y - x);
        System.out.println(min == reverseMax);

        // 2. frequency
        Collections.frequency(mySet, 4);

        // 3. synchronized for thread safe
        Set<Integer> syncSet = Collections.synchronizedSet(mySet);
        synchronized (syncSet) {
            Iterator<Integer> iterator = syncSet.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }
    }
}
