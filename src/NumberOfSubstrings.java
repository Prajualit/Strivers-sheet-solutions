import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class NumberOfSubstrings {
    public static int numberOfSubstrings(String s) {

        int l = 0, r = 0;
        int NoOfStrings = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('a', 0);
        map.put('b', 0);
        map.put('c', 0);

        while (r < s.length()) {

            map.put(s.charAt(r), map.get(s.charAt(r)) + 1);

            while (map.get('a') > 0 && map.get('b') > 0 && map.get('c') > 0) {
                map.put(s.charAt(l), map.get(s.charAt(l)) - 1);
                l++;
            }

            NoOfStrings += l;
            r++;
        }
        return NoOfStrings;
    }

    public static void main(String[] args) {
        System.out.println(numberOfSubstrings("abcabc"));
        System.out.println(numberOfSubstrings("aaacb"));
        System.out.println(numberOfSubstrings("abc"));
    }
}
