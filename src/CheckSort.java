import java.util.Arrays;
import java.util.LinkedList;

public class CheckSort {


    public static boolean check(int[] nums) {
        int count = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            // Compare each element with the next, wrapping around using modulo
            if (nums[i] > nums[(i + 1) % n]) {
                count++;
            }
            if (count > 1) return false;
        }

        return true;
    }


    public static void main(String[] args) {

        int[] nums = {3, 4, 5, 1, 2};

        System.out.println(check(nums));

    }
}
