import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Contests {

    public int mirrorDistance(int n) {

        return Math.abs(n - reverse(n));

    }

    public int reverse(int n) {
        StringBuilder s = new StringBuilder(String.valueOf(n));

        s.reverse();

        return Integer.parseInt(s.toString());
    }

    public static long minCost(String s, int[] cost) {

        long totalCost = 0;
        long[] charTotalCost = new long[26];
        boolean[] isPresent = new boolean[26];

        for (int i = 0; i < s.length(); i++) {
            int charIndex = s.charAt(i) - 'a';
            totalCost += cost[i];
            charTotalCost[charIndex] += cost[i];
            isPresent[charIndex] = true;
        }

        long minimumTotalCost = Long.MAX_VALUE;

        for (int i = 0; i < 26; i++) {
            if (isPresent[i]) {
                long costToKeep = totalCost - charTotalCost[i];
                minimumTotalCost = Math.min(minimumTotalCost, costToKeep);
            }
        }

        return minimumTotalCost;
    }

    public int minSwaps(int[] nums, int[] forbidden) {

        int n = nums.length;
        int[][] pair = new int[n][2];
        for (int i = 0; i < n; i++) {
            pair[i][0] = nums[i];
            pair[i][1] = i;
        }

        Arrays.sort(pair, (a, b) -> Integer.compare(a[0], b[0]));

        boolean[] visited = new boolean[n];
        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i] || pair[i][1] == i) {
                continue;
            }

            int cycleSize = 0;
            int j = i;
            while (!visited[j]) {
                visited[j] = true;
                j = pair[j][1];
                cycleSize++;
            }

            if (cycleSize > 0) {
                ans += (cycleSize - 1);
            }
        }

        return ans;
    }

    public static void main(String[] args) {

        System.out.println(minCost("aabaac", new int[]{1, 2, 3, 4, 1, 10}));

    }
}
