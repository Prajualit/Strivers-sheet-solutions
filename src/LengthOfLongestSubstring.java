import java.util.HashSet;
import java.util.Set;

public class LengthOfLongestSubstring {
    public static int lengthOfLongestSubstring(String s) {

        if (s.isEmpty()) {
            return 0;
        }
        int largest = Integer.MIN_VALUE;
        Set<Character> set = new HashSet<>();

        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                while (j < i && set.contains(s.charAt(i))) {
                    set.remove(s.charAt(j));
                    j++;
                }
            }
            set.add(s.charAt(i));
            largest = Math.max(largest, i - j + 1);
        }
        return largest;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }
}
