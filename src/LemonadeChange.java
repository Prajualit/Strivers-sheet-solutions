import java.util.ArrayList;

public class LemonadeChange {

    public static boolean lemonadeChange(int[] bills) {

        int fiveCount = 0;
        int tenCount = 0;

        for (int bill : bills) {

            if (bill == 5) {
                fiveCount += 1;
            } else if (bill == 10) {
                if (fiveCount > 0) {
                    fiveCount -= 1;
                    tenCount += 1;
                } else {
                    return false;
                }
            } else if (bill == 20) {
                if (fiveCount > 0 && tenCount > 0) {
                    fiveCount -= 1;
                    tenCount -= 1;
                } else if (fiveCount >= 3) {
                    fiveCount -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {

        int[] bills = {5, 5, 5, 10, 20};
        int[] bills1 = {5, 5, 10, 10, 20};
        System.out.println(lemonadeChange(bills));
        System.out.println(lemonadeChange(bills1));

    }
}
