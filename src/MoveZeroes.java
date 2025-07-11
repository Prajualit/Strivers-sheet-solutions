import java.util.ArrayList;

public class MoveZeroes {

    public static void moveZeroes(int[] nums) {
        int count = 0;
        for (int num : nums) {
            if (num != 0) {
                nums[count++] = num;
            }
        }
        while (count < nums.length) {
            nums[count++] = 0;
        }
    }

    public static void main(String[] args) {

        int[] nums = {0, 1, 0};

        moveZeroes(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }

    }
}
