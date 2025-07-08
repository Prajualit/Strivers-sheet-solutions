public class validPalindrome {

    public static String removeSpace(String s) {
        if (!s.contains(" ")) {
            return s;
        }
        s = s.replaceAll("[^a-zA-Z0-9]", "");

        return s.toLowerCase();
    }

    public static boolean isPalindrome(String s) {

        s = removeSpace(s);

        if (s.isEmpty()) {
            return true;
        }

        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) == s.charAt(s.length() - i - 1)) {
                continue;
            } else {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));

    }
}
