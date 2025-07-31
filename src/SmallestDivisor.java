import java.util.Arrays;

public class SmallestDivisor {

    public static int smallestDivisor(int[] nums, int threshold) {
        int start = 1;
        int end = 0;

        for (int num : nums) {
            end = Math.max(end, num);
        }

        int res = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (mid == 0) {
                start = 1;
                continue;
            }

            if (isDivisor(nums, threshold, mid)) {
                res = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return res;
    }

    public static boolean isDivisor(int[] nums, int threshold, int divisor) {
        int total = 0;
        for (int num : nums) {
            total += (int) Math.ceil((double) num / divisor);
        }
        return total <= threshold;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 5, 9};
        // For nums={1,2,5,9} and threshold=6
        // Divisor 1: 1+2+5+9 = 17 > 6 (Fail)
        // Divisor 2: 1+1+3+5 = 10 > 6 (Fail)
        // Divisor 3: 1+1+2+3 = 7 > 6 (Fail)
        // Divisor 4: 1+1+2+3 = 7 > 6 (Fail)
        // Divisor 5: 1+1+1+2 = 5 <= 6 (Pass) -> Smallest is 5
        System.out.println("The smallest divisor is: " + smallestDivisor(nums, 6)); // Expected output: 5

        int[] nums2 = {44,22,33,11,1};
        System.out.println("The smallest divisor is: " + smallestDivisor(nums2, 5)); // Expected output: 44
    }
}