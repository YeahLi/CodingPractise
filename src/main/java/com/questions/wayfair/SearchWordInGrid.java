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
                if (grid[i][j] ==  word.charAt(0)) {
                    List<int[]> res = new ArrayList<>();
                    res.add(new int[]{i, j});
                    boolean isFind = dfs(grid, word, 1, res, i, j);
                    if (isFind) {
                        return res;
                    }
                }
            }
        }
        return new ArrayList<>();
    }

    private static boolean dfs(char[][] grid, String word, int index, List<int[]> path, int i, int j) {
        if (index == word.length()) {
            return true;
        }

        char c = word.charAt(index);
        //System.out.println(i+","+j);
        if (i+1 < grid.length && j < grid[0].length && grid[i+1][j] == c) {
            path.add(new int[]{i+1, j});
            if(dfs(grid,word, index+1, path, i+1, j)) {
                return true;
            }
            path.remove(path.size()-1);
        }

        if (i < grid.length && j+1 < grid[0].length && grid[i][j+1] == c) {
            path.add(new int[]{i, j+1});
            if(dfs(grid,word, index+1, path, i, j+1)) {
                return true;
            }
            path.remove(path.size()-1);
        }

        return false;
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

        List<int[]> result = findWordLocation(grid1, word1);
        for (int[] pair : result) {
            System.out.println(Arrays.toString(pair));
        }
    }
}
