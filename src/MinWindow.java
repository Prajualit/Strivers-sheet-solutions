import java.util.*;

public class MinWindow {

    public static String minWindow(String s, String t) {
        if (t.isEmpty() || s.isEmpty()) {
            return "";
        }

        int l = 0, r = 0;
        int have = 0;
        int smallest = Integer.MAX_VALUE;
        int resultStart = 0;

        Map<Character, Integer> Tmap = new HashMap<>();
        Map<Character, Integer> Smap = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            Tmap.put(t.charAt(i), Tmap.getOrDefault(t.charAt(i), 0) + 1);
        }

        while (r < s.length()) {

            Smap.put(s.charAt(r), Smap.getOrDefault(s.charAt(r), 0) + 1);
            if (Objects.equals(Tmap.get(s.charAt(r)), Smap.get(s.charAt(r)))) {
                have += 1;
            }

            while (Tmap.size() == have) {
                if (smallest > r - l + 1) {
                    smallest = r - l + 1;
                    resultStart = l;
                }
                Smap.put(s.charAt(l), Smap.get(s.charAt(l)) - 1);
                if (Tmap.containsKey(s.charAt(l)) && Smap.get(s.charAt(l)) < Tmap.get(s.charAt(l))) {
                    have -= 1;
                }
                l++;
            }
            r++;
        }

        if (smallest == Integer.MAX_VALUE) {
            return "";
        } else {
            return s.substring(resultStart, resultStart + smallest);
        }
    }

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
//        System.out.println(minWindow("a", "a"));
//        System.out.println(minWindow("a", "aa"));
    }
}
