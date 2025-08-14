import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsWithDup {

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        findSubsets(result, new ArrayList<>(), nums, 0);
        return result;
    }

    public static void findSubsets(List<List<Integer>> result, List<Integer> currentSubset, int[] nums, int index) {
        result.add(new ArrayList<>(currentSubset));
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i - 1]) {
                continue;
            }
            currentSubset.add(nums[i]);
            findSubsets(result, currentSubset, nums, i + 1);
            currentSubset.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        System.out.println(subsetsWithDup(nums));
    }

}
