import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        findCombination(result, new ArrayList<>(), candidates, target, 0);

        return result;
    }

    public static void findCombination(List<List<Integer>> result, List<Integer> currentCombo, int[] candidates, int target, int index) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            result.add(new ArrayList<>(currentCombo));
        }
        for (int i = index; i < candidates.length; i++) {
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            currentCombo.add(candidates[i]);
            findCombination(result, currentCombo, candidates, target - candidates[i], i + 1);
            currentCombo.removeLast();
        }
    }

    public static void main(String[] args) {

    }

}
