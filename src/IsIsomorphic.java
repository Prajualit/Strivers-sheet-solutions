import java.util.HashMap;

public class IsIsomorphic {

    public static boolean isIsomorphic(String s, String t) {

        int[] sMap = new int[256];
        int[] tMap = new int[256];

        for (int i = 0; i < s.length(); i++) {
            char char_s = s.charAt(i);
            char char_t = t.charAt(i);

            if (sMap[char_s] != tMap[char_t]) {
                return false;
            }

            sMap[char_s] = i + 1;
            tMap[char_t] = i + 1;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "foo";
        String t = "bar";
        System.out.println(isIsomorphic(s, t));
    }
}
