import java.util.Arrays;

public class FindContentChildren {

    public static int findContentChildren(int[] g, int[] s) {

        Arrays.sort(g);
        Arrays.sort(s);

        int maxContentChildren = 0;
        int sPointer = 0;

        for (int i = 0; i < s.length; i++) {
            if (sPointer < g.length && s[i] >= g[sPointer]) {
                sPointer += 1;
                maxContentChildren += 1;
                continue;
            }
        }

        return maxContentChildren;
    }

    public static void main(String[] args) {
        int[] g = {1, 2, 3};
        int[] s = {1, 1};
        System.out.println(findContentChildren(g, s));
    }
}
