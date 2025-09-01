public class NumSubarrayWithSum {

    private static int atMost(int[] nums, int goal) {
        if (goal < 0) return 0;

        int l = 0, sum = 0, count = 0;

        for (int r = 0; r < nums.length; r++) {
            sum += nums[r];

            while (sum > goal) {
                sum -= nums[l];
                l++;
            }

            count += (r - l + 1);
        }
        return count;
    }

    public static int numSubarraysWithSum(int[] nums, int goal) {
        return atMost(nums, goal) - atMost(nums, goal - 1);
    }

    public static void main(String[] args) {
        int[] nums = {1, 0, 1, 0, 1};
        int[] nums1 = {0, 0, 0, 0, 0};

        System.out.println(numSubarraysWithSum(nums, 2));  // 4
        System.out.println(numSubarraysWithSum(nums1, 0)); // 15
    }
}
