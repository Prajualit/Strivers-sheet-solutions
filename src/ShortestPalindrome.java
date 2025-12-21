import javax.sound.midi.Soundbank;

public class ShortestPalindrome {

    public static String shortestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        StringBuilder rev = new StringBuilder(s).reverse();
        String temp = s + "#" + rev.toString();

        int[] lps = computeLPS(temp);
        int longestPalindromePrefixLen = lps[temp.length() - 1];

        String suffixToReverse = s.substring(longestPalindromePrefixLen);
        StringBuilder res = new StringBuilder(suffixToReverse).reverse();
        res.append(s);

        return res.toString();
    }

    public static String longestPrefix(String s) {
        int[] lps = computeLPS(s);
        return s.substring(0, lps[s.length() - 1]);
    }

    public static int[] computeLPS(String pattern) {
        int n = pattern.length();
        int[] lps = new int[n];
        int len = 0;
        int i = 1;

        while (i < n) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }


    public static void main(String[] args) {

        String s1 = "aacecaaa";
        String s2 = "abcd";

        System.out.println(shortestPalindrome(s1));
        System.out.println(shortestPalindrome(s2));
    }
}
