public class NextPermutation {

    public static void nextPermutation(int[] nums) {

        if (nums == null || nums.length <= 1) {
            return;
        }

        int pivotIndex = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                pivotIndex = i;
                break;
            }
        }

        if (pivotIndex == -1) {
            reverse(nums, 0);
            return;
        }

        for (int i = nums.length - 1; i > pivotIndex; i--) {
            if (nums[i] > nums[pivotIndex]) {
                swap(nums, pivotIndex, i);
                break;
            }
        }

        reverse(nums, pivotIndex + 1);
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void reverse(int[] nums, int start) {
        int i = start;
        int j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        nextPermutation(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
