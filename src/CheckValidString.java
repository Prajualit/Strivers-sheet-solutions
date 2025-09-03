public class CheckValidString {

    public static boolean checkValidString(String s) {

        int min_Open = 0;
        int max_Open = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                min_Open += 1;
                max_Open += 1;
            } else if (s.charAt(i) == ')') {
                min_Open -= 1;
                max_Open -= 1;
            } else {
                min_Open -= 1;
                max_Open += 1;
            }

            if (max_Open < 0) {
                return false;
            }

            if (min_Open < 0) {
                min_Open = 0;
            }

        }

        return min_Open == 0;
    }

    public static void main(String[] args) {
        System.out.println(checkValidString("()"));
        System.out.println(checkValidString("(*)"));
        System.out.println(checkValidString("(*))"));
    }
}
