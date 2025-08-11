import java.util.ArrayList;
import java.util.List;

public class Subsets {

    public static List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        findSubsets(result, new ArrayList<>(), nums, 0);
        return result;
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
        int[] nums = {1, 2, 3};
        System.out.println(subsets(nums));
    }
}
