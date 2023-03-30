package day1;

import java.util.*;

public class Solution {
    /**
     * given a string s, find the length of the longest substring without
     * repeating characters
     * LeetCode #3
     * @param s a string
     *
     * @return max num
     */
    public static int lengthOfLongestSubstring(String s){
        int max = -1;
        int currentMax = 0;
        int length = s.length();
        Queue<Character> list = new LinkedList<>();
        for (int i = 0; i < length; i ++){
            char current = s.charAt(i);
            if (!list.contains(current)){
                // if the list does not contain the character, i will add that into the
                list.add(current);
                currentMax ++;
            }else {
                if (max < currentMax){
                    max = currentMax;
                }
                while(!list.peek().equals(current) ){
                    // poll all the element including the first appearance of the item
                    list.poll();
                }
                list.poll();
                list.add(current);
                currentMax = list.size();
            }
        }
        return Math.max(max, currentMax);
    }

    /**
     * Given a string s, return the longest palindromic substring in s
     * a palindromic read the same from back to front and front to back
     * use dynamic programming skill
     * leetcode #5
     * @param s a string
     * @return a substring
     */
    public static String longestPalindrome(String s){
        int[][] indexes = new int[s.length()][s.length()];
        if (s.length() == 0) return "";
        int maxNum = 1;
        int[] maxIndex = new int[2];
        int len = s.length();
        for (int a = 0; a < len; a ++){
            int j = a;
            for (int i = 0; i < len; i ++){
                if (j == len){
                    break;
                }
                if (i == j){
                    indexes[i][j] = 1;
                    j ++;
                    continue;
                }
                if (s.charAt(i) == s.charAt(j)){
                    if (i == j - 1){
                        // this is aa bb cc case
                        indexes[i][j] = 1; // set to one
                        if ((j - i + 1) > maxNum){
                            maxNum = j - i + 1;
                            maxIndex[0] = i;
                            maxIndex[1] = j;
                        }
                    }else {
                        if (indexes[i+1][j-1] == 1){
                            // this is true
                            indexes[i][j] = 1;
                            if ((j - i + 1) > maxNum){
                                maxNum = j - i + 1;
                                maxIndex[0] = i;
                                maxIndex[1] = j;
                            }
                        }else {
                            indexes[i][j] = 0;
                        }
                    }
                }
                j ++;

            }
        }
        return s.substring(maxIndex[0], maxIndex[1] + 1);

    }

    // T(n) = n + 2 T(n/2)
    // for (1 = n) n
    // call T(n/2) left
    // call T(n/2) right
    /**
     * get the integer from the substring
     * leetcode #8
     * @param s a given string
     * @return an integer
     */
    public static int atoi(String s){
        s = s.trim();
        int start = 0, end = 0;
        boolean negative = false;
        int len = s.length();
        for (int i = 0; i < len; i ++){
            if (s.charAt(i) - '0' >= 0 && s.charAt(i) - '0' <= 9){
                // start of the number
                start = i;
                break;
            }
        }
        if (start != 0) {
            if (s.charAt(start - 1) == '-') {
                negative = true;
            }
        }
        for (int i = start; i < len; i ++){
            if (s.charAt(i) - '0' < 0 || s.charAt(i) - '0' > 9){
                // end of the int
                end = i - 1;
                break;
            }
            if (i == len - 1){
                end = len - 1;
                break;
            }
        }

        String sub = s.substring(start, end + 1);
        while (sub.charAt(0) == '0'){
            sub = sub.substring(1, sub.length()-1);
        }
        int num = Integer.parseInt(sub);
        if (negative){
            num = -num;
        }
        return num;
    }

    public static void main(String[] args) {
        String x = "words and 987";
        System.out.println(atoi(x));
    }
}
