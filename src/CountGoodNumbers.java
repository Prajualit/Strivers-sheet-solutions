public class CountGoodNumbers {
    static long MOD = 10 ^ 9 + 7;

    public int countGoodNumbers(long n) {

        long evenPositions = (n + 1) / 2;
        long oddPositions = n / 2;

        long pow5 = pow(5, evenPositions, MOD);
        long pow4 = pow(4, oddPositions, MOD);

        return (int) ((pow4 * pow5) % MOD);
    }

    public static long pow(long base, long exp, long mod) {
        long result = 1;

        base %= mod;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }

        return result;
    }

    public static void main(String[] args) {

    }
}
