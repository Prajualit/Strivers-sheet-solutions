public class MaxDepth {

    public static int maxDepth(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int maxDepth = 0;
        int currentDepth = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                currentDepth++;
            }
            if (s.charAt(i) == ')') {
                currentDepth--;
            }
            maxDepth = Math.max(maxDepth, currentDepth);
        }
        return maxDepth;
    }

    public static void main(String[] args) {
        String s = "(1+(2*3)+((8)/4))+1";
        System.out.println(maxDepth(s));
    }
}
