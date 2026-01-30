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



    }

    public static void main(String[] args) {

    }

}
