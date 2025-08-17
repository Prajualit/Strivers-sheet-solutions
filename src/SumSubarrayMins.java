import java.util.ArrayList;
import java.util.List;

public class SumSubarrayMins {
    public static int sumSubarrayMins(int[] arr) {

        int MOD = 1_000_000_007;

        List<List<Integer>> list = new ArrayList<>();
        findSubsets(list, new ArrayList<>(), arr, 0);
        System.out.println(list);
        list.removeFirst();
        System.out.println(list);

        int sum = 0;

        for (int i = 0; i < list.size(); i++) {
            int minimum = Integer.MAX_VALUE;
            List<Integer> l = list.get(i);
            for (int j = 0; j < l.size(); j++) {
                if (l.get(i) < minimum) {
                    minimum = l.get(i);
                }
            }
            sum += minimum;
        }
        return sum % MOD;
    }

    public static void findSubsets(List<List<Integer>> result, List<Integer> currentSubset, int[] nums, int index) {
        result.add(new ArrayList<>(currentSubset));
        for (int i = index; i < nums.length; i++) {
            currentSubset.add(nums[i]);
            findSubsets(result, currentSubset, nums, i + 1);
            currentSubset.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 4};
        System.out.println(sumSubarrayMins(arr));
    }
}
