public class LargestOddNumber {

    public static String largestOddNumber(String num) {

        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);

            if (c == '1' || c == '3' || c == '5' || c == '7' || c == '9') {
                return num.substring(0, i + 1);
            }
        }

        return "";
    }

    public static void main(String[] args) {
        String num = "52";
        System.out.println(largestOddNumber(num));
    }
}
