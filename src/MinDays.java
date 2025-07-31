public class MinDays {

    public int minDays(int[] bloomDay, int m, int k) {
        if (bloomDay == null || bloomDay.length < (long) m * k) {
            return -1;
        }

        int start = 1;
        int end = 0;
        for (int day : bloomDay) {
            end = Math.max(end, day);
        }

        int result = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (canMakeBouquets(bloomDay, mid, m, k)) {
                result = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return result;
    }

    private boolean canMakeBouquets(int[] bloomDay, int day, int m, int k) {
        int bouquets = 0;
        int flowers = 0;

        for (int d : bloomDay) {
            if (d <= day) {
                flowers++;
            } else {
                flowers = 0;
            }
            if (flowers == k) {
                bouquets++;
                flowers = 0;
            }
        }
        return bouquets >= m;
    }


    public static void main(String[] args) {
        MinDays m = new MinDays();
        int[] bloomDay = {1, 10, 3, 10, 2};
        System.out.println(m.minDays(bloomDay, 3, 1));
    }
}
