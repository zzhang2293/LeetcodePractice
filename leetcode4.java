//mid of two arrays
class Solution {
    public static void change(int[] num){
        for (int i = 0; i < num.length - 1; i ++){
            num[i] = num[i + 1];
        }
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] sum = new int[nums1.length + nums2.length];
        int index = 0;
        int size1 = nums1.length, size2 = nums2.length;
        while(size1 != 0 || size2 != 0){
            if(size1 == 0){
                sum[index] = nums2[0];
                size2 --;
                change(nums2);
            }else if (size2 == 0){
                sum[index] = nums1[0];
                size1 --;

                change(nums1);
            }else{
                if (nums1[0] <= nums2[0]){
                    sum[index] = nums1[0];
                    size1 --;
                    change(nums1);

                }else{
                    sum[index] = nums2[0];
                    size2 --;
                    change(nums2);
                }
                
            }
            index ++;
        }
        if(sum.length % 2 == 1) return sum[sum.length / 2];
        else return (sum[sum.length / 2-1] + sum[sum.length/2])/2.0;
    }
}
