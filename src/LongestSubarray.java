public class LongestSubarray {

    public static int longestSubarray(int[] nums, int k) {
        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            int currentSum = 0;
            for (int j = i; j < nums.length; j++) {
                currentSum += nums[j];

                if (currentSum <= k) {
                    int currentLength = j - i + 1;
                    if (currentLength > maxLength) {
                        maxLength = currentLength;
                    }
                } else {
                    break;
                }
            }

        }
        return maxLength;
    }

    public static void main(String[] args) {

        int[] nums = {10, 5, 2, 7, 1, 9};

        System.out.println(longestSubarray(nums, 15));
    }
}
