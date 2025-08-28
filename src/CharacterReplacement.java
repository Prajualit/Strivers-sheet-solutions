public class CharacterReplacement {
    public static int characterReplacement(String s, int k) {

        int l = 0, r = 0;
        int[] freq = new int[26];
        int maxLength = 0;
        int count = 0;

        while (r < s.length()) {

            freq[s.charAt(r) - 'A']++;
            count = Math.max(count, freq[s.charAt(r) - 'A']);

            while ((r - l + 1) - count > k) {
                freq[s.charAt(l) - 'A']--;
                l++;
            }
            maxLength = Math.max(maxLength, r - l + 1);
            r++;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(characterReplacement("ABAB", 2));
        System.out.println(characterReplacement("AABABBA", 1));
    }
}
