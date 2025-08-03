public class ReverseWords {

    public static String reverseWords(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        StringBuilder result = new StringBuilder();

        String trimmedS = s.trim();
        int n = trimmedS.length();
        int i = n - 1;
        int wordEnd = n;

        while (i >= 0) {
            if (trimmedS.charAt(i) == ' ') {
                result.append(trimmedS, i + 1, wordEnd).append(" ");

                while (i >= 0 && trimmedS.charAt(i) == ' ') {
                    i--;
                }
                wordEnd = i + 1;
            } else {
                i--;
            }
        }
        result.append(trimmedS, 0, wordEnd);
        return result.toString();
    }

    public static void main(String[] args) {
        String s = "the sky is blue";
        System.out.println("'" + reverseWords(s) + "'");

        String s1 = "a good   example";
        System.out.println("'" + reverseWords(s1) + "'");

        String s2 = "  hello world  ";
        System.out.println("'" + reverseWords(s2) + "'");
    }
}