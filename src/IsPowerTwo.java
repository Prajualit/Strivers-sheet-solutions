public class IsPowerTwo {

    public static boolean isPowerOfTwo(int n) {
        if (n == 0) {
            return false;
        }
        if (n == 1 || n == 2) {
            return true;
        }
        if (n % 2 != 0) {
            return false;
        }
        if (n / 2 == 2) {
            return true;
        } else {
            return isPowerOfTwo(n / 2);
        }
    }

    public static void main(String[] args) {
        System.out.println(isPowerOfTwo(0));
    }
}
