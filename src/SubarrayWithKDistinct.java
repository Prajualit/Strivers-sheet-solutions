import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SubarrayWithKDistinct {

    public static int subArraysWithKDistinct(int[] nums, int k) {
        return helper(nums, k) - helper(nums, k - 1);
    }

    public static int helper(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        int l = 0;
        int totalSubArrays = 0;

        for (int r = 0; r < nums.length; r++) {
            map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);

            while (map.size() > k) {
                map.put(nums[l], map.get(nums[l]) - 1);
                if (map.get(nums[l]) == 0) {
                    map.remove(nums[l]);
                }

                l++;
            }
            totalSubArrays += r - l + 1;

        }
        return totalSubArrays;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 2, 3};
        int[] nums1 = {1, 2, 1, 3, 4};
        System.out.println(subArraysWithKDistinct(nums, 2));
        System.out.println(subArraysWithKDistinct(nums1, 3));
    }

}
