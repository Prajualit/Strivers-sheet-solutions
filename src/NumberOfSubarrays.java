public class NumberOfSubarrays {

    public static int numberOfSubarrays(int[] nums, int k) {
        return helper(nums, k) - helper(nums, k - 1);
    }

    public static int helper(int[] nums, int k) {
        int l = 0, r = 0;
        int SubarrayCount = 0;
        int OddCount = 0;
        while (r < nums.length) {

            if (nums[r] % 2 == 1) {
                OddCount++;
            }

            while (OddCount > k) {
                if (nums[l] % 2 == 1) {
                    OddCount--;
                }
                l++;
            }

            SubarrayCount += r - l + 1;

            r++;
        }

        return SubarrayCount;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 1, 1};
        int[] nums1 = {2, 2, 2, 1, 2, 2, 1, 2, 2, 2};
        int[] nums2 = {2, 4, 6};
        System.out.println(numberOfSubarrays(nums, 3));
        System.out.println(numberOfSubarrays(nums1, 2));
        System.out.println(numberOfSubarrays(nums2, 1));
    }
}
