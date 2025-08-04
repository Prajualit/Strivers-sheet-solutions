import java.util.Objects;

public class RotateString {

    public static boolean rotateString(String s, String goal) {

        if (s.length() != goal.length()) {
            return false;
        }

        String concatenatedS = s + s;

        return concatenatedS.contains(goal);
    }

    public static String rotateLeft(String str, int positions) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        int len = str.length();
        int effectivePositions = positions % len;

        if (effectivePositions < 0) {
            effectivePositions += len;
        }

        String part1 = str.substring(effectivePositions);
        String part2 = str.substring(0, effectivePositions);

        return part1 + part2;
    }

    public static void main(String[] args) {
        String s = "abcde";
        String goal = "cdeab";

        System.out.println(rotateString(s, goal));
    }
}
