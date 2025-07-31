public class ShipWithinDays {

    public int shipWithinDays(int[] weights, int days) {

        int start = 0;
        int end = 0;
        for (int weight : weights) {
            start = Math.max(weight, start);
            end += weight;
        }

        int capacity = end;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (isPossible(weights, mid, days)) {
                capacity = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return capacity;
    }

    public static boolean isPossible(int[] weights, int capacity, int days) {
        int daysNeeded = 1;
        int currentLoad = 0;

        for (int weight : weights) {
            if (currentLoad + weight > capacity) {
                daysNeeded++;
                currentLoad = weight;
            } else {
                currentLoad += weight;
            }
        }
        return daysNeeded <= days;
    }

    public static void main(String[] args) {

    }
}
