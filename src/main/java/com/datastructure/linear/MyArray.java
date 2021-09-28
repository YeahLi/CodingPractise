package com.datastructure.linear;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MyArray {

    public static void main(String[] args) {
        /**
         * Two ways to define an array
         * Two ways to traverse an array
         */
        oneDimensionArray();

        /**
         * Define a two dimension array
         * Traverse a grid.
         */
        twoDimensionArray();

        /**
         * 1. Sort - Arrays.sort(array, comparator) and sort desc(not working for basic type)
         * 2. To string - Arrays.toString(arr)
         * 3. To stream - Arrays.stream(arr)
         * 4. To list - Arrays.asList(arr)
         * 5. reverse - Collections.reverse(list) (not working for basic type)
         * 6. binary search - Arrays.binarySearch(arr, key)
         * 7. Equals - Arrays.equals() vs Arrays.deepEquals()
         * 8. Expand - Arrays.copyOf(arr, new length)
         * 9. Copy - Arrays.copyOfRange(arr, from, to)
         * 10. 复制数组: System.arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
         */
        arraysDemo();
    }

    public static void oneDimensionArray() {
        // Basic array operations
        String[] books = new String[5];
        books[0] = "Harry Porter";
        for (int i = 0; i < books.length; i++) {
            System.out.println(books[i]);
        }

        // Another way to create an Array
        books = new String[] {"Harry Porter", "English"};
        for (String book : books) {
            System.out.println(book);
        }
    }

    public static void twoDimensionArray() {
        int[][] grid = new int[3][];

        // Define rows
        grid[0] = new int[3];
        grid[1] = new int[4];
        grid[2] = new int[5];

        // traverse two dimension array
        for (int row = 0; row < grid.length; row++) {
            System.out.print("Row " + row + ": ");
            for (int column =0; column < grid[row].length; column++) {
                if (column == grid[row].length - 1) {
                    System.out.println(column);
                } else {
                    System.out.print(column + " ");
                }
            }
        }
    }

    public static void arraysDemo() {
        int[] arr = new int[]{1, 4, 3, 2, 5};

        // 1. Sort
        Arrays.sort(arr);

        // 2. Transfer to a string
        System.out.println(arr);
        System.out.println(Arrays.toString(arr));

        //Cannot use sort with comparator for basic types
        //Arrays.sort(arr, Collections.reverseOrder());
        Integer[] intArr = new Integer[]{1, 4, 3, 2, 5};
        Arrays.sort(intArr, Collections.reverseOrder());
        System.out.println(Arrays.toString(intArr));

        // 3. Transfer to stream
        Arrays.stream(arr).forEach(System.out::println);


        // 4. Transfer to array list
        intArr = new Integer[] {1, 2, 3, 4, 5};
        List<Integer> list = Arrays.asList(intArr);
        System.out.println(list);

        // The array cannot be a primitive type, for example
        List<int[]> myList = Arrays.asList(arr);
        System.out.println("the list element type is an array: " + Arrays.toString(myList.get(0)));
        //work around
        list = Arrays.stream(arr).boxed().collect(Collectors.toList());

        // 5. How to reverse an array
        Collections.reverse(list);
        System.out.println(list);

        // 6. Binary Search
        Arrays.sort(arr);
        int index = Arrays.binarySearch(arr, 3);
        System.out.println("find index = " + index);
        index = Arrays.binarySearch(arr, 100);
        System.out.println("Not found index = " + index);

        // 7. Equals
        int a[][] = new int[2][2];
        int b[][] = new int[2][2];
        for(int i=0;i<2;i++)
            for(int j=0;j<2;j++) {
                a[i][j] = i+j;
                b[i][j] = i+j;
            }
        System.out.println(Arrays.deepEquals(a,b));//return true
        System.out.println(Arrays.equals(a, b));//return false

        // 8. Expand
        arr = Arrays.copyOf(arr, 10);
        System.out.println(arr.length);

        // 9. Copy [from, to) open interval and close interval
        int[] arr1 = new int[]{1, 2, 3, 4, 5, 6, 7};
        int[] arr2 = Arrays.copyOfRange(arr1, 2, 6);
        System.out.println(Arrays.toString(arr2));
    }
}
