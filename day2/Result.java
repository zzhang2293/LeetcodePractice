package day2;

import java.util.ArrayList;
import java.util.List;

public class Result {

    /**
     * helper method of generate
     * @param num number of pairs
     * @param list the list that () will be added
     * @param s one element
     * @param left number of (
     * @param right number of )
     */
    public static void generateParenthesisHelper(int num, List<String> list, String s, int left, int right){
        if (s.length() == num * 2){
            list.add(s);
        }
        if (left < num){
            generateParenthesisHelper(num, list, s + "(", left+1, right);
        }
        if (right < left){
            generateParenthesisHelper(num, list, s + ")", left, right + 1);
        }
    }

    /**
     * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
     * @param num number of pairs
     * @return combination
     */
    public static List<String> generateParenthesis(int num){
        List<String> res = new ArrayList<>();
        generateParenthesisHelper(num, res, "", 0, 0);
        return res;
    }

    public static int longestValidParenthesis(String s){
        int[][] val = new int[s.length()][s.length()];
        int len = s.length();
        for (int a = 0; a < len; a ++){
            int j = a;
            for (int i = 0; i < len; i ++){
                if ( j >= len) break;
                if (i == j) {
                    if (s.charAt(i) == '(') {
                        val[i][j] += -1;
                    } else {
                        val[i][j] = Integer.MAX_VALUE;
                    }
                    j ++;
                    continue;
                }else {
                    if (val[i][j-1] == Integer.MAX_VALUE) {
                        val[i][j] = Integer.MAX_VALUE;
                    }
                    else if (s.charAt(j) == '('){
                        val[i][j] += val[i][j-1] -1;
                    }else {
                        if (val[i][j-1] >= 0){
                            val[i][j] = Integer.MAX_VALUE;
                            j++;
                            continue;
                        }
                        val[i][j] += val[i][j-1] + 1;
                    }
                }
                j ++;
            }
        }
        int longest = 0;
        for (int a = 0; a < len; a ++){
            int j = a;
            for (int i = 0; i <len; i ++){
                if (j >= len){
                    break;
                }
                if (val[i][j] == 0){
                    if (longest < j - i + 1){
                        longest = j - i + 1;
                    }
                }
                j++;
            }

        }
        return longest;
    }

    public static void main(String[] args) {

    }
}
