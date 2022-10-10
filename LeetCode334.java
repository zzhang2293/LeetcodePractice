import java.util.*;
public class LeetCode334 {
    public static boolean hasBigger(int[] nums, int start, int val){
        for ( int i = start; i < nums.length; i ++){
            if ( nums[i] > val ) return true;
        }
        return false;
    }
    public static boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) return false;
        int length = nums.length;
        int first = nums[0], second = Integer.MAX_VALUE;
        for (int i = 1; i < length; i ++){
            if ( nums[i] > second ) return true;
            else if (nums[i] > first && nums[i] < second) second = nums[i];
            else {
                first = nums[i];
               // second = Integer.MAX_VALUE;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] test1 = {1,2,3};
        System.out.println(increasingTriplet(test1));
        int[] test2 = {1,2,1,4,3,2,3,1,5,6,3};
        System.out.println(increasingTriplet(test2));
        int[] test3 = {1,3,2,4,3,5,4,6,5};
        System.out.println(increasingTriplet(test3));
        System.out.println(increasingTriplet(new int[] {0,4,2,1,0,-1,-3}));
    }
}
