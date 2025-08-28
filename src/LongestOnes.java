public class LongestOnes {

    public static int longestOnes(int[] nums, int k) {

        int l = 0, r = 0;
        int maxOnes = 0;
        int count = 0;
        while (r < nums.length) {
            if (nums[r] == 0) {
                count++;
            }
            while (count > k) {
                if (nums[l] == 0) {
                    count--;
                }
                l++;
            }
            maxOnes = Math.max(maxOnes, r - l + 1);
            r++;
        }
        return maxOnes;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int[] nums1 = {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        System.out.println(longestOnes(nums, 2));
        System.out.println(longestOnes(nums1, 3));
    }

}
