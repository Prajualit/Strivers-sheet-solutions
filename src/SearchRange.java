public class SearchRange {
    public static int[] searchRange(int[] nums, int target) {

        int[] result = new int[]{-1, -1};
        if (nums == null || nums.length == 0) {
            return result;
        }

        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] >= target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
            if (nums[mid] == target) {
                result[0] = mid;
            }
        }
        start = 0;
        end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] <= target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
            if (nums[mid] == target) {
                result[1] = mid;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        System.out.println(java.util.Arrays.toString(searchRange(nums, 8))); // Expected: [3, 4]
        System.out.println(java.util.Arrays.toString(searchRange(nums, 6))); // Expected: [-1, -1]
    }
}