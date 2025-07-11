import java.util.Arrays;

public class ConsecutiveOnes {

    public static int findMaxConsecutiveOnes(int[] nums) {
        int count = 0;
        int inc = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                inc++;
                count = Math.max(inc, count);
            } else {
                inc = 0;
            }
        }
        return count;
    }

    public static void main(String[] args) {

        int[] nums = {0};
        int[] nums1 = {1, 1, 0, 1, 1, 1};
        int[] nums2 = {1, 0, 1, 1, 0, 1};

        System.out.println(findMaxConsecutiveOnes(nums2));
    }
}
