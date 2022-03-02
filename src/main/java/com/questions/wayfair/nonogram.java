package com.questions.wayfair;
/*
A nonogram is a logic puzzle, similar to a crossword, in which the player is given a blank grid and has to color it according to some instructions. Specifically, each cell can be either black or white, which we will represent as 0 for black and 1 for white.

+------------+
| 1  1  1  1 |
| 0  1  1  1 |
| 0  1  0  0 |
| 1  1  0  1 |
| 0  0  1  1 |
+------------+

For each row and column, the instructions give the lengths of contiguous runs of black (0) cells. For example, the instructions for one row of [ 2, 1 ] indicate that there must be a run of two black cells, followed later by another run of one black cell, and the rest of the row filled with white cells.

These are valid solutions: [ 1, 0, 0, 1, 0 ] and [ 0, 0, 1, 1, 0 ] and also [ 0, 0, 1, 0, 1 ]
This is not valid: [ 1, 0, 1, 0, 0 ] since the runs are not in the correct order.
This is not valid: [ 1, 0, 0, 0, 1 ] since the two runs of 0s are not separated by 1s.

Your job is to write a function to validate a possible solution against a set of instructions. Given a 2D matrix representing a player's solution; and instructions for each row along with additional instructions for each column; return True or False according to whether both sets of instructions match.

Example instructions #1

matrix1 = [[1,1,1,1],
           [0,1,1,1],
           [0,1,0,0],
           [1,1,0,1],
           [0,0,1,1]]
rows1_1    =  [], [1], [1,2], [1], [2]
columns1_1 =  [2,1], [1], [2], [1]
validateNonogram(matrix1, rows1_1, columns1_1) => True

Example solution matrix:
matrix1 ->
                                   row
                +------------+     instructions
                | 1  1  1  1 | <-- []
                | 0  1  1  1 | <-- [1]
                | 0  1  0  0 | <-- [1,2]
                | 1  1  0  1 | <-- [1]
                | 0  0  1  1 | <-- [2]
                +------------+
                  ^  ^  ^  ^
                  |  |  |  |
  column       [2,1] | [2] |
  instructions      [1]   [1]


Example instructions #2

(same matrix as above)
rows1_2    =  [], [], [1], [1], [1,1]
columns1_2 =  [2], [1], [2], [1]
validateNonogram(matrix1, rows1_2, columns1_2) => False

The second and third rows and the first column do not match their respective instructions.

Example instructions #3

matrix2 = [
[ 1, 1 ],
[ 0, 0 ],
[ 0, 0 ],
[ 1, 0 ]
]
rows2_1    = [], [2], [2], [1]
columns2_1 = [1, 1], [3]
validateNonogram(matrix2, rows2_1, columns2_1) => False

The black cells in the first column are not separated by white cells.

n: number of rows in the matrix
m: number of columns in the matrix
"""

        matrix1 = [
        [1,1,1,1], # []
        [0,1,1,1], # [1] -> a single run of _1_ zero (i.e.: "0")
        [0,1,0,0], # [1, 2] -> first a run of _1_ zero, then a run of _2_ zeroes
        [1,1,0,1], # [1]
        [0,0,1,1], # [2]
        ]

        # True
        rows1_1 = [[],[1],[1,2],[1],[2]]
        columns1_1 = [[2,1],[1],[2],[1]]
        # False
        rows1_2 = [[],[],[1],[1],[1,1]]
        columns1_2 = [[2],[1],[2],[1]]

        matrix2 = [
        [1,1],
        [0,0],
        [0,0],
        [1,0]
        ]
        # False
        rows2_1 = [[],[2],[2],[1]]
        columns2_1 = [[1,1],[3]]
 */
public class nonogram {

    public static void main(String[] args) {
        int[][] matrix1 = new int[][] {
                {1,1,1,1},
                {0,1,1,1},
                {0,1,0,0},
                {1,1,0,1},
                {0,0,1,1}
        };
        int[][] rows1_1 = new int[][]{ {},{1},{1,2},{1},{2}};
        int[][] columns1_1 = new int[][]{{2,1},{1},{2},{1}};
        System.out.println(validateNonogram(matrix1, rows1_1, columns1_1)); //true

        int[][] rows1_2 = new int[][]{ {},{},{1},{1},{1,1}};
        int[][] columns1_2 = new int[][]{{2},{1},{2},{1}};
        System.out.println(validateNonogram(matrix1, rows1_2, columns1_2)); //false

        int[][] matrix2 = new int[][]{
                {1, 1},
                {0, 0},
                {0, 0},
                {1, 0}
        };
        int[][] rows2_1 = new int[][]{ {},{2},{2},{1}};
        int[][] columns2_1 = new int[][]{{1,1},{3}};
        System.out.println(validateNonogram(matrix2, rows2_1, columns2_1)); //false
    }

    public static boolean validateNonogram(int[][] matrix, int[][] rows, int[][] columns) {
        //by row
        for(int i = 0; i < matrix.length; i++) {
            int[] arr = matrix[i];
            int[] check = rows[i];
            if(!isValid(arr, check)) {
                return false;
            }
        }

        //by column
        for (int j = 0; j < matrix[0].length; j++) {
            int[] arr = new int[matrix.length];
            for(int i = 0; i < matrix.length; i++) {
                arr[i] = matrix[i][j];
            }
            int[] check = columns[j];
            if(!isValid(arr, check)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isValid(int[] arr, int[] check) {
        int i = 0, j = 0;

        int count = 0;
        boolean preBlack = false;
        while(i < arr.length && j < check.length) {
            if(arr[i] == 0) {
                count ++;
                preBlack = true;
            } else {
                if (preBlack) {
                    if (count == check[j]) {
                        j++;
                    } else {
                        return false;
                    }
                }
                count = 0;
                preBlack = false;
            }
            i++;
        }

        //remember to do one more operation for the case when reaching to the end boundary.
        if(preBlack && j < check.length && count == check[j] ) {
            j++;
        }

        while (i < arr.length && arr[i] != 0) {
            i++;
        }

        if (i >= arr.length && j >= check.length) {
            return true;
        }
        return false;
    }
}
