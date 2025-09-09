import java.util.ArrayList;
import java.util.List;

public class Insert {

    public static int[][] insert(int[][] intervals, int[] newInterval) {

        List<int[]> list = new ArrayList<>();
        int i = 0;
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            list.add(intervals[i]);
            i++;
        }
        while (i < intervals.length && !(intervals[i][1] < newInterval[0] || intervals[i][0] > newInterval[1])) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        list.add(newInterval);

        while (i < intervals.length && intervals[i][0] > newInterval[1]) {
            list.add(intervals[i]);
            i++;
        }


        int[][] result = new int[list.size()][2];
        for (int j = 0; j < result.length; j++) {
            result[j] = list.get(j);
        }

        return result;
    }

    public static void main(String[] args) {

        int[][] intervals = {{1, 3}, {6, 9}};
        int[] newInterval = {2, 5};

        int[][] result = insert(intervals, newInterval);

        for (int[] ints : result) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }
}
