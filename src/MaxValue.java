import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MaxValue {

    public static int maxValue(int[][] events, int k) {

        int value = 0;
        int count = 0;

        for (int i = 0; i < events.length; i++) {
            int startDate = events[i][0];
            int endDate = events[i][1];

            for (int j = 0; j < events.length; j++) {
                if (count == k) {
                    return value;
                }
                if (i != j && (!(events[j][0] >= startDate && events[j][1] <= endDate) && !((events[j][0] >= startDate && events[j][0] <= endDate) || events[j][0] == startDate || events[j][0] == endDate) && !((events[j][1] >= startDate && events[j][1] <= endDate) || events[j][1] == startDate || events[j][1] == endDate))) {
                    value += events[j][2];
                    count++;
                }
            }
        }

        return value;
    }

    public static int maxValue2(int[][] events, int k) {
        // Sort events by end time
        Arrays.sort(events, (a, b) -> Integer.compare(a[1], b[1]));

        int n = events.length;
        int[][] dp = new int[n + 1][k + 1];
        int[] endTimes = new int[n];
        for (int i = 0; i < n; i++) {
            endTimes[i] = events[i][1];
        }

        for (int i = 1; i <= n; i++) {
            int start = events[i - 1][0];
            int val = events[i - 1][2];

            // Find last event that ends before current starts
            int last = binarySearch(events, start);

            for (int j = 1; j <= k; j++) {
                // Skip or Take
                dp[i][j] = Math.max(dp[i - 1][j], dp[last + 1][j - 1] + val);
            }
        }

        return dp[n][k];
    }

    // Binary search for the last event that ends before 'start'
    private static int binarySearch(int[][] events, int start) {
        int low = 0, high = events.length - 1;
        int res = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (events[mid][1] < start) {
                res = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] events = {{1, 2, 4}, {3, 4, 3}, {2, 3, 1}};
        int k = 2;

        System.out.println(maxValue(events, k));
        System.out.println(maxValue2(events, k));
    }
}
