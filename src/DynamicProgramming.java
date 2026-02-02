import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class DynamicProgramming {

    public static int countPaths(List<List<String>> grid) {
        return countPaths(0, 0, grid, new HashMap<>());
    }

    public static int countPaths(int r, int c, List<List<String>> grid, HashMap<List<Integer>, Integer> map) {

        if (r == grid.size() || c == grid.getFirst().size()) return 0;

        if (Objects.equals(grid.get(r).get(c), "X")) return 0;

        if (r == grid.size() - 1 && c == grid.getFirst().size() - 1) return 1;

        List<Integer> position = List.of(r, c);
        if (map.containsKey(position)) return map.get(position);

        int result = countPaths(r + 1, c, grid, map) + countPaths(r, c + 1, grid, map);
        map.put(position, result);
        return result;
    }

    public static int maxPathSum(List<List<Integer>> grid) {
        return (int) maxPathSum(0, 0, grid, new HashMap<>());
    }

    public static double maxPathSum(int r, int c, List<List<Integer>> grid, HashMap<List<Integer>, Integer> map) {

        if (r == grid.size() || c == grid.getFirst().size()) return Double.NEGATIVE_INFINITY;

        if (r == grid.size() - 1 && c == grid.getFirst().size() - 1) return grid.get(r).get(c);

        List<Integer> list = List.of(r, c);
        if (map.containsKey(list)) return map.get(list);

        double result = grid.get(r).get(c) + Math.max(maxPathSum(r + 1, c, grid, map), maxPathSum(r, c + 1, grid, map));
        map.put(list, (int) result);

        return result;
    }

    public static int nonAdjacentSum(List<Integer> nums) {
        return nonAdjacentSum(nums, 0, new HashMap<>());
    }

    public static int nonAdjacentSum(List<Integer> nums, int index, HashMap<Integer, Integer> map) {

        if (index >= nums.size()) return 0;

        if (map.containsKey(index)) return map.get(index);

        int result = Math.max(nums.get(index) + nonAdjacentSum(nums, index + 2, map), nonAdjacentSum(nums, index + 1, map));
        map.put(index, result);

        return result;
    }

    public static int summingSquares(int n) {
        return (int) summingSquaresHelper(n);
    }

    public static double summingSquaresHelper(int n) {

        if (n == 0) return 0;

        double minSquares = Double.POSITIVE_INFINITY;

        for (int i = 0; i <= Math.sqrt(n); i++) {
            int square = i * i;
            double numSquares = 1 + summingSquaresHelper(n - square);
            if (numSquares < minSquares) {
                minSquares = numSquares;
            }
        }

        return minSquares;
    }

    public static int countingChange(int amount, List<Integer> coins) {
        return countingChange(amount, coins, 0, new HashMap<>());
    }

    public static int countingChange(int amount, List<Integer> coins, int coinIndex, HashMap<List<Integer>, Integer> map) {

        if (amount == 0) return 1;
        if (coinIndex >= coins.size()) return 0;

        List<Integer> list = List.of(amount, coinIndex);
        if (map.containsKey(list)) return map.get(list);

        int totalWays = 0;
        int value = coins.get(coinIndex);
        for (int qty = 0; qty * value <= amount; qty++) {
            int subAmount = amount - (qty * value);
            totalWays += countingChange(subAmount, coins, coinIndex + 1, map);
        }

        int result = totalWays;
        map.put(list, result);

        return result;
    }

    public static void main(String[] args) {

    }

}
