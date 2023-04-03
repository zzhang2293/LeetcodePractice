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

    public static int numDecoding(String s){
        int len = s.length();
        int[] num = new int[len];
        if (s.charAt(0) == '0') return 0;
        if (len == 1) return 1;
        boolean handleIndex2Is0Case = s.charAt(0) == '1' || s.charAt(0) == '2';
        if (len == 2){
            if (s.charAt(1) == '0'){
                if (handleIndex2Is0Case) return 1;
                else return 0;
            }else {
                if (Integer.parseInt(s) <= 26 ) return 2;
                else return 1;
            }
        }
        num[0] = 1;
        if (s.charAt(1) == '0'){
            if (handleIndex2Is0Case) num[1] = 1;
            else num[1] = 0;
        }else {
            if (Integer.parseInt(s.substring(0, 2)) <= 26 ) num[1] = 2;
            else num[1] = 1;
        }


        for (int i = 2; i < len; i ++){
            if (s.charAt(i) != '0')
                num[i] = num[i-1];
            if (s.charAt(i-1) != '0' && Integer.parseInt(s.substring(i-1, i+1)) <= 26) num[i] += num[i-2];
        }

        return num[len-1];
    }

//    public static Integer numDecodingHelper(String s, Integer[] num, int current){
//        int len = s.length();
//        if (current == len - 1) return 1;
//        if (s.charAt(current) == '0') return 0;
//        if (num[current] != null) return num[current];
//
//
//    }

    public static void main(String[] args) {
        System.out.println(numDecoding("1101"));
    }
}

