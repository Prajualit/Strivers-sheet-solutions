import java.util.Arrays;

public class MinBitFlips {

    public static int minBitFlips(int start, int goal) {

        start = start ^ goal;

        return Integer.bitCount(start);
    }

    public static int singleNumber(int[] nums) {

        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    }

    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }

        boolean[] primes = new boolean[n];
        Arrays.fill(primes, true);

        primes[0] = false;
        primes[1] = false;

        for (int i = 2; i * i < n; i++) {
            if (primes[i]) {
                for (int j = i * i; j < n; j += i) {
                    primes[j] = false;
                }
            }
        }

        int count = 0;
        for (int i = 2; i < n; i++) {
            if (primes[i]) {
                count++;
            }
        }
        return count;
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3, 2};
        System.out.println(singleNumber(nums));
    }
}
