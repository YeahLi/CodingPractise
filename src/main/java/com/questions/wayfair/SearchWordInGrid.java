package com.questions.wayfair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * For coding, I got the question for searching a word in a grid.
 * The word may start anywhere in the grid, and consecutive letters can be either immediately below or immediately to the right of the previous letter.
 * grid1 = [
 * ['c', 'c', 'x', 't', 'i', 'b'],
 * ['c', 'c', 'a', 't', 'n', 'i'],
 * ['a', 'c', 'n', 'n', 't', 't'],
 * ['t', 'c', 's', 'i', 'p', 't'],
 * ['a', 'o', 'o', 'o', 'a', 'a'],
 * ['o', 'a', 'a', 'a', 'o', 'o'],
 * ['k', 'a', 'i', 'c', 'k', 'i'],
 * ]
 * word1 = "catnip"
 * find_word_location(grid1, word1) => [ (1, 1), (1, 2), (1, 3), (2, 3), (3, 3), (3, 4) ]
 * https://leetcode.com/problems/word-search/
 */
public class SearchWordInGrid {
    public static List<int[]> findWordLocation(char[][] grid, String word) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == word.charAt(0)) {
                    List<int[]> path = new ArrayList<>();
                    path.add(new int[]{i, j});

                    List<int[]> res = dfs(grid, word, 1, i, j, path);
                    if (!res.isEmpty()) {
                        return res;
                    }
                }
            }
        }
        return new ArrayList<>();
    }

    //idx: next compared char, i and j current position
    private static List<int[]> dfs(char[][] grid, String word, int idx, int i, int j, List<int[]> path) {
        if (idx >= word.length()) {
            return new ArrayList<>(path);
        }

        if (i + 1 < grid.length && grid[i + 1][j] == word.charAt(idx)) {
            path.add(new int[]{i + 1, j});
            List<int[]> res = dfs(grid, word, idx + 1, i + 1, j, path);
            if (!res.isEmpty()) {
                return res;
            }
            path.remove(path.size() - 1);
        }

        if (j + 1 < grid[0].length && grid[i][j + 1] == word.charAt(idx)) {
            path.add(new int[]{i, j + 1});
            List<int[]> res = dfs(grid, word, idx + 1, i, j + 1, path);
            if (!res.isEmpty()) {
                return res;
            }
            path.remove(path.size() - 1);
        }

        return new ArrayList<>();
    }

    public static void main(String[] args) {
        char[][] grid1 = new char[][] {
                {'c', 'c', 'x', 't', 'i', 'b'},
                {'c', 'c', 'a', 't', 'n', 'i'},
                {'a', 'c', 'n', 'n', 't', 't'},
                {'t', 'c', 's', 'i', 'p', 't'},
                {'a', 'o', 'o', 'o', 'a', 'a'},
                {'o', 'a', 'a', 'a', 'o', 'o'},
                {'k', 'a', 'i', 'c', 'k', 'i'}
        };

        String word1 = "catnip";
        String word2 = "cccc";
        String word3 = "s";
        String word4 = "bit";
        String word5 = "aoi";
        String word6 = "ki";
        String word7 = "aaa";
        String word8 = "ooo";

        char[][] grid2 = {{'a'}};
        String word9 = "a";

        List<int[]> result = findWordLocation(grid1, word1);
        for (int[] pair : result) {
            System.out.print(Arrays.toString(pair));
            System.out.print(" ");
        }
        System.out.println();

        result = findWordLocation(grid1, word2);
        for (int[] pair : result) {
            System.out.print(Arrays.toString(pair));
            System.out.print(" ");
        }
        System.out.println();

        result = findWordLocation(grid1, word3);
        for (int[] pair : result) {
            System.out.print(Arrays.toString(pair));
            System.out.print(" ");
        }
        System.out.println();

        result = findWordLocation(grid1, word4);
        for (int[] pair : result) {
            System.out.print(Arrays.toString(pair));
            System.out.print(" ");
        }
        System.out.println();

        result = findWordLocation(grid1, word5);
        for (int[] pair : result) {
            System.out.print(Arrays.toString(pair));
            System.out.print(" ");
        }
        System.out.println();

        result = findWordLocation(grid1, word6);
        for (int[] pair : result) {
            System.out.print(Arrays.toString(pair));
            System.out.println(" ");
        }
        System.out.println();

        result = findWordLocation(grid1, word7);
        for (int[] pair : result) {
            System.out.print(Arrays.toString(pair));
            System.out.print(" ");
        }
        System.out.println();

        result = findWordLocation(grid1, word8);
        for (int[] pair : result) {
            System.out.print(Arrays.toString(pair));
            System.out.print(" ");
        }
        System.out.println();

        result = findWordLocation(grid2, word9);
        for (int[] pair : result) {
            System.out.print(Arrays.toString(pair));
            System.out.print(" ");
        }
        System.out.println();
    }
}
