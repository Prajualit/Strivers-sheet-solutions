import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {

    public static int[][] merge(int[][] intervals) {

        if (intervals.length <= 1) {
            return intervals;
        }

        Arrays.sort(intervals, (arr1, arr2) -> Integer.compare(arr1[0], arr2[0]));

        List<int[]> result = new ArrayList<>();
        int[] currentInterval = intervals[0];

        result.add(currentInterval);

        for (int[] interval : intervals) {
            int currentStart = interval[0];
            int currentEnd = interval[1];

            int lastMergedEnd = currentInterval[1];

            if (currentStart <= lastMergedEnd) {
                currentInterval[1] = Math.max(lastMergedEnd, currentEnd);
            } else {
                currentInterval = interval;
                result.add(currentInterval);
            }
        }
        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {

        int[][] nums0 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};

        int[][] nums = merge(nums0);

        for (int[] num : nums) {
            for (int j = 0; j < nums[0].length; j++) {
                System.out.print(num[j] + " ");
            }
            System.out.println();
        }
    }
}
