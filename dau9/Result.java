package dau9;

import java.util.Arrays;

public class Result {
    /**
     * Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.
     *
     * @param nums num of arrays
     * @return true if satisfied
     */
    public static boolean canPartition(int[] nums){
        int average = Arrays.stream(nums).sum();
        if (average % 2 != 0) return false;
        average = average/2;
        int lengthOfNums = nums.length;
        boolean[][] matrix = new boolean[lengthOfNums][average+1];
        // initialize the val
        for (int i = 0; i < lengthOfNums; i ++)
            matrix[i][0] = true;

        for(int currentNum = 1; currentNum <= average; currentNum ++){
            for (int indexOfCoins = lengthOfNums-1; indexOfCoins >= 0; indexOfCoins --){
                if (nums[indexOfCoins] - currentNum == 0)
                    matrix[indexOfCoins][currentNum] = true;
                else{
                    if (indexOfCoins == lengthOfNums-1)
                        matrix[indexOfCoins][currentNum] = false;
                    else {
                        if (currentNum - nums[indexOfCoins] < 0)
                            matrix[indexOfCoins][currentNum] = matrix[indexOfCoins+1][currentNum];
                        else matrix[indexOfCoins][currentNum] = matrix[indexOfCoins+1][currentNum] ||
                                matrix[indexOfCoins+1][currentNum-nums[indexOfCoins]];
                    }


                }
            }
        }
        return matrix[0][average];
    }


    public static void main(String[] args) {
        System.out.println(canPartition(new int[]{1,5,11,5}));
    }
}
