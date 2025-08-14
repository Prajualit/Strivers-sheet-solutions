import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        findCombo(result, new ArrayList<>(), k, n, 1);
        return result;
    }

    public static void findCombo(List<List<Integer>> result, List<Integer> currentCombo, int k, int n, int index) {
        if (n == 0 && k == 0) {
            result.add(new ArrayList<>(currentCombo));
        }
        if (n < 0 || k < 0) {
            return;
        }
        for (int i = index; i <= 9; i++) {
            currentCombo.add(i);
            findCombo(result, currentCombo, k - 1, n - i, i + 1);
            currentCombo.removeLast();
        }
    }

    public static void main(String[] args) {
        System.out.println(combinationSum3(3, 7));
    }

}
