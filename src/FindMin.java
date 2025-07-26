public class FindMin {
    public int findMin(int[] nums) {

        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > nums[end]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return nums[start];
    }

    public static void main(String[] args) {
        int[] nums = {3, 4, 5, 1, 2};
        FindMin f = new FindMin();
        System.out.println(f.findMin(nums));
    }
}
