package day4;

import java.util.Arrays;

public class solution {
    /**
     * You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].
     * Each element nums[i] represents the maximum length of a forward jump from index i.
     * In other words, if you are at nums[i], you can jump to any nums[i + j] where:
     *
     * #45
     * @param nums a given array
     * @return minimum jump
     */
    public static int jump(int[] nums){
        // current + nums[current]
        int len = nums.length;
        if (len == 0 || len == 1) return 0;
        if (nums[0] >= len - 1) return 1;
        if (nums[0] == 0) return Integer.MAX_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= nums[0]; i ++){
            int current = jump(Arrays.copyOfRange(nums, i, len));
            if (current == -1) continue;
            if (current < min){
                min = current;
            }
        }
        if (min == Integer.MAX_VALUE){
            return -1;
        }
        return min + 1;

    }
    public static int jump2(int[] nums){
        if (nums.length == 0 || nums.length == 1) return 0;
        int len = nums.length;
        int[] track = new int[len];
        int step = 0;
        int far = 0;
        int current = 0;
        for (int i = 0; i < len; i ++){
            far = Math.max(far, i + nums[i]);
            if (i == current){
                if (i == len-1) break;
                current = far;
                step ++;
            }
        }
        return step;
    }


    /**
     * Given an integer array nums, find the
     * subarray with the largest sum, and return its sum.
     * #53
     * @param nums array of nums
     * @return max subarray
     */
    public static int maxSubArray(int[] nums){
        int len = nums.length;
        if (len == 1) return nums[0];
        int[] track = new int[len];
        track[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < len; i ++){
            track[i] = Math.max(track[i-1] + nums[i], nums[i]);
            if (max < track[i]){
                max = track[i];
            }
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[] {-1,-2}));
    }
}
