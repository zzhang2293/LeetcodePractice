package day3;

import java.util.Arrays;

public class Solution {
    /**
     * Given n non-negative integers representing an elevation map where the width of each bar is 1,
     * compute how much water it can trap after raining.
     *
     * @param height all individual height
     * @return sum of rain
     */
    public static int trap1(int[] height) {

        int[][][] info = new int[height.length][height.length][2];
        int len = height.length;
        for (int a = 0; a < len; a++) {
            int j = a;
            for (int i = 0; i < len; i++) {
                if (j >= len) break;
                if (i == 0 || j == len - 1) {
                    j++;
                    continue;
                }
                int left = height[i - 1];
                int innerLeft = height[i];
                int right = height[j + 1];
                int innerRight = height[j];
                int dif = 0;
                if (i == j){
                    dif = Math.min(left, right) - innerLeft;
                    info[i][j][1] = innerLeft;
                    if (dif > 0){
                        info[i][j][0] = dif;
                    }
                }else {
                    int innerMax = Math.max(innerLeft, innerRight);
                    info[i][j][1] = Math.max(info[i + 1][j - 1][1], innerMax);
                    dif = Math.min(left, right) - Math.max(info[i + 1][j - 1][1], innerMax);
                }
                if (dif > 0) {
                    info[i][j][0] = (j - i + 1 ) * dif;
                } else {
                    info[i][j][0] = 0;
                }


                j++;
            }
        }
        int total = 0;
        for (int i = 0; i < len; i ++){
            for (int j = 0; j < len; j ++){
                total += info[i][j][0];
            }
        }
        return total;
    }

    /**
     * better solution
     * @param height all individual height
     * @return sum of rain
     */
    public static int trap2(int[] height){
        int len = height.length;
        int[] ltr = new int[len];
        ltr[0] = height[0];
        int[] rtl = new int[len];
        rtl[len-1] = height[len-1];
        for (int i = 1; i < len; i ++){
            ltr[i] = Math.max(ltr[i-1], height[i]);
            rtl[len-1-i] = Math.max(rtl[len-i], height[len-1-i]);
        }
        int sum = 0;
        for (int i = 0; i < len; i ++){
            sum += Math.max(Math.min(ltr[i], rtl[i]) - height[i], 0);
        }
        return sum;
    }


    public static void main(String[] args) {
        int[] height = {10527,740,9013,1300,29680,4898,13993,15213,18182,24254,3966,24378,11522,9190,6389,32067,21464,7115};
        System.out.println(trap2(height));
    }
}
