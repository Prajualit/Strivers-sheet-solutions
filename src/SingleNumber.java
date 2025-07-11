import java.util.Arrays;

public class SingleNumber {

    public static int singleNumber(int[] nums) {

        Arrays.sort(nums);

        int count = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (count != nums[i] && i % 2 == 1) {
                break;
            }
            count = nums[i];
        }
        return count;
    }

    public static void main(String[] args) {

        int[] nums = {4, 1, 2, 1, 2};
        System.out.println(singleNumber(nums));

    }
}
