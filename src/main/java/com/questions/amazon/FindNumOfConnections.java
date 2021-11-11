package com.questions.amazon;

public class FindNumOfConnections {
    public static int findNumOfCollections(int[][] grid) {
        int connections = 0;
        int prevLevelNum = 0;
        for (int i = 0; i < grid.length; i++) {
            int currentLevelNum = 0;
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    currentLevelNum++;
                }
            }
            if (currentLevelNum > 0) {
                if (prevLevelNum != 0) {
                    int levelConns = prevLevelNum * currentLevelNum;
                    connections += levelConns;
                }
                prevLevelNum = currentLevelNum;
            }
        }
        return connections;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1, 0, 1, 1}, {0, 1, 1, 0}, {0, 0, 0, 0}, {1, 0, 0, 0}};
        System.out.println(findNumOfCollections(grid));
    }
}
