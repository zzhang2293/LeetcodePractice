package day7;

import java.util.*;

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }
public class Solution {

    /**
     * find all possible binary tree
     * @param start start val of a binary tree
     * @param end end val of a binary tree
     * @return list of all combination of binary tree
     *
     */
    public static List<TreeNode> generateTreesHelper(int start, int end){
        List<TreeNode> list = new ArrayList<>();
        if (start > end) list.add(null);
        for (int i = start; i <= end; i ++){
            List<TreeNode> left = generateTreesHelper(start, i-1);
            List<TreeNode> right = generateTreesHelper(i+1, end);
            for (TreeNode nodeLeft : left){
                for (TreeNode nodeRight : right){
                    TreeNode root = new TreeNode(i);
                    root.left = nodeLeft;
                    root.right = nodeRight;
                    list.add(root);
                }
            }
        }
        return list;
    }
    public List<TreeNode> generateTrees(int n) {
        return generateTreesHelper(1, n);
    }

    /**
     * interleaving problem
     * Given strings s1, s2, and s3, find whether s3 is
     * formed by an interleaving of s1 and s2.
     * #97
     * @param s1 a string
     * @param s2 a string
     * @param s3 interleaving string
     * @return true if s3 is interleaving
     */
    public static boolean isInterleave(String s1, String s2, String s3){
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 + len2 != s3.length()) return false;
        boolean[][] index = new boolean[len1+1][len2+1];
        index[len1][len2] = true;
        for (int i = len1; i >= 0; i --){
            for(int j = len2; j >= 0; j --){
                if (i < len1 && index[i+1][j] && s1.charAt(i) == s3.charAt(i+j)) index[i][j] = true;
                if (j < len2 && index[i][j+1] && s2.charAt(j) == s3.charAt(i+j)) index[i][j] = true;
            }
        }
        return index[0][0];
    }

    public static int numDistinct(String s, String t){
        int lenS = s.length();
        int lenT = t.length();
        int[][] index = new int[lenT+1][lenS+1];
        for(int i = 0; i <= lenS; i ++)
            index[0][i] = 1;

        for (int i = 1; i < lenT; i ++)
            index[i][0] = 0;
        for( int i = 1; i <= lenT; i ++){
            for (int j = 1; j <= lenS; j ++){
                index[i][j] = index[i][j-1];
                if (s.charAt(j-1) == t.charAt(i-1)){
                    index[i][j] += index[i-1][j-1];
                }
            }
        }
        return index[lenT][lenS];

    }

    public static int maximalRectangle(int[] prices){
        int buy = prices[0];
        int sell = 0;
        for (int price : prices){
            buy = Math.min(buy, price);
            sell = Math.max(sell, price - buy);
        }
        return sell;
    }
    public static int maxProfit(int[] prices){
        int money = 0;
        int currentLowPrice = Integer.MAX_VALUE;
        for (int price : prices){
            if (currentLowPrice > price){
                currentLowPrice = price;
                continue;
            }
            money += price - currentLowPrice;
            currentLowPrice = price;

        }
        return money;
    }


    public static int findTargetSumWaysHelper(Map<Pair, Integer> index, int[] nums, int pointer, int current, int target){
        int len = nums.length;
        if (pointer == len && current != target ) return 0;
        if (pointer == len) return  1;
        Pair pair = new Pair(pointer, current);
        if (index.containsKey(pair)) {
            System.out.println("true");
            return index.get(pair);
        }
        int ways = findTargetSumWaysHelper(index, nums, pointer + 1, current+nums[pointer], target)
                + findTargetSumWaysHelper(index, nums, pointer+1, current-nums[pointer],target);
        index.put(pair, ways);
        return ways;


    }
    public static int findTargetSumWays(int[] nums, int target){
        int sum = Arrays.stream(nums).sum();
        Map<Pair, Integer> maps = new HashMap<>();
        return findTargetSumWaysHelper(maps, nums, 0, 0, target);
    }

    /**
     *  #983
     * @param days days need travel
     * @param cost cost of different tickets
     * @return minimum dollar
     */
    public static int minCostTickets(int[] days, int[] cost){
        int[] index = new int[days.length];

        return minCostHelper(index, days, cost, 0, 0);
    }
    private static int minCostHelper(int[] matrix, int[] days, int[] costs, int cover, int money){
        int dayLen = days.length;
        if (cover >= days[dayLen-1]) return money;
        int index = -1;
        for (int i = 0; i < dayLen; i ++){
            if (days[i] <= cover) index = i;
            else break;
        }
        if (matrix[index+1] != 0) return money + matrix[index+1];
        int min = Integer.MAX_VALUE;
        min = Math.min(min, minCostHelper(matrix, days, costs, days[index+1], costs[0]));
        min = Math.min(min, minCostHelper(matrix, days, costs, days[index+1]+6, costs[1]));
        min = Math.min(min, minCostHelper(matrix, days, costs, days[index+1]+29, costs[2]));
        matrix[index+1] = min;
        return min + money;
    }

    public static void main(String[] args) {
        System.out.println(minCostTickets(new int[]{1,4,6,7,8,20}, new int[]{2,7,15}));
    }

}
class Pair{
    public int pointer;
    public int value;
    public Pair(){

    }
    public Pair(int pointer, int value){
        this.pointer = pointer;
        this.value = value;
    }
    @Override
    public boolean equals(Object obj) {
        Pair other;
        other = (Pair) obj;
        return this.pointer == other.pointer && this.value == other.value;
    }

    @Override
    public int hashCode() {
        return this.pointer;
    }
}
