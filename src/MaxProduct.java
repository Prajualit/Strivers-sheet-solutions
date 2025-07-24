public class MaxProduct {

    public static int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int maxSoFar = nums[0];
        int minSoFar = nums[0];
        int result = maxSoFar;

        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];

            int tempMax = Math.max(current, Math.max(maxSoFar * current, minSoFar * current));
            minSoFar = Math.min(current, Math.min(maxSoFar * current, minSoFar * current));

            maxSoFar = tempMax;

            result = Math.max(maxSoFar, result);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, -2, 4};
        System.out.println(maxProduct(nums)); // Expected: 6

        int[] nums1 = {-2, 0, -1};
        System.out.println(maxProduct(nums1)); // Expected: 0

        int[] nums2 = {-3, -1, -2};
        System.out.println(maxProduct(nums2)); // Expected: 6
    }
}