package com.questions.amazon;

import java.util.LinkedList;

public class RobotMinSteps {
    static class Grid {
        int row;
        int col;

        public Grid(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "Grid{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }
    }

    public static int minSteps(int[][] matrix) {
        int steps = 0;
        int targetValue = 9;

        LinkedList<Grid> currentLevelGrids = new LinkedList<>();
        Grid start = new Grid(0, 0);
        currentLevelGrids.offer(start);
        matrix[start.row][start.col] = 0;

        while (!currentLevelGrids.isEmpty()) {
            System.out.println(currentLevelGrids);
            LinkedList<Grid> nextLevelGrids = new LinkedList<>();
            for (Grid grid : currentLevelGrids) {
                int row = grid.row;
                int col = grid.col;

//                for (int i = (row-1 >= 0) ? row-1:row; i < matrix.length && i <= row+1; i++) {
//                    for (int j = (col-1 >= 0) ? col-1:col; j < matrix[i].length && j <= col+1; j++) {
//                        if (matrix[i][j] != 0) {
//                            if (matrix[i][j] == targetValue) {
//                                return steps + 1;
//                            } else {
//                                Grid newGrid = new Grid(i, j);
//                                nextLevelGrids.offer(newGrid);
//                                matrix[i][j] = 0;
//                            }
//                        }
//                    }
//                }

                if (row - 1 >= 0 && matrix[row - 1][col] != 0) {
                    if (addGrid(nextLevelGrids, row - 1, col, matrix) == 1) {
                        return steps + 1;
                    }
                }

                if (row + 1 < matrix.length && matrix[row + 1][col] != 0) {
                    if (addGrid(nextLevelGrids, row + 1, col, matrix) == 1) {
                        return steps + 1;
                    }
                }

                if (col - 1 >= 0 && matrix[row][col - 1] != 0) {
                    if (addGrid(nextLevelGrids, row, col - 1, matrix) == 1) {
                        return steps + 1;
                    }
                }

                if (col + 1 < matrix[row].length && matrix[row][col + 1] != 0) {
                    if (addGrid(nextLevelGrids, row, col + 1, matrix) == 1) {
                        return steps + 1;
                    }
                }
            }
            steps++;
            currentLevelGrids = nextLevelGrids;
        }

        return -1;
    }

    private static int addGrid(LinkedList<Grid> nextLevelGrids, int row, int col, int[][] matrix) {
        if (matrix[row][col] == 9) {
            return 1;
        }

        Grid newGrid = new Grid(row, col);
        nextLevelGrids.offer(newGrid);
        matrix[row][col] = 0;
        return 0;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 0, 0}, {1, 0, 0}, {1, 9, 0}};
        System.out.println(minSteps(matrix));
    }
}
