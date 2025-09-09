import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class EraseOverlapIntervals {

    public int eraseOverlapIntervals(int[][] intervals) {

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        int removed = 0;
        int previousEnd = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < previousEnd) {
                removed += 1;
                previousEnd = Math.min(intervals[i][1], previousEnd);
            } else {
                previousEnd = intervals[i][1];
            }
        }


        return removed;
    }

    public static void main(String[] args) {

    }

}
