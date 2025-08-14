import java.util.ArrayList;
import java.util.List;

public class Partition {

    public static List<List<String>> partition(String s) {

        List<List<String>> result = new ArrayList<>();

        findResult(result, new ArrayList<>(), s, 0);
        return result;
    }

    public static void findResult(List<List<String>> result, List<String> currentPartition, String s, int index) {

        if (!currentPartition.isEmpty() && index == s.length()) {
            result.add(new ArrayList<>(currentPartition));
        }
        for (int i = index; i < s.length(); i++) {
            if (isPalindrome(s.substring(index, i + 1))) {
                currentPartition.add(s.substring(index, i + 1));
                findResult(result, currentPartition, s, i + 1);
                currentPartition.removeLast();
            }
        }
    }

    public static boolean isPalindrome(String s) {

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
        System.out.println(partition("aab"));
    }
}
