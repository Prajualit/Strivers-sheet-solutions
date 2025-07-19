import java.util.Arrays;

public class RearrangeArray {

    public static int[] rearrangeArray(int[] nums) {
        int n = nums.length;
        int[] posArray = new int[n / 2];
        int[] negArray = new int[n / 2];

        int posIndex = 0;
        int negIndex = 0;

        for (int num : nums) {
            if (num > 0) {
                posArray[posIndex++] = num;
            } else {
                negArray[negIndex++] = num;
            }
        }

        posIndex = 0;
        negIndex = 0;

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                nums[i] = posArray[posIndex++];
            } else {
                nums[i] = negArray[negIndex++];
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, -2, -5, 2, -4};

        nums = rearrangeArray(nums);
        System.out.println(Arrays.toString(nums));
    }
}