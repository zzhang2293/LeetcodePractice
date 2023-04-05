package day7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static void main(String[] args) {

    }

}
