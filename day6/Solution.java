package day6;

public class Solution {

    public static int minPathSum(int[][] grid){
        int row = grid.length;
        int column = grid[0].length;
        int[][] matrix = new int[row][column];
        for (int i = 0; i < row; i ++){
            for (int j = 0; j < column; j ++){
                int top = 0;
                boolean hasTop = false;
                int left = 0;
                boolean hasLeft = false;
                if (i != 0) {
                    top = matrix[i - 1][j];
                    hasTop = true;
                }
                if (j != 0) {
                    left = matrix[i][j - 1];
                    hasLeft = true;
                }
                if (!hasTop) matrix[i][j] = grid[i][j] + left;
                else if (!hasLeft) matrix[i][j] = grid[i][j] + top;
                else matrix[i][j] = grid[i][j] + Math.min(top, left);

            }
        }
        return matrix[row-1][column-1];
    }

    public static int climbChairs(int num){
        if (num == 1) return 1;
        if (num == 2) return 2;
        int[] ways = new int[num];
        ways[0] = 1;
        ways[1] = 2;
        for (int i = 2; i < num; i ++){
            ways[i] = ways[i-1] + ways[i-2];
        }
        return ways[num-1];


    }

    public static void main(String[] args) {
        System.out.println(minPathSum(new int[][]{{0,2,2}}));
    }
}

