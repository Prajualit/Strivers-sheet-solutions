import java.util.EnumSet;

public class MinEatingSpeed {
    public int minEatingSpeed(int[] piles, int h) {
        int l = 1;
        int r = 0;
        for (int p : piles) {
            r = Math.max(r, p);
        }

        int res = r;

        while (l <= r) {
            int k = l + (r - l) / 2;
            if (k == 0) { // Safeguard, though l starts at 1
                l = 1;
                continue;
            }

            long totalTime = 0;
            for (int p : piles) {
                totalTime += (long) (p + k - 1) / k;
            }

            if (totalTime <= h) {
                res = k;
                r = k - 1;
            } else {
                l = k + 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
