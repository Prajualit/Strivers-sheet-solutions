import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class BeautySum {

    public static int beautySum(String s) {

        int totalBeauty = 0;

        for (int i = 0; i < s.length(); i++) {

            int[] freq = new int[26];

            for (int j = i; j < s.length(); j++) {

                char currentChar = s.charAt(j);
                freq[currentChar - 'a']++;

                int maxFreq = Integer.MIN_VALUE;
                int minFreq = Integer.MAX_VALUE;

                for (int k = 0; k < 26; k++) {
                    if (freq[k] > 0) {
                        maxFreq = Math.max(maxFreq, freq[k]);
                        minFreq = Math.min(minFreq, freq[k]);
                    }
                }
                totalBeauty += (maxFreq - minFreq);
            }
        }
        return totalBeauty;
    }

    public static void main(String[] args) {
        System.out.println(beautySum("aabcb"));
    }
}
