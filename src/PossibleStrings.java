public class PossibleStrings {

    public static int possibleStringCount(String word) {

        int count = 0;

        for (int i = 1; i < word.length(); i++) {
            if (word.charAt(i - 1) == word.charAt(i)) {
                count += 1;
            }
        }
        return count + 1;
    }

    public static int possibleStringCount2(String word, int k) {
        if (k > word.length()) {
            return 0;
        }
        if (k == word.length()) {
            return 1;
        }
        for (int i = 0; i < word.length(); i++) {

        }
    }

    public static void main(String[] main) {

        String word = "abbcccc";
//        System.out.println(possibleStringCount(word));

        String word2 = "aabbccdd";
        System.out.println(possibleStringCount2(word2, 7));
    }
}
