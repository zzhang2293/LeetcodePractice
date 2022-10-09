public class LeetCode334 {
    public static boolean increasingTriplet(int[] nums) {
        if (nums.length == 0 || nums.length == 1) return false;
        int left = 0, right = 1, length = nums.length;
        while (left + 2 < length) {
            int current = nums[left];
            int end = nums[right];
            while (end > current) {
                if (right - left >= 2) {
                    return true;
                }
                current = nums[right];
                right++;
                if (right > length - 1) break;
                end = nums[right];
            }
            left = right;
            right++;
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
    }
}
