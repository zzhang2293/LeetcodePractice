package day5;

public class Solution {

    public static int uniquePathsHelper(int m, int n, int x, int y){
        if (x == n - 1 && y == m - 1) return 1;
        int sum = 0;
        if (x < n - 1) sum += uniquePathsHelper(m, n, x + 1, y);
        if (y < m - 1) sum += uniquePathsHelper(m, n, x, y + 1);
        return sum;
    }
    public static int uniquePaths(int m, int n){
        return uniquePathsHelper(m, n, 0, 0);
    }

    public static int uniquePaths2(int m, int n){
        int[][] matrix = new int[m][n];
        int left = 0, top = 0;
        matrix[0][0] = 1;
        for (int i = 0; i < m; i ++){
            for (int j = 0; j < n; j ++){
                if (i == 0 && j == 0) continue;
                if ( i != 0)
                    top = matrix[i-1][j];
                if (j != 0)
                    left = matrix[i][j-1];
                matrix[i][j] = top + left;
                top = 0;
                left = 0;
            }

        }
        return matrix[m-1][n-1];

    }
    public static int uniquePathWithObstacles(int[][] obstacleGrid){
        int row = obstacleGrid.length;
        int column = obstacleGrid[0].length;
        if (row == 1 && column == 1 && obstacleGrid[0][0] == 1) return 0;
        int[][] matrix = new int[row][column];
        matrix[0][0] = 1;
        int top = 0, left = 0;
        for (int i = 0; i < row; i ++){
            for (int j = 0; j < column; j ++){
                if (i == 0 && j == 0) continue;
                if ( obstacleGrid[i][j] == 1) continue;
                if (i != 0) top = matrix[i-1][j];
                if (j != 0) left = matrix[i][j-1];
                matrix[i][j] = top + left;
                top = 0;
                left = 0;
            }
        }
        return matrix[row-1][column-1];
    }

    public static void main(String[] args) {
        System.out.println(uniquePathWithObstacles(new int[][]{{0,0,0},{0,1,0},{0,0,0}}));
    }
}
